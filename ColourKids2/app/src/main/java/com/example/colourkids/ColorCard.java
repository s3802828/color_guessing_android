package com.example.colourkids;

import android.os.Parcel;
import android.os.Parcelable;

public class ColorCard{
    private final Integer colorImage;
    private final String colorName;

    public ColorCard(Integer colorImage, String colorName){
        this.colorImage = colorImage;
        this.colorName = colorName;
    }

//    protected ColorCard(Parcel in) {
//        if (in.readByte() == 0) {
//            colorImage = null;
//        } else {
//            colorImage = in.readInt();
//        }
//        colorName = in.readString();
//    }
//
//    public static final Creator<ColorCard> CREATOR = new Creator<ColorCard>() {
//        @Override
//        public ColorCard createFromParcel(Parcel in) {
//            return new ColorCard(in);
//        }
//
//        @Override
//        public ColorCard[] newArray(int size) {
//            return new ColorCard[size];
//        }
//    };

    public Integer getColorImage() {
        return colorImage;
    }

    public String getColorName() {
        return colorName;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        if (colorImage == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(colorImage);
//        }
//        dest.writeString(colorName);
//    }
}
