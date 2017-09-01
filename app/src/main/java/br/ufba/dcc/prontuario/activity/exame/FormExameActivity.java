package br.ufba.dcc.prontuario.activity.exame;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import br.ufba.dcc.prontuario.R;
import br.ufba.dcc.prontuario.activity.consulta.FormConsultaActivity;
import br.ufba.dcc.prontuario.adapter.ImageGridAdapter;
import br.ufba.dcc.prontuario.dao.ExameDao;
import br.ufba.dcc.prontuario.domain.Exame;
import br.ufba.dcc.prontuario.util.DbFactory;
import br.ufba.dcc.prontuario.util.FormUtils;

import java.io.File;

public class FormExameActivity extends AppCompatActivity {

    private FormExameHelper formExameHelper;
    private static final int CAMERA_REQUEST_CODE = 1;
    private Intent cameraIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exames");
        setContentView(R.layout.activity_form_exame);

        initSpinner();
        initButton();
        initGridView();

        EditText editTextData = (EditText) findViewById(R.id.form_exame_editText_data);
        FormUtils.initDatePicker(FormExameActivity.this, editTextData);

        EditText editTextHorario = (EditText) findViewById(R.id.form_exame_editText_horario);
        FormUtils.initTimePicker(FormExameActivity.this, editTextHorario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DbFactory dbFactory = new DbFactory(FormExameActivity.this);
        String mensagem = "";

        switch (item.getItemId()) {
            case R.id.menu_form_salvar:
                ExameDao exameDao = new ExameDao(dbFactory);

                try {
                    Exame exame = formExameHelper.obterDadosDoFormulario();

                    exameDao.inserir(exame);
                    mensagem = "Exame " + exame.getData() + " registrado com sucesso.";

                } catch (Exception ex) {
                    mensagem = ex.getLocalizedMessage();

                } finally {
                    Toast.makeText(FormExameActivity.this, mensagem, Toast.LENGTH_LONG).show();
                    Intent listaExameIntent = new Intent(FormExameActivity.this, ListaExameActivity.class);
                    startActivity(listaExameIntent);

                    finish();
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_tipoExame);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FormExameActivity.this,
                R.array.tipos_de_exame, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initButton() {
        Button btnAnexarImagens = (Button) findViewById(R.id.btnAnexarImagens);

        btnAnexarImagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraIntent = getCameraIntent();

                //Verifica a versão do Android instalada no dispositivo
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                    } else{
                        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                    }
                } else {
                    startActivity(cameraIntent);
                }
            }
        });
    }

    private Intent getCameraIntent() {
        String filePath = Environment.getExternalStorageDirectory() + "/";
        String fileName = System.currentTimeMillis() + ".jpg";

        File file = new File(filePath.concat(fileName));
        Uri photoUri = FileProvider.getUriForFile(
                FormExameActivity.this,
                "br.ufba.dcc.prontuario.fileprovider", file);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

        return cameraIntent;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initGridView(){
        GridView imageGridView = (GridView) findViewById(R.id.grid_view_image);

        ImageGridAdapter imageGridAdapter = new ImageGridAdapter(FormExameActivity.this, new String[]{});
        imageGridView.setAdapter(imageGridAdapter);
    }
}
