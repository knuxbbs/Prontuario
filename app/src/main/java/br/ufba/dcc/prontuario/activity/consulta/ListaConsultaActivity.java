package br.ufba.dcc.prontuario.activity.consulta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.ufba.dcc.prontuario.R;

public class ListaConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Consultas");
        setContentView(R.layout.activity_lista_consulta);

        initButton();
    }

    private void initButton() {
        Button btnAdicionar = (Button) findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formConsultaIntent = new Intent(ListaConsultaActivity.this, FormConsultaActivity.class);
                startActivity(formConsultaIntent);
            }
        });
    }
}
