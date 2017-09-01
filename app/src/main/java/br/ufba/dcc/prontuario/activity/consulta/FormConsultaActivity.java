package br.ufba.dcc.prontuario.activity.consulta;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
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
        initButtons();

        EditText editTextData = (EditText) findViewById(R.id.form_consulta_editText_data);
        FormUtils.initDatePicker(FormConsultaActivity.this, editTextData);

        EditText editTextHorario = (EditText) findViewById(R.id.form_consulta_editText_horario);
        FormUtils.initTimePicker(FormConsultaActivity.this, editTextHorario);

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

    private void initButtons() {
        Button btnRegistrarDiagnostico = (Button) findViewById(R.id.btnRegistrarDiagnostico);

        btnRegistrarDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDiagnosticoDialog();
            }
        });
    }

    private void initDiagnosticoDialog() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_diagnotico, (ViewGroup) findViewById(R.id.dialog_diagnostico_root));

        AlertDialog.Builder builder = new AlertDialog.Builder(FormConsultaActivity.this);
        builder.setView(layout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }
}
