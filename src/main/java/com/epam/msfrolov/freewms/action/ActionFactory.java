package com.epam.msfrolov.freewms.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private static final Logger log = LoggerFactory.getLogger(ActionFactory.class);
    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("GET/", new ShowPageAction("signin"));
        actions.put("GET/signin", new ShowPageAction("signin"));
        actions.put("POST/signin", new SignInAction());
        actions.put("GET/signout", new SignOutAction());
        actions.put("GET/home", new ShowPageAction("home"));
        actions.put("GET/products_catalog", new ProductsCatalogShowAction());
        actions.put("GET/product_card", new ProductCardShowAction());
        actions.put("POST/product_card", new ProductCardUpdateAction());
        actions.put("GET/cabinet", new ShowCabinetAction());
    }

    public Action getAction(String actionName) {
        log.debug("getAction: {}", actionName);
        Action action = actions.get(actionName);
        if (action != null)
            log.debug("getAction: {} : {}", action, action.getClass());
        return action;
    }
}
