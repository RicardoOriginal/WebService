/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Ricardo
 */
public class Colaborador extends Nivel{
    
    private int colaborador_id;
    private int matricula;
    private String colaborador_nome;
    private String senha;
    private boolean ativo;

    public Colaborador() {
    }

    public int getColaborador_id() {
        return colaborador_id;
    }

    public void setColaborador_id(int colaborador_id) {
        this.colaborador_id = colaborador_id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getColaborador_nome() {
        return colaborador_nome;
    }

    public void setColaborador_nome(String colaborador_nome) {
        this.colaborador_nome = colaborador_nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
