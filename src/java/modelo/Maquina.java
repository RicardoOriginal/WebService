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
public class Maquina extends TipoMaquina{
    private int maquina_id; 
    private int Frota;
    private String modelo;
    private Double horas_trabalhadas;
    private Boolean ativo;

    public Maquina() {
    }

    public int getMaquina_id() {
        return maquina_id;
    }

    public void setMaquina_id(int maquina_id) {
        this.maquina_id = maquina_id;
    }

    public int getFrota() {
        return Frota;
    }

    public void setFrota(int Frota) {
        this.Frota = Frota;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getHoras_trabalhadas() {
        return horas_trabalhadas;
    }

    public void setHoras_trabalhadas(Double horas_trabalhadas) {
        this.horas_trabalhadas = horas_trabalhadas;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

   
}
