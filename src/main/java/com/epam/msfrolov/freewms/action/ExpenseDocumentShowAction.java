package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.*;
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

public class ExpenseDocumentShowAction implements Action {
    public static final String COUNT_DESC = "count(desc)";
    public static final String COUNT_ASCE = "count(asce)";
    public static final String PRODUCT_ASCE = "product(asce)";
    public static final String PRODUCT_DESC = "product(desc)";
    private static final Logger log = LoggerFactory.getLogger(ExpenseDocumentShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 9;
    private ActionResult home = new ActionResult("home", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList;
        List<Warehouse> senderList;
        List<Counterpart> recipientList;
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
        Object expenseDocumentObj = null;
        try {
            expenseDocumentObj = req.getSession(false).getAttribute("expense_document");
        } catch (Exception e) {
            //this exception does not have to handle
        }
        log.debug("attribute document {}", expenseDocumentObj);
        ExpenseDocument expenseDocument;
        if (expenseDocumentObj != null) expenseDocument = (ExpenseDocument) expenseDocumentObj;
        else expenseDocument = new ExpenseDocument();
        try (DocumentService documentService = new DocumentService()) {
            Warehouse warehouse;
            Counterpart counterpart;
            warehouse = expenseDocument.getSender();
            counterpart = expenseDocument.getRecipient();
            productList = documentService.findAllProduct();
            senderList = documentService.findAllWarehouse();
            recipientList = documentService.findAllCounterparts();
            if (delete) deleteDocumentLine(idLineDelStr, expenseDocument);
            else if (add) addDocumentLine(productIdStr, countStr, expenseDocument, documentService, req);
            warehouse = setSender(EditSender, expenseDocument, documentService, warehouse);
            counterpart = setRecipient(EditRecipient, expenseDocument, documentService, counterpart);
            req.setAttribute("sender", warehouse);
            req.setAttribute("recipient", counterpart);
            sortDocumentLine(sort, expenseDocument, req);
            List<TableLine> tableLineList;
            int size;
            int fromIndex;
            int toIndex;
            size = expenseDocument.size();
            fromIndex = (pageNumber - 1) * DEFAULT_PAGE_SIZE;
            toIndex = fromIndex + DEFAULT_PAGE_SIZE;
            if (fromIndex > size - 1)
                tableLineList = new ArrayList<>();
            else {
                if (toIndex > size)
                    toIndex = size;
                log.debug(" fromIndex  {}", fromIndex);
                log.debug(" toIndex   {}", toIndex);
                tableLineList = expenseDocument.getSubList(fromIndex, toIndex);
            }
            double i = (size / DEFAULT_PAGE_SIZE + 1);
            log.debug("size {}", size);
            log.debug("size / DEFAULT_PAGE_SIZE + 1 = {}", i);
            log.debug("pageNumber {}", pageNumber);
            if (add || delete) if (i > pageNumber) {
                pageNumber++;
            } else if (pageNumber > i) {
                pageNumber--;
            }
            try {
                expenseDocument.setDate(LocalDate.parse(docDate, DateTimeFormatter.ISO_LOCAL_DATE));
            } catch (Exception e) {
                //this exception does not have to handle
            }
            String format = expenseDocument.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            log.debug("document date {}", format);
            req.setAttribute("current_document_list", tableLineList);
            req.setAttribute("doc_date", format);
            req.setAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            req.setAttribute("product_list", productList);
            req.setAttribute("sender_list", senderList);
            req.setAttribute("recipient_list", recipientList);
            req.setAttribute("page_number", pageNumber);
            req.setAttribute("start", fromIndex);
            req.getSession(false).setAttribute("expense_document", expenseDocument);
            if (save) {
                if (saveDocument(req, expenseDocument, documentService))
                    return new ActionResult("expense_document?doc_save=true", true);
            } else if (clear) {
                return clearDocument(req);
            }
            if (req.getParameter("doc_save") != null) req.setAttribute("doc_save", "true");
            return new ActionResult("expense_document");
        }
    }

    private Counterpart setRecipient(String editRecipient, ExpenseDocument receiptDocument, DocumentService documentService, Counterpart counterpart) {
        if (editRecipient != null) {
            try {
                counterpart = documentService.findCounterpartById(Integer.parseInt(editRecipient));
                receiptDocument.setRecipient(counterpart);
            } catch (Exception e) {
                //this exception does not have to handle
            }
        }
        return counterpart;
    }

    private Warehouse setSender(String editSender, ExpenseDocument expenseDocument, DocumentService documentService, Warehouse warehouse) {
        if (editSender != null) {
            try {
                warehouse = documentService.findWarehouseById(Integer.parseInt(editSender));
                expenseDocument.setSender(warehouse);
            } catch (Exception e) {
                //this exception does not have to handle
            }
        }
        return warehouse;
    }

    private ActionResult clearDocument(HttpServletRequest req) {
        req.getSession(false).setAttribute("expense_document", null);
        return home;
    }

    private boolean saveDocument(HttpServletRequest req, ExpenseDocument expenseDocument, DocumentService documentService) {
        boolean b = documentService.insertExpenseDocument(expenseDocument);
        if (!b) req.setAttribute("doc_not_save", "true");
        else {
            req.getSession(false).setAttribute("expense_document", null);
            return true;
        }
        return false;
    }

    private void deleteDocumentLine(String idLineDelStr, ExpenseDocument expenseDocument) {
        int idLineDel;
        if (!Validator.isValid(idLineDelStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException("delete: product id parameter is incorrect (not a number)");
        else if ((idLineDel = Integer.parseInt(idLineDelStr)) < 0)
            throw new ActionException("delete: product id parameter is incorrect (negative number)");
        TableLine removeLine = expenseDocument.remove(idLineDel);
        log.debug("removeLine: {}", removeLine);
    }

    private void addDocumentLine(String productIdStr, String countStr, ExpenseDocument expenseDocument, DocumentService documentService, HttpServletRequest req) {
        int productId;
        int count;
        if (!Validator.isValid(productIdStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException("  add: product id parameter is incorrect (not a number)");
        else if ((productId = Integer.parseInt(productIdStr)) < 1)
            throw new ActionException("  add: product id parameter is incorrect (negative number)");
        if (!Validator.isValid(countStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException("  add: count parameter is incorrect (not a number)");
        else if ((count = Integer.parseInt(countStr)) < 1)
            throw new ActionException("  add: count parameter is incorrect (negative number)");
        Product product = documentService.findProduct(productId);
        if (product == null) throw new ActionException(" add: product with the id is deleted or does not exist");
        final boolean[] addNewLine = {true};
        expenseDocument.forEach(tableLine -> {
            if (tableLine.getProduct().equals(product)) {
                tableLine.setCount(tableLine.getCount() + count);
                addNewLine[0] = false;
            }
        });
        if (addNewLine[0]) {
            TableLine tableLine = new TableLine();
            tableLine.setCount(count);
            tableLine.setProduct(product);
            log.debug("count {}", count);
            log.debug("product {}", product);
            expenseDocument.add(tableLine);
        }
        req.setAttribute("curProd", product);
    }

    private void sortDocumentLine(String sort, ExpenseDocument expenseDocument, HttpServletRequest req) {
        if (COUNT_DESC.equals(sort)) {
            expenseDocument.sort(TableLine.COMPARE_COUNT_DESC);
            req.setAttribute("sort", COUNT_DESC);
        } else if (PRODUCT_DESC.equals(sort)) {
            expenseDocument.sort(TableLine.COMPARE_PRODUCT_NAME_DESC);
            req.setAttribute("sort", PRODUCT_DESC);
        } else if (PRODUCT_ASCE.equals(sort)) {
            expenseDocument.sort(TableLine.COMPARE_PRODUCT_NAME);
            req.setAttribute("sort", PRODUCT_ASCE);
        } else if (COUNT_ASCE.equals(sort)) {
            expenseDocument.sort(TableLine.COMPARE_COUNT);
            req.setAttribute("sort", COUNT_ASCE);
        }
    }
}
