package com.project.core.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageHelper {

    private static MessageSource messageSource;

    @Autowired
    public MessageHelper(MessageSource messageSource) {
        MessageHelper.messageSource = messageSource;
    }

    public static String getMessage(String name, Object... parameters) {
        try {
            return messageSource.getMessage(name, parameters, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return name;
        }
    }
}
