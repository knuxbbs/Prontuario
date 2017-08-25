package br.ufba.dcc.prontuario.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.activity.consulta.ListaConsultaActivity;
import br.ufba.dcc.prontuario.activity.exame.ListaExameActivity;
import br.ufba.dcc.prontuario.activity.tratamento.ListaTratamentoActivity;

public class MainActivity extends AppCompatActivity {

    private static final String CONSULTA_MENU_OPTION = "Consultas";
    private static final String EXAME_MENU_OPTION = "Exames";
    private static final String TRATAMENTO_MENU_OPTION = "Tratamentos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewActivities = (ListView) findViewById(R.id.listview_activities);

        String[] mainActivityOptions = new String[]{CONSULTA_MENU_OPTION, EXAME_MENU_OPTION, TRATAMENTO_MENU_OPTION};

        initListView(mainActivityOptions);

        registerForContextMenu(listViewActivities);

        listViewActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedOption = (String) adapterView.getItemAtPosition(i);

                switch (selectedOption){
                    case CONSULTA_MENU_OPTION:
                        Intent listaConsultaIntent = new Intent(MainActivity.this, ListaConsultaActivity.class);
                        startActivity(listaConsultaIntent);
                        break;
                    case EXAME_MENU_OPTION:
                        Intent listaExameIntent = new Intent(MainActivity.this, ListaExameActivity.class);
                        startActivity(listaExameIntent);
                        break;
                    case TRATAMENTO_MENU_OPTION:
                        Intent listaTratamentoIntent = new Intent(MainActivity.this, ListaTratamentoActivity.class);
                        startActivity(listaTratamentoIntent);
                        break;
                }
            }
        });
    }

    private void initListView(String[] activityOptions) {
        ListView listViewActivities = (ListView) findViewById(R.id.listview_activities);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activityOptions);

        listViewActivities.setAdapter(adapter);
    }
}
