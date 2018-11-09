/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.EmpresaDAO;
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
import modelo.Empresa;

/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("empresa")
public class EmpresaWS {

    @Context
    private UriInfo context;

    public EmpresaWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Empresa";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        Gson g = new Gson();
        EmpresaDAO dao = new EmpresaDAO();
        List<Empresa> empresas = dao.mostrarTodos();
        System.out.println(g.toJson(empresas));
        return g.toJson(empresas);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content){
        Gson g = new Gson();
        Empresa e = (Empresa) g.fromJson(content, Empresa.class);
        
        EmpresaDAO dao = new EmpresaDAO();
        return dao.inserir(e);
    }
    
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletar/{id}")
    public boolean deletar(@PathParam("id")int id){
        EmpresaDAO dao = new EmpresaDAO();
        return dao.deletar(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content){
        Gson g = new Gson();
        Empresa e = (Empresa) g.fromJson(content, Empresa.class);
        
        EmpresaDAO dao = new EmpresaDAO();
        return dao.atualizar(e);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
