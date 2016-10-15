/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.preguntas;
import GUI.JFInterfazAlumno;
import GUI.JFInterfazProfesor;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import java.util.ArrayList;

/**
 *
 * @author Mariano
 */
public class controllerEstudiante {

    private static ArrayList<String[]> examen = new ArrayList<String[]>();
  
    public static void setPregunta(String IdPregunta) {
        int numeroID = Integer.parseInt(IdPregunta);
        numeroID--;
        ArrayList<String> pregunta = new ArrayList<String>();
        pregunta = preguntas.mostrarPreguntasXID(IdPregunta);
        String[] listaEnunciado;
        String EnunciadoLista = (String)pregunta.get(1);
        listaEnunciado= EnunciadoLista.split("☺");

        switch (pregunta.get(9)) {
            case "1":
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(0, true);
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(1, false);
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(2, false);
                JFInterfazAlumno.txtExplicacion.setEnabled(true);
                JFInterfazAlumno.txtExplicacion.setEditable(false);
                JFInterfazAlumno.jTabbedPane1.setSelectedIndex(0);
                JFInterfazAlumno.panelPregunta11.txtPregunta.setText(pregunta.get(1));
                JFInterfazAlumno.panelPregunta11.txtA.setText(pregunta.get(2));
                JFInterfazAlumno.panelPregunta11.txtB.setText(pregunta.get(3));
                JFInterfazAlumno.panelPregunta11.txtC.setText(pregunta.get(4));
                JFInterfazAlumno.panelPregunta11.txtD.setText(pregunta.get(5));
                JFInterfazAlumno.txtExplicacion.setText(pregunta.get(7));
                JFInterfazAlumno.txtExplicacion.setVisible(false);
                break;
            case "2":
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(0, false);
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(1, true);
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(2, false);
                JFInterfazAlumno.txtExplicacion.setEnabled(true);
                JFInterfazAlumno.txtExplicacion.setEditable(false);
                JFInterfazAlumno.jTabbedPane1.setSelectedIndex(1);
                JFInterfazAlumno.panelPregunta21.txtPregunta.setText(listaEnunciado[0]);
                JFInterfazAlumno.panelPregunta21.txtPregunta1.setText(listaEnunciado[1]);
                JFInterfazAlumno.panelPregunta21.txtA.setText(pregunta.get(2));
                JFInterfazAlumno.panelPregunta21.txtB.setText(pregunta.get(3));
                JFInterfazAlumno.panelPregunta21.txtC.setText(pregunta.get(4));
                JFInterfazAlumno.panelPregunta21.txtD.setText(pregunta.get(5));
                JFInterfazAlumno.txtExplicacion.setText(pregunta.get(5));
                JFInterfazAlumno.txtExplicacion.setText(pregunta.get(7));
                JFInterfazAlumno.txtExplicacion.setVisible(false);                
                break;
            case "3":
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(0, false);
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(1, false);
                JFInterfazAlumno.jTabbedPane1.setEnabledAt(2, true);
                JFInterfazAlumno.txtExplicacion.setEnabled(true);
                JFInterfazAlumno.txtExplicacion.setEditable(false);
                JFInterfazAlumno.jTabbedPane1.setSelectedIndex(2);
                JFInterfazAlumno.panelPregunta31.txtPregunta.setText(listaEnunciado[0]);
                JFInterfazAlumno.panelPregunta31.txtSegundo.setText(listaEnunciado[1]);
                JFInterfazAlumno.panelPregunta31.txtA.setText(pregunta.get(2));
                JFInterfazAlumno.panelPregunta31.txtB.setText(pregunta.get(3));
                JFInterfazAlumno.panelPregunta31.txtC.setText(pregunta.get(4));
                JFInterfazAlumno.panelPregunta31.txtD.setText(pregunta.get(5));
                JFInterfazAlumno.txtExplicacion.setText(pregunta.get(5));
                JFInterfazAlumno.txtExplicacion.setText(pregunta.get(7));
                JFInterfazAlumno.txtExplicacion.setVisible(false);                
                break;
        }
        JFInterfazAlumno.labelSubtema.setText(pregunta.get(8));

        /*
         preguntaID.add((String) idXML.get(j));//ID  0
         preguntaID.add((String) enunciadoXML.get(j));1
         preguntaID.add(lista[0]);2
         preguntaID.add(lista[1]);3
         preguntaID.add(lista[2]);4
         preguntaID.add(lista[3]);5
         preguntaID.add((String) respuestaCorrectaXML.get(j));6
         preguntaID.add((String) explicacionXML.get(j));7
         preguntaID.add((String) subtemaXML.get(j));8
         preguntaID.add((String) tipoPreguntaXML.get(j));9
         */
    }
    public static void changeStateQuestion(String id, String state){
        int numero=Integer.parseInt(id);
        String arr1[] = {"id","subtema","correcta","Unanswered"};
        for(int i=0;i<getExamen().size();i++){
            arr1=getExamen().get(i);
            if(id.equalsIgnoreCase(arr1[0]) && 
                    !(arr1[3].equalsIgnoreCase("Help"))){
                arr1[3]=state;
                getExamen().remove(i);
                getExamen().add(i, arr1);
            }
        }
        
    }
    public static String calificar(){
        String temp[] = {"Subtema","0","0"};
        boolean inSubtemasTemp=false;
        String arr2[] = {"Subtema","total","acertadas"};
        String arr1[] = {"id","subtema","correcta","Unanswered"};
        ArrayList <String[]> respuestas=new ArrayList<>();
        ArrayList <String[]> subtemasTemp=new ArrayList<>();
        for (int i=0;i<getExamen().size();i++){//Crear sublista con temas
            temp=new String[3];
            arr1=getExamen().get(i).clone();
            for(int j=0;j<subtemasTemp.size();j++){
                temp[0]=subtemasTemp.get(j)[0];
                temp[1]=subtemasTemp.get(j)[1];
                temp[2]=subtemasTemp.get(j)[2];
                if(arr1[1].equalsIgnoreCase(temp[0])){
                    inSubtemasTemp=true;      
                }
            }
            if (inSubtemasTemp==false){
                temp[0]=arr1[1].toString();
                subtemasTemp.add(temp);
            }

            inSubtemasTemp=false;
        }//Crear sublista con temas.
        
        for(int i=0;i<subtemasTemp.size();i++){
            temp=new String[3];
            temp[0]=subtemasTemp.get(i)[0];
            temp[1]=subtemasTemp.get(i)[1];//total
            temp[2]=subtemasTemp.get(i)[2];//correctas
            
            int contSubtemas=0;
            int contCorrectas=0;
            System.out.println("size: "+getExamen().size());
            for(int j=0;j<getExamen().size();j++){
                arr1=new String[4];
                arr1[0]=getExamen().get(j)[0];//id
                arr1[1]=getExamen().get(j)[1];//subtema
                arr1[2]=getExamen().get(j)[2];//correcta
                arr1[3]=getExamen().get(j)[3];//respuesta
                System.out.println(getExamen().get(j)[3]);
                System.out.println("correc: "+arr1[2]);
                 System.out.println("{3}: "+arr1[3]);
                 System.out.println("{1}: "+arr1[1]);
                if(arr1[1].equalsIgnoreCase(temp[0])){
                    contSubtemas++;
                    if(arr1[2].equalsIgnoreCase(arr1[3])){
                        contCorrectas++;
                    }
                }
            }
            String contSubS=String.valueOf(contSubtemas);
            String contCorS=String.valueOf(contCorrectas);
            subtemasTemp.get(i)[1]=contSubS;
            subtemasTemp.get(i)[2]=contCorS;
            
        }
        String respuesta="";
        int totalPreguntas=0;
        int totalCorrectas=0;
        for(int i=0;i<subtemasTemp.size();i++){
            respuesta+="Subtema: "+subtemasTemp.get(i)[0]+"\n"
            +"Totales: "+subtemasTemp.get(i)[1]+"\n"+
            "Correctas: "+subtemasTemp.get(i)[2]+"\n-------\n";
            totalPreguntas+=Integer.parseInt(subtemasTemp.get(i)[1]);
            totalCorrectas+=Integer.parseInt(subtemasTemp.get(i)[2]);
        }
        double nota=((double)100/(double)totalPreguntas)*(double)totalCorrectas;
        
        
        respuesta+="De "+totalPreguntas+" preguntas \nObtuvo: "+totalCorrectas+
                " para una nota de: "+(float)nota;
        return respuesta;
    }
    public static void firstFillPreguntas() {//OK
        getExamen().clear();
        preguntas p=new preguntas();
        int maximo=p.getMajorID();
        //ArrayList <String> pregunta=new ArrayList<String>();
        //pregunta=preguntas.mostrarPreguntasXID(numeroPregunta);
        String numeroS="";
        String arr1[] = {"id","subtema","correcta","Unanswered"};
        ArrayList <String> pregunta=new ArrayList<String>();
        for(int i=0;i<maximo;i++){
            int valTemp=i+1;
            numeroS=String.valueOf(valTemp);
            pregunta=preguntas.mostrarPreguntasXID(numeroS);
            arr1[0]=numeroS;
            arr1[1]=pregunta.get(8);
            arr1[2]=pregunta.get(6);
            arr1[3]=pregunta.get(8);
            getExamen().add(arr1);
            arr1=new String[4];
        }
    }

    /**
     * @return the examen
     */
    public static ArrayList<String[]> getExamen() {
        return examen;
    }

    /**
     * @param examen the examen to set
     */
    public void setExamen(ArrayList<String[]> examen) {
        this.examen = examen;
    }

}
