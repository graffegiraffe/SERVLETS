package by.rublevskaya.model.listener;

import by.rublevskaya.model.log.CustomLogger;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class UserLoginListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("username".equals(event.getName())) {
            String username = (String) event.getValue();
            CustomLogger.info("User logged in: " + username);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("username".equals(event.getName())) {
            String username = (String) event.getValue();
            CustomLogger.info("User logged out: " + username);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if ("username".equals(event.getName())) {
            String oldUsername = (String) event.getValue();
            CustomLogger.info("User replaced: old username - " + oldUsername);
        }
    }
}