/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.UnidadeDAO;
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
import modelo.Unidade;

/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("unidade")
public class UnidadeWS {

    @Context
    private UriInfo context;

    public UnidadeWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Unidade";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        Gson g = new Gson();
        UnidadeDAO dao = new UnidadeDAO();
        List<Unidade> unidades = dao.mostrarTodos();
        System.out.println(g.toJson(unidades));
        return g.toJson(unidades);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content){
        Gson g = new Gson();
        Unidade u = (Unidade) g.fromJson(content, Unidade.class);
        UnidadeDAO dao = new UnidadeDAO();
        return dao.inserir(u);
    }
    
    @GET
    @Path("deletar/{id}")
    public boolean deletar(@PathParam("id")int id){
        UnidadeDAO dao = new UnidadeDAO();
        return dao.deletar(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content){
        Gson g = new Gson();
        Unidade u = (Unidade) g.fromJson(content, Unidade.class);
        UnidadeDAO dao = new UnidadeDAO();
        return dao.atualizar(u);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
