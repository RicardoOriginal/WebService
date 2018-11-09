/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Ricardo
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.ColaboradorWS.class);
        resources.add(ws.DivisaoWS.class);
        resources.add(ws.EmpresaWS.class);
        resources.add(ws.FrenteWS.class);
        resources.add(ws.MaquinaWS.class);
        resources.add(ws.NivelWS.class);
        resources.add(ws.TipoMaquinaWS.class);
        resources.add(ws.TurnoWS.class);
        resources.add(ws.UnidadeWS.class);
    }
    
}
