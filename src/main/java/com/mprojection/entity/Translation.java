package com.mprojection.entity;

import java.util.Arrays;
import java.util.List;

public final class Translation {

    private String en;
    private String ru;

    /**
     * Creates an empty translation object.
     */
    public Translation() {

    }

    /**
     * Creates a new translation.
     *
     * @param en English translation
     * @param ru Russian translation
     */
    public Translation(String en, String ru) {
        this.en = en;
        this.ru = ru;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    /**
     * Gets translation value depending on current {@code locale}.
     *
     * @param locale current locale
     * @return translation value depending on current {@code locale}
     */
    public String value(String locale) {
        if (locale == null) {
            return en;
        }
        return locale.equals("ru") ? ru : en;
    }

    /**
     * Gets all values of the translation.
     *
     * @return all values of the translation
     */
    public List<String> values() {
        return Arrays.asList(en, ru);
    }

    /**
     * Clones all values of the {@code location}.
     *
     * @param translation translation to clone
     */
    public void clone(Translation translation) {
        en = translation.en;
        ru = translation.ru;
    }

}
