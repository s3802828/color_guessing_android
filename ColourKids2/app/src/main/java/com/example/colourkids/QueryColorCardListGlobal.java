package com.example.colourkids;

import android.graphics.Color;

import java.util.ArrayList;

public class QueryColorCardListGlobal {
    private static ArrayList<ColorCard> colorCardArrayList = new ArrayList<>();
    private static ArrayList<ColorCard> totalColorChoices = new ArrayList<>();

    public static ArrayList<ColorCard> getColorCardArrayList() {
        return colorCardArrayList;
    }

    public static ArrayList<ColorCard> getTotalColorChoices() {
        return totalColorChoices;
    }
}
