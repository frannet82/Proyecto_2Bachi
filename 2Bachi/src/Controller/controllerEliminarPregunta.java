/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.preguntas;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class controllerEliminarPregunta {
    
    public String  mostrarPreguntas() throws Exception
    {
        return (preguntas.mostrarPreguntas(""));
    }
    
    public boolean EliminarPreguntas(String id) throws Exception
    {
        preguntas.eliminarPreguntas(id);
        return true;
    }
    
}
