/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ericroc.practicaintegradora1;

import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

/**
 *
 * @author Eric
 */
public class GestorReportesXML {
    
    
    public void generarXMLDOM(ArrayList<Vehiculo> flota) throws ParserConfigurationException, TransformerException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element raiz = document.createElement("flota");
        document.appendChild(raiz);
    
        for (Vehiculo v : flota) {
            
            // 1. Fabrícas la etiqueta del coche
            Element nodoVehiculo = document.createElement("vehiculo");
            
            // 2. Fabrícas la etiqueta de la matrícula, por ejemplo
            Element nodoMatricula = document.createElement("matricula");
            
            // 3. Le metes el texto de la matrícula dentro de la etiqueta
            nodoMatricula.setTextContent(v.getMatricula());
            
            // 4. Pegas la matrícula dentro del coche
            nodoVehiculo.appendChild(nodoMatricula);
            
            //5. Creamos el resto de nodos
            Element nodoMarca = document.createElement("marca");
            nodoMarca.setTextContent(v.getMarca().name());
            nodoVehiculo.appendChild(nodoMarca);
            
            Element nodoModelo = document.createElement("modelo");
            nodoModelo.setTextContent(v.getModelo());
            nodoVehiculo.appendChild(nodoModelo);
            
            Element nodoAño = document.createElement("año");
            nodoAño.setTextContent(String.valueOf(v.getAño()));
            nodoVehiculo.appendChild(nodoAño);
            
            Element nodoKm = document.createElement("km");
            nodoKm.setTextContent(String.valueOf(v.getKm()));
            nodoVehiculo.appendChild(nodoKm);
            
            Element nodoCatMan = document.createElement("categoriaMant");
            nodoCatMan.setTextContent(v.getCategoriaMan().name());
            nodoVehiculo.appendChild(nodoCatMan);
            
                          
            // 6. Pegas el coche terminado a la raíz general
            raiz.appendChild(nodoVehiculo);
             
        }
        
        //Creamos las instancias de la clase transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        
        //Origen: Documento en RAM
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);        
        
        //Destino: archivo fisico en la ruta requerida
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new java.io.File("datos/reports/informe_flota.xml"));
        
        transformer.transform(source, result);
    }
    
    public void leerXMLSAX(){
        try{
            //1. Instanciar el motor SAX
            javax.xml.parsers.SAXParserFactory factory = javax.xml.parsers.SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser parser = factory.newSAXParser();
            
            //2. Crear el objeto manejador
            ManejadorSAX manejador = new ManejadorSAX();
            
            //3. Arrancamos la lectura, pasamos la ruta del archivo y el manejador
            parser.parse(new File("datos/reports/informe_flota.xml"), manejador);
            
            //4. COmprobar que el hashset ha evitado duplicados
            System.out.println("Auditoria XML finalizada");
            System.out.println("Categorias de mantenimiento únicas encontradas" + manejador.categoriasUnicas);
            
            
        }catch(Exception e){
            System.out.println("Error durante la lectura SAX: " + e.getMessage());
        }
    }

}
