package br.ufba.dcc.prontuario.activity.consulta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.activity.exame.FormExameActivity;
import br.ufba.dcc.prontuario.util.FormUtils;

public class FormConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Consultas");
        setContentView(R.layout.activity_form_consulta);

        EditText editTextData = (EditText) findViewById(R.id.form_consulta_editText_data);
        FormUtils.initDatePicker(FormConsultaActivity.this, editTextData);

        EditText editTexHorario = (EditText) findViewById(R.id.editText_horario);
        FormUtils.initTimePicker(FormConsultaActivity.this, editTexHorario);
    }
}
