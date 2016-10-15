/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author pc
 */
import java.io.File;
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
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class  subTemas {
       
       private String  subTema;

    
    public static void guardarsubTema(
            String subTemaInsertado) 
            
            throws Exception{
        
        String nombreXML = "wsock33";
        ArrayList subTemaXML = new ArrayList();

        try{
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock33.xml"),"wsock33.xml");
            DocumentBuilderFactory fábricaCreadorDocumento2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento2 = fábricaCreadorDocumento2.newDocumentBuilder();
            Document documento2 = creadorDocumento2.parse("wsock33.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento2.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "subTema"
            NodeList listasubTemas = raiz.getElementsByTagName("subTema");
            
            for(int i=0; i<listasubTemas.getLength(); i++) {   
                
                Node preguntas = listasubTemas.item(i);

                //Obtener la lista de los datos que contiene ese subTema
                NodeList datossubTema = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene ese subTema
                for(int j=0; j<datossubTema.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datossubTema.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                             subTemaXML.add(datoContenido.getNodeValue());
                        }
                           
                            
                    }
                }
    
            }
            }
            catch (Exception e) { System.out.println("Error Corregido");  }
            finally  {
            
            
            

                

            subTemaXML.add(subTemaInsertado);


        
            generarDocumento(
            nombreXML, 
            subTemaXML
            );

        
        
    }}
    
    
    public static void generarDocumento(
            String nombreXML,
            ArrayList<String> subtema

            
            
            
   ) throws Exception{
 
        if(
           subtema.isEmpty() 
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
            //Por cada subTema creamos una pregunta que contendra los valores
            for(int i=0; i<subtema.size();i++){
                
                //Nodo subtema
                Element raizSubTemaNodo = documento.createElement("subTema"); 
              
                // llave nombre
                Element subTemaNodo = documento.createElement("nombre"); 
                Text subTemaNodoValor = documento.createTextNode(subtema.get(i));
                subTemaNodo.appendChild(subTemaNodoValor); // Agrega el valor que le corresponde



                raizSubTemaNodo.appendChild(subTemaNodo);
                
                
                //se agregan las preguntas a la raiz
                raiz.appendChild(raizSubTemaNodo); //insertamos a la raiz del documento
            }                
            
            //Se crea el XML
            Source source = new DOMSource(documento);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(nombreXML+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
        Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock33.xml"),"wsock33.xml");
    }
    
    
   
    public static String mostrarsubTemas() {
        String nombreXML = "wsock33";
        ArrayList subTemaXML = new ArrayList();
        String CadenaSubtema = "";
        
        try{
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock33.xml"),"wsock33.xml");
            DocumentBuilderFactory fábricaCreadorDocumento2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento2 = fábricaCreadorDocumento2.newDocumentBuilder();
            Document documento2 = creadorDocumento2.parse("wsock33.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento2.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "subTema"
            NodeList listasubTemas = raiz.getElementsByTagName("subTema");

            for(int i=0; i<listasubTemas.getLength(); i++) {   
                //Obtener de la lista un subTema tras otro
                Node preguntas = listasubTemas.item(i);

                //Obtener la lista de los datos que contiene ese subTema
                NodeList datossubTema = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene ese subTema
                for(int j=0; j<datossubTema.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datossubTema.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                             subTemaXML.add(datoContenido.getNodeValue());
                        }
                           
                            
                    }
                }
    
            }

        for(int j=0; j<subTemaXML.size();j++)
        {
            
            CadenaSubtema=CadenaSubtema+subTemaXML.get(j)+"\n";
        
        }
    
        Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock33.xml"),"wsock33.xml");
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

    
    return CadenaSubtema;
    }
    
      public static String[] listSubTemas() {
        String nombreXML = "wsock33";
        List<String> subTemaXML = new ArrayList<String>();
        String[] CadenaSubtema = null;
        
        try{
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock33.xml"),"wsock33.xml");
            DocumentBuilderFactory fábricaCreadorDocumento2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento2 = fábricaCreadorDocumento2.newDocumentBuilder();
            Document documento2 = creadorDocumento2.parse("wsock33.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento2.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "subTema"
            NodeList listasubTemas = raiz.getElementsByTagName("subTema");

            for(int i=0; i<listasubTemas.getLength(); i++) {   
                //Obtener de la lista un subTema tras otro
                Node preguntas = listasubTemas.item(i);

                //Obtener la lista de los datos que contiene ese subTema
                NodeList datossubTema = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene ese subTema
                for(int j=0; j<datossubTema.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datossubTema.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                             subTemaXML.add(datoContenido.getNodeValue());
                        }
                           
                            
                    }
                }
    
            }
        
        CadenaSubtema = subTemaXML.toArray(new String[subTemaXML.size()]);
        
        Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock33.xml"),"wsock33.xml");
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

    
    return CadenaSubtema;
    }  
    
    public static void eliminarsubTemas(String subTema) throws Exception {
        String nombreXML = "wsock33";
        ArrayList subTemaXML = new ArrayList();
        
       try{
            Seguridad.EscrituraDesencriptada(Seguridad.Lecura("wsock33.xml"),"wsock33.xml");
            DocumentBuilderFactory fábricaCreadorDocumento2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento2 = fábricaCreadorDocumento2.newDocumentBuilder();
            Document documento2 = creadorDocumento2.parse("wsock33.xml");
            //Obtener el elemento raíz del documento
            Element raiz = documento2.getDocumentElement();

            //Obtener la lista de nodos que tienen etiqueta "subTema"
            NodeList listasubTemas = raiz.getElementsByTagName("subTema");
            //Recorrer la lista de empleados
            for(int i=0; i<listasubTemas.getLength(); i++) {   
                //Obtener de la lista un empleado tras otro
                Node preguntas = listasubTemas.item(i);

                //Obtener la lista de los datos que contiene ese subTema
                NodeList datossubTema = preguntas.getChildNodes();
                //Recorrer la lista de los datos que contiene ese subTema
                for(int j=0; j<datossubTema.getLength(); j++) {
                    //Obtener de la lista de datos un dato tras otro
                    Node dato = datossubTema.item(j);

                    //Comprobar que el dato se trata de un nodo de tipo Element
                    if(dato.getNodeType()==Node.ELEMENT_NODE) {
                        Node datoContenido = dato.getFirstChild();
                        //Mostrar el nombre del tipo de dato
                        if(datoContenido!=null && datoContenido.getNodeType()==Node.TEXT_NODE)
                        {
                             subTemaXML.add(datoContenido.getNodeValue());
                        }
                           
                            
                    }
                }
    
            }
        
        if(subTemaXML.size()>1){
        for(int j=0; j<subTemaXML.size();j++)
        {
            if ((subTemaXML.get(j)).equals(subTema))
            {

                subTemaXML.remove(j);
                
            
            }
        
        }
        

            generarDocumento(
            nombreXML, 
            subTemaXML);
           Seguridad.EscrituraEncriptada(Seguridad.Lecura("wsock33.xml"),"wsock33.xml");
        
        }

         else{
            File archivo=new File("wsock33");
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
    

}