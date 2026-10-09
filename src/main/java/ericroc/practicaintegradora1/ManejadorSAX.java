/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ericroc.practicaintegradora1;

import org.xml.sax.Attributes;
import java.util.HashSet;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Eric
 */
public class ManejadorSAX extends DefaultHandler{
    
    //Aqui se guarda las categorias únicas que pide el ejercicio
    public HashSet<String> categoriasUnicas = new HashSet<>();
    
    //Una "bandera" (flag) para saber si estamos leyendo una categoria
    private boolean esCategoria = false;
    
    //Evento 1: SAX detecta que se abre una etiqueta
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        //TODO: Si el nombre de la etiqueta (qName) es "categoriaMant", ponemos esCategortia a true
        if(qName.equalsIgnoreCase("categoriaMant")){
            esCategoria = true;
        }
    }
          
    //Evento 2. SAX lee el texto de dentro de la etiqueta
    @Override
    public void characters(char[] ch, int start, int length){
        // TODO: Si esCategoria es true, convertimos el texto a String y lo añadimos al HashSet
    
        if(esCategoria){
            String texto = new String(ch, start, length);
            categoriasUnicas.add(texto);
        }
    }
    
    //Evento 3. SAX detecta que se cierra la etiqueta (ej: </categoriaMant>)
    @Override
    public void endElement(String uri, String localName, String qName){
        //TODO: Si el nombre de la etiqueta es "categoriaMant", ponemos esCategoria a false
            if(qName.equalsIgnoreCase("categoriaMant")){
            esCategoria = false;
        }
    }
}
