package com.mprojection.util;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * A util for translation.
 */
public class Translator {

    private Map<String, ResourceBundle> resourceBundles;
    /**
     * Creates a new translator.
     *
     * @param bundlePath path to bundle of {@code *.properties} files that contains translations.
     * @param locales    a collection of locales
     */
    public Translator(String bundlePath, Iterable<String> locales) {
        resourceBundles = new HashMap<>();
        for (String locale : locales) {
            ResourceBundle bundle = ResourceBundle.getBundle(bundlePath, new Locale(locale));
            resourceBundles.put(locale, bundle);
        }
    }

    /**
     * Gets a translation for the specified {@code key} .
     *
     * @param key key to look for
     * @return found translation
     */
    public String translate(String key) {
        ResourceBundle bundle = resourceBundles.get(getLocale());
        return bundle.getString(key);
    }

    public String translate(String key, String locale) {
        ResourceBundle bundle = resourceBundles.get(locale);
        return bundle.getString(key);
    }

    private String getLocale() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

}
