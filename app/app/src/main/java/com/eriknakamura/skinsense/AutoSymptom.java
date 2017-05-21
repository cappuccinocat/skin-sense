package com.eriknakamura.skinsense;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class AutoSymptom extends AppCompatActivity {
    CheckBox autoy;
    CheckBox auton;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_symptom);
        autoy = (CheckBox) findViewById(R.id.autoy);
        auton = (CheckBox) findViewById(R.id.auton);
        handler = new Handler();
    }

    public void onCheckboxClicked(View view){
        handler.postDelayed(changeSymptom, 400);
    }

    private Runnable changeSymptom = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);
            intent.putExtra("autoy", autoy.isChecked());
            intent.putExtra("auton", auton.isChecked());
            startActivity(intent);
        }
    };
}
