package com.eriknakamura.skinsense;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class RashSymptom extends AppCompatActivity {
    CheckBox rashy;
    CheckBox rashn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rash_symptom);
        rashy = (CheckBox) findViewById(R.id.rashy);
        rashn = (CheckBox) findViewById(R.id.rashn);
        handler = new Handler();
    }

    public void onCheckboxClicked(View view){
        handler.postDelayed(changeSymptom, 400);
    }

    private Runnable changeSymptom = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), DrynessSymptom.class);
            intent.putExtra("rashy", rashy.isChecked());
            intent.putExtra("rashn", rashn.isChecked());
            intent.putExtra("a", intent.getStringExtra("a"));
            intent.putExtra("b", intent.getStringExtra("b"));
            intent.putExtra("c", intent.getStringExtra("c"));
            intent.putExtra("d", intent.getStringExtra("d"));
            System.out.println("rashes" + intent.getStringExtra("a") + intent.getStringExtra("b") + intent.getStringExtra("c") + intent.getStringExtra("d"));
            startActivity(intent);
        }
    };
}
