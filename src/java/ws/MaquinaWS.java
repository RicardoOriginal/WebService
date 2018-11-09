/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.MaquinaDAO;
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
import modelo.Maquina;

/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("maquina")
public class MaquinaWS {

    @Context
    private UriInfo context;

    public MaquinaWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Maquina";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        Gson g = new Gson();
        MaquinaDAO dao = new MaquinaDAO();
        List<Maquina> maquinas = dao.mostrarTodos();
        return g.toJson(maquinas);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content){
        Gson g = new Gson();
        Maquina m = (Maquina) g.fromJson(content, Maquina.class);
        
        MaquinaDAO dao = new MaquinaDAO();
        return dao.inserir(m);
    }
    
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletar/{id}")
    public boolean deletar(@PathParam("id")int id){
        MaquinaDAO dao = new MaquinaDAO();
        return dao.deletar(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content){
        Gson g = new Gson();
        Maquina m = (Maquina) g.fromJson(content, Maquina.class);
        MaquinaDAO dao = new MaquinaDAO();
        return dao.atualizar(m);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
