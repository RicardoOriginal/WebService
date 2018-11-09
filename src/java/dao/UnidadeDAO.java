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
import modelo.Unidade;

/**
 *
 * @author Ricardo
 */
public class UnidadeDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<Unidade> mostrarTodos() throws ClassNotFoundException, SQLException{

    List<Unidade> unidades = new ArrayList<>();
    Unidade u = null;
    sql = "SELECT "
            + "u.id, "
            + "u.nome, "
            + "e.id as empresa_id, "
            + "e.nome as empresa_nome, "
            + "c.id as gerente_id, "
            + "c.nome as gerente_nome\n" +
            "FROM "
            + "unidade u , "
            + "empresa e, "
            + "colaborador c\n" +
            "Where "
            + "e.id = u.empresa_id\n" +
            "and "
            + "c.id = u.gerente_id;";
    con = C.cb();
    pst = con.prepareStatement(sql);
    rs = pst.executeQuery();
    while (rs.next()) {
        u = new Unidade();
        u.setUnidade_id(rs.getInt("id"));
        u.setUnidade_nome(rs.getString("nome"));
        u.setEmpresa_id(rs.getInt("empresa_id"));
        u.setEmpresa_nome(rs.getString("empresa_nome"));
        u.setColaborador_id(rs.getInt("gerente_id"));
        u.setColaborador_nome(rs.getString("gerente_nome"));
        unidades.add(u);
    }
    C.db();
    return unidades;
    }
    
    public boolean inserir(Unidade u){
        try {
            sql = "INSERT INTO unidade (nome, empresa_id, gerente_id) VALUES (?, ?, ?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, u.getUnidade_nome());
            pst.setInt(2, u.getEmpresa_id());
            pst.setInt(3, u.getColaborador_id());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id){
        try {
            sql = "DELETE FROM unidade WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean atualizar(Unidade u){
        try {
            sql = "UPDATE Unidade SET nome = ?, empresa_id = ?, gerente_id = ? WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, u.getUnidade_nome());
            pst.setInt(2, u.getEmpresa_id());
            pst.setInt(3, u.getEmpresa_id());
            pst.setInt(4, u.getUnidade_id());
            pst.executeUpdate();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UnidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
