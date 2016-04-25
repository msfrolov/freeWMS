package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.MoveDocument;
import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.model.TableLine;
import com.epam.msfrolov.freewms.model.Warehouse;
import com.epam.msfrolov.freewms.service.DocumentService;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MoveDocumentShowAction implements Action {
    public static final String COUNT_DESC = "count(desc)";
    public static final String COUNT_ASCE = "count(asce)";
    public static final String PRODUCT_ASCE = "product(asce)";
    public static final String PRODUCT_DESC = "product(desc)";
    private static final Logger log = LoggerFactory.getLogger(MoveDocumentShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 9;
    private ActionResult home = new ActionResult("home", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList;
        List<Warehouse> senderList;
        List<Warehouse> recipientList;
        String pageNumberStr = req.getParameter("page_number");
        String productIdStr = req.getParameter("product");
        String countStr = req.getParameter("count");
        String sort = req.getParameter("sort");
        String EditSender = req.getParameter("EditSender");
        String EditRecipient = req.getParameter("EditRecipient");
        String docDate = req.getParameter("doc_date");
        boolean save = "save".equalsIgnoreCase(req.getParameter("save"));
        boolean clear = "clear".equalsIgnoreCase(req.getParameter("clear"));
        String idLineDelStr;
        boolean delete = null != (idLineDelStr = req.getParameter("delete"));
        boolean add = null != req.getParameter("add");
        log.debug("parameter pageNumberStr {}", pageNumberStr);
        log.debug("parameter delete {}", delete);
        log.debug("parameter add {}", add);
        log.debug("parameter docDate {}", docDate);
        log.debug("parameter save {}", save);
        log.debug("parameter clear {}", clear);
        int pageNumber;
        if (!Validator.isValid(pageNumberStr, Validator.DIGITS_MIN1_MAX9)) pageNumber = 1;
        else {
            pageNumber = Integer.parseInt(pageNumberStr);
            if (pageNumber < 1) pageNumber = DEFAULT_PAGE_NUMBER;
        }
        Object moveDocumentObj = null;
        try {
            moveDocumentObj = req.getSession(false).getAttribute("document_move");
        } catch (Exception e) {
            //this exception does not have to handle
        }
        log.debug("attribute document {}", moveDocumentObj);
        MoveDocument moveDocument;
        if (moveDocumentObj != null) moveDocument = (MoveDocument) moveDocumentObj;
        else moveDocument = new MoveDocument();
        try (DocumentService documentService = new DocumentService()) {
            Warehouse warehouse1;
            Warehouse warehouse;
            warehouse1 = moveDocument.getSender();
            warehouse = moveDocument.getRecipient();
            productList = documentService.findAllProduct();
            senderList = documentService.findAllWarehouse();
            recipientList = documentService.findAllWarehouse();
            if (delete) deleteDocumentLine(idLineDelStr, moveDocument);
            else if (add) addDocumentLine(productIdStr, countStr, moveDocument, documentService, req);
            warehouse1 = setSender(EditSender, moveDocument, documentService, warehouse1);
            warehouse = setRecipient(EditRecipient, moveDocument, documentService, warehouse);
            req.setAttribute("sender", warehouse1);
            req.setAttribute("recipient", warehouse);
            sortDocumentLine(sort, moveDocument, req);
            List<TableLine> tableLineList;
            int size;
            int fromIndex;
            int toIndex;
            size = moveDocument.size();
            fromIndex = (pageNumber - 1) * DEFAULT_PAGE_SIZE;
            toIndex = fromIndex + DEFAULT_PAGE_SIZE;
            if (fromIndex > size - 1)
                tableLineList = new ArrayList<>();
            else {
                if (toIndex > size)
                    toIndex = size;
                log.debug("fromIndex  {}", fromIndex);
                log.debug("toIndex   {}", toIndex);
                tableLineList = moveDocument.getSubList(fromIndex, toIndex);
            }
            double i = (size / DEFAULT_PAGE_SIZE + 1);
            log.debug("size {}", size);
            log.debug("size / DEFAULT_PAGE_SIZE + 1 = {}", i);
            log.debug("pageNumber {}", pageNumber);
            if (add || delete) if (i > pageNumber) {
                pageNumber++;
            } else if (pageNumber > i) pageNumber--;
            try {
                moveDocument.setDate(LocalDate.parse(docDate, DateTimeFormatter.ISO_LOCAL_DATE));
            } catch (Exception e) {
                //this exception does not have to handle
            }
            String format = moveDocument.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            log.debug("document date {}", format);
            req.setAttribute("current_document_list", tableLineList);
            req.setAttribute("doc_date", format);
            req.setAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            req.setAttribute("product_list", productList);
            req.setAttribute("sender_list", senderList);
            req.setAttribute("recipient_list", recipientList);
            req.setAttribute("page_number", pageNumber);
            req.setAttribute("start", fromIndex);
            req.getSession(false).setAttribute("document_move", moveDocument);
            if (save) {
                if (saveDocument(req, moveDocument, documentService))
                    return new ActionResult("move_document?doc_save=true", true);
            } else if (clear) {
                return clearDocument(req);
            }
            if (req.getParameter("doc_save") != null) req.setAttribute("doc_save", "true");
            return new ActionResult("move_document");
        }
    }

    private Warehouse setRecipient(String editRecipient, MoveDocument receiptDocument, DocumentService documentService, Warehouse warehouse) {
        if (editRecipient != null) {
            try {
                warehouse = documentService.findWarehouseById(Integer.parseInt(editRecipient));
                receiptDocument.setRecipient(warehouse);
            } catch (Exception e) {
                //this exception does not have to handle
            }
        }
        return warehouse;
    }

    private Warehouse setSender(String editSender, MoveDocument moveDocument, DocumentService documentService, Warehouse warehouse) {
        if (editSender != null) {
            try {
                warehouse = documentService.findWarehouseById(Integer.parseInt(editSender));
                moveDocument.setSender(warehouse);
            } catch (Exception e) {
                //this exception does not have to handle
            }
        }
        return warehouse;
    }

    private ActionResult clearDocument(HttpServletRequest req) {
        req.getSession(false).setAttribute("document_move", null);
        return home;
    }

    private boolean saveDocument(HttpServletRequest req, MoveDocument moveDocument, DocumentService documentService) {
        boolean b = documentService.insertMoveDocument(moveDocument);
        if (!b) req.setAttribute("doc_not_save", "true");
        else {
            req.getSession(false).setAttribute("document_move", null);
            return true;
        }
        return false;
    }

    private void deleteDocumentLine(String idLineDelStr, MoveDocument moveDocument) {
        int idLineDel;
        if (!Validator.isValid(idLineDelStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException("delete: product id parameter is incorrect (not a number)");
        else if ((idLineDel = Integer.parseInt(idLineDelStr)) < 0)
            throw new ActionException("delete: product id parameter is incorrect (negative number)");
        TableLine removeLine = moveDocument.remove(idLineDel);
        log.debug("removeLine: {}", removeLine);
    }

    private void addDocumentLine(String productIdStr, String countStr, MoveDocument moveDocument, DocumentService documentService, HttpServletRequest req) {
        int productId;
        int count;
        if (!Validator.isValid(productIdStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException(" add: product id parameter is incorrect (not a number)");
        else if ((productId = Integer.parseInt(productIdStr)) < 1)
            throw new ActionException(" add: product id parameter is incorrect (negative number)");
        if (!Validator.isValid(countStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException(" add: count parameter is incorrect (not a number)");
        else if ((count = Integer.parseInt(countStr)) < 1)
            throw new ActionException(" add: count parameter is incorrect (negative number)");
        Product product = documentService.findProduct(productId);
        if (product == null) throw new ActionException(" add: product with the id is deleted or does not exist");
        final boolean[] addNewLine = {true};
        moveDocument.forEach(tableLine -> {
            if (tableLine.getProduct().equals(product)) {
                tableLine.setCount(tableLine.getCount() + count);
                addNewLine[0] = false;
            }
        });
        if (addNewLine[0]) {
            TableLine tableLine = new TableLine();
            tableLine.setCount(count);
            tableLine.setProduct(product);
            moveDocument.add(tableLine);
        }
        req.setAttribute("curProd", product);
    }

    private void sortDocumentLine(String sort, MoveDocument moveDocument, HttpServletRequest req) {
        if (COUNT_ASCE.equals(sort)) {
            moveDocument.sort(TableLine.COMPARE_COUNT);
            req.setAttribute("sort", COUNT_ASCE);
        } else if (COUNT_DESC.equals(sort)) {
            moveDocument.sort(TableLine.COMPARE_COUNT_DESC);
            req.setAttribute("sort", COUNT_DESC);
        } else if (PRODUCT_DESC.equals(sort)) {
            moveDocument.sort(TableLine.COMPARE_PRODUCT_NAME_DESC);
            req.setAttribute("sort", PRODUCT_DESC);
        } else if (PRODUCT_ASCE.equals(sort)) {
            moveDocument.sort(TableLine.COMPARE_PRODUCT_NAME);
            req.setAttribute("sort", PRODUCT_ASCE);
        }
    }
}
