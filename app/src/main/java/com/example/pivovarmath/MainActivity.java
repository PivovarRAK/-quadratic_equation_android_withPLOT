package com.example.pivovarmath;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    Button btn_confirm, btn_reset,btn_startActivity;
    TextView tv_varA, tv_varB, tv_varC, tv_new_formula, tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        customClickListener();

    }
    private void setupUI(){
        btn_confirm = findViewById(R.id.btn_confirm);
        tv_varA = findViewById(R.id.et_var_A);
        tv_varB = findViewById(R.id.et_var_B);
        tv_varC = findViewById(R.id.et_var_C);
        tv_new_formula = findViewById(R.id.tv_new_formula);
        tv_result = findViewById(R.id.tv_result);
        btn_reset = findViewById(R.id.btn_reset);
        btn_startActivity = findViewById(R.id.btn_startActivity2);
    }

    private void customClickListener(){
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tv_varA.getText().toString().equals("") && !tv_varB.getText().toString().equals("") && !tv_varC.getText().toString().equals("")){
                tv_new_formula.setText("Ihre Funktionsformel ist:\n" + tv_varA.getText().toString() + "x² +" + tv_varB.getText().toString() + "x +" + tv_varC.getText().toString());
                double[] y_werte = berechnungen.getY(Double.parseDouble(tv_varA.getText().toString()), Double.parseDouble(tv_varB.getText().toString()), Double.parseDouble(tv_varC.getText().toString()));
                if(y_werte == null){
                    tv_result.setText("Keine Nullstellen gefunden.\nBitte mit anderen Zahlen versuchen.");
                }else {
                    tv_result.setText("Die Nullstellen liegen bei: \nx=0, y=" + Math.round(y_werte[0] * 100) / 100. +"\nx=0, y=" + Math.round(y_werte[1] *100) / 100. );
                }
                }else{
                    Toast.makeText(MainActivity.this, "Bitte gültige Angaben machen", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView[] object_Array = {tv_varA, tv_varB, tv_varC, tv_new_formula, tv_result};
               for(int i = 0; i < object_Array.length; i++){
                   object_Array[i].setText("");
               }
            }
        });

        btn_startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tv_varA.getText().toString().equals("") && !tv_varB.getText().toString().equals("") && !tv_varC.getText().toString().equals("")){
                    double[] y_werte = berechnungen.getY(Double.parseDouble(tv_varA.getText().toString()), Double.parseDouble(tv_varB.getText().toString()), Double.parseDouble(tv_varC.getText().toString()));

                Intent intent = new Intent(MainActivity.this, coordinate_system.class);
                intent.putExtra("Variable A", tv_varA.getText().toString());
                intent.putExtra("Variable B", tv_varB.getText().toString());
                intent.putExtra("Variable C", tv_varC.getText().toString());
                    if(y_werte != null){
                        intent.putExtra("ns1", Math.round(y_werte[0] *100)/100.);
                        intent.putExtra("ns2", Math.round(y_werte[1] *100)/100.);
                    }

                startActivity(intent);



            }else{
                    Toast.makeText(MainActivity.this, "Bitte gültige Angaben machen", Toast.LENGTH_SHORT).show();
                }
            
                
            }
        });
    }
}
