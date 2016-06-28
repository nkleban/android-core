package net.intellectsoft.sample;

import android.os.Bundle;
import android.view.View;

import net.intellectsoft.core.ui.BaseAppCompatActivity;
import net.intellectsoft.sample.mvp.MvpLoadActivity;

import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.btn_mvp_load)
    public void openMvpLoadActivity(View v) {
        MvpLoadActivity.launch(this);
    }

}
