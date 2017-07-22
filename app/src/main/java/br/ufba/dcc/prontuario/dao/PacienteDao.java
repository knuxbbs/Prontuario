package br.ufba.dcc.prontuario.dao;

import android.database.sqlite.SQLiteDatabase;
import br.ufba.dcc.prontuario.domain.Paciente;
import br.ufba.dcc.prontuario.util.Factory;

/**
 * Created by Bruno on 19/07/2017.
 */
public class PacienteDao {
    private Factory factory;

    public PacienteDao(Factory pFactory){
        factory = pFactory;
    }

    public void inserir(Paciente paciente){
        SQLiteDatabase db = factory.getWritableDatabase();

        db.insert("Paciente", null, obterDadosDoPaciente(paciente));

        factory.close();
    }



}
