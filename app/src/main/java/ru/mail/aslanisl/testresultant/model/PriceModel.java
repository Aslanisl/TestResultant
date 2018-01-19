package ru.mail.aslanisl.testresultant.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ivan on 19.01.2018.
 */

public class PriceModel implements Parcelable {
    private String currency;
    private float amount;

    protected PriceModel(Parcel in) {
        currency = in.readString();
        amount = in.readFloat();
    }

    public static final Creator<PriceModel> CREATOR = new Creator<PriceModel>() {
        @Override
        public PriceModel createFromParcel(Parcel in) {
            return new PriceModel(in);
        }

        @Override
        public PriceModel[] newArray(int size) {
            return new PriceModel[size];
        }
    };

    public String getCurrency() {
        return currency;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(currency);
        parcel.writeFloat(amount);
    }
}
