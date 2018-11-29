package com.alisakralaraby.feeconnectdemo2;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;

import com.alisakralaraby.feeconnectdemo2.adapters.AlarmsAdapter;

import java.util.ArrayList;

public class AddAlarmActivity extends AppCompatActivity {

    private boolean isAlarmBtnCreated;
    private Button btnAddNewAlarm;
    private ListView alarmListView;
    private ArrayList<String> alarmNames;
    private Dialog dialog;
    private int numberOfAlarms = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        btnAddNewAlarm = (Button)findViewById(R.id.btnAddNewAlarm);
        alarmListView = (ListView)findViewById(R.id.alarmsListView);
        dialog = new Dialog(AddAlarmActivity.this);

        alarmNames = new ArrayList<>();

        btnAddNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ++numberOfAlarms;

                String alarmName = "alarm " + String.valueOf(numberOfAlarms);
                alarmNames.add(alarmName);

                dialog.setContentView(R.layout.alarm_layout);
                dialog.setTitle("Set Alarm");
                TimePicker timePicker = (TimePicker)dialog.findViewById(R.id.timePicker);
                Button btnStartAlarm = (Button)dialog.findViewById(R.id.btnAlarmStart);
                dialog.show();

                btnStartAlarm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alarmListView.setAdapter(new AlarmsAdapter(getApplicationContext(), alarmNames));
                        dialog.dismiss();
                        isAlarmBtnCreated = true;
                    }
                });
            }
        });
    }
}
