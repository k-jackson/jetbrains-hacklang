package com.hack;

import com.intellij.lang.Language;

public class HackLanguage extends Language {
    public static final HackLanguage INSTANCE = new HackLanguage();

    public HackLanguage() {
        // TODO: hh mime-type?
        super("Hack", "text/hack");
    }
}
