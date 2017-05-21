package com.eriknakamura.skinsense;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class RednessSymptom extends AppCompatActivity {
    CheckBox redy;
    CheckBox redn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redness_symptom);
        redy = (CheckBox) findViewById(R.id.rednessy);
        redn = (CheckBox) findViewById(R.id.rednessn);
        handler = new Handler();
    }

    public void onCheckboxClicked(View view){
        handler.postDelayed(changeSymptom, 400);
    }

    private Runnable changeSymptom = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), StiffSymptom.class);
            intent.putExtra("redy", redy.isChecked());
            intent.putExtra("redn", redn.isChecked());
            intent.putExtra("a", intent.getStringExtra("a"));
            intent.putExtra("b", intent.getStringExtra("b"));
            intent.putExtra("c", intent.getStringExtra("c"));
            intent.putExtra("d", intent.getStringExtra("d"));
            startActivity(intent);
        }
    };
}
