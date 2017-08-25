package br.ufba.dcc.prontuario.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bruno on 19/07/2017.
 */
public class Consulta implements Serializable {
    private long id;
    private String data;
    private String horario;
    private String especialidade;
    private String medico;
    private String endereco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return getData() + " - " + getEspecialidade() + " - " + getMedico();
    }
}
