package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.model.Product;
import com.epam.msfrolov.freewms.service.DocumentService;
import com.epam.msfrolov.freewms.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class DocumentJournalShowAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(DocumentJournalShowAction.class);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private ActionResult documentJournal = new ActionResult("document_journal");

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer pageNumber;
        String pageString = req.getParameter("page_number");
        if (Validator.isValid(pageString, Validator.DIGITS_MIN1_MAX9)) {
            pageNumber = Integer.parseInt(pageString);
            if (pageNumber < 1) {
                log.debug("set the default page ");
                pageNumber = DEFAULT_PAGE_NUMBER;
            }
        } else {
            log.debug("set the default page");
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        log.debug("current page number {}", pageNumber);
        try (DocumentService service = new DocumentService()) {
            List<Map<String, String>> documentsList = service.documentsList(pageNumber, DEFAULT_PAGE_SIZE);
            int elemNumber = service.documentsListSize();
            int totalPages = (int) Math.ceil((double) elemNumber / DEFAULT_PAGE_SIZE);
            req.setAttribute("total_pages", totalPages);
            req.setAttribute("document_list", documentsList);
        }
        req.setAttribute("page_number", pageNumber);
        return documentJournal;
    }
}
