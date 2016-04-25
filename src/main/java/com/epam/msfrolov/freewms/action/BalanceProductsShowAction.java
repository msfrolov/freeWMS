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
import java.util.List;

public class BalanceProductsShowAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(BalanceProductsShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private ActionResult productsCatalog = new ActionResult("products_catalog");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer pageNumber;
        LocalDate date;
        Warehouse warehouse;
        String pageString = req.getParameter("page_number");
        String dateString = req.getParameter("date");
        String warehouseString = req.getParameter("warehouse");
        pageNumber = checkPageNumber(pageString);
        log.debug("current page number {}", pageNumber);
        date = checkDate(dateString);
        log.debug("document date {}", date);
        List<TableLine> tableLineList;
        try (OrderService orderService = new OrderService()) {
            Integer warehouseId = checkWarehouseId(warehouseString);
            warehouse = orderService.findWarehouseById(warehouseId);
            if (warehouse == null) throw new ActionException("failed to find the warehouse");
            tableLineList = orderService.calculateProdBalance(pageNumber, DEFAULT_PAGE_SIZE, warehouse, date);
        }
        req.setAttribute("balance_list", tableLineList);
        req.setAttribute("page_number", pageNumber);
        req.setAttribute("page_size", DEFAULT_PAGE_SIZE);
        req.setAttribute("date", date);
        req.setAttribute("warehouse", warehouse);
        return productsCatalog;
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
                throw new ActionException("incorrect warehouse id (negative number)");
            }
        } else {
            throw new ActionException("incorrect warehouse id (non-numeric characters)");
        }
        return idInt;
    }
}
