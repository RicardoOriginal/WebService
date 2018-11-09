/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author Ricardo
 */
public class Turno extends Frente{

    private int turno_id;
    private String data;
    private int turno;
    private String data_hora_atualizacao;
    private String temaDds;
    private Double producao;
    private Double chuva;
    private String observacao;
    private List<Colaborador> listaColaborador;

    public Turno() {
    }

    public int getTurno_id() {
        return turno_id;
    }

    public void setTurno_id(int turno_id) {
        this.turno_id = turno_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getData_hora_atualizacao() {
        return data_hora_atualizacao;
    }

    public void setData_hora_atualizacao(String data_hora_atualizacao) {
        this.data_hora_atualizacao = data_hora_atualizacao;
    }

    public String getTemaDds() {
        return temaDds;
    }

    public void setTemaDds(String temaDds) {
        this.temaDds = temaDds;
    }

    public Double getProducao() {
        return producao;
    }

    public void setProducao(Double producao) {
        this.producao = producao;
    }

    public Double getChuva() {
        return chuva;
    }

    public void setChuva(Double chuva) {
        this.chuva = chuva;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Colaborador> getListaColaborador() {
        return listaColaborador;
    }

    public void setListaColaborador(List<Colaborador> listaColaborador) {
        this.listaColaborador = listaColaborador;
    }
    
}
