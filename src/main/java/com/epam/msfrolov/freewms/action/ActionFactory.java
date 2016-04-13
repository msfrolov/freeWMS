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
        //Properties properties = FileManager.getProperties("properties/action.properties");

        actions.put("GET/", new ShowPageAction("welcome"));
        actions.put("GET/signin", new ShowPageAction("signin"));
        actions.put("POST/signin", new LoginAction());
        actions.put("GET/login", new ShowPageAction("login"));
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/home", new ShowPageAction("home"));
    }

    public Action getAction(String actionName) {
        log.debug("getAction: {}", actionName);
        Action action = actions.get(actionName);
        if (action != null)
            log.debug("getAction: {} : {}", action, action.getClass());
        return action;
    }
}
