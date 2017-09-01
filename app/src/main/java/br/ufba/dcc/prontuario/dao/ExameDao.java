package br.ufba.dcc.prontuario.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.ufba.dcc.prontuario.domain.Exame;
import br.ufba.dcc.prontuario.util.DbFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Bruno on 19/07/2017.
 */
public class ExameDao {
    private DbFactory dbFactory;

    public ExameDao(DbFactory eDbFactory) {
        dbFactory = eDbFactory;
    }

    private ContentValues obterDadosDoExame(Exame exame) {
        ContentValues values = new ContentValues();

        values.put("tipoExame", exame.getTipoExame());
        values.put("data", exame.getData());
        values.put("horario", exame.getHorario());
        values.put("endereco", exame.getEndereco());
        values.put("observacoes", exame.getObservacoes());

        return values;
    }

    public void inserir(Exame exame) {
        SQLiteDatabase db = dbFactory.getWritableDatabase();

        db.insert("Exame", null, obterDadosDoExame(exame));

        dbFactory.close();
    }

    public List<Exame> listar() {
        SQLiteDatabase db = dbFactory.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Exame", null);
        List<Exame> lista = new ArrayList<>();

        while (c.moveToNext()) {
            Exame exame = new Exame();

            exame.setId(c.getLong(c.getColumnIndex("idExame")));
            exame.setTipoExame(c.getString(c.getColumnIndex("tipoExame")));
            exame.setData(c.getString(c.getColumnIndex("data")));
            exame.setHorario(c.getString(c.getColumnIndex("horario")));
            exame.setEndereco(c.getString(c.getColumnIndex("endereco")));
            exame.setObservacoes(c.getString(c.getColumnIndex("observacoes")));

            lista.add(exame);
        }

        c.close();
        dbFactory.close();

        //Ordena lista de consultas de acordo com a data.
        /*
        Collections.sort(lista, new Comparator<Exame>() {
            @Override
            public int compare(Exame e1, Exame e2) {
                return e1.getData().compareTo(e2.getData());
            }
        });
        */

        return lista;
    }
}
