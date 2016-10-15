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
public class controllerAgregarPregunta {
    public boolean AgregarPregunta(ArrayList<String> preguntaTipo) throws Exception
    {
        preguntas.guardarPregunta(preguntaTipo.get(0),
                preguntaTipo.get(1),
                preguntaTipo.get(2),
                preguntaTipo.get(3),
                preguntaTipo.get(4),
                preguntaTipo.get(5));
        
                
        return true;
    }
    
    
}
