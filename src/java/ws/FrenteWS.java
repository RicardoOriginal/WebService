/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.FrenteDAO;
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
import modelo.Frente;
/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("frente")
public class FrenteWS {

    @Context
    private UriInfo context;

    public FrenteWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Frente";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        System.out.println("Um usuario solicitou a listagem de frentes");
        Gson g = new Gson();
        FrenteDAO dao = new FrenteDAO();
        List<Frente> frentes = dao.mostrarTodos();
        return g.toJson(frentes);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodosA")
    public String mostrarTodosA() throws ClassNotFoundException, SQLException {
        System.out.println("Um usuario solicitou a listagem de frentes");
        Gson g = new Gson();
        FrenteDAO dao = new FrenteDAO();
        List<Frente> frentes = dao.mostrarTodos();
        return g.toJson(frentes);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content) {
        System.out.println("Um usuario solicitou inserção de frente");

        Gson g = new Gson();
        Frente f = (Frente) g.fromJson(content, Frente.class);

        FrenteDAO dao = new FrenteDAO();
        return dao.inserir(f);
    }

    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletar/{id}")
    public boolean deletar(@PathParam("id") int id) {
        System.out.println("Um usuario solicitou deleção de frente");
        FrenteDAO dao = new FrenteDAO();
        return dao.deletar(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content) {
        System.out.println("Um usuario solicitou atualização de frente");
        Gson g = new Gson();
        Frente f = (Frente) g.fromJson(content, Frente.class);

        FrenteDAO dao = new FrenteDAO();
        return dao.atualizar(f);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
