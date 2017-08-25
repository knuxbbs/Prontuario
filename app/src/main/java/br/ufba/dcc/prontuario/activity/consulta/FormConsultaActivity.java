package br.ufba.dcc.prontuario.activity.consulta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.dao.ConsultaDao;
import br.ufba.dcc.prontuario.domain.Consulta;
import br.ufba.dcc.prontuario.util.DbFactory;
import br.ufba.dcc.prontuario.util.FormUtils;

public class FormConsultaActivity extends AppCompatActivity {

    private DbFactory dbFactory;
    private FormConsultaHelper formConsultaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Consultas");
        setContentView(R.layout.activity_form_consulta);

        formConsultaHelper = new FormConsultaHelper(this);

        initSpinner();

        EditText editTextData = (EditText) findViewById(R.id.form_consulta_editText_data);
        FormUtils.initDatePicker(FormConsultaActivity.this, editTextData);

        EditText editTexHorario = (EditText) findViewById(R.id.editText_horario);
        FormUtils.initTimePicker(FormConsultaActivity.this, editTexHorario);

        Consulta consulta = (Consulta) getIntent().getSerializableExtra("Consulta");

        if (consulta == null) return;

        formConsultaHelper.preencherFormulario(consulta);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        dbFactory = new DbFactory(FormConsultaActivity.this);
        String mensagem = "";

        switch (item.getItemId()) {
            case R.id.menu_form_salvar:
                ConsultaDao consultaDao = new ConsultaDao(dbFactory);

                try {
                    Consulta consulta = formConsultaHelper.obterDadosDoFormulario();

                    consultaDao.inserir(consulta);
                    mensagem = "Consulta com " + consulta.getMedico() + " registrada com sucesso.";

                } catch (Exception ex) {
                    mensagem = ex.getLocalizedMessage();

                } finally {
                    Toast.makeText(FormConsultaActivity.this, mensagem, Toast.LENGTH_LONG).show();
                    Intent listaConsultaIntent = new Intent(FormConsultaActivity.this, ListaConsultaActivity.class);
                    startActivity(listaConsultaIntent);

                    finish();
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_especialidade);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FormConsultaActivity.this,
                R.array.especialidades_medicas, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
