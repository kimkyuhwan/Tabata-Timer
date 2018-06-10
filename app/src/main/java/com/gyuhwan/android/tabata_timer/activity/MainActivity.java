package com.gyuhwan.android.tabata_timer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.gyuhwan.android.tabata_timer.R;
import com.gyuhwan.android.tabata_timer.fragment.ExerciseFragment;
import com.gyuhwan.android.tabata_timer.fragment.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    MainFragment mainFragment;
    ExerciseFragment exerciseFragment;
    @BindView(R.id.add_btn)
    ImageButton addBtn;
    @BindView(R.id.back_btn)
    ImageButton backBtn;
    @BindView(R.id.setting_btn)
    ImageButton settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainFragment = new MainFragment();
        exerciseFragment = new ExerciseFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainFragment).commit();
        }
    }

    public void onFragmentChange(String name) {
        if (name.equals("main")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mainFragment).commit();
            addBtn.setVisibility(View.VISIBLE);
            backBtn.setVisibility(View.INVISIBLE);
        } else if (name.equals("exercise")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, exerciseFragment).commit();
            addBtn.setVisibility(View.INVISIBLE);
            backBtn.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.add_btn, R.id.back_btn, R.id.setting_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                break;
            case R.id.back_btn:
                onFragmentChange("main");
                break;
            case R.id.setting_btn:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
