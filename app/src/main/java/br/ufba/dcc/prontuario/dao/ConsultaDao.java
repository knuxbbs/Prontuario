package br.ufba.dcc.prontuario.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufba.dcc.prontuario.domain.Consulta;
import br.ufba.dcc.prontuario.util.DbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruno on 19/07/2017.
 */
public class ConsultaDao {
    private DbFactory dbFactory;

    public ConsultaDao(DbFactory pDbFactory) {
        dbFactory = pDbFactory;
    }

    public void inserir(Consulta consulta) {
        SQLiteDatabase db = dbFactory.getWritableDatabase();

        db.insert("Consulta", null, obterDadosDaConsulta(consulta));

        dbFactory.close();
    }

    public List<Consulta> listar() {
        SQLiteDatabase db = dbFactory.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM `Consulta`", null);
        List<Consulta> lista = new ArrayList<>();

        while (c.moveToNext()) {
            Consulta consulta = new Consulta();

            consulta.setId(c.getInt(c.getColumnIndex("IdConsulta")));
            consulta.setData(c.getString(c.getColumnIndex("Data")));
            consulta.setHorario(c.getString(c.getColumnIndex("Horario")));
            consulta.setMedico(c.getString(c.getColumnIndex("Medico")));
            consulta.setEndereco(c.getString(c.getColumnIndex("Endereco")));

            lista.add(consulta);
        }

        c.close();
        dbFactory.close();

        return lista;
    }

    private ContentValues obterDadosDaConsulta(Consulta consulta) {
        ContentValues values = new ContentValues();

        values.put("Data", consulta.getData());
        values.put("Horario", consulta.getHorario());
        values.put("Medico", consulta.getMedico());
        values.put("Endereco", consulta.getEndereco());

        return values;
    }
}
