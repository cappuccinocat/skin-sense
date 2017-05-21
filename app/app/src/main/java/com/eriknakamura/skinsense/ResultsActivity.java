package com.eriknakamura.skinsense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;

public class ResultsActivity extends AppCompatActivity {
    SymptomAnalyzer sa = new SymptomAnalyzer();
    boolean[] l1 = new boolean[8];
        int[] l2 = new int[8];
    String dis = null;
    String descr = null;
    String conf = null;
    TextView disease;
    TextView description;
    TextView confidence;
    String a;
    String b;
    String c;
    String d;

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

        double[] weightSymptoms = sa.probability(l2);
        for(int i = 0; i < weightSymptoms.length; i++){
            weightSymptoms[i] = 0.4*weightSymptoms[i];
        }

        a = Results.e;
        b = Results.f;
        c = Results.g;
        d = Results.h;

        System.out.println("hellohellooo" + a + b + c + d);

        try {
            JSONParse.mainletsgo(a, b, c, d);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONParse.accountWeighted(weightSymptoms);

        dis = JSONParse.dis;
        conf = Double.toString(JSONParse.conf);
        //add calculations for dis and conf here

        if(dis.equals("Ringworm")){
            descr = "Based on our diagnostic software, it appears you have a case of ringworm (dermatophytosis). We recommend that you treat this fungal disease with an antifungal topical treatment such as Lamasil. This treatment method should clear up symptoms within a week. A doctor prescription for antifungals is usually not necessary.\n";
        }else if(dis.equals("Eczema")){
            descr = "A dermatologist will be required to provide a proper treatment. Treatments may include antibiotics, antifungals, or antivirus. Need for a medical professional need be especially expedient if patient has a fever, for a fever likely means the patient is infected.";
        }else if(dis.equals("Lyme Disease")){
            descr = "You likely have Lyme disease. If you think you've been bitten and have signs and symptoms of Lyme disease — particularly if you live in an area where Lyme disease is prevalent — contact your doctor. Treatment for Lyme disease is more effective if begun early. If treatment is not carried out you many experience more acute symptoms\n";
        }else if(dis.equals("Psoriasis")){
            descr = "You likely have Psoriasis, which is a common skin condition that speeds up the life cycle of skin cells. It causes cells to build up rapidly on the surface of the skin. Unfortunately, There is no cure for psoriasis, but you can manage symptoms. We recommend you contact a dermatologist to determine the best method for treatment. Lifestyle measures, such as moisturizing, quitting smoking and managing stress, may help.\n";
        }else if(dis.equals("MRSA")){
            descr = "You likely have Methicillin-resistant Staphylococcus aureus (MRSA), an infection is caused by a type of staph bacteria that's become resistant to many of the antibiotics used to treat ordinary staph infections. MRSA infections can resist the effects of many common antibiotics, so they are more difficult to treat. This can allow the infections to spread and sometimes become life-threatening. We recommend you schedule an appointment with your doctor for proper treatment.\n";
        }else if(dis.equals("Pyoderma")){
            descr = "You most likely have pyoderma gangrenosum. Pyoderma gangrenosum isn't infectious or contagious, but no one knows exactly what causes it. Some studies indicate that it may have a genetic component.";
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        disease = (TextView) findViewById(R.id.disease);
        disease.setText(dis);
        description = (TextView) findViewById(R.id.description);
        description.setText(descr);
        confidence = (TextView) findViewById(R.id.confidence);
        confidence.setText(conf);
    }
}
