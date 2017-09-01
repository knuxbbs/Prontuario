package br.ufba.dcc.prontuario.activity.exame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.dao.ExameDao;
import br.ufba.dcc.prontuario.domain.Exame;
import br.ufba.dcc.prontuario.util.DbFactory;

import java.util.List;

public class ListaExameActivity extends AppCompatActivity {

    private DbFactory dbFactory = new DbFactory(this);
    private ListView listViewExames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exames");
        setContentView(R.layout.activity_lista_exame);

        initButton();

        listViewExames = (ListView) findViewById(R.id.listview_exames);

        listViewExames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Exame exame = (Exame) adapterView.getItemAtPosition(i);

                Intent formExameIntent = new Intent(ListaExameActivity.this, FormExameActivity.class);
                formExameIntent.putExtra("Exame", exame);
                startActivity(formExameIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        initListView();
    }

    private void initListView() {
        ExameDao exameDao = new ExameDao(dbFactory);

        List<Exame> listaExames = exameDao.listar();

        listViewExames = (ListView) findViewById(R.id.listview_exames);

        ArrayAdapter<Exame> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaExames);

        listViewExames.setAdapter(adapter);

        registerForContextMenu(listViewExames);
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
