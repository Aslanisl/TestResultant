package ru.mail.aslanisl.testresultant.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ivan on 19.01.2018.
 */

public class ValueModel implements Parcelable{
    private String name;
    private PriceModel price;
    @SerializedName("percent_change")
    @Expose
    private float percentChange;
    private int volume;
    private String symbol;

    protected ValueModel(Parcel in) {
        name = in.readString();
        price = in.readParcelable(PriceModel.class.getClassLoader());
        percentChange = in.readFloat();
        volume = in.readInt();
        symbol = in.readString();
    }

    public static final Creator<ValueModel> CREATOR = new Creator<ValueModel>() {
        @Override
        public ValueModel createFromParcel(Parcel in) {
            return new ValueModel(in);
        }

        @Override
        public ValueModel[] newArray(int size) {
            return new ValueModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeParcelable(price, i);
        parcel.writeFloat(percentChange);
        parcel.writeInt(volume);
        parcel.writeString(symbol);
    }
}
