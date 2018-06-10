package com.gyuhwan.android.tabata_timer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyuhwan.android.tabata_timer.R;
import com.gyuhwan.android.tabata_timer.dataModel.Activity;
import com.gyuhwan.android.tabata_timer.dataModel.Routine;
import com.gyuhwan.android.tabata_timer.tool.RoutineViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TextView titleBar;
    @BindView(R.id.title_edit)
    EditText titleEdit;
    @BindView(R.id.activity_list)
    ListView activityList;
    @BindView(R.id.back_btn)
    ImageButton backBtn;
    @BindView(R.id.activity_add_btn)
    ImageButton activityAddBtn;
    @BindView(R.id.register_btn)
    Button registerBtn;

    String name;
    ArrayList<Activity> activities;
    RoutineViewModel viewModel=new RoutineViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        name="";
        activities=new ArrayList<>();

    }

    @OnClick({R.id.back_btn, R.id.activity_add_btn, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.activity_add_btn:
                break;
            case R.id.register_btn:
                correctCheck();
                break;
        }
    }

    void addActivity(String name,long playtime,long resttime){
        Activity activity=new Activity();
        activity.setName(name);
        activity.setPlaytime(playtime);
        activity.setResttime(resttime);
        activities.add(activity);
    }

    void correctCheck(){
        name=titleEdit.getText().toString();
        boolean isIdValid=!name.isEmpty();
        boolean isActivitySizeValid = !activities.isEmpty();
        boolean isUnique = !viewModel.isExistFromName(name);
        if(isIdValid && isActivitySizeValid && isUnique){
            addActivityIntoRealm();
        }
        else if(!isIdValid){
            Toast.makeText(getApplicationContext(),"이름을 설정해주세요.",Toast.LENGTH_LONG).show();
        }
        else if(!isActivitySizeValid){
            Toast.makeText(getApplicationContext(),"목록이 비어있습니다.",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"이미 같은 이름의 루틴이 존재합니다.",Toast.LENGTH_LONG).show();
        }
    }

    void addActivityIntoRealm(){
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Routine routine=new Routine();
                routine.setName(name);
                routine.setActivities(new RealmList<Activity>(activities.toArray(new Activity[activities.size()])));
                viewModel.createRoutine(routine);
                Toast.makeText(getApplicationContext(),"등록되었습니다.",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
