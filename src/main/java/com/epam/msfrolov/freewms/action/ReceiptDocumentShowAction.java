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

public class ReceiptDocumentShowAction implements Action {
    private static final String COUNT_DESC = "count(desc)";
    private static final String COUNT_ASCE = "count(asce)";
    private static final String PRODUCT_ASCE = "product(asce)";
    private static final String PRODUCT_DESC = "product(desc)";
    private static final Logger log = LoggerFactory.getLogger(ReceiptDocumentShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 9;
    private ActionResult home = new ActionResult("home", true);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList;
        List<Counterpart> senderList;
        List<Warehouse> recipientList;
        String pageNumberStr = req.getParameter("page_number");
        String productIdStr = req.getParameter("product");
        String countStr = req.getParameter("count");
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

        Object receiptDocumentObj;
        try {
            receiptDocumentObj = req.getSession(false).getAttribute("receipt_document");
        } catch (Exception e) {
            receiptDocumentObj = null;
        }
        log.debug("attribute document {}", receiptDocumentObj);
        ReceiptDocument receiptDocument;
        if (receiptDocumentObj != null) receiptDocument = (ReceiptDocument) receiptDocumentObj;
        else receiptDocument = new ReceiptDocument();
        try (DocumentService documentService = new DocumentService()) {
            Counterpart counterpart;
            Warehouse warehouse;
            counterpart = receiptDocument.getSender();
            warehouse = receiptDocument.getRecipient();
            productList = documentService.findAllProduct();
            senderList = documentService.findAllCounterparts();
            recipientList = documentService.findAllWarehouse();
            if (delete) deleteDocumentLine(idLineDelStr, receiptDocument);
            else if (add) addDocumentLine(productIdStr, countStr, receiptDocument, documentService, req);
            counterpart = setSender(EditSender, receiptDocument, documentService, counterpart);
            warehouse = setRecipient(EditRecipient, receiptDocument, documentService, warehouse);
            req.setAttribute("sender", counterpart);
            req.setAttribute("recipient", warehouse);
            sortDocumentLine(receiptDocument, req);
            List<TableLine> tableLineList;
            int size;
            int fromIndex;
            int toIndex;
            size = receiptDocument.size();
            fromIndex = (pageNumber - 1) * DEFAULT_PAGE_SIZE;
            toIndex = fromIndex + DEFAULT_PAGE_SIZE;
            if (fromIndex > size - 1)
                tableLineList = new ArrayList<>();
            else {
                if (toIndex > size)
                    toIndex = size;
                log.debug("fromIndex {}", fromIndex);
                log.debug("toIndex   {}", toIndex);
                int totalPages = (int) Math.ceil((double) receiptDocument.size() / DEFAULT_PAGE_SIZE);
                req.setAttribute("total_pages", totalPages);
                tableLineList = receiptDocument.getSubList(fromIndex, toIndex);
            }
            double i = (size / DEFAULT_PAGE_SIZE + 1);
            log.debug("size {}", size);
            log.debug("size / DEFAULT_PAGE_SIZE + 1 = {}", i);
            log.debug("pageNumber {}", pageNumber);
            if (add || delete) if (i > pageNumber) pageNumber++;
            else if (pageNumber > i) pageNumber--;
            if (Validator.isValid(docDate, Validator.DATE))
                receiptDocument.setDate(LocalDate.parse(docDate, DateTimeFormatter.ISO_LOCAL_DATE));
            log.debug("document date {}", docDate);
            req.setAttribute("current_document_list", tableLineList);
            req.setAttribute("doc_date", docDate);
            req.setAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            req.setAttribute("product_list", productList);
            req.setAttribute("sender_list", senderList);
            req.setAttribute("recipient_list", recipientList);
            req.setAttribute("page_number", pageNumber);
            req.setAttribute("start", fromIndex);
            req.getSession(false).setAttribute("receipt_document", receiptDocument);
            if (save) {
                if (saveDocument(req, receiptDocument, documentService))
                    return new ActionResult("receipt_document?doc_save=true", true);
            } else if (clear) {
                return clearDocument(req);
            }
            if (req.getParameter("doc_save") != null) req.setAttribute("doc_save", "true");
            return new ActionResult("receipt_document");
        }

    }

    private Warehouse setRecipient(String editRecipient, ReceiptDocument receiptDocument, DocumentService documentService, Warehouse warehouse) {
        if (editRecipient != null) {
            try {
                warehouse = documentService.findWarehouseById(Integer.parseInt(editRecipient));
                receiptDocument.setRecipient(warehouse);
            } catch (Exception e) {
                throw new ActionException("failed to find a warehouse for id", e);
            }
        }
        return warehouse;
    }

    private Counterpart setSender(String editSender, ReceiptDocument receiptDocument, DocumentService documentService, Counterpart counterpart) {
        if (editSender != null) {
            try {
                counterpart = documentService.findCounterpartById(Integer.parseInt(editSender));
                receiptDocument.setSender(counterpart);
            } catch (Exception e) {
                throw new ActionException("failed to find a counterpart sender for id", e);
            }
        }
        return counterpart;
    }

    private ActionResult clearDocument(HttpServletRequest req) {
        req.getSession(false).setAttribute("receipt_document", null);
        return home;
    }

    private boolean saveDocument(HttpServletRequest req, ReceiptDocument receiptDocument, DocumentService documentService) {
        boolean b = documentService.insertReceiptDocument(receiptDocument);
        if (!b) req.setAttribute("doc_not_save", "true");
        else {
            req.getSession(false).setAttribute("receipt_document", null);
            return true;
        }
        return false;
    }

    private void deleteDocumentLine(String idLineDelStr, ReceiptDocument receiptDocument) {
        int idLineDel;
        if (!Validator.isValid(idLineDelStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException("delete: product id parameter is incorrect (not a number)");
        else if ((idLineDel = Integer.parseInt(idLineDelStr)) < 0)
            throw new ActionException("delete: product id parameter is incorrect (negative number)");
        TableLine removeLine = receiptDocument.remove(idLineDel);
        log.debug("removeLine: {}", removeLine);
    }

    private void addDocumentLine(String productIdStr, String countStr, ReceiptDocument receiptDocument, DocumentService documentService, HttpServletRequest req) {
        int productId;
        int count;
        if (!Validator.isValid(productIdStr, Validator.DIGITS_MIN1_MAX9))
            return;
        else if ((productId = Integer.parseInt(productIdStr)) < 1)
            return;
        if (!Validator.isValid(countStr, Validator.DIGITS_MIN1_MAX9))
            return;
        else if ((count = Integer.parseInt(countStr)) < 1)
            return;
        Product product = documentService.findProduct(productId);
        if (product == null)
            return;
        final boolean[] addNewLine = {true};
        receiptDocument.forEach(tableLine -> {
            if (tableLine.getProduct().equals(product)) {
                tableLine.setCount(tableLine.getCount() + count);
                addNewLine[0] = false;
            }
        });
        if (addNewLine[0]) {
            TableLine tableLine = new TableLine();
            tableLine.setProduct(product);
            tableLine.setCount(count);
            receiptDocument.add(tableLine);
        }
        req.setAttribute("curProd", product);
    }

    private void sortDocumentLine(ReceiptDocument receiptDocument, HttpServletRequest req) {
        List<String> sort_list = new ArrayList<>();
        sort_list.add(COUNT_ASCE);
        sort_list.add(PRODUCT_ASCE);
        sort_list.add(COUNT_DESC);
        sort_list.add(PRODUCT_DESC);
        req.setAttribute("sort_list", sort_list);
        String sort = req.getParameter("sort_select");
        if (COUNT_ASCE.equals(sort)) {
            receiptDocument.sort(TableLine.COMPARE_COUNT);
            req.setAttribute("sort", COUNT_ASCE);
        } else if (PRODUCT_ASCE.equals(sort)) {
            receiptDocument.sort(TableLine.COMPARE_PRODUCT_NAME);
            req.setAttribute("sort", PRODUCT_ASCE);
        } else if (COUNT_DESC.equals(sort)) {
            receiptDocument.sort(TableLine.COMPARE_COUNT_DESC);
            req.setAttribute("sort", COUNT_DESC);
        } else if (PRODUCT_DESC.equals(sort)) {
            receiptDocument.sort(TableLine.COMPARE_PRODUCT_NAME_DESC);
            req.setAttribute("sort", PRODUCT_DESC);
        }
    }
}
