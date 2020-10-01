package com.rkleib.android.playground.util;


import java.util.regex.Pattern;

public class Utilities {

    /**
     * Email format validation
     */
    public static final Pattern EMAIL_ADDRESS_PATTERN =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
                    + "\\@"
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
                    + "("
                    + "\\."
                    + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"
                    + ")+");
}
