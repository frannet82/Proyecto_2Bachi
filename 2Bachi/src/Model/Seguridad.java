/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author pc
 */
public class Seguridad {
    
    private SecretKey key;       
    private Cipher cipher;  
    private String algoritmo= "AES";
    private int keysize=16;

    
 /*
    * Crea la Llave para encriptar/desencriptar
 */
    public Seguridad() {
        this.key = new SecretKeySpec( Arrays.copyOf("hola".getBytes(), keysize ) , algoritmo );
    }



   public static String Lecura(String Documento) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
 
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (Documento);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         if((linea=br.readLine())!=null)
            return linea;
      
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el archivo
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
   return "";
   }

    
 

public static void EscrituraEncriptada(String linea, String Documento)
{
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            Seguridad sec = new Seguridad();
            fichero = new FileWriter(Documento);
            pw = new PrintWriter(fichero);
            pw.println(sec.encriptar(linea));
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }

public static void EscrituraDesencriptada(String linea, String Documento)
{
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            Seguridad sec = new Seguridad();
            fichero = new FileWriter(Documento);
            pw = new PrintWriter(fichero);
            pw.println(sec.desencriptar(linea));
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }


    public String encriptar( String texto ){
        String value="";
        try {
            cipher = Cipher.getInstance( algoritmo );             
            cipher.init( Cipher.ENCRYPT_MODE, key );             
            byte[] textobytes = texto.getBytes();
            byte[] cipherbytes = cipher.doFinal( textobytes );
            value = DatatypeConverter.printBase64Binary( cipherbytes );
        } catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchPaddingException ex) {
            System.err.println( ex.getMessage() );
        } catch (InvalidKeyException ex) {
            System.err.println( ex.getMessage() );
        } catch (IllegalBlockSizeException ex) {
            System.err.println( ex.getMessage() );
        } catch (BadPaddingException ex) {
            System.err.println( ex.getMessage() );
        }
        return value;
    }

     
    public String desencriptar( String texto ){
        String str="";        
        try {
            byte[] value = DatatypeConverter.parseBase64Binary(texto);                 
            cipher = Cipher.getInstance( algoritmo );            
            cipher.init( Cipher.DECRYPT_MODE, key );
            byte[] cipherbytes = cipher.doFinal( value );
            str = new String( cipherbytes );                                  
        } catch (InvalidKeyException ex) {
            System.err.println( ex.getMessage() );
        }  catch (IllegalBlockSizeException ex) {
            System.err.println( ex.getMessage() );
        } catch (BadPaddingException ex) {
            System.err.println( ex.getMessage() );            
        }catch (NoSuchAlgorithmException ex) {
            System.err.println( ex.getMessage() );
        } catch (NoSuchPaddingException ex) {
            System.err.println( ex.getMessage() );
        }
        return str;
    }
}
