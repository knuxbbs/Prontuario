package br.ufba.dcc.prontuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.ufba.dcc.prontuario.dao.PacienteDao;
import br.ufba.dcc.prontuario.domain.Paciente;
import br.ufba.dcc.prontuario.util.DbFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DbFactory dbFactory = new DbFactory(this);
    PacienteDao pacienteDao = new PacienteDao(dbFactory);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewPacientes = (ListView) findViewById(R.id.lista_pacientes);

        fillDatabase();
        initListView();

        registerForContextMenu(listViewPacientes);
    }

    private void fillDatabase(){
        if(!pacienteDao.listar().isEmpty()) return;

        Paciente paciente = new Paciente();
        paciente.setNome("Bruno");
        paciente.setDataNascimento(new Date());
        paciente.setSexo('M');
        paciente.setPeso(80);
        paciente.setAltura((float) 1.8);

        pacienteDao.inserir(paciente);

        paciente = new Paciente();
        paciente.setNome("Bernardo");
        paciente.setDataNascimento(new Date());
        paciente.setSexo('M');
        paciente.setPeso(80);
        paciente.setAltura((float) 1.8);

        pacienteDao.inserir(paciente);

        paciente = new Paciente();
        paciente.setNome("Carlos");
        paciente.setDataNascimento(new Date());
        paciente.setSexo('M');
        paciente.setPeso(80);
        paciente.setAltura((float) 1.8);

        pacienteDao.inserir(paciente);

        paciente = new Paciente();
        paciente.setNome("Fernando");
        paciente.setDataNascimento(new Date());
        paciente.setSexo('M');
        paciente.setPeso(80);
        paciente.setAltura((float) 1.8);

        pacienteDao.inserir(paciente);
    }

    private void initListView() {
        List<Paciente> listaPaciente = pacienteDao.listar();

        ListView listViewPacientes = (ListView) findViewById(R.id.lista_pacientes);

        ArrayAdapter<Paciente> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPaciente);

        listViewPacientes.setAdapter(adapter);
    }
}
