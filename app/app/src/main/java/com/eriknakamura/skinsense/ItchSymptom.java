package com.eriknakamura.skinsense;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class ItchSymptom extends AppCompatActivity {
    CheckBox itchy;
    CheckBox itchn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itch_symptom);
        itchy = (CheckBox) findViewById(R.id.itchy);
        itchn = (CheckBox) findViewById(R.id.itchn);
        handler = new Handler();
    }

    public void onCheckboxClicked(View view){
        handler.postDelayed(changeSymptom, 400);
    }

    private Runnable changeSymptom = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), PainSymptom.class);
            intent.putExtra("itchy", itchy.isChecked());
            intent.putExtra("itchn", itchn.isChecked());
            startActivity(intent);
        }
    };
}
