package com.example.colourkids;

import java.util.ArrayList;

public class ColorCardListGlobal {
    private static ArrayList<ColorCard> colorCardArrayList = new ArrayList<>();

    public static ArrayList<ColorCard> getColorCardArrayList() {
        return colorCardArrayList;
    }
}
