package br.ufba.dcc.prontuario.util;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.activity.consulta.FormConsultaActivity;
import br.ufba.dcc.prontuario.domain.Consulta;

public class FormHelper extends AppCompatActivity {
    private EditText editTextData;
    private EditText editTextHorario;
    private EditText editTextMedico;
    private EditText editTextEndereco;
    private Consulta consulta;

    public FormHelper(FormConsultaActivity formConsultaActivity) {
        editTextData = (EditText) formConsultaActivity.findViewById(R.id.form_consulta_editText_data);
        editTextHorario = (EditText) formConsultaActivity.findViewById(R.id.editText_horario);
        editTextMedico = (EditText) formConsultaActivity.findViewById(R.id.editText_medico);
        editTextEndereco = (EditText) formConsultaActivity.findViewById(R.id.editText_endereco);

        consulta = new Consulta();
    }

    public Consulta obterDadosDoFormulario() {
        consulta.setData(editTextData.getText().toString());
        consulta.setHorario(editTextHorario.getText().toString());
        consulta.setMedico(editTextMedico.getText().toString());
        consulta.setEndereco(editTextEndereco.getText().toString());

        return consulta;
    }
}
