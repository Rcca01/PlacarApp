package com.example.logonrm.placarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etTimeCasa;
    private EditText etTimeVisitante;
    private EditText edtTempo;
    private boolean pauseJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTimeCasa = (EditText) findViewById(R.id.time1);
        etTimeVisitante = (EditText) findViewById(R.id.time2);
        edtTempo = (EditText) findViewById(R.id.edtTempo);
    }

    public void podePausar(View v){
        pauseJogo = ((CheckBox) v).isChecked();
    }

    public void comecarJogo(View v){
        if (etTimeCasa.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe o time da casa", Toast.LENGTH_SHORT).show();
        }else if (etTimeVisitante.getText().toString().isEmpty()){
            Toast.makeText(this, "Informe o time visitante", Toast.LENGTH_SHORT).show();
        }else if(edtTempo.getText().toString().isEmpty()){
            Toast.makeText(this, "Informe o tempo de jogo", Toast.LENGTH_SHORT).show();
        }else{
            Intent gameActivity = new Intent(this, GameActivity.class);
            gameActivity.putExtra("TimeCasa", etTimeCasa.getText().toString());
            gameActivity.putExtra("TimeVisitante", etTimeVisitante.getText().toString());
            gameActivity.putExtra("TempoJogo", edtTempo.getText().toString());
            gameActivity.putExtra("PodePausar", pauseJogo);
            startActivity(gameActivity);
        }
    }
}
