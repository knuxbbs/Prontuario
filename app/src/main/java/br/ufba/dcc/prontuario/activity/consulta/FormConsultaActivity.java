package br.ufba.dcc.prontuario.activity.consulta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.activity.exame.FormExameActivity;
import br.ufba.dcc.prontuario.dao.ConsultaDao;
import br.ufba.dcc.prontuario.domain.Consulta;
import br.ufba.dcc.prontuario.util.DbFactory;
import br.ufba.dcc.prontuario.util.FormHelper;
import br.ufba.dcc.prontuario.util.FormUtils;

import java.sql.SQLException;

public class FormConsultaActivity extends AppCompatActivity {

    private Consulta consulta;
    private FormHelper formHelper;
    private DbFactory dbFactory = new DbFactory(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Consultas");
        setContentView(R.layout.activity_form_consulta);

        formHelper = new FormHelper(this);

        EditText editTextData = (EditText) findViewById(R.id.form_consulta_editText_data);
        FormUtils.initDatePicker(FormConsultaActivity.this, editTextData);

        EditText editTexHorario = (EditText) findViewById(R.id.editText_horario);
        FormUtils.initTimePicker(FormConsultaActivity.this, editTexHorario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String mensagem = "";

        switch (item.getItemId()){
            case R.id.menu_form_ok:
                ConsultaDao consultaDao = new ConsultaDao(dbFactory);

                try{
                    consulta = formHelper.obterDadosDoFormulario();

                    consultaDao.inserir(consulta);
                    mensagem = "Consulta nº " + consulta.getId() + " inserida com sucesso.";
                } catch (Exception ex){
                    mensagem = ex.getLocalizedMessage();
                } finally {
                    Toast.makeText(FormConsultaActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                    Intent listaConsultaIntent = new Intent(FormConsultaActivity.this, ListaConsultaActivity.class);
                    startActivity(listaConsultaIntent);

                    finish();
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
