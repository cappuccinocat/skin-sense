package com.eriknakamura.skinsense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultsActivity extends AppCompatActivity {
    SymptomAnalyzer sa = new SymptomAnalyzer();
    boolean[] l1 = new boolean[8];
    int[] l2 = new int[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        l1[0] = intent.getBooleanExtra("rashy", false);
        l1[1] = intent.getBooleanExtra("dryy", false);
        l1[2] = intent.getBooleanExtra("bumpy", false);
        l1[3] = intent.getBooleanExtra("redy", false);
        l1[4] = intent.getBooleanExtra("stiffy", false);
        l1[5] = intent.getBooleanExtra("itchy", false);
        l1[6] = intent.getBooleanExtra("painy", false);
        l1[7] = intent.getBooleanExtra("autoy", false);

        for(int i = 0; i < l1.length; i++){
            l2[i] = l1[i] ? 1 : 0;
        }

        System.out.println(sa.probability(l2));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }
}
