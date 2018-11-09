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
import modelo.Colaborador;
import util.DataAtual;

/**
 *
 * @author Ricardo
 */
public class ColaboradorDAO {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public List<Colaborador> mostrarTodos() throws ClassNotFoundException, SQLException {

        List<Colaborador> colaboradores = new ArrayList<>();
        Colaborador c = null;
        sql = "SELECT "
                + "c.id, "
                + "c.matricula, "
                + "c.nome, "
                + "c.senha, "
                + "c.ativo, "
                + "i.id, "
                + "i.cargo\n" +
                "FROM "
                + "colaborador c, "
                + "nivel i\n" +
                "WHERE i.id = c.nivel_id ORDER BY c.nome ASC;";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            c = new Colaborador();
            c.setColaborador_id(rs.getInt("id"));
            c.setMatricula(rs.getInt("matricula"));
            c.setColaborador_nome(rs.getString("nome"));
            c.setSenha(rs.getString("senha"));
            c.setAtivo(rs.getBoolean("ativo"));
            c.setNivel_id(rs.getInt("i.id"));
            c.setCargo(rs.getString("i.cargo"));
            colaboradores.add(c);
        }
        C.db();
        return colaboradores;
    }

    public List<Colaborador> mostrarTodosDesc() throws ClassNotFoundException, SQLException {

        List<Colaborador> colaboradores = new ArrayList<>();
        Colaborador c = null;
        sql = "SELECT * FROM colaborador ORDER BY id desc;";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            c = new Colaborador();
            c.setColaborador_id(rs.getInt("id"));
            c.setMatricula(rs.getInt("matricula"));
            c.setColaborador_nome(rs.getString("nome"));
            c.setSenha(rs.getString("senha"));
            c.setAtivo(rs.getBoolean("ativo"));
            c.setNivel_id(rs.getInt("nivel_id"));
            colaboradores.add(c);
        }
        C.db();
        return colaboradores;
    }

    public boolean inserir(Colaborador c) {
        try {
            sql = "INSERT INTO colaborador (matricula, nome, senha, ativo, nivel_id) "
                    + "VALUES(?, ?, ?, ?, ?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, c.getMatricula());
            pst.setString(2, c.getColaborador_nome());
            pst.setString(3, c.getSenha());
            pst.setBoolean(4, c.isAtivo());
            pst.setInt(5, c.getNivel_id());
            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Colaborador autenticar(int matricula, String senha) throws ClassNotFoundException, SQLException {
        Colaborador c = new Colaborador();
        sql = "SELECT * FROM colaborador WHERE matricula = ? AND senha = ?;";
        con = C.cb();
        pst = con.prepareStatement(sql);
        pst.setInt(1, matricula);
        pst.setString(2, senha);
        rs = pst.executeQuery();
        while (rs.next()) {
            c.setColaborador_id(rs.getInt("id"));
            c.setMatricula(rs.getInt("matricula"));
            c.setColaborador_nome(rs.getString("nome"));
            c.setSenha(rs.getString("senha"));
            c.setAtivo(rs.getBoolean("ativo"));
            c.setNivel_id(rs.getInt("nivel_id"));
        }
        C.db();
        DataAtual dataFormatada = new DataAtual();
        System.out.println(dataFormatada + ": " + c.getColaborador_nome() + " fez o login no sistema");
        return c;
    }

    public boolean deletar(int id) {
        try {
            sql = "DELETE FROM colaborador WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            DataAtual dataFormatada = new DataAtual();
            System.out.println(dataFormatada + ": " + "Colaborador: ID: " + id + " deletado");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ColaboradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            DataAtual dataFormatada = new DataAtual();
            System.out.println(dataFormatada + ": " + "Colaborador: ID: " + id + " nao deletado");
            return false;
        }
    }

    public boolean atualizar(Colaborador c) {
        try {
            sql = "UPDATE colaborador "
                    + "SET "
                    + "matricula = ?, "
                    + "nome = ?, "
                    + "senha = ?, "
                    + "ativo = ?, "
                    + "nivel_id = ? "
                    + "WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, c.getMatricula());
            pst.setString(2, c.getColaborador_nome());
            pst.setString(3, c.getSenha());
            pst.setBoolean(4, c.isAtivo());
            pst.setInt(5, c.getNivel_id());
            pst.setInt(6, c.getColaborador_id());
            pst.executeUpdate();
            C.db();
            DataAtual dataFormatada = new DataAtual();
            System.out.println(dataFormatada+": "+"Usuario "+c.getColaborador_nome()+" atualizado");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            DataAtual dataFormatada = new DataAtual();
            System.out.println(dataFormatada +": " +ex.getMessage());
            return false;
        }
    }
}
