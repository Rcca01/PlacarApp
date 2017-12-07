package com.example.logonrm.placarapp;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {

    private String timeCasa;
    private String timeVisitante;

    private TextView tvTimeCasa;
    private TextView tvTimeVisitante;

    private TextView tvPlacarCasa;
    private TextView tvPlacarVisitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvTimeCasa = (TextView) findViewById(R.id.tvTimeCasa);
        tvTimeVisitante = (TextView) findViewById(R.id.tvTimeVisitante);

        tvPlacarCasa = (TextView) findViewById(R.id.tvPlacarCasa);
        tvPlacarVisitante = (TextView) findViewById(R.id.tvPlacarVisitante);

        if (getIntent() != null){
            timeCasa = getIntent().getStringExtra("TimeCasa");
            timeVisitante = getIntent().getStringExtra("TimeVisitante");

            tvTimeCasa.setText(timeCasa);
            tvTimeVisitante.setText(timeVisitante);
        }

        if(savedInstanceState != null){
            tvPlacarCasa.setText(String.valueOf(savedInstanceState.getInt("GOLCASA")));
            tvPlacarVisitante.setText(String.valueOf(savedInstanceState.getInt("GOLVISITANTE")));
        }
    }

    /**
     * Função para retornar o valor atual do placar Casa ou Visitante
     * @param referencia
     * @return Int
     */
    private int getPlacarAtual(TextView referencia){
        String textValor = referencia.getText().toString();
        return Integer.parseInt(textValor);
    }

    /**
     * Função para incrementar o placar do time da casa
     * @param v parametro da view (this)
     */
    public void golCasa(View v){
        int valorPlacarCasa = this.getPlacarAtual(tvPlacarCasa);
        valorPlacarCasa++;
        tvPlacarCasa.setText(String.valueOf(valorPlacarCasa));
    }

    /**
     * Função para incrementar o placar do time visitante
     * @param v parametro da view (this)
     */
    public void golVisitante(View v){
        int valorPlacarVisitante = this.getPlacarAtual(tvPlacarVisitante);
        valorPlacarVisitante++;
        tvPlacarVisitante.setText(String.valueOf(valorPlacarVisitante));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("GOLCASA",this.getPlacarAtual(tvPlacarCasa));
        outState.putInt("GOLVISITANTE",this.getPlacarAtual(tvPlacarVisitante));
    }
}
