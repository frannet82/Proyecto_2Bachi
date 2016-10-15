/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Examen {
    
    
    
    public static void Iniciar(){
        Prueba(preguntas.crearExamen());

    }
    
    
   
    public static void Prueba(ArrayList<ArrayList<String>> Preguntas){
        int contador;
        ArrayList<String> correctoIncorrecto = new ArrayList();
        
        System.out.print("Examen ");
        
        for(contador=0;contador<Preguntas.size();contador++)
        {
           System.out.print("Pregunta "+Integer.toString(contador+1)+": "+"\n");
           System.out.print("\n");
           System.out.print(Preguntas.get(contador).get(1)+"\n");//Enunciado
           System.out.print("\n");
           System.out.print("Seleccione una de las siguientes respuestas :\n");
           System.out.print("\n");
           String[] respuestasArray = (Preguntas.get(contador).get(2)).split("☺");
           for (int i = 0; i < respuestasArray.length; i++) 
           {
               System.out.println(Integer.toString(i)+")"+respuestasArray[i]+"\n");
           }
           
           System.out.print("Escriba su respuesta: ");
           Scanner entradaEscaner = new Scanner (System.in);
           String entradaTeclado =  entradaEscaner.nextLine();
           if (entradaTeclado.equals(Preguntas.get(contador).get(3)))
           {
               correctoIncorrecto.add("Correcta");
         
           }
           else
           {
               correctoIncorrecto.add("Incorrecta");
           }
           
           
           
           //System.out.print(Preguntas.get(contador).get(2));//Respuestas
           //System.out.print(Preguntas.get(contador).get(3)+"\n");//RespuestaCorrecta
           //System.out.print(Preguntas.get(contador).get(4)+"\n");//Explicacion
           //System.out.print(Preguntas.get(contador).get(5)+"\n");//Explicacion
        
        
        }
        
        
        
        for(contador=0;contador<Preguntas.size();contador++)
        {
           System.out.print("Pregunta "+Integer.toString(contador+1)+": "+"\n");
           System.out.print("\n");
           System.out.print(Preguntas.get(contador).get(1)+"\n");//Enunciado
           System.out.print("\n");
           System.out.print("Respuesta correcta :\n");
           System.out.print(Preguntas.get(contador).get(3)+"\n");
           System.out.print("Explicación :\n");
           System.out.print(Preguntas.get(contador).get(4)+"\n");
           System.out.print("Su respuesta fue :\n");
           System.out.print(correctoIncorrecto.get(contador)+"\n");
           System.out.print("\n");
        
           //System.out.print(Preguntas.get(contador).get(2));//Respuestas
           //System.out.print(Preguntas.get(contador).get(3)+"\n");//RespuestaCorrecta
           //System.out.print(Preguntas.get(contador).get(4)+"\n");//Explicacion
        }
        
        
        
        
    }
}
