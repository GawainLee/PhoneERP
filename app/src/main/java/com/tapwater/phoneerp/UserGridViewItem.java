package com.tapwater.phoneerp;

/**
 * Created by Tapwater on 17-1-21.
 */

public class UserGridViewItem {
    private int iconHead;
    private String iconName;
    private Object itemFragment;

    public UserGridViewItem() {
    }

    public UserGridViewItem(int iconHead, String iconName, Object itemFragment) {

        this.iconHead = iconHead;
        this.iconName = iconName;
        this.itemFragment = itemFragment;
    }

    public int getIconHead() {

        return iconHead;
    }

    public void setIconHead(int iconHead) {
        this.iconHead = iconHead;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public Object getItemFragment() {
        return itemFragment;
    }

    public void setItemFragment(Object itemFragment) {
        this.itemFragment = itemFragment;
    }
}




