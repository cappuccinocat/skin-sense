package com.eriknakamura.skinsense;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class DrynessSymptom extends AppCompatActivity {
    CheckBox dryy;
    CheckBox dryn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryness_symptom);
        dryy = (CheckBox) findViewById(R.id.dryy);
        dryn = (CheckBox) findViewById(R.id.dryn);
        handler = new Handler();
    }

    public void onCheckboxClicked(View view){
        handler.postDelayed(changeSymptom, 500);
    }

    private Runnable changeSymptom = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), BumpsSymptom.class);
            intent.putExtra("dryy", dryy.isChecked());
            intent.putExtra("dryn", dryn.isChecked());
            startActivity(intent);
        }
    };
}
