package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.TableLine;
import com.epam.msfrolov.freewms.model.Warehouse;
import com.epam.msfrolov.freewms.service.OrderService;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BalanceProductsShowAction implements Action {
    private static final String COUNT_DESC = "count(desc)";
    private static final String COUNT_ASCE = "count(asce)";
    private static final String PRODUCT_ASCE = "product(asce)";
    private static final String PRODUCT_DESC = "product(desc)";
    private static final Logger log = LoggerFactory.getLogger(BalanceProductsShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private ActionResult balance_products = new ActionResult("balance_products");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer pageNumber;
        LocalDate date;
        Warehouse warehouse;
        String pageString = req.getParameter("page_number");
        String dateString = req.getParameter("doc_date");
        String warehouseString = req.getParameter("warehouse_select");
        pageNumber = checkPageNumber(pageString);
        log.debug("current page number {}", pageNumber);
        date = checkDate(dateString);
        log.debug("document date {}", date);
        List<TableLine> tableLineList;
        try (OrderService orderService = new OrderService()) {
            Integer warehouseId = checkWarehouseId(warehouseString);
            warehouse = orderService.findWarehouseById(warehouseId);
            if (warehouse == null) throw new ActionException("failed to find the warehouse");
            tableLineList = orderService.calculateProdBalance(pageNumber, DEFAULT_PAGE_SIZE, warehouse, date, req);
            fillWarehouseList(orderService, req);
        }


        sortDocumentLine(tableLineList, req);
        req.setAttribute("balance_list", tableLineList);
        req.setAttribute("page_number", pageNumber);
        req.setAttribute("page_size", DEFAULT_PAGE_SIZE);
        req.setAttribute("doc_date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        req.setAttribute("warehouse_select", warehouse);
        return balance_products;
    }

    private void fillWarehouseList(OrderService orderService, HttpServletRequest req) {
        List<Warehouse> warehouses = orderService.findAllWarehouse();
        req.setAttribute("warehouse_list", warehouses);

    }

    private LocalDate checkDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    private Integer checkPageNumber(String pageString) {
        Integer pageNumber;
        if (Validator.isValid(pageString, Validator.DIGITS_MIN1_MAX9)) {
            pageNumber = Integer.parseInt(pageString);
            if (pageNumber < 1) {
                log.debug(" set the default page");
                pageNumber = DEFAULT_PAGE_NUMBER;
            }
        } else {
            log.debug("set the default page ");
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        return pageNumber;
    }

    private Integer checkWarehouseId(String id) {
        Integer idInt;
        if (Validator.isValid(id, Validator.DIGITS_MIN1_MAX9)) {
            idInt = Integer.parseInt(id);
            if (idInt < 1) {
                log.debug("incorrect warehouse id (negative number)");
                idInt = 1;
            }
        } else {
            log.debug("incorrect warehouse id (non-numeric characters)");
            idInt = 1;
        }
        return idInt;
    }

    private void sortDocumentLine(List<TableLine> tableLines, HttpServletRequest req) {
        String sort = req.getParameter("sort_select");
        List<String> sort_list = new ArrayList<>();
        sort_list.add(COUNT_ASCE);
        sort_list.add(COUNT_DESC);
        sort_list.add(PRODUCT_ASCE);
        sort_list.add(PRODUCT_DESC);
        req.setAttribute("sort_list", sort_list);
        if (COUNT_ASCE.equals(sort)) {
            tableLines.sort(TableLine.COMPARE_COUNT);
            req.setAttribute("sort_select", COUNT_ASCE);
        } else if (PRODUCT_ASCE.equals(sort)) {
            tableLines.sort(TableLine.COMPARE_PRODUCT_NAME);
            req.setAttribute("sort_select", PRODUCT_ASCE);
        } else if (COUNT_DESC.equals(sort)) {
            tableLines.sort(TableLine.COMPARE_COUNT_DESC);
            req.setAttribute("sort_select", COUNT_DESC);
        } else if (PRODUCT_DESC.equals(sort)) {
            tableLines.sort(TableLine.COMPARE_PRODUCT_NAME_DESC);
            req.setAttribute("sort_select", PRODUCT_DESC);
        }
    }
}
