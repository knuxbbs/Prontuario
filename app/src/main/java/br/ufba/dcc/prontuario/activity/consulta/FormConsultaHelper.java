package br.ufba.dcc.prontuario.activity.consulta;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.domain.Consulta;
import br.ufba.dcc.prontuario.util.FormUtils;

public class FormConsultaHelper extends AppCompatActivity {
    private Spinner spinnerEspecialidade;
    private EditText editTextMedico;
    private EditText editTextData;
    private EditText editTextHorario;
    private EditText editTextEndereco;
    private Consulta consulta;

    public FormConsultaHelper(FormConsultaActivity formConsultaActivity) {
        editTextData = (EditText) formConsultaActivity.findViewById(R.id.form_consulta_editText_data);
        editTextHorario = (EditText) formConsultaActivity.findViewById(R.id.form_consulta_editText_horario);
        spinnerEspecialidade = (Spinner) formConsultaActivity.findViewById(R.id.spinner_especialidade);
        editTextMedico = (EditText) formConsultaActivity.findViewById(R.id.editText_medico);
        editTextEndereco = (EditText) formConsultaActivity.findViewById(R.id.form_consulta_editText_local);

        consulta = new Consulta();
    }

    public void preencherFormulario(Consulta consulta) {
        int spinnerSelectedIndex = FormUtils.getSpinnerSelectedIndex(spinnerEspecialidade, consulta.getEspecialidade());

        spinnerEspecialidade.setSelection(spinnerSelectedIndex);
        editTextMedico.setText(consulta.getMedico());
        editTextData.setText(consulta.getData());
        editTextHorario.setText(consulta.getHorario());
        editTextEndereco.setText(consulta.getEndereco());
    }

    public Consulta obterDadosDoFormulario() {
        consulta.setEspecialidade(spinnerEspecialidade.getSelectedItem().toString());
        consulta.setMedico(editTextMedico.getText().toString());
        consulta.setData(editTextData.getText().toString());
        consulta.setHorario(editTextHorario.getText().toString());
        consulta.setEndereco(editTextEndereco.getText().toString());

        return consulta;
    }
}
