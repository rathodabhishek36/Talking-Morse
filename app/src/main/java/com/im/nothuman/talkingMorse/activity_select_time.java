package com.im.nothuman.talkingMorse;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;


import java.util.Calendar;
import java.util.HashMap;


public class activity_select_time extends AppCompatActivity
    {
        private DatabaseReference mUserDatabase;
        private FirebaseUser mCurrentUser;
        private TimePicker timePicker1;
        private TimePicker timePicker2;
        private  int hour;
        private int minute;
        private int hour2;
        private EditText title;
        private RadioGroup radioGroup;
        private RadioButton radioButton;
        private int minute2;
         private String t1, t2,title2,radiotext;

        private TextView dateText;
        public void onCreate(Bundle savedInstanceState)
        {
             super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_select_time);

final EditText description=(EditText)findViewById(R.id.desription) ;


            title=(EditText)findViewById(R.id.title);

       title2=title.getText().toString();


title.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        title2=title.getText().toString();
    }

    @Override
    public void afterTextChanged(Editable s) {
        title2=title.getText().toString();
    }
});


            final Calendar c = Calendar.getInstance();
             Button btnNext=(Button)findViewById(R.id.button2);
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
            hour2 = c.get(Calendar.HOUR_OF_DAY);
            minute2 = c.get(Calendar.MINUTE);
            timePicker1= (TimePicker) findViewById(R.id.timePicker);
            timePicker1.setCurrentHour(hour);
            timePicker1.setCurrentMinute(minute);
            timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
            timePicker2.setCurrentHour(hour2);
            timePicker2.setCurrentMinute(minute2);


            hour = timePicker1.getCurrentHour();
            minute = timePicker1.getCurrentMinute();
            hour2 = timePicker2.getCurrentHour();
            minute2 = timePicker2.getCurrentMinute();
            timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                      t1=hourOfDay+":"+minute;
                }
            });
            timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    t2=hourOfDay+":"+minute;
                }
            });
            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
            String current_uid = mCurrentUser.getUid();
            // get selected radio button from radioGroup
            int selectedId = radioGroup.getCheckedRadioButtonId();

            // find the radiobutton by returned id
            radioButton = (RadioButton) findViewById(selectedId);
            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid).child("Activity");
            mUserDatabase.keepSynced(true);
            String device_token = FirebaseInstanceId.getInstance().getToken();
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb= (RadioButton) findViewById(checkedId);
                    radiotext = rb.getText().toString();
                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {





                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("from", t1);
                    userMap.put("to", t2);
                    userMap.put("title",title2 );
                    userMap.put("description",description.getText().toString());
                    userMap.put("status",radiotext);
                    mUserDatabase.child(title2).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {


                                Toast.makeText(activity_select_time.this, "success", Toast.LENGTH_LONG).show();
                                finish();

                            } else {


                                Toast.makeText(activity_select_time.this, "Cannot Sign in. Please check the form and try again.", Toast.LENGTH_LONG).show();

                            }


                        }
                    });

                }
            });
        }
    }