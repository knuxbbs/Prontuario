package br.ufba.dcc.prontuario.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.ufba.dcc.prontuario.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Bruno on 19/07/2017.
 */
public class DbFactory extends SQLiteOpenHelper {
    public DbFactory(Context context) {
        super(context, "Prontuario_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*InputStream inputStream = context.getResources().openRawResource(R.raw.create_database);

        String queries = "";

        try {
            queries = IOUtils.toString(inputStream);
        } catch (IOException e) {
            Util.logException(e);
        }

        for (String query : queries.split(";")) {
            sqLiteDatabase.execSQL(query);
        }*/

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE `Paciente` (");
        sql.append("IdPaciente integer NOT NULL PRIMARY KEY,");
        sql.append("Nome varchar(150) NOT NULL,");
        sql.append("DataNascimento datetime NOT NULL,");
        sql.append("Sexo char NOT NULL,");
        sql.append("Peso float NOT NULL,");
        sql.append("Altura float NOT NULL);");

        sql.append("CREATE TABLE `Medico` (");
        sql.append("CRM number NOT NULL PRIMARY KEY,");
        sql.append("Nome varchar(150) NOT NULL);");

        sql.append("CREATE TABLE `Consulta` (");
        sql.append("Paciente_id integer NOT NULL PRIMARY KEY,");
        sql.append("Medico_id integer NOT NULL PRIMARY KEY,");
        sql.append("DataConsulta datetime NOT NULL PRIMARY KEY,");
        sql.append("FOREIGN KEY(Paciente) REFERENCES Paciente(IdPaciente),");
        sql.append("FOREIGN KEY(Medico_id) REFERENCES Medico(CRM));");

        sql.append("CREATE TABLE `Exame` (");
        sql.append("IdExame integer NOT NULL PRIMARY KEY,");
        sql.append("Tipo varchar(100) NOT NULL,");
        sql.append("Resultado text,");
        sql.append("Consulta_id integer,");
        sql.append("FOREIGN KEY(Consulta_id) REFERENCES Consulta(Paciente_id, Medico_id, DataConsulta));");

        sql.append("CREATE TABLE `Patologia` (");
        sql.append("SID integer NOT NULL PRIMARY KEY,");
        sql.append("Nome varchar(150) NOT NULL);");

        sql.append("CREATE TABLE `Medicamento` (");
        sql.append("IdMedicamento integer NOT NULL PRIMARY KEY,");
        sql.append("Nome varchar(150) NOT NULL);");

        sql.append("CREATE TABLE `Diagnostico` (");
        sql.append("Consulta_id integer NOT NULL PRIMARY KEY,");
        sql.append("Patologia_id integer,");
        sql.append("FOREIGN KEY(Consulta_id) REFERENCES Consulta(Paciente_id, Medico_id, DataConsulta),");
        sql.append("FOREIGN KEY(Patologia_id) REFERENCES Patologia(SID));");

        sql.append("CREATE TABLE `Tratamento` (");
        sql.append("IdTratamento integer NOT NULL PRIMARY KEY,");
        sql.append("DataInicio datetime NOT NULL,");
        sql.append("DataFim datetime NOT NULL,");
        sql.append("Dieta text,");
        sql.append("Medicamento_id integer,");
        sql.append("Diagnostico_id integer NOT NULL,");
        sql.append("FOREIGN KEY(Medicamento_id) REFERENCES Medicamento(IdMedicamento),");
        sql.append("FOREIGN KEY(Diagnostico_id) REFERENCES Diagnostico(Consulta_id));");

        sql.append("CREATE TABLE `EvolucaoTratamento` (");
        sql.append("Consulta_id integer NOT NULL PRIMARY KEY,");
        sql.append("Tratamento_id integer NOT NULL PRIMARY KEY,");
        sql.append("FOREIGN KEY(Consulta_id) REFERENCES Consulta(Paciente_id, Medico_id, DataConsulta),");
        sql.append("FOREIGN KEY(Tratamento_id) REFERENCES Tratamento(IdTratamento));");

        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}