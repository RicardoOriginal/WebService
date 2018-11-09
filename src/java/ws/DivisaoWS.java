/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.DivisaoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Divisao;

/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("divisao")
public class DivisaoWS {

    @Context
    private UriInfo context;

    public DivisaoWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "divisao";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        System.out.println("Usuario solicitou lista de divisoes");
        Gson g = new Gson();
        DivisaoDAO dao = new DivisaoDAO();
        List<Divisao> divisaos = dao.mostrarTodos();
        return g.toJson(divisaos);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content) {
        System.out.println("Usuario solicitou inserção de uma divisao");
        Gson g = new Gson();
        Divisao d = (Divisao) g.fromJson(content, Divisao.class);
        DivisaoDAO dao = new DivisaoDAO();
        return dao.inserir(d);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("deletar/{id}")
    public boolean deletar(@PathParam("id") int id) {
        System.out.println("Usuario solicitou deleção de uma divisão");
        DivisaoDAO dao = new DivisaoDAO();
        return dao.deletar(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content) {
        System.out.println("Usuario solicitou atualização de uma divisão");
        Gson g = new Gson();
        Divisao d = (Divisao) g.fromJson(content, Divisao.class);
        DivisaoDAO dao = new DivisaoDAO();
        return dao.atualizar(d);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
