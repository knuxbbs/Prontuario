package br.ufba.dcc.prontuario.activity.exame;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.domain.Exame;
import br.ufba.dcc.prontuario.util.FormUtils;

/**
 * Created by Bernardo Bastos on 01/09/2017.
 */

public class FormExameHelper extends AppCompatActivity {

    private Spinner spinnerTipoExame;
    private EditText editTextData;
    private EditText editTextHorario;
    private EditText editTextEndereco;
    private EditText editTextObservacoes;
    private Exame exame;


    public FormExameHelper(FormExameActivity formExameActivity) {
        editTextData = formExameActivity.findViewById(R.id.form_exame_editText_data);
        editTextHorario = formExameActivity.findViewById(R.id.form_exame_editText_horario);
        spinnerTipoExame = formExameActivity.findViewById(R.id.spinner_tipoExame);
        editTextEndereco = formExameActivity.findViewById(R.id.form_exame_editText_local);
        editTextObservacoes = formExameActivity.findViewById(R.id.editText_observacoes);

        exame = new Exame();
    }

    public void preencherFormulario(Exame exame) {
        int spinnerSelectedIndex = FormUtils.getSpinnerSelectedIndex(spinnerTipoExame, exame.getTipoExame());

        spinnerTipoExame.setSelection(spinnerSelectedIndex);
        editTextObservacoes.setText(exame.getObservacoes());
        editTextData.setText(exame.getData());
        editTextHorario.setText(exame.getHorario());
        editTextEndereco.setText(exame.getEndereco());
    }

    public Exame obterDadosDoFormulario() {
        exame.setTipoExame(spinnerTipoExame.getSelectedItem().toString());
        exame.setData(editTextData.getText().toString());
        exame.setHorario(editTextHorario.getText().toString());
        exame.setEndereco(editTextEndereco.getText().toString());
        exame.setObservacoes(editTextObservacoes.getText().toString());

        return exame;
    }

}
