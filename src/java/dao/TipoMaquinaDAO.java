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
import modelo.TipoMaquina;

/**
 *
 * @author Ricardo
 */
public class TipoMaquinaDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<TipoMaquina> mostrarTodos() throws ClassNotFoundException, SQLException{
        
        List<TipoMaquina> niveis = new ArrayList<>();
        TipoMaquina t = null;
        sql = "SELECT * FROM tipo_maquina;";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            t = new TipoMaquina();
            t.setTipoMaquina_id(rs.getInt("id_tipo_maquina"));
            t.setTipo(rs.getString("tipo_maquina"));
            niveis.add(t);
        }
        C.db();
        return niveis;
    }
    
    public boolean inserir(TipoMaquina t){
        try {
            sql = "INSERT INTO tipo_maquina (id_tipo_maquina, tipo_maquina) VALUES (?, ?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, t.getTipoMaquina_id());
            pst.setString(2, t.getTipo());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TipoMaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id){
        try {
            sql = "DELETE FROM tipo_maquina WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TipoMaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
    }
    
    public boolean atualizar(TipoMaquina t){
        try {
            sql = "UPDATE tipo_maquina SET tipo_maquina = ? WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getTipo());
            pst.setInt(2, t.getTipoMaquina_id());
            pst.executeUpdate();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TipoMaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
