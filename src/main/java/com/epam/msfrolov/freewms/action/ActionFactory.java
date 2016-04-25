package com.epam.msfrolov.freewms.action;

import com.epam.msfrolov.freewms.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ActionFactory {

    private static final Logger log = LoggerFactory.getLogger(ActionFactory.class);
    private Map<String, Action> actions;

    public ActionFactory() {

        actions = new HashMap<>();
        Properties properties = FileManager.getProperties("properties/action.properties");
        Set<String> keySet = properties.stringPropertyNames();
        keySet.forEach(actionUrlPattern -> {
            log.debug("action url pattern = {}", actionUrlPattern);
            String actionClassName = properties.getProperty(actionUrlPattern);
            actionClassName = ActionFactory.class.getPackage().getName() + '.' + actionClassName;
            log.debug("action class name = {}", actionClassName);
            Action action = createAction(actionClassName);
            actions.put(actionUrlPattern, action);
        });
    }

    private Action createAction(String v) {
        try {
            Class clazz = Class.forName(v);
            return (Action) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new ActionException("failed to find the class action", e);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ActionException("failed to get an instance of the action", e);
        }
    }

    public Action getAction(String actionName) {
        log.debug("getAction: {}", actionName);
        Action action = actions.get(actionName);
        if (action != null)
            log.debug("getAction: {} : {}", action, action.getClass());
        return action;
    }
}
