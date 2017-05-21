package com.eriknakamura.skinsense;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class StiffSymptom extends AppCompatActivity {
    CheckBox stiffy;
    CheckBox stiffn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stiff_symptom);
        stiffy = (CheckBox) findViewById(R.id.stiffy);
        stiffn = (CheckBox) findViewById(R.id.stiffn);
        handler = new Handler();
    }

    public void onCheckboxClicked(View view){
        handler.postDelayed(changeSymptom, 500);
    }

    private Runnable changeSymptom = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), ItchSymptom.class);
            intent.putExtra("stiffy", stiffy.isChecked());
            intent.putExtra("stiffn", stiffn.isChecked());
            startActivity(intent);
        }
    };
}
