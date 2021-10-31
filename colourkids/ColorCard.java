package com.example.colourkids;

public class ColorCard {
    private final Integer colorImage;
    private final String colorName;

    public ColorCard(Integer colorImage, String colorName){
        this.colorImage = colorImage;
        this.colorName = colorName;
    }

    public Integer getColorImage() {
        return colorImage;
    }

    public String getColorName() {
        return colorName;
    }
}
