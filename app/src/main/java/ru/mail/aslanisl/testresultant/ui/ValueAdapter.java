package ru.mail.aslanisl.testresultant.ui;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mail.aslanisl.testresultant.R;
import ru.mail.aslanisl.testresultant.model.ValueModel;
import ru.mail.aslanisl.testresultant.utils.MyDiffCallback;

/**
 * Created by Ivan on 19.01.2018.
 */

public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.ViewHolder> {

    private List<ValueModel> values = new ArrayList<>();

    public void updateList(List<ValueModel> newValues){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(values, newValues));
        values.clear();
        values.addAll(newValues);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_value, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ValueModel model = values.get(position);
        if (model != null) {
            holder.bindHolder(model);
        }
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name) TextView name;
        @BindView(R.id.item_volume) TextView volume;
        @BindView(R.id.item_amount) TextView amount;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHolder(ValueModel model){
            name.setText(model.getName());
            volume.setText(String.format(Locale.getDefault(), "%d", model.getVolume()));
            amount.setText(String.format(Locale.getDefault(), "%.2f", model.getPrice() != null
                    ? model.getPrice().getAmount() : 0));
        }
    }
}
