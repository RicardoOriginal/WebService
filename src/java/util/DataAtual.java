/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ricardo
 */
public class DataAtual {

    private String dataFormatada;

    @Override
    public String toString() {
        return dataFormatada;
    }

//    public DataAtual(String dataFormatada) {
//        this.dataFormatada = dataFormatada;
//    }
//
//    public String getDataFormatada() {
//        return dataFormatada;
//    }
//
    public DataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date hora = Calendar.getInstance().getTime();
        dataFormatada = sdf.format(hora);
    }
}
