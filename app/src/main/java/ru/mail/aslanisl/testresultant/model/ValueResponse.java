package ru.mail.aslanisl.testresultant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 19.01.2018.
 */

public class ValueResponse {

    @SerializedName("stock")
    @Expose
    private ArrayList<ValueModel> valueModels;

    @SerializedName("as_of")
    @Expose
    private String date;

    public ArrayList<ValueModel> getValueModels() {
        return valueModels;
    }

    public String getDate() {
        return date;
    }
}
