package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.model.ReceiptDocument;
import com.epam.msfrolov.freewms.model.TableLine;
import com.epam.msfrolov.freewms.service.DocumentService;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDocumentShowAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(ReceiptDocumentShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 9;

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList;
        String pageNumberStr = req.getParameter("page_number");
        String productIdStr = req.getParameter("product");
        String countStr = req.getParameter("count");
        String sort = req.getParameter("sort");
        String idLineDelStr;
        boolean delete = null != (idLineDelStr = req.getParameter("delete"));
        boolean add = null != req.getParameter("add");
        log.debug("parameter pageNumberStr {}", pageNumberStr);
        log.debug("parameter delete {}", delete);
        log.debug("parameter add {}", add);
        int pageNumber;
        if (!Validator.isValid(pageNumberStr, Validator.DIGITS_MIN1_MAX9)) pageNumber = 1;
        else {
            pageNumber = Integer.parseInt(pageNumberStr);
            if (pageNumber < 1) pageNumber = DEFAULT_PAGE_NUMBER;
        }
        Object receiptDocumentObj = req.getSession(false).getAttribute("document");
        log.debug("attribute document {}", receiptDocumentObj);
        ReceiptDocument receiptDocument;
        if (receiptDocumentObj != null) receiptDocument = (ReceiptDocument) receiptDocumentObj;
        else receiptDocument = new ReceiptDocument();
        try (DocumentService documentService = new DocumentService()) {
            productList = documentService.findAllProduct();
            if (delete) deleteDocumentLine(idLineDelStr, receiptDocument);
            else if (add) addDocumentLine(productIdStr, countStr, receiptDocument, documentService);
            sortDocumentLine(sort, receiptDocument, req);
        }
        List<TableLine> tableLineList;
        int size = receiptDocument.size();
        int fromIndex = (pageNumber - 1) * DEFAULT_PAGE_SIZE + 1;
        int toIndex = fromIndex + DEFAULT_PAGE_SIZE;
        if (fromIndex > size)
            tableLineList = new ArrayList<>();
        else {
            if (toIndex > size)
                toIndex = size;
            log.debug("fromIndex {}", fromIndex);
            log.debug("toIndex   {}", toIndex);
            tableLineList = receiptDocument.getSubList(fromIndex - 1, toIndex);
        }
        double i = (size / DEFAULT_PAGE_SIZE + 1);
        log.debug("size {}", size);
        log.debug("size / DEFAULT_PAGE_SIZE + 1 = {}", i);
        log.debug("pageNumber {}", pageNumber);
        if (add || delete) if (i > pageNumber) pageNumber++;
        else if (pageNumber > i) pageNumber--;
        req.setAttribute("current_document_list", tableLineList);
        req.setAttribute("product_list", productList);
        req.setAttribute("page_number", pageNumber);
        req.setAttribute("start", fromIndex);
        req.getSession(false).setAttribute("document", receiptDocument);
        return new ActionResult("receipt_document");
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

    private void addDocumentLine(String productIdStr, String countStr, ReceiptDocument receiptDocument, DocumentService documentService) {
        int productId;
        int count;
        if (!Validator.isValid(productIdStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException("add: product id parameter is incorrect (not a number)");
        else if ((productId = Integer.parseInt(productIdStr)) < 1)
            throw new ActionException("add: product id parameter is incorrect (negative number)");
        if (!Validator.isValid(countStr, Validator.DIGITS_MIN1_MAX9))
            throw new ActionException("add: count parameter is incorrect (not a number)");
        else if ((count = Integer.parseInt(countStr)) < 1)
            throw new ActionException("add: count parameter is incorrect (negative number)");
        Product product = documentService.findProduct(productId);
        if (product == null) throw new ActionException("add: product with the id is deleted or does not exist");
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
    }

    private void sortDocumentLine(String sort, ReceiptDocument receiptDocument, HttpServletRequest req) {
        if ("count_asce".equals(sort)) {
            receiptDocument.sort(TableLine.COMPARE_COUNT);
            req.setAttribute("sort", "count_asce");
        } else if ("product_asce".equals(sort)) {
            receiptDocument.sort(TableLine.COMPARE_PRODUCT_NAME);
            req.setAttribute("sort", "product_asce");
        } else if ("count_desc".equals(sort)) {
            receiptDocument.sort(TableLine.COMPARE_COUNT_DESC);
            req.setAttribute("sort", "count_desc");
        } else if ("product_desc".equals(sort)) {
            receiptDocument.sort(TableLine.COMPARE_PRODUCT_NAME_DESC);
            req.setAttribute("sort", "product_desc");
        }
    }
}
