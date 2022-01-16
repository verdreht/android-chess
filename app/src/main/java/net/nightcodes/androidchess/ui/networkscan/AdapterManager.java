package net.nightcodes.androidchess.ui.networkscan;

import net.nightcodes.androidchess.ui.networkscan.view.ItemAdapter;

public class AdapterManager {

    private static ItemAdapter adapter;

    public static ItemAdapter getAdapter() {
        return adapter;
    }

    public static void setAdapter(ItemAdapter adapter) {
        AdapterManager.adapter = adapter;
    }
}