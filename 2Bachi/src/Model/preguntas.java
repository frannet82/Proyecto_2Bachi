package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.Scanner;

public class  preguntas {
       
       private int id;
       private String  enunciado;
       private ArrayList<String> respuestas;
       private String respuestaCorrecta;
       private String explicacion;
       private String subtema;
       private String tipoPregunta;

    
    public static void guardarPregunta(
            String enunciadoInsertado, 
            String respuestaInsertado, 
            String respuestaCorrectaInsertado, 
            String explicacionInsertado, 
            String subtemaInsertado,
            String tipoPreguntaInsertado) 
            throws Exception{
        
        String nombreXML = "wsock32";
        ArrayList idXML = new ArrayList();
        ArrayList enunciadoXML = new ArrayList();
        ArrayList respuestaXML = new ArrayList();
        ArrayList respuestaCorrectaXML = new ArrayList();
        ArrayList explicacionXML = new ArrayList();
        ArrayList subtemaXML = new ArrayList();
        ArrayList tipoPreguntaXML = new ArrayList();
       
        try{
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            DocumentBuilderFactory fábricaCreadorDocumento2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento2 = fábricaCreadorDocumento2.newDocumentBuilder();
            Document documento2 = creadorDocumento2.parse("wsock32.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento2.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "id"
            NodeList listaPreguntas = raiz.getElementsByTagName("preguntas");
            //Recorrer la lista de Preguntas
            for(int i=0; i<listaPreguntas.getLength(); i++) {   
                //Obtener de la lista una Pregunta tras otro
                Node preguntas = listaPreguntas.item(i);

                //Obtener la lista de los datos que contiene esa pregunta
                NodeList datosPregunta = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene esa pregunta
                for(int j=0; j<datosPregunta.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosPregunta.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                        switch(dato.getNodeName()){
                             case "id":
                                 idXML.add(datoContenido.getNodeValue());
                                 
                                 break;
                            case "enunciado":
                                enunciadoXML.add(datoContenido.getNodeValue());
                                
                                 break;                                
                            case "respuesta":
                                respuestaXML.add(datoContenido.getNodeValue());
                                
                                 break;
                            case "respuestaCorrecta":
                                respuestaCorrectaXML.add(datoContenido.getNodeValue());
                                
                                 break; 
                            case "explicacion":
                                explicacionXML.add(datoContenido.getNodeValue());
                                
                                 break; 
                            case "subtema":
                                 subtemaXML.add(datoContenido.getNodeValue());
                                 
                                 break;

                            case "tipoPregunta":
                                 tipoPreguntaXML.add(datoContenido.getNodeValue());
                                 
                                 break;   
                                 
                        }
                        }
                           
                            
                    }
                }
    
            }
            }
            catch (Exception e) { System.out.println("Error Corregido");  }
            finally  {
            
            
            

                
            idXML.add(idMayor(idXML));
            enunciadoXML.add(enunciadoInsertado);
            respuestaXML.add(respuestaInsertado);
            respuestaCorrectaXML.add(respuestaCorrectaInsertado);
            explicacionXML.add(explicacionInsertado);
            subtemaXML.add(subtemaInsertado);
            tipoPreguntaXML.add(tipoPreguntaInsertado);

        
            generarDocumento(
            nombreXML, 
            idXML,
            enunciadoXML,
            respuestaXML,
            respuestaCorrectaXML,
            explicacionXML,
            subtemaXML,
            tipoPreguntaXML);

        
        
    }}
    
    
    public static void generarDocumento(
            String nombreXML, 
            ArrayList<String> id,
            ArrayList<String> enunciado,
            ArrayList<String> respuesta,
            ArrayList<String> respuestaCorrecta,
            ArrayList<String> explicacion,
            ArrayList<String> subtema,
            ArrayList<String> tipoPregunta
            
            
            
   ) throws Exception{
 
        if(
           id.isEmpty() && 
           enunciado.isEmpty() &&
           respuesta.isEmpty() &&
           respuestaCorrecta.isEmpty() &&                 
           explicacion.isEmpty() &&
           subtema.isEmpty() &&
           tipoPregunta.isEmpty()&&
           (+subtema.size()
            -id.size()
            +enunciado.size()
            -respuesta.size()
            +respuestaCorrecta.size()    
            -explicacion.size()
            + tipoPregunta.size()
            )== tipoPregunta.size()
           
           )
           {
            
            System.out.println("Formato incorrecto de arreglos");
            return;
        }
       
        else{
 
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            DOMImplementation implementation = creadorDocumento.getDOMImplementation();
            Document documento = implementation.createDocument(null, nombreXML, null);
            documento.setXmlVersion("1.0");
 
            //El nodo principal
            Element raiz = documento.getDocumentElement();
            //Por cada id creamos una pregunta que contendra los valores
            for(int i=0; i<id.size();i++){
                //Nodo pregunta
                Element preguntasNodo = documento.createElement("preguntas"); 
              
                // llave id
                Element idNodo = documento.createElement("id"); 
                Text idNodoValor = documento.createTextNode(id.get(i));
                idNodo.appendChild(idNodoValor); // Agrega el valor que le corresponde
                
                // llave enunciado
                Element enunciadoNodo = documento.createElement("enunciado"); 
                Text enunciadoNodoValor = documento.createTextNode(enunciado.get(i));
                enunciadoNodo.appendChild(enunciadoNodoValor); // Agrega el valor que le corresponde
                
                 // llave respuesta
                Element respuestaNodo = documento.createElement("respuesta"); 
                Text respuestaNodoValor = documento.createTextNode(respuesta.get(i));
                respuestaNodo.appendChild(respuestaNodoValor); // Agrega el valor que le corresponde
                
                // llave repuestaCorrecta
                Element respuestaCorrectaNodo = documento.createElement("respuestaCorrecta"); 
                Text respuestaCorrectaNodoValor = documento.createTextNode(respuestaCorrecta.get(i));
                respuestaCorrectaNodo.appendChild(respuestaCorrectaNodoValor); // Agrega el valor que le corresponde
                
                 // llave explicacion
                Element explicacionNodo = documento.createElement("explicacion"); 
                Text explicacionNodoValor = documento.createTextNode(explicacion.get(i));
                explicacionNodo.appendChild(explicacionNodoValor); // Agrega el valor que le corresponde
                
                 // llave subtema
                Element subtemaNodo = documento.createElement("subtema"); 
                Text subtemaNodoValor = documento.createTextNode(subtema.get(i));
                subtemaNodo.appendChild(subtemaNodoValor); // Agrega el valor que le corresponde
 
                // llave tipoPregunta
                Element tipoPreguntaNodo = documento.createElement("tipoPregunta"); 
                Text tipoPreguntaNodoValor = documento.createTextNode(tipoPregunta.get(i));
                tipoPreguntaNodo.appendChild(tipoPreguntaNodoValor); // Agrega el valor que le corresponde

                preguntasNodo.appendChild(idNodo);
                preguntasNodo.appendChild(enunciadoNodo);
                preguntasNodo.appendChild(respuestaNodo);
                preguntasNodo.appendChild(respuestaCorrectaNodo);               
                preguntasNodo.appendChild(explicacionNodo);
                preguntasNodo.appendChild(subtemaNodo); 
                preguntasNodo.appendChild(tipoPreguntaNodo); 
                
                
                //se agregan las preguntas a la raiz
                raiz.appendChild(preguntasNodo); //insertamos a la raiz del documento
            }                
            
            //Se crea el XML
            Source source = new DOMSource(documento);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(nombreXML+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
        Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
    }
    
    
   
    public static String mostrarPreguntas(String subtema) {
        String nombreXML = "wsock32";
        ArrayList idXML = new ArrayList();
        ArrayList enunciadoXML = new ArrayList();
        ArrayList respuestaXML = new ArrayList();
        ArrayList respuestaCorrectaXML = new ArrayList();
        ArrayList explicacionXML = new ArrayList();
        ArrayList subtemaXML = new ArrayList();
        ArrayList tipoPreguntaXML = new ArrayList();
        String preguntasCadena= "";
        String[] lista;
        String[] listaEnunciado;
        
        
    try {
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("wsock32.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "id"
            NodeList listaPreguntas = raiz.getElementsByTagName("preguntas");
            //Recorrer la lista de Preguntas
            for(int i=0; i<listaPreguntas.getLength(); i++) {   
                //Obtener de la lista una Pregunta tras otro
                Node preguntas = listaPreguntas.item(i);

                //Obtener la lista de los datos que contiene esa pregunta
                NodeList datosPregunta = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene esa pregunta
                for(int j=0; j<datosPregunta.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosPregunta.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                        switch(dato.getNodeName()){
                            
                             case "id":
                                 idXML.add(datoContenido.getNodeValue());
                                 break;
                            case "enunciado":
                                enunciadoXML.add(datoContenido.getNodeValue());
                                 break;                                
                            case "respuesta":
                                respuestaXML.add(datoContenido.getNodeValue());
                                 break;
                            case "respuestaCorrecta":
                                respuestaCorrectaXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "explicacion":
                                explicacionXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "subtema":
                                 subtemaXML.add(datoContenido.getNodeValue());
                                 break;    
                                 
                            case "tipoPregunta":
                                tipoPreguntaXML.add(datoContenido.getNodeValue());
                                break;    
                                 
                        }
                        }
                           
                            
                    }
                }
             
            }

        for(int j=0; j<subtemaXML.size();j++)
        {
            if ((subtemaXML.get(j)).equals(subtema))
            {
                
                        
                String respuestasLista =(String)respuestaXML.get(j);
                String EnunciadoLista = (String)enunciadoXML.get(j);
                lista=respuestasLista.split("☺");
                listaEnunciado= EnunciadoLista.split("☺");
                if (tipoPreguntaXML.get(j).equals("1"))
                {
                preguntasCadena= 
                "ID: "+preguntasCadena+ idXML.get(j)+"\n"+
                "Enunciado de la pregunta: "+enunciadoXML.get(j)+"\n"+
                "A) "+lista[0]+"\n"+
                "B) "+lista[1]+"\n"+
                "C) "+lista[2]+"\n"+
                "D) "+lista[3]+"\n"+
                "Respuesta Correcta: "+respuestaCorrectaXML.get(j)+"\n"+
                "Explicacion: "+explicacionXML.get(j)+"\n"+
                "subtema: "+ subtemaXML.get(j)+"\n"+
                "Tipo de pregunta: "+tipoPreguntaXML.get(j)+"\n"+
                "\n";
                }
                if (tipoPreguntaXML.get(j).equals("2"))
                {
                preguntasCadena= 
                "ID: "+preguntasCadena+ idXML.get(j)+"\n"+
                "Enunciado de la pregunta: "+listaEnunciado[0]+"\n"+
                listaEnunciado[1]+"\n"+
                "A) "+lista[0]+"\n"+
                "B) "+lista[1]+"\n"+
                "C) "+lista[2]+"\n"+
                "D) "+lista[3]+"\n"+
                "Respuesta Correcta: "+respuestaCorrectaXML.get(j)+"\n"+
                "Explicacion: "+explicacionXML.get(j)+"\n"+
                "subtema: "+ subtemaXML.get(j)+"\n"+
                "Tipo de pregunta: "+tipoPreguntaXML.get(j)+"\n"+
                "\n";
                }
                
                if (tipoPreguntaXML.get(j).equals("3"))
                {
                preguntasCadena=
                "ID: "+preguntasCadena+ idXML.get(j)+"\n"+
                "Enunciado de la pregunta: "+listaEnunciado[0]+"\n"+
                listaEnunciado[1]+"\n"+
                listaEnunciado[2]+"\n"+
                "A) "+lista[0]+"\n"+
                "B) "+lista[1]+"\n"+
                "C) "+lista[2]+"\n"+
                "D) "+lista[3]+"\n"+
                "Respuesta Correcta: "+respuestaCorrectaXML.get(j)+"\n"+
                "Explicacion: "+explicacionXML.get(j)+"\n"+
                "subtema: "+ subtemaXML.get(j)+"\n"+
                "Tipo de pregunta: "+tipoPreguntaXML.get(j)+"\n"+
                "\n";
                }                
                
            }
            else if (subtema.isEmpty())
            {
                String respuestasLista =(String)respuestaXML.get(j);
                String EnunciadoLista = (String)enunciadoXML.get(j);
                lista=respuestasLista.split("☺");
                listaEnunciado= EnunciadoLista.split("☺");
                if (tipoPreguntaXML.get(j).equals("1"))
                {
                preguntasCadena= 
                preguntasCadena+"ID: "+ idXML.get(j)+"\n"+
                "Enunciado de la pregunta: "+enunciadoXML.get(j)+"\n"+
                "A) "+lista[0]+"\n"+
                "B) "+lista[1]+"\n"+
                "C) "+lista[2]+"\n"+
                "D) "+lista[3]+"\n"+
                "Respuesta Correcta: "+respuestaCorrectaXML.get(j)+"\n"+
                "Explicacion: "+explicacionXML.get(j)+"\n"+
                "subtema: "+ subtemaXML.get(j)+"\n"+
                "Tipo de pregunta: "+tipoPreguntaXML.get(j)+"\n"+
                "\n";
                }
                else if (tipoPreguntaXML.get(j).equals("2"))
                {
                preguntasCadena= 
                preguntasCadena+"ID: "+ idXML.get(j)+"\n"+
                "Enunciado de la pregunta: "+
                "\n"+
                listaEnunciado[0]+"\n"+
                listaEnunciado[1]+"\n"+
                "\n"+
                "A) "+lista[0]+"\n"+
                "B) "+lista[1]+"\n"+
                "C) "+lista[2]+"\n"+
                "D) "+lista[3]+"\n"+
                "Respuesta Correcta: "+respuestaCorrectaXML.get(j)+"\n"+
                "Explicacion: "+explicacionXML.get(j)+"\n"+
                "subtema: "+ subtemaXML.get(j)+"\n"+
                "Tipo de pregunta: "+tipoPreguntaXML.get(j)+"\n"+
                "\n";
                }
                
                else if (tipoPreguntaXML.get(j).equals("3"))
                {
                preguntasCadena=
                preguntasCadena+"ID: "+ idXML.get(j)+"\n"+
                "Enunciado de la pregunta: "+
                "\n"+
                listaEnunciado[0]+"\n"+
                listaEnunciado[1]+"\n"+
                listaEnunciado[2]+"\n"+
                "\n"+
                "A) "+lista[0]+"\n"+
                "B) "+lista[1]+"\n"+
                "C) "+lista[2]+"\n"+
                "D) "+lista[3]+"\n"+
                "Respuesta Correcta: "+respuestaCorrectaXML.get(j)+"\n"+
                "Explicacion: "+explicacionXML.get(j)+"\n"+
                "subtema: "+ subtemaXML.get(j)+"\n"+
                "Tipo de pregunta: "+tipoPreguntaXML.get(j)+"\n"+
                "\n";
                }         
                
            }
        
        }
    
        Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        }
          catch(Throwable e) {
          }

   
    return preguntasCadena;}
 
    
        public static String tipoPregunta(String id) {
        String nombreXML = "wsock32";
        ArrayList idXML = new ArrayList();
        ArrayList enunciadoXML = new ArrayList();
        ArrayList respuestaXML = new ArrayList();
        ArrayList respuestaCorrectaXML = new ArrayList();
        ArrayList explicacionXML = new ArrayList();
        ArrayList subtemaXML = new ArrayList();
        ArrayList tipoPreguntaXML = new ArrayList();
        String tipoCadena= "";
        String[] lista;
        
        
    try {
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("wsock32.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "id"
            NodeList listaPreguntas = raiz.getElementsByTagName("preguntas");
            //Recorrer la lista de Preguntas
            for(int i=0; i<listaPreguntas.getLength(); i++) {   
                //Obtener de la lista una Pregunta tras otro
                Node preguntas = listaPreguntas.item(i);

                //Obtener la lista de los datos que contiene esa pregunta
                NodeList datosPregunta = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene esa pregunta
                for(int j=0; j<datosPregunta.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosPregunta.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                        switch(dato.getNodeName()){
                            
                             case "id":
                                 idXML.add(datoContenido.getNodeValue());
                                 break;
                            case "enunciado":
                                enunciadoXML.add(datoContenido.getNodeValue());
                                 break;                                
                            case "respuesta":
                                respuestaXML.add(datoContenido.getNodeValue());
                                 break;
                            case "respuestaCorrecta":
                                respuestaCorrectaXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "explicacion":
                                explicacionXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "subtema":
                                 subtemaXML.add(datoContenido.getNodeValue());
                                 break;    
                                 
                            case "tipoPregunta":
                                tipoPreguntaXML.add(datoContenido.getNodeValue());
                                break;    
                                 
                        }
                        }
                           
                            
                    }
                }
             
            }

        for(int j=0; j<idXML.size();j++)
        {
            if ((idXML.get(j)).equals(id))
            {
             tipoCadena = (String)(tipoPreguntaXML.get(j));
            }

        
        }
    
        Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        }
          catch(Throwable e) {
          }
     
    return tipoCadena;}
    
    
    public static ArrayList<ArrayList<String>> crearExamen() {
        String nombreXML = "wsock32";
        
        ArrayList<ArrayList<String>> examen = new ArrayList();
        
    try {
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("wsock32.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "id"
            NodeList listaPreguntas = raiz.getElementsByTagName("preguntas");
            //Recorrer la lista de Preguntas
            for(int i=0; i<listaPreguntas.getLength(); i++) {
                ArrayList<String> Preguntas = new ArrayList();
                //Obtener de la lista un Pregunta tras otro
                Node preguntas = listaPreguntas.item(i);

                //Obtener la lista de los datos que contiene esa pregunta
                NodeList datosPregunta = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene esa pregunta
                for(int j=0; j<datosPregunta.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosPregunta.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                            Preguntas.add(datoContenido.getNodeValue());
                        
                        }
                           
                            
                    }
                }
             
           examen.add(Preguntas);
           
            }
        
        Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        }

    return examen;}
    
    
    public static void eliminarPreguntas(String id) throws Exception {
        String nombreXML = "wsock32";
        ArrayList idXML = new ArrayList();
        ArrayList enunciadoXML = new ArrayList();
        ArrayList respuestaXML = new ArrayList();
        ArrayList respuestaCorrectaXML = new ArrayList();
        ArrayList explicacionXML = new ArrayList();
        ArrayList subtemaXML = new ArrayList();
        ArrayList tipoPreguntaXML = new ArrayList();
        
    try {
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("wsock32.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "id"
            NodeList listaPreguntas = raiz.getElementsByTagName("preguntas");
            //Recorrer la lista de Preguntas
            for(int i=0; i<listaPreguntas.getLength(); i++) {   
                //Obtener de la lista un Pregunta tras otro
                Node preguntas = listaPreguntas.item(i);

                //Obtener la lista de los datos que contiene esa pregunta
                NodeList datosPregunta = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene esa pregunta
                for(int j=0; j<datosPregunta.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosPregunta.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                        switch(dato.getNodeName()){
                             case "id":
                                 idXML.add(datoContenido.getNodeValue());
                                 break;
                            case "enunciado":
                                enunciadoXML.add(datoContenido.getNodeValue());
                                 break;                                
                            case "respuesta":
                                respuestaXML.add(datoContenido.getNodeValue());
                                 break;
                            case "respuestaCorrecta":
                                respuestaCorrectaXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "explicacion":
                                explicacionXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "subtema":
                                 subtemaXML.add(datoContenido.getNodeValue());
                                 break;    
                                 
                            case "tipoPregunta":
                                tipoPreguntaXML.add(datoContenido.getNodeValue());
                                break;                                 
                                 
                        }
                        }
                           
                            
                    }
                }
             
            }
        
        if(subtemaXML.size()>1){
        for(int j=0; j<subtemaXML.size();j++)
        {
            if ((idXML.get(j)).equals(id))
            {
                idXML.remove(j);
                enunciadoXML.remove(j);
                respuestaXML.remove(j);
                respuestaCorrectaXML.remove(j);
                explicacionXML.remove(j);
                subtemaXML.remove(j);
                tipoPreguntaXML.remove(j);
                
            
            }
        
        }
        
         for(int j=0; j<idXML.size();j++)
        {
            idXML.set(j, Integer.toString(j+1));
        
        }
            generarDocumento(
            nombreXML, 
            idXML,
            enunciadoXML,
            respuestaXML,
            respuestaCorrectaXML,
            explicacionXML,
            subtemaXML,
            tipoPreguntaXML);
           Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
        
        }
         else{
            File archivo=new File("wsock32");
            archivo.delete();      
             }
        
        

        
        System.out.print("Eliminado \n");
        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        }

    }
 
    
    public static void modificarPreguntas(String id, String enunciado, String respuesta, String respuestaCorrecta, String explicacion, String subtema, String tipoPregunta) throws Exception {
        String nombreXML = "wsock32";
        ArrayList idXML = new ArrayList();
        ArrayList enunciadoXML = new ArrayList();
        ArrayList respuestaXML = new ArrayList();
        ArrayList respuestaCorrectaXML = new ArrayList();
        ArrayList explicacionXML = new ArrayList();
        ArrayList subtemaXML = new ArrayList();
        ArrayList tipoPreguntaXML = new ArrayList();
        
    try {
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("wsock32.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "id"
            NodeList listaPreguntas = raiz.getElementsByTagName("preguntas");
            //Recorrer la lista de Preguntas
            for(int i=0; i<listaPreguntas.getLength(); i++) {   
                //Obtener de la lista una Pregunta tras otro
                Node preguntas = listaPreguntas.item(i);

                //Obtener la lista de los datos que contiene esa pregunta
                NodeList datosPregunta = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene esa pregunta
                for(int j=0; j<datosPregunta.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosPregunta.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                        switch(dato.getNodeName()){
                             case "id":
                                 idXML.add(datoContenido.getNodeValue());
                                 break;
                            case "enunciado":
                                enunciadoXML.add(datoContenido.getNodeValue());
                                 break;                                
                            case "respuesta":
                                respuestaXML.add(datoContenido.getNodeValue());
                                 break;
                            case "respuestaCorrecta":
                                respuestaCorrectaXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "explicacion":
                                explicacionXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "subtema":
                                 subtemaXML.add(datoContenido.getNodeValue());
                                 break;    
                                 
                            case "tipoPregunta":
                                tipoPreguntaXML.add(datoContenido.getNodeValue());
                                break;                                 
                                 
                        }
                        }
                           
                            
                    }
                }
             
            }

        for(int j=0; j<idXML.size();j++)
        {
            if ((idXML.get(j)).equals(id))
            {
              if (id.isEmpty()){}
              else{idXML.set(j, id);}
              
              if (enunciado.isEmpty()){}
              else{enunciadoXML.set(j, enunciado);}             
              
              if (respuesta.isEmpty()){}
              else{respuestaXML.set(j, respuesta);}               

              if (respuestaCorrecta.isEmpty()){}
              else{respuestaCorrectaXML.set(j, respuestaCorrecta);} 
              
              if (explicacion.isEmpty()){}
              else{explicacionXML.set(j, explicacion);}               
 
              if (subtema.isEmpty()){}
              else{subtemaXML.set(j, subtema);}               

              if (tipoPregunta.isEmpty()){}
              else{tipoPreguntaXML.set(j, tipoPregunta);}    

              
            }
        
        }
            generarDocumento(
            nombreXML, 
            idXML,
            enunciadoXML,
            respuestaXML,
            respuestaCorrectaXML,
            explicacionXML,
            subtemaXML,
            tipoPreguntaXML);
        
  
    
    
        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        }

    }
    
    //funcion que sirve para determinar el incremento del id
    public static String idMayor(ArrayList<String> idlista)
    {
        int cont;
        String Mayor = "0";
        
        if (idlista.size()==0)
        {
            return "1";
        
        }
        
        for(cont=0;cont<idlista.size();cont++)
        {
            if (cont==0)
            {
            Mayor=idlista.get(cont);
        
            }
            
            else if (Integer.parseInt(idlista.get(cont))> Integer.parseInt(Mayor))
            {
                Mayor= idlista.get(cont);
                
            }
            
        
        }
        
        return Integer.toString(Integer.parseInt(Mayor)+1);
    
    }
    
        /*Abrir una imagen*/
    public static byte[] AbrirAImagen(File archivo){
       FileInputStream entrada;
      
        byte[] bytesImg = new byte[1024*100];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(bytesImg);
        } catch (Exception e) {
        }
        return bytesImg;
    }
    /*
    //Guardar imagen
    public static boolean GuardarAImagen(File archivo, byte[] bytesImg){
        FileOutputStream salida;
        try {
            salida = new FileOutputStream(archivo);
            salida.write(bytesImg);
            return true;
        } catch (Exception e) {
            return false;
        }
   
    }
    */
        public static int getMajorID() {
        String nombreXML = "wsock32";
        ArrayList subTemaXML = new ArrayList();
        
        try{
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            DocumentBuilderFactory fábricaCreadorDocumento2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento2 = fábricaCreadorDocumento2.newDocumentBuilder();
            Document documento2 = creadorDocumento2.parse("wsock32.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento2.getDocumentElement();

            NodeList listID=raiz.getElementsByTagName("id");
    
            Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            return listID.getLength();
        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        }
          catch(Throwable e) {
          }

    
        return -1;
    }
        public static ArrayList <String> mostrarPreguntasXID(String ID) {
        String nombreXML = "wsock32";
        ArrayList idXML = new ArrayList();
        ArrayList enunciadoXML = new ArrayList();
        ArrayList respuestaXML = new ArrayList();
        ArrayList respuestaCorrectaXML = new ArrayList();
        ArrayList explicacionXML = new ArrayList();
        ArrayList subtemaXML = new ArrayList();
        ArrayList tipoPreguntaXML = new ArrayList();
        String preguntasCadena= "";
        String[] lista;
        ArrayList<String> preguntaID = new ArrayList<>();
        
    try {
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            Document documento = creadorDocumento.parse("wsock32.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "id"
            NodeList listaPreguntas = raiz.getElementsByTagName("preguntas");
            //Recorrer la lista de Preguntas
            for(int i=0; i<listaPreguntas.getLength(); i++) {   
                //Obtener de la lista una Pregunta tras otro
                Node preguntas = listaPreguntas.item(i);

                //Obtener la lista de los datos que contiene esa pregunta
                NodeList datosPregunta = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene esa pregunta
                for(int j=0; j<datosPregunta.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datosPregunta.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                        switch(dato.getNodeName()){
                            
                             case "id":
                                 idXML.add(datoContenido.getNodeValue());
                                 break;
                            case "enunciado":
                                enunciadoXML.add(datoContenido.getNodeValue());
                                 break;                                
                            case "respuesta":
                                respuestaXML.add(datoContenido.getNodeValue());
                                 break;
                            case "respuestaCorrecta":
                                respuestaCorrectaXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "explicacion":
                                explicacionXML.add(datoContenido.getNodeValue());
                                 break; 
                            case "subtema":
                                 subtemaXML.add(datoContenido.getNodeValue());
                                 break;    
                                 
                            case "tipoPregunta":
                                tipoPreguntaXML.add(datoContenido.getNodeValue());
                                break;    
                                 
                        }
                        }
                           
                            
                    }
                }
             
            }
        
        for(int j=0; j<idXML.size();j++)
        {
            if ((idXML.get(j)).equals(ID))
            {
                
                String respuestasLista =(String)respuestaXML.get(j);
                lista=respuestasLista.split("☺");
                preguntaID.add((String) idXML.get(j));//ID 0
                preguntaID.add((String) enunciadoXML.get(j));//1
                preguntaID.add(lista[0]);//2
                preguntaID.add(lista[1]);
                preguntaID.add(lista[2]);
                preguntaID.add(lista[3]);
                preguntaID.add((String) respuestaCorrectaXML.get(j));//6
                preguntaID.add((String) explicacionXML.get(j));
                preguntaID.add((String) subtemaXML.get(j));//8
                preguntaID.add((String) tipoPreguntaXML.get(j));
                preguntasCadena= 
                "ID: "+preguntasCadena+ idXML.get(j)+"\n"+
                "Enunciado de la pregunta: "+enunciadoXML.get(j)+"\n"+
                "A) "+lista[0]+"\n"+
                "B) "+lista[1]+"\n"+
                "C) "+lista[2]+"\n"+
                "D) "+lista[3]+"\n"+
                "Respuesta Correcta: "+respuestaCorrectaXML.get(j)+"\n"+
                "Explicacion: "+explicacionXML.get(j)+"\n"+
                "subtema: "+ subtemaXML.get(j)+"\n"+
                "Tipo de pregunta: "+tipoPreguntaXML.get(j)+"\n"+
                "\n";
                break;
            }
            
        
        }
    
        Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock32.xml"),"wsock32.xml");
        } catch (SAXException ex) {
            System.out.println("ERROR: El formato XML del fichero no es correcto\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("ERROR: Se ha producido un error el leer el fichero\n"+ex.getMessage());
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
            ex.printStackTrace();
        }
          catch(Throwable e) {
          }

   
    return preguntaID;
    }
    public String getSubtema() {
        return subtema;
    }

    public void setSubtema(String subtema) {
        this.subtema = subtema;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<String> respuestas) {
        this.respuestas = respuestas;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }
    
    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

}
