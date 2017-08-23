package br.ufba.dcc.prontuario.activity.tratamento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.ufba.dcc.prontuario.R;

public class ListaTratamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tratamentos");
        setContentView(R.layout.activity_lista_tratamento);
    }
}
