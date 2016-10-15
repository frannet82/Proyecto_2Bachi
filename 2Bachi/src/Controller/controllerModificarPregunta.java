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
public class controllerModificarPregunta {
    
        public boolean ModificarPregunta(ArrayList<String> preguntaTipo) throws Exception
    {
        preguntas.modificarPreguntas(preguntaTipo.get(0),
                preguntaTipo.get(1),
                preguntaTipo.get(2),
                preguntaTipo.get(3),
                preguntaTipo.get(4),
                preguntaTipo.get(5),
                preguntaTipo.get(6));
        
                
        return true;
    }
    
    public String tipoPregunta(String ID) throws Exception
    {
                
        return preguntas.tipoPregunta(ID);
    }   
        
        
}
