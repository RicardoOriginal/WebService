/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.ColaboradorDAO;
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
import modelo.Colaborador;

/**
 * REST Web Service
 *
 * @author Ricardo
 */
@Path("colaborador")
public class ColaboradorWS {

    @Context
    private UriInfo context;

    public ColaboradorWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Colaborador";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodos")
    public String mostrarTodos() throws ClassNotFoundException, SQLException {
        System.out.println("Um usuario solicitou a listagem de colaboradores");
        Gson g = new Gson();
        ColaboradorDAO dao = new ColaboradorDAO();
        List<Colaborador> colaboradores = dao.mostrarTodos();
        return g.toJson(colaboradores);
    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("mostrarNomes")
//    public String mostrarNomes() throws ClassNotFoundException, SQLException {
//        System.out.println("Um usuario solicitou a listagem de colaboradores");
//        Gson g = new Gson();
//        ColaboradorDAO dao = new ColaboradorDAO();
//        List<Colaborador> colaboradores = dao.mostrarNomes();
//        return g.toJson(colaboradores);
//    }
//    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mostrarTodosDesc")
    public String mostrarTodosDesc() throws ClassNotFoundException, SQLException {
        System.out.println("Um usuario solicitou a listagem de colaboradores");
        Gson g = new Gson();
        ColaboradorDAO dao = new ColaboradorDAO();
        List<Colaborador> colaboradores = dao.mostrarTodosDesc();
        return g.toJson(colaboradores);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir")
    public boolean inserir(String content){
        System.out.println("Um usuario solicitou inserção de colaborador");
        Gson g = new Gson();
        Colaborador c = (Colaborador) g.fromJson(content, Colaborador.class);
        ColaboradorDAO dao = new ColaboradorDAO();
        return dao.inserir(c);
    }
    
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletar/{id}")
    public Boolean deletar(@PathParam("id")int id){
        System.out.println("Um usuario solicitou a deleção um de colaborador");
        ColaboradorDAO dao = new ColaboradorDAO();
        return dao.deletar(id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("autenticar/{matricula}/{senha}")
    public String autenticar(@PathParam("matricula") int matricula, @PathParam("senha") String senha) throws ClassNotFoundException, SQLException{
        System.out.println("Um usuario solicitou acesso");
        ColaboradorDAO dao = new ColaboradorDAO();
        Gson g = new Gson();
        Colaborador c = dao.autenticar(matricula, senha);
        return g.toJson(c);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("atualizar")
    public boolean atualizar(String content){
        System.out.println("Um usuario solicitou a atualização de um colaborador");
        Gson g = new Gson();
        Colaborador c = (Colaborador) g.fromJson(content, Colaborador.class);
        ColaboradorDAO dao = new ColaboradorDAO();
        return dao.atualizar(c);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
