package br.ufba.dcc.prontuario.activity.exame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.util.FormUtils;

public class FormExameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exames");
        setContentView(R.layout.activity_form_exame);

        initSpinner();

        EditText editTextData = (EditText) findViewById(R.id.form_exame_editText_data);
        FormUtils.initDatePicker(FormExameActivity.this, editTextData);
    }

    private void initSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_tipoExame);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FormExameActivity.this,
                R.array.tipos_de_exame, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
