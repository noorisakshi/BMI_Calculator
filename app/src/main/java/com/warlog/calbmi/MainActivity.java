package com.warlog.calbmi;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtWt;
    EditText edtHtFt;
    EditText edtHtInc;
    TextView txtRes, txtSuggest,txtRisk,suggestions, risks;
    Button btnChk, btnReset;
    LinearLayout Linear;
    String overWeightSuggest, underWeightSuggest,underRisk,overRisk;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        edtWt = findViewById(R.id.edtWt);
        edtHtFt = findViewById(R.id.edtHtFt);
        edtHtInc = findViewById(R.id.edtHtInc);
        txtRes = findViewById(R.id.txtRes);
        btnChk = findViewById(R.id.btnChk);
        Linear = findViewById(R.id.llM);
        txtSuggest = findViewById(R.id.tvSuggestion);
        btnReset = findViewById(R.id.btnReset);
        txtRisk=findViewById(R.id.tvRisk);
        suggestions = findViewById(R.id.suggestions);
        risks = findViewById(R.id.risks);


        underRisk="If you are underweight, you may be at greater risk of certain health conditions, including malnutrition, osteoporosis, decreased muscle strength, hypothermia and lowered immunity. You are more likely to die at a younger age.";
        overRisk="Obesity increases the risk of several debilitating, and deadly diseases, including diabetes, heart disease, and some cancers. It does this through a variety of pathways, some as straightforward as the mechanical stress of carrying extra pounds and some involving complex changes in hormones and metabolism.";




        overWeightSuggest = "1] Avoid fried and oily foods.\n" +
                "2] Avoid taking fats like butter, ghee because they increase the level of cholesterol in their blood.\n" +
                "3] stop eating between meals.\n" +
                "4] Eating something all the time increases the calorie intake and thus increases weight.";
        underWeightSuggest = "\n" +
                "1] Add healthy calories. You don't need to\n" +
                "   drastically change your diet\n" +
                "2] Snack away.\n" +
                "3] Eat mini-meals.\n" +
                "4] Bulk up.";
        btnReset.setOnClickListener(v -> {
            ResetButton();
        });


        btnChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtWt.getText().toString().isEmpty() || edtHtFt.getText().toString().isEmpty() || edtHtInc.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Some Field is Empty", Toast.LENGTH_SHORT).show();
                    edtWt.requestFocus();
                    edtHtFt.requestFocus();
                    edtHtInc.requestFocus();
                } else {
                    int wt = Integer.parseInt(edtWt.getText().toString());
                    int ft = Integer.parseInt(edtHtFt.getText().toString());
                    int in = Integer.parseInt(edtHtInc.getText().toString());


                    int totalInc = ft * 12 + in;
                    double totalCm = totalInc * 2.53;
                    double totalM = totalCm / 100;
                    double bmi = wt / (totalM * totalM);

                    if (bmi > 25) {
                        txtRes.setText("You're OverWeight." + " your Bmi = " + bmi);
                        Linear.setBackgroundColor(getResources().getColor(R.color.ovW));
                        txtSuggest.setText(overWeightSuggest);
                        txtRisk.setText(overRisk);

                    } else if (bmi < 18) {
                        txtRes.setText("You're Underweight." + " Your Bmi= " + bmi);
                        Linear.setBackgroundColor(getResources().getColor(R.color.unW));
                        txtSuggest.setText(underWeightSuggest);
                        txtRisk.setText(underRisk);

                    } else {
                        txtRes.setText("You're Healthy. " + " Your Bmi= " + bmi);
                        Linear.setBackgroundColor(getResources().getColor(R.color.nrm));
                        suggestions.setText("");
                        risks.setText("");

                    }
                }

            }
        });
    }

    void ResetButton() {
        edtWt.setText("");
        edtHtFt.setText("");
        edtHtInc.setText("");
        txtRes.setText("");
        txtSuggest.setText("");
        txtRisk.setText("");
        Linear.setBackgroundColor(getResources().getColor(R.color.white));


    }
}