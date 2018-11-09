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
import modelo.Frente;
import modelo.Turno;
import util.DataAtual;

/**
 *
 * @author Ricardo
 */
public class TurnoDAO {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public List<Turno> mostrarTodos() throws ClassNotFoundException, SQLException {

        List<Turno> turnos = new ArrayList<>();
        Turno t = null;
        sql = "SELECT "
                + "t.id, "
                + "f.id, "
                + "f.nome, "
                + "DATE_FORMAT( t.data, '%d/%m/%Y' ) AS data, "
                + "t.turno, "
                + "c.id, "
                + "c.nome, "
                + "DATE_FORMAT( t.data_hora_atualizacao,'%d/%m/%Y %T') AS data_hora_atualizacao, "
                + "t.tema_dds, "
                + "t.producao, "
                + "t.chuva, "
                + "t.observacao\n" +
                "FROM "
                + "turno t, "
                + "frente f, "
                + "colaborador c\n" +
                "WHERE "
                + "f.id = t.frente_id\n" +
                "AND "
                + "c.id = t.colaborador_id\n" +
                "ORDER BY "
                + "t.data_hora_atualizacao DESC, t.turno DESC";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            t = new Turno();
            t.setTurno_id(rs.getInt("id"));
            t.setFrente_id(rs.getInt("f.id"));
            t.setFrente_nome(rs.getString("f.nome"));
            t.setData(rs.getString("data"));
            t.setTurno(rs.getInt("turno"));
            t.setColaborador_nome(rs.getString("c.nome"));
            t.setColaborador_id(rs.getInt("c.id"));
            t.setData_hora_atualizacao(rs.getString("data_hora_atualizacao"));
            t.setTemaDds(rs.getString("tema_dds"));
            t.setProducao(rs.getDouble("producao"));
            t.setChuva(rs.getDouble("chuva"));
            t.setObservacao(rs.getString("observacao"));
            turnos.add(t);
        }
        C.db();
        return turnos;
    }
    
    public List mostrarTodosA() throws ClassNotFoundException, SQLException {

        List lista = new ArrayList<>();
        Turno t = null;
        Frente f = null;
        Colaborador c = null;
        sql = "SELECT "
                + "t.id, "
                + "f.nome, "
                //                + "DATE_FORMAT( t.data, '%d/%m/%Y' ) AS data, "
                + "t.turno, "
                + "c.nome, "
                //                + "DATE_FORMAT( t.data_hora_atualizacao,'%d/%m/%Y %T') AS data_hora_atualizacao, "
                + "t.tema_dds, "
                + "t.producao, "
                + "t.chuva, "
                + "t.observacao "
                + "FROM turno t, colaborador c, frente f "
                + "WHERE c.id = t.id and f.id = t.id "
                + "ORDER BY id desc; ";
        con = C.cb();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            t = new Turno();
            f = new Frente();
            c = new Colaborador();
            t.setTurno_id(rs.getInt("t.id"));
            f.setFrente_nome(rs.getString("f.nome"));
//            t.setData(rs.getString("t.data"));
            t.setTurno(rs.getInt("t.turno"));
            c.setColaborador_nome(rs.getString("c.nome"));
//            t.setData_hora_atualizacao(rs.getString("t.data_hora_atualizacao"));
            t.setTemaDds(rs.getString("tema_dds"));
            t.setProducao(rs.getDouble("producao"));
            t.setChuva(rs.getDouble("chuva"));
            t.setObservacao(rs.getString("observacao"));
            lista.add(t);
        }
        C.db();
        return lista;
    }

    public boolean inserir(Turno t) {
        try {
            sql = "INSERT INTO turno (frente_id, data, turno, colaborador_id, "
                    + "tema_dds, producao, chuva, observacao) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, t.getFrente_id());
            pst.setString(2, t.getData());
            pst.setInt(3, t.getTurno());
            pst.setInt(4, t.getColaborador_id());
            pst.setString(5, t.getTemaDds());
            pst.setDouble(6, t.getProducao());
            pst.setDouble(7, t.getChuva());
            pst.setString(8, t.getObservacao());

            pst.execute();
            C.db();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean atualizar(Turno t) {
        try {
            sql = "UPDATE turno SET frente_id = ?, data = ?, turno = ?, colaborador_id = ?, "
                    + "tema_dds = ?, producao = ?, chuva = ?, observacao = ? WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, t.getFrente_id());
            pst.setString(2, t.getData());
            pst.setInt(3, t.getTurno());
            pst.setInt(4, t.getColaborador_id());
            pst.setString(5, t.getTemaDds());
            pst.setDouble(6, t.getProducao());
            pst.setDouble(7, t.getChuva());
            pst.setString(8, t.getObservacao());
            pst.setInt(9, t.getTurno_id());
            pst.executeUpdate();
            C.db();
            DataAtual dataFormatada = new DataAtual();
            System.out.println(dataFormatada+": "+"Registro de turno "+t.getTurno_id()+" atualizado");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int id) {
        System.out.println("delete chamado");
        try {
            sql = "DELETE FROM turno WHERE id = ?;";
            con = C.cb();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            C.db();
            System.out.println("true");
            return true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TurnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("false");
            return false;
        }
    }

}
