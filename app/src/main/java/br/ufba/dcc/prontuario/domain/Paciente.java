package br.ufba.dcc.prontuario.domain;

import java.util.Date;

/**
 * Created by Bruno on 19/07/2017.
 */
public class Paciente {
    private int Id;
    private String Nome;
    private Date DataNascimento;
    private char Sexo;
    private float Peso;
    private float Altura;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Date getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public char getSexo() {
        return Sexo;
    }

    public void setSexo(char sexo) {
        Sexo = sexo;
    }

    public float getPeso() {
        return Peso;
    }

    public void setPeso(float peso) {
        Peso = peso;
    }

    public float getAltura() {
        return Altura;
    }

    public void setAltura(float altura) {
        Altura = altura;
    }

    @Override
    public String toString() {
        return Id + " - " + this.Nome;
    }
}
