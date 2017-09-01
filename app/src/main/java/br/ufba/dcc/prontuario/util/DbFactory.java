package br.ufba.dcc.prontuario.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bruno on 19/07/2017.
 */
public class DbFactory extends SQLiteOpenHelper {
    public DbFactory(Context context) {
        super(context, "Prontuario_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql = new StringBuilder();

        /*
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
        */

        sql.append("CREATE TABLE `Consulta` (");
        sql.append("idConsulta integer NOT NULL PRIMARY KEY,");
        sql.append("data varchar(10) NOT NULL,");
        sql.append("horario varchar(5) NOT NULL,");
        sql.append("especialidade varchar(80),");
        sql.append("medico varchar(150),");
        sql.append("endereco text);");

        sqLiteDatabase.execSQL(sql.toString());

        sql = new StringBuilder();

        sql.append("CREATE TABLE `Exame` (");
        sql.append("idExame integer NOT NULL PRIMARY KEY,");
        sql.append("data varchar(10) NOT NULL,");
        sql.append("horario varchar(5) NOT NULL,");
        sql.append("tipoExame varchar(80),");
        sql.append("observacoes varchar(150),");
        sql.append("endereco text);");

        sqLiteDatabase.execSQL(sql.toString());

        /*
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
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}