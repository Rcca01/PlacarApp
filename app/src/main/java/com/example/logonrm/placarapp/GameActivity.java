package com.example.logonrm.placarapp;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private String timeCasa;
    private String timeVisitante;
    private int tempoJogo;
    private boolean podePausar;

    private TextView tvTimeCasa;
    private TextView tvTimeVisitante;

    private TextView tvPlacarCasa;
    private TextView tvPlacarVisitante;

    private Button btnGolCasa;
    private Button btnGolVisitante;
    private Button btnPause;
    private Button btnStart;

    private Chronometer ch;
    private long milliseconds;
    private String textCronometro;

    private boolean ativoCronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvTimeCasa = (TextView) findViewById(R.id.tvTimeCasa);
        tvTimeVisitante = (TextView) findViewById(R.id.tvTimeVisitante);

        tvPlacarCasa = (TextView) findViewById(R.id.tvPlacarCasa);
        tvPlacarVisitante = (TextView) findViewById(R.id.tvPlacarVisitante);

        btnGolCasa = (Button) findViewById(R.id.btnGolCasa);
        btnGolVisitante = (Button) findViewById(R.id.btnGolVisitante);

        btnPause = (Button) findViewById(R.id.btnPause);
        btnStart = (Button) findViewById(R.id.btnStart);

        ch = (Chronometer) findViewById(R.id.cronometro);
        this.milliseconds = 0;

        this.ativoCronometro = false;

        if (getIntent() != null){
            timeCasa = getIntent().getStringExtra("TimeCasa");
            timeVisitante = getIntent().getStringExtra("TimeVisitante");
            tempoJogo = Integer.parseInt(getIntent().getStringExtra("TempoJogo"));
            podePausar = getIntent().getExtras().getBoolean("PodePausar");

            tvTimeCasa.setText(timeCasa);
            tvTimeVisitante.setText(timeVisitante);
        }

        if(savedInstanceState != null){
            tvPlacarCasa.setText(String.valueOf(savedInstanceState.getInt("GOLCASA")));
            tvPlacarVisitante.setText(String.valueOf(savedInstanceState.getInt("GOLVISITANTE")));
            this.ativoCronometro = savedInstanceState.getBoolean("ATIVOCRONOMETRO");
            this.textCronometro = savedInstanceState.getString("TEXTCRONOMETRO");
            this.milliseconds = savedInstanceState.getLong("TEMPO");
            if (this.ativoCronometro){
                ch.setBase(SystemClock.elapsedRealtime() - milliseconds);
                ch.start();
                btnStart.setEnabled(false);
            }else{
                ch.setText(this.textCronometro);
            }
        }

        if (podePausar == false){
            btnPause.setVisibility(View.GONE);
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
        this.tvPlacarCasa.setText(String.valueOf(valorPlacarCasa));
    }

    /**
     * Função para incrementar o placar do time visitante
     * @param v parametro da view (this)
     */
    public void golVisitante(View v){
        int valorPlacarVisitante = this.getPlacarAtual(tvPlacarVisitante);
        valorPlacarVisitante++;
        this.tvPlacarVisitante.setText(String.valueOf(valorPlacarVisitante));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("GOLCASA",this.getPlacarAtual(tvPlacarCasa));
        outState.putInt("GOLVISITANTE",this.getPlacarAtual(tvPlacarVisitante));
        if (this.ativoCronometro){
            outState.putLong("TEMPO", SystemClock.elapsedRealtime()- ch.getBase());
        }else{
            outState.putLong("TEMPO", this.milliseconds);
        }
        outState.putBoolean("ATIVOCRONOMETRO",this.ativoCronometro);
        outState.putString("TEXTCRONOMETRO",this.textCronometro);
    }

    public void startCronometro(View v){
        ch.setBase(SystemClock.elapsedRealtime() - milliseconds);
        ch.start();
        this.ativoCronometro = true;
        btnStart.setEnabled(false);
        btnPause.setEnabled(true);
        btnGolCasa.setEnabled(true);
        btnGolVisitante.setEnabled(true);
    }

    public void pauseCronometro(View v){
        this.milliseconds = SystemClock.elapsedRealtime()- ch.getBase();
        ch.stop();
        this.textCronometro = ch.getText().toString();
        this.ativoCronometro = false;
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
        btnGolCasa.setEnabled(false);
        btnGolVisitante.setEnabled(false);
    }
}
