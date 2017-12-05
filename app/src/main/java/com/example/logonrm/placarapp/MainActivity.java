package com.example.logonrm.placarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etTimeCasa;
    private EditText etTimeVisitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTimeCasa = (EditText) findViewById(R.id.time1);
        etTimeVisitante = (EditText) findViewById(R.id.time2);
    }

    public void comecarJogo(View v){
        if (etTimeCasa.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe o time da casa", Toast.LENGTH_SHORT).show();
        }else if (etTimeVisitante.getText().toString().isEmpty()){
            Toast.makeText(this, "Informe o time visitante", Toast.LENGTH_SHORT).show();
        }else{
            Intent gameActivity = new Intent(this, GameActivity.class);
            gameActivity.putExtra("TimeCasa", etTimeCasa.getText().toString());
            gameActivity.putExtra("TimeVisitante", etTimeVisitante.getText().toString());
            startActivity(gameActivity);
        }
    }
}
