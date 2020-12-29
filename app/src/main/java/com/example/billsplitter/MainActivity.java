package com.example.billsplitter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button bt;
    EditText edCasal1, edCasal2, edCasal3;
    TextView textViewRedistribuir, textViewTotalGasto, textViewCasal1p, textViewCasal2p, textViewCasal3p;
    double casal1, casal2, casal3, total, casal1p, casal2p, casal3p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edCasal1 = findViewById(R.id.editTextNumberDecimal);
        edCasal2 = findViewById(R.id.editTextNumberDecimal2);
        edCasal3 = findViewById(R.id.editTextNumberDecimal3);
        bt = findViewById(R.id.button);
        textViewRedistribuir = findViewById(R.id.textView5);
        textViewTotalGasto = findViewById(R.id.textView6);
        textViewCasal1p = findViewById(R.id.textView9);
        textViewCasal2p = findViewById(R.id.textView8);
        textViewCasal3p = findViewById(R.id.textView7);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //quanto o casal deu
                casal1 = Double.parseDouble(edCasal1.getText().toString());
                casal2 = Double.parseDouble(edCasal2.getText().toString());
                casal3 = Double.parseDouble(edCasal3.getText().toString());

                total = casal1 + casal2 + casal3;

                textViewTotalGasto.setText("Total gasto: "+total);

                //cálculo % por casal:
                textViewCasal1p.setText("Gezer & Evellyse: "+String.format("%.2f", casal1/total*100) + "%.");
                textViewCasal2p.setText("Leandro & Camilla: "+String.format("%.2f", casal2/total*100) + "%.");
                textViewCasal3p.setText("Jonathas & Amanda: "+String.format("%.2f", casal3/total*100) + "%.");

                //redistribuir:
                //TODO soma/3 é o que cada um deveria ter dado, quem deu menos que isso: passar para quem deu mais "o que deveria ter dado - o que já deu"

                //quanto cada casal deveria ter dado:
                double quantoDeveriaDar = total/3;

                //casal1 foi o que mais deu
                if (casal1 > casal2 && casal1 > casal3){
                    //casal2 e casal3 devem repassar para o casal1;
                    double quantoRepassar2 = quantoDeveriaDar - casal2;
                    double quantoRepassar3 = quantoDeveriaDar - casal3;

                    String string = "Redistribuir: \n Leandro & Camilla -> " + String.format("%.2f", quantoRepassar2) + " para Gezer & Evellyse; \n Jonathas & Amanda -> " + String.format("%.2f", quantoRepassar3) + " para Gezer & Evellyse";

                    textViewRedistribuir.setText(string);
                }
                if (casal2 > casal1 && casal2 > casal3){
                    //casal1 e casal3 devem repassar para o casal2;
                    double quantoRepassar = quantoDeveriaDar - casal1;
                    double quantoRepassar3 = quantoDeveriaDar - casal3;

                    String string = "Redistribuir: \n Gezer & Evellyse -> " + String.format("%.2f", quantoRepassar) + " para Leandro & Camilla e Jonathas & Amanda -> " + String.format("%.2f", quantoRepassar3) + " para Leandro & Camilla";

                    textViewRedistribuir.setText(string);
                }
                if (casal3 > casal2 && casal3 > casal1){
                    //casal1 e casal2 devem repassar para o casal3;
                    double quantoRepassar = quantoDeveriaDar - casal1;
                    double quantoRepassar2 = quantoDeveriaDar - casal2;

                    String string = "Redistribuir: \n Gezer & Evellyse -> " + String.format("%.2f", quantoRepassar) + " para Jonathas & Amanda e Leandro & Camilla -> " + String.format("%.2f", quantoRepassar2) + " Jonathas & Amanda";

                    textViewRedistribuir.setText(string);
                }


            }
        });

    }
}