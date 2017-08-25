package br.ufba.dcc.prontuario.activity.consulta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.dao.ConsultaDao;
import br.ufba.dcc.prontuario.domain.Consulta;
import br.ufba.dcc.prontuario.util.DbFactory;

import java.util.List;

public class ListaConsultaActivity extends AppCompatActivity {

    private DbFactory dbFactory = new DbFactory(this);
    private ListView listViewConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Consultas");
        setContentView(R.layout.activity_lista_consulta);

        initButton();

        listViewConsultas = (ListView) findViewById(R.id.listview_consultas);

        listViewConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Consulta consulta = (Consulta) adapterView.getItemAtPosition(i);

                Intent formConsultaIntent = new Intent(ListaConsultaActivity.this, FormConsultaActivity.class);
                formConsultaIntent.putExtra("Consulta", consulta);
                startActivity(formConsultaIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        initListView();
    }

    private void initButton() {
        Button btnAdicionar = (Button) findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formConsultaIntent = new Intent(ListaConsultaActivity.this, FormConsultaActivity.class);
                startActivity(formConsultaIntent);

                finish();
            }
        });
    }

    private void initListView(){
        ConsultaDao consultaDao = new ConsultaDao(dbFactory);

        List<Consulta> listaConsultas = consultaDao.listar();

        listViewConsultas = (ListView) findViewById(R.id.listview_consultas);

        ArrayAdapter<Consulta> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaConsultas);

        listViewConsultas.setAdapter(adapter);

        registerForContextMenu(listViewConsultas);
    }
}
