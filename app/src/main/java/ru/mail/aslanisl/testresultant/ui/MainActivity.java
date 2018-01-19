package ru.mail.aslanisl.testresultant.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.mail.aslanisl.testresultant.R;
import ru.mail.aslanisl.testresultant.api.Api;
import ru.mail.aslanisl.testresultant.model.ValueModel;
import ru.mail.aslanisl.testresultant.model.ValueResponse;

public class MainActivity extends AppCompatActivity implements Callback<ValueResponse>{

    private static final int CALL_INTERVAL_TIME = 15 * 1000;

    private Call<ValueResponse> valueCall;

    private Handler callHandler;
    private Runnable callRunnable;

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    @BindView(R.id.activity_main_progress_view) ProgressView progressView;
    @BindView(R.id.activity_main_recycle) RecyclerView recyclerView;
    private ValueAdapter valueAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        valueAdapter = new ValueAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(valueAdapter);
    }

    @OnClick(R.id.activity_main_progress_view)
    void refreshClick(){
        progressView.setLoading(true);
        makeCall(false);
    }

    private void makeCall(boolean interval){
        if (callHandler == null){
            callHandler = new Handler();
        }
        if (callRunnable == null){
            callRunnable = ()->{
                if (valueCall == null){
                    valueCall = Api.getApiService().getValues();
                    valueCall.enqueue(this);
                } else {
                    valueCall.cancel();
                    valueCall.clone().enqueue(this);
                }
            };
        }
        callHandler.removeCallbacks(callRunnable);
        if (interval){
            callHandler.postDelayed(callRunnable, CALL_INTERVAL_TIME);
        } else {
            callHandler.post(callRunnable);
        }
    }

    private void stopCall(){
        if (callHandler != null && callRunnable != null){
            callHandler.removeCallbacks(callRunnable);
        }
        if (valueCall != null) valueCall.cancel();
    }

    @Override
    public void onResponse(Call<ValueResponse> call, Response<ValueResponse> response) {
        if (response.isSuccessful()){
            ValueResponse valueResponse = response.body();
            if (valueResponse != null && valueResponse.getValueModels() != null){
                valueAdapter.updateList(valueResponse.getValueModels());
                progressView.setLoading(false);
            }
        }
        makeCall(true);
    }

    @Override
    public void onFailure(Call<ValueResponse> call, Throwable t) {
        makeCall(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        makeCall(false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopCall();
    }
}
