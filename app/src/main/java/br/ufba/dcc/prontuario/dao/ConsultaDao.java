package br.ufba.dcc.prontuario.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufba.dcc.prontuario.domain.Consulta;
import br.ufba.dcc.prontuario.util.DbFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Bruno on 19/07/2017.
 */
public class ConsultaDao {
    private DbFactory dbFactory;

    public ConsultaDao(DbFactory pDbFactory) {
        dbFactory = pDbFactory;
    }

    private ContentValues obterDadosDaConsulta(Consulta consulta) {
        ContentValues values = new ContentValues();

        values.put("Especialidade", consulta.getEspecialidade());
        values.put("Medico", consulta.getMedico());
        values.put("Data", consulta.getData());
        values.put("Horario", consulta.getHorario());
        values.put("Endereco", consulta.getEndereco());

        return values;
    }

    public void inserir(Consulta consulta) {
        SQLiteDatabase db = dbFactory.getWritableDatabase();

        db.insert("Consulta", null, obterDadosDaConsulta(consulta));

        dbFactory.close();
    }

    public List<Consulta> listar() {
        SQLiteDatabase db = dbFactory.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Consulta", null);
        List<Consulta> lista = new ArrayList<>();

        while (c.moveToNext()) {
            Consulta consulta = new Consulta();

            consulta.setId(c.getLong(c.getColumnIndex("idConsulta")));
            consulta.setEspecialidade(c.getString(c.getColumnIndex("especialidade")));
            consulta.setMedico(c.getString(c.getColumnIndex("medico")));
            consulta.setData(c.getString(c.getColumnIndex("data")));
            consulta.setHorario(c.getString(c.getColumnIndex("horario")));
            consulta.setEndereco(c.getString(c.getColumnIndex("endereco")));

            lista.add(consulta);
        }

        c.close();
        dbFactory.close();

        //Ordena lista de consultas de acordo com a data.
        Collections.sort(lista, new Comparator<Consulta>() {
            @Override
            public int compare(Consulta c1, Consulta c2) {
                return c1.getData().compareTo(c2.getData());
            }
        });

        return lista;
    }
}
