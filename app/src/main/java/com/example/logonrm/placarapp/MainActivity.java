package com.example.logonrm.placarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etTimeCasa;
    private EditText etTimeVisitante;
    private EditText edtTempo;
    private boolean pauseJogo;

    private CheckBox ckbPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTimeCasa = (EditText) findViewById(R.id.time1);
        etTimeVisitante = (EditText) findViewById(R.id.time2);
        edtTempo = (EditText) findViewById(R.id.edtTempo);
        ckbPause = (CheckBox) findViewById(R.id.ckbPause);
    }

    public void podePausar(final CheckBox checkBox){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                pauseJogo = isChecked;
            }
        });
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
            podePausar(ckbPause);
            gameActivity.putExtra("PodePausar", pauseJogo);
            startActivity(gameActivity);
        }
    }
}
