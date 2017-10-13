package com.funnyhacks.pulse;

/**
 * Created by ksandom on 13/10/17.
 */

public class InterfaceHelper {
    private static InterfaceHelper instance = null;

    private int tab = -1;

    public static InterfaceHelper getInstance() {
        if (instance == null) {
            instance = new InterfaceHelper();
        }

        return instance;
    }

    public void setTab(int tabNumber) {
        tab = tabNumber;
    }

    public int getTab() {
        return tab;
    }
}
