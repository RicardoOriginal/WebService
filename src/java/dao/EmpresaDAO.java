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
import modelo.Empresa;

/**
 *
 * @author Ricardo
 */
public class EmpresaDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;
    
    public List<Empresa> mostrarTodos() throws ClassNotFoundException, SQLException{
        
        List<Empresa> empresas = new ArrayList<>();
        Empresa e = null;
        sql = "SELECT "
                + "e.id, "
                + "e.nome, "
                + "c.id, "
                + "c.nome\n" +
                "FROM "
                + "empresa e, "
                + "colaborador c\n" +
                "where "
                + "c.id = e.administrador_id;";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            e = new Empresa();
            e.setEmpresa_id(rs.getInt("e.id"));
            e.setEmpresa_nome(rs.getString("e.nome"));
            e.setColaborador_id(rs.getInt("c.id"));
            e.setColaborador_nome(rs.getString("c.nome"));
            empresas.add(e);
        }
        C.db();
        return empresas;
    }
    
    public boolean inserir(Empresa e){
        try {
            sql = "INSERT INTO empresa (nome, administrador_id) VALUES (?, ?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getEmpresa_nome());
            pst.setInt(2, e.getColaborador_id());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id){
        try {
            sql = "DELETE FROM empresa WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean atualizar(Empresa e){
        try {
            sql = "UPDATE empresa SET nome = ?, administrador_id = ? WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getEmpresa_nome());
            pst.setInt(2, e.getColaborador_id());
            pst.setInt(3, e.getEmpresa_id());
            pst.executeUpdate();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
