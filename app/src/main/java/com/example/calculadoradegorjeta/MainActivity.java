package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {
    private EditText editValor;
    private TextView textTotal, textPorcentagem,textGorjeta;
    private SeekBar seekBarGorjeta;
    private double porcetagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editValor = findViewById(R.id.editValor);
        textTotal = findViewById(R.id.textTotal);
        textPorcentagem = findViewById(R.id.textPorcentagem);
        textGorjeta = findViewById(R.id.textGorjeta);
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);

        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcetagem = progress;
                textPorcentagem.setText(Math.round(porcetagem) + "%");
                calcular();
              }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){
        String valorRecuperado = editValor.getText().toString();
        if(valorRecuperado==null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor da conta",
                    Toast.LENGTH_SHORT
            ).show();
        }else{
            //Converte String em Double
            double valorDigitado = Double.parseDouble(valorRecuperado);
            //Calcula a porcemtagem
            double gorjeta = valorDigitado * (porcetagem/100);
            double total = gorjeta + valorDigitado;
            //exibe o valor em R$
            textGorjeta.setText(MessageFormat.format("R$ {0}", gorjeta));
            textTotal.setText(MessageFormat.format("R$ {0}", total));
        }
    }
}