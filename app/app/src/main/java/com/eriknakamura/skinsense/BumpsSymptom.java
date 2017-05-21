package com.eriknakamura.skinsense;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class BumpsSymptom extends AppCompatActivity {
    CheckBox bumpsy;
    CheckBox bumpsn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bumps_symptom);
        bumpsy = (CheckBox) findViewById(R.id.bumpsy);
        bumpsn = (CheckBox) findViewById(R.id.bumpsn);
        handler = new Handler();
    }

    public void onCheckboxClicked(View view){
        handler.postDelayed(changeSymptom, 400);
    }

    private Runnable changeSymptom = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), RednessSymptom.class);
            intent.putExtra("bumpsy", bumpsy.isChecked());
            intent.putExtra("bumpsn", bumpsn.isChecked());
            intent.putExtra("a", intent.getStringExtra("a"));
            intent.putExtra("b", intent.getStringExtra("b"));
            intent.putExtra("c", intent.getStringExtra("c"));
            intent.putExtra("d", intent.getStringExtra("d"));
            startActivity(intent);
        }
    };
}
