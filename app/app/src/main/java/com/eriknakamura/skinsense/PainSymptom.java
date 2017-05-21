package com.eriknakamura.skinsense;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class PainSymptom extends AppCompatActivity {
    CheckBox painy;
    CheckBox painn;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pain_symptom);
        painy = (CheckBox) findViewById(R.id.painy);
        painn = (CheckBox) findViewById(R.id.painn);
        handler = new Handler();
    }

    public void onCheckboxClicked(View view){
        handler.postDelayed(changeSymptom, 500);
    }

    private Runnable changeSymptom = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), AutoSymptom.class);
            intent.putExtra("painy", painy.isChecked());
            intent.putExtra("painn", painn.isChecked());
            startActivity(intent);
        }
    };
}
