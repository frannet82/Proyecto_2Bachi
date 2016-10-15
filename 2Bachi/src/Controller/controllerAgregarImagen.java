/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.preguntas;
import java.io.File;

/**
 *
 * @author pc
 */
public class controllerAgregarImagen {
    
    public byte[] aperturaImagen(File archivo)
    {
        return preguntas.AbrirAImagen(archivo);
    }
    
    /*
    public boolean almacenarImagen(File archivo,byte[] bytesImg)
    {
      return preguntas.GuardarAImagen(archivo,bytesImg);
    }
    */
    
 
    
}
