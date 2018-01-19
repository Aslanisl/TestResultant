package ru.mail.aslanisl.testresultant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 19.01.2018.
 */

public class ValueModel {
    private String name;
    private PriceModel price;
    @SerializedName("percent_change")
    @Expose
    private float percentChange;
    private int volume;
    private String symbol;

    public String getName() {
        return name;
    }

    public PriceModel getPrice() {
        return price;
    }

    public float getPercentChange() {
        return percentChange;
    }

    public int getVolume() {
        return volume;
    }

    public String getSymbol() {
        return symbol;
    }
}
