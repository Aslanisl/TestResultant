package ru.mail.aslanisl.testresultant.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

import ru.mail.aslanisl.testresultant.model.ValueModel;

/**
 * Created by Ivan on 19.01.2018.
 */

public class MyDiffCallback extends DiffUtil.Callback{

    private List<ValueModel> oldValues;
    private List<ValueModel> newValues;

    public MyDiffCallback(List<ValueModel> newValues, List<ValueModel> oldValues) {
        this.newValues = newValues;
        this.oldValues = oldValues;
    }

    @Override
    public int getOldListSize() {
        return oldValues.size();
    }

    @Override
    public int getNewListSize() {
        return newValues.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldValues.get(oldItemPosition).getName().equals(newValues.get(newItemPosition).getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ValueModel oldValue = oldValues.get(oldItemPosition);
        ValueModel newValue = newValues.get(newItemPosition);
        return oldValue.getName().equals(newValue.getName())
                && oldValue.getVolume() == newValue.getVolume()
                && isFloatSame(oldValue.getPercentChange(), newValue.getPercentChange())
                && oldValue.getSymbol().equals(newValue.getSymbol())
                && isFloatSame(oldValue.getPrice().getAmount(), newValue.getPrice().getAmount())
                && oldValue.getPrice().getCurrency().equals(newValue.getPrice().getCurrency());
    }

    private boolean isFloatSame(float valueOne, float valueTwo){
        return Math.round(valueOne * 100) / 100 == Math.round(valueTwo * 100) / 100;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
