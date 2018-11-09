/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controle.C;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Maquina;

/**
 *
 * @author Ricardo
 */
public class MaquinaDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<Maquina> mostrarTodos() throws ClassNotFoundException, SQLException{
        
        List<Maquina> maquinas = new ArrayList<>();
        Maquina m = null;
        sql = "SELECT "
                + "m.id, "
                + "m.frota, "
                + "t.id_tipo_maquina, "
                + "t.tipo_maquina, "
                + "m.modelo, "
                + "m.horas_trabalhadas, "
                + "m.ativo\n" +
                "FROM "
                + "maquina m, tipo_maquina t\n" +
                "WHERE "
                + "m.id_tipo = t.id_tipo_maquina;";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            m = new Maquina();
            m.setMaquina_id(rs.getInt("id"));
            m.setFrota(rs.getInt("m.frota"));
            m.setTipoMaquina_id(rs.getInt("t.id_tipo_maquina"));
            m.setTipo(rs.getString("t.tipo_maquina"));
            m.setModelo(rs.getString("m.modelo"));
            m.setHoras_trabalhadas(rs.getDouble("m.horas_trabalhadas"));
            m.setAtivo(rs.getBoolean("m.ativo"));
            maquinas.add(m);
        }
        C.db();
        return maquinas;
    }
    
    public boolean inserir(Maquina m){
        try {
            sql = "INSERT INTO maquina (frota, modelo, horas_trabalhadas, ativo, id_tipo) VALUES (?, ?, ?, ?, ?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1,m.getFrota());
            pst.setString(2, m.getModelo());
            pst.setDouble(3, m.getHoras_trabalhadas());
            pst.setBoolean(4, m.getAtivo());
            pst.setInt(5, m.getTipoMaquina_id());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id){
        try {
            sql = "DELETE FROM maquina WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean atualizar(Maquina m){
        try {
            sql = "UPDATE maquina "
                    + "SET "
                    + "frota = ?, "
                    + "modelo = ?, "
                    + "horas_trabalhadas = ?, "
                    + "ativo = ?, "
                    + "id_tipo = ? "
                    + "WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, m.getFrota());
            pst.setString(2, m.getModelo());
            pst.setDouble(3, m.getHoras_trabalhadas());
            pst.setBoolean(4, m.getAtivo());
            pst.setInt(5, m.getTipoMaquina_id());
            pst.setInt(6, m.getMaquina_id());
            pst.executeUpdate();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
