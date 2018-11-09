/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.EmpresaDAO;
import dao.NivelDAO;
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
import modelo.Nivel;

/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("nivel")
public class NivelWS {

    @Context
    private UriInfo context;

    public NivelWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "nivel";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        Gson g = new Gson();
        NivelDAO dao = new NivelDAO();
        List<Nivel> niveis = dao.mostrarTodos();
        return g.toJson(niveis);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content){
        Gson g = new Gson();
        Nivel n = (Nivel) g.fromJson(content, Nivel.class);
        
        NivelDAO dao = new NivelDAO();
        return dao.inserir(n);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletar/{id}")
    public boolean deletar(@PathParam("id")int id){
        NivelDAO dao = new NivelDAO();
        return dao.deletar(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content){
        Gson g = new Gson();
        Nivel n = (Nivel) g.fromJson(content, Nivel.class);
        
        NivelDAO dao = new NivelDAO();
        return dao.atualizar(n);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
