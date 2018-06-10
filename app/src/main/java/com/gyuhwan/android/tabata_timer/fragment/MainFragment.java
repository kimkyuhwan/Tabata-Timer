package com.gyuhwan.android.tabata_timer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyuhwan.android.tabata_timer.R;
import com.gyuhwan.android.tabata_timer.activity.MainActivity;
import com.gyuhwan.android.tabata_timer.dataModel.Routine;
import com.gyuhwan.android.tabata_timer.tool.RoutineViewModel;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;

public class MainFragment extends Fragment {

    @BindView(R.id.spinner_routine)
    NiceSpinner spinnerRoutine;
    @BindView(R.id.activity_list)
    ListView activityList;
    @BindView(R.id.edit_btn)
    TextView editBtn;
    @BindView(R.id.start_btn)
    Button startBtn;
    Unbinder unbinder;
    MainActivity mainActivity;
    ArrayList<String> routine_names;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RoutineViewModel routineViewModel = new RoutineViewModel();
        routine_names = new ArrayList<>();
        ArrayList<Routine> routines = routineViewModel.getAllRoutine();
        for (Routine routine : routines) {
            routine_names.add(routine.getName());
            Log.w("DEBUGYU",routine.getName());
        }
        if(getActivity() instanceof MainActivity){
            mainActivity=(MainActivity)getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        if(!routine_names.isEmpty()) {
            spinnerRoutine.attachDataSource(routine_names);
            Log.w("DEBUGYU","SIZE "+routine_names.size());

        }


        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.edit_btn, R.id.start_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_btn:
                Toast.makeText(getActivity(),"Edit Button",Toast.LENGTH_LONG).show();
                break;
            case R.id.start_btn:
                if(mainActivity!=null){
                    mainActivity.onFragmentChange("exercise");
                }
                break;
        }
    }
}
