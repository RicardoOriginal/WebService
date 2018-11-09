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
import modelo.Frente;

/**
 *
 * @author Ricardo
 */
public class FrenteDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<Frente> mostrarTodos() throws ClassNotFoundException, SQLException{
        
        List<Frente> frentes = new ArrayList<>();
        Frente f = null;
        sql = "SELECT "
                + "f.id, "
                + "f.nome, "
                + "d.id, "
                + "d.nome, "
                + "c.id, "
                + "c.nome\n" +
                "FROM "
                + "frente f, "
                + "divisao d, "
                + "colaborador c\n" +
                "WHERE "
                + "f.divisao_id = d.id\n" +
                "AND "
                + "f.encarregado_id = c.id";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            f = new Frente();
            f.setFrente_id(rs.getInt("id"));
            f.setFrente_nome(rs.getString("nome"));
            f.setDivisao_id(rs.getInt("d.id"));
            f.setDivisao_nome(rs.getString("d.nome"));
            f.setColaborador_id(rs.getInt("c.id"));
            f.setColaborador_nome(rs.getString("c.nome"));

            frentes.add(f);
        }
        C.db();
        return frentes;
    }
    
    public boolean inserir(Frente f){
        try {
            sql = "INSERT INTO frente (nome, divisao_id, encarregado_id) VALUES (?, ?, ?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, f.getFrente_nome());
            pst.setInt(2, f.getDivisao_id());
            pst.setInt(3, f.getColaborador_id());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FrenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id){
        try {
            sql = "DELETE FROM frente WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FrenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean atualizar(Frente f){
        try {
            sql = "UPDATE frente SET nome = ?, divisao_id = ?, encarregado_id = ? WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, f.getFrente_nome());
            pst.setInt(2, f.getDivisao_id());
            pst.setInt(3, f.getColaborador_id());
            pst.setInt(4, f.getFrente_id());
            pst.executeUpdate();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FrenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
