package br.ufba.dcc.prontuario.activity.exame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.ufba.dcc.prontuario.R;

public class ListaExameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exames");
        setContentView(R.layout.activity_lista_exame);

        initButton();
    }

    private void initButton() {
        Button btnAdicionar = (Button) findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formExameIntent = new Intent(ListaExameActivity.this, FormExameActivity.class);
                startActivity(formExameIntent);
            }
        });
    }
}
