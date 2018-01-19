package ru.mail.aslanisl.testresultant.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import ru.mail.aslanisl.testresultant.R;

/**
 * Created by Ivan on 19.01.2018.
 */

public class ProgressView extends LinearLayout {

    private ImageView reload;
    private ProgressBar progressBar;

    public ProgressView(Context context) {
        super(context);
        init(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        inflate(context, R.layout.custom_progress_view, this);
        reload = findViewById(R.id.custom_view_reload);
        progressBar = findViewById(R.id.custom_view_progress);
    }

    public void setLoading(boolean enable){
        setEnabled(!enable);
        reload.setVisibility(enable ? GONE : VISIBLE);
        progressBar.setVisibility(enable ? VISIBLE : GONE);
    }
}
