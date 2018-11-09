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
import modelo.Divisao;

/**
 *
 * @author Ricardo
 */
public class DivisaoDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public List<Divisao> mostrarTodos() throws ClassNotFoundException, SQLException{
        
        List<Divisao> divisoes = new ArrayList<>();
        Divisao d = null;
        sql = "SELECT "
                + "d.id, "
                + "d.nome,  "
                + "c.id as supervisor_id, "
                + "c.nome as supervisor, "
                + "u.id as unidade_id, "
                + "u.nome as unidade\n" +
                "FROM "
                + "divisao d, "
                + "colaborador c, "
                + "unidade u\n" +
                "where u.id = d.unidade_id\n" +
                "and c.id = d.supervisor_id";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            d = new Divisao();
            d.setDivisao_id(rs.getInt("d.id"));
            d.setDivisao_nome(rs.getString("d.nome"));            
            d.setColaborador_id(rs.getInt("supervisor_id")); 
            d.setColaborador_nome(rs.getString("supervisor"));
            d.setUnidade_id(rs.getInt("unidade_id"));           
            d.setUnidade_nome(rs.getString("unidade"));             
            divisoes.add(d);
        }
        C.db();
        return divisoes;
    }
    
    public boolean inserir(Divisao d){
        try {
            sql = "INSERT INTO divisao (nome, unidade_id, supervisor_id) VALUES (?, ?, ?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, d.getDivisao_nome());
            pst.setInt(2, d.getUnidade_id());
            pst.setInt(3, d.getColaborador_id());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DivisaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id){
        try {
            sql = "DELETE FROM divisao WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DivisaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean atualizar(Divisao d){
        try {
            sql = "UPDATE divisao SET nome = ?, unidade_id = ?, supervisor_id = ? WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, d.getDivisao_nome());
            pst.setInt(2, d.getUnidade_id());
            pst.setInt(3, d.getColaborador_id());
            pst.setInt(4, d.getDivisao_id());
            pst.executeUpdate();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DivisaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
