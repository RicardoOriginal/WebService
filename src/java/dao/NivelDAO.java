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
import modelo.Nivel;

/**
 *
 * @author Ricardo
 */
public class NivelDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<Nivel> mostrarTodos() throws ClassNotFoundException, SQLException{
        
        List<Nivel> niveis = new ArrayList<>();
        Nivel n = null;
        sql = "SELECT * FROM nivel;";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            n = new Nivel();
            n.setNivel_id(rs.getInt("id"));
            n.setCargo(rs.getString("cargo"));
            niveis.add(n);
        }
        C.db();
        return niveis;
    }
    
    public boolean inserir(Nivel e){
        try {
            sql = "INSERT INTO nivel (cargo) VALUES (?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getCargo());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id){
        try {
            sql = "DELETE FROM nivel WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }
    }
    
    public boolean atualizar(Nivel n){
        try {
            sql = "UPDATE nivel SET nome = ? WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, n.getCargo());
            pst.setInt(2, n.getNivel_id());
            pst.executeUpdate();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(NivelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
