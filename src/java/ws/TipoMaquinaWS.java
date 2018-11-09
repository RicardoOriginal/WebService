/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.TipoMaquinaDAO;
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
import modelo.TipoMaquina;

/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("tipoMaquina")
public class TipoMaquinaWS {

    @Context
    private UriInfo context;

    public TipoMaquinaWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "tipoMaquina";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        Gson g = new Gson();
        TipoMaquinaDAO dao = new TipoMaquinaDAO();
        List<TipoMaquina> tiposdeMaquinas = dao.mostrarTodos();
        return g.toJson(tiposdeMaquinas);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content){
        Gson g = new Gson();
        TipoMaquina t = (TipoMaquina) g.fromJson(content, TipoMaquina.class);
        TipoMaquinaDAO dao = new TipoMaquinaDAO();
        return dao.inserir(t);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletar/{id}")
    public boolean deletar(@PathParam("id")int id){
        TipoMaquinaDAO dao = new TipoMaquinaDAO();
        return dao.deletar(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content){
        Gson g = new Gson();
        TipoMaquina t = (TipoMaquina) g.fromJson(content, TipoMaquina.class);
        TipoMaquinaDAO dao = new TipoMaquinaDAO();
        return dao.atualizar(t);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
