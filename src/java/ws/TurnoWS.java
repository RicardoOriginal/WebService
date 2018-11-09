/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.TurnoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Turno;

/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("turno")
public class TurnoWS {

    @Context
    private UriInfo context;

    public TurnoWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Turno";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        System.out.println("Um usuario solicitou a listagem de turnos");
        Gson g = new Gson();
        TurnoDAO dao = new TurnoDAO();
        List<Turno> turnos = dao.mostrarTodos();
        return g.toJson(turnos);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodosA")
    public String mostrarTodosA() throws ClassNotFoundException, SQLException {
        System.out.println("Um usuario solicitou a listagem de turnos atualizada");
        Gson g = new Gson();
        TurnoDAO dao = new TurnoDAO();
        List turnos = dao.mostrarTodosA();
        return g.toJson(turnos);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content) {
        Gson g = new Gson();
        Turno t = (Turno) g.fromJson(content, Turno.class);
        System.out.println("Um usuario solicitou a inserção de um turno");
        TurnoDAO dao = new TurnoDAO();
        return dao.inserir(t);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content) {
        Gson g = new Gson();
        Turno t = (Turno) g.fromJson(content, Turno.class);
        System.out.println("Um usuario solicitou a auteração de um turno");
        TurnoDAO dao = new TurnoDAO();
        return dao.atualizar(t);
    }

    @GET
    @Path("deletar/{id}")
    public boolean deletar(@PathParam("id")int id){
        TurnoDAO dao = new TurnoDAO();
        System.out.println(dao.deletar(id));
        return (dao.deletar(id));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
