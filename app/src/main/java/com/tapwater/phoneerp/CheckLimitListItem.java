package com.tapwater.phoneerp;

/**
 * Created by Tapwater on 16-12-22.
 */

public class CheckLimitListItem {
    private String checkLimitTextString,checkLimitEditString;
    private boolean isFocus = false;

    public CheckLimitListItem() {
    }

    public CheckLimitListItem(String checkLimitEditString, String checkLimitTextString, boolean isFocus) {
        this.checkLimitEditString = checkLimitEditString;
        this.checkLimitTextString = checkLimitTextString;
        this.isFocus = isFocus;
    }

    public String getCheckLimitEditString() {
        return checkLimitEditString;
    }

    public void setCheckLimitEditString(String checkLimitEditString) {
        this.checkLimitEditString = checkLimitEditString;
    }

    public String getCheckLimitTextString() {
        return checkLimitTextString;
    }

    public void setCheckLimitTextString(String checkLimitTextString) {
        this.checkLimitTextString = checkLimitTextString;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }
}
