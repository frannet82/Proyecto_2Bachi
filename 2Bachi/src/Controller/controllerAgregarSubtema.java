/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.subTemas;
 
/**
 * 
 * @author pc
 */
public class controllerAgregarSubtema {
    
    public boolean AgregarSubtema(String subtema) throws Exception
    {
        subTemas.guardarsubTema(subtema);
        return true;
    }
   
    public String MostrarSubtema() throws Exception
    {
        return subTemas.mostrarsubTemas();
    }
    
     public String[] lista() throws Exception
    {
        if (subTemas.listSubTemas().equals(null))
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            return strings;
        }
        else{
            return subTemas.listSubTemas();
            }
    }   
    
    
}
