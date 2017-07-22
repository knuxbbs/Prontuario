package br.ufba.dcc.prontuario.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufba.dcc.prontuario.domain.Paciente;
import br.ufba.dcc.prontuario.util.DbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno on 19/07/2017.
 */
public class PacienteDao {
    private DbFactory dbFactory;

    public PacienteDao(DbFactory pDbFactory) {
        dbFactory = pDbFactory;
    }

    public void inserir(Paciente paciente) {
        SQLiteDatabase db = dbFactory.getWritableDatabase();

        db.insert("Paciente", null, obterDadosDoPaciente(paciente));

        dbFactory.close();
    }

    public List<Paciente> listar() {
        SQLiteDatabase db = dbFactory.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Paciente", null);
        List<Paciente> lista = new ArrayList<>();

        while (c.moveToNext()) {
            Paciente paciente = new Paciente();
            paciente.setId(c.getInt(c.getColumnIndex("IdPaciente")));
            paciente.setNome(c.getString(c.getColumnIndex("Nome")));
            //paciente.setDataNascimento(c.getString(c.getColumnIndex("DataNascimento")));
            paciente.setSexo((char)(c.getColumnIndex("Sexo")));
            paciente.setPeso(c.getFloat(c.getColumnIndex("Peso")));
            paciente.setAltura(c.getFloat(c.getColumnIndex("Altura")));

            lista.add(paciente);
        }

        c.close();
        dbFactory.close();

        return lista;
    }

    public ContentValues obterDadosDoPaciente(Paciente paciente) {
        ContentValues values = new ContentValues();

        values.put("Nome", paciente.getNome());
        values.put("DataNascimento", String.valueOf(paciente.getDataNascimento()));
        values.put("Sexo", String.valueOf(paciente.getSexo()));
        values.put("Peso", paciente.getPeso());
        values.put("Altura", paciente.getAltura());

        return values;
    }
}
