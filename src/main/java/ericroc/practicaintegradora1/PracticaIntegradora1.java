/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ericroc.practicaintegradora1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Eric
 */
public class PracticaIntegradora1 {

    public static void main(String[] args) {
        
        // 1. MÓDULO 6: Declaramos el array con las rutas que pide el ejercicio
        String[] carpetas = {
            "datos/config",
            "datos/json",
            "datos/backup",
            "datos/reports"
        };
        
        // 2. MÓDULO 6: Bloque try-catch para crear los directorios con NIO.2
        try {
            for (String carpeta : carpetas) {
                Path ruta = Paths.get(carpeta);
                Files.createDirectories(ruta);
            }
            System.out.println("Directorios creados o verificados correctamente.");
            
        } catch (IOException e) {
            System.out.println("Error al crear los directorios: " + e.getMessage());
        }

        // 3. MÓDULO 2: Instanciamos el Gestor de Configuración
        // Como pusimos los '0' y el 'IntroducirConfig()' en su constructor, 
        // con solo esta línea ya se crea el archivo 'empresa.dat'
        GestorConfiguracion gestor = GestorConfiguracion.getConfiguracion();
          
        // ------------------ DATOS DE PRUEBA ------------------
        Vehiculo v1 = new Vehiculo("1111-ABC", Marca.Audi, "A3", 2020, 50000, CategoriaMan.Mecanico);
        Vehiculo v2 = new Vehiculo("2222-XYZ", Marca.Seat, "Leon", 2022, 10000, CategoriaMan.Electrico);

        // 4. MÓDULO 3: Probar Persistencia JSON
        GestorInventarioJSON gestorJson = new GestorInventarioJSON();
        gestorJson.registrarVehiculo(v1);
        gestorJson.registrarVehiculo(v2);
        gestorJson.exportarJSON(); // Esto crea 'flota.json'

        // Creamos una lista para los módulos que necesitan un ArrayList
        ArrayList<Vehiculo> listaTemporal = new ArrayList<>();
        listaTemporal.add(v1); 
        listaTemporal.add(v2);

        // 5. MÓDULO 4: Probar Backup Binario
        GestorBackup gestorBackup = new GestorBackup();
        gestorBackup.crearBackup(listaTemporal); // Esto crea 'flota_backup.ser'

        // 6. MÓDULO 5: Probar XML (DOM y SAX)
        GestorReportesXML gestorXml = new GestorReportesXML();
        try {
            gestorXml.generarXMLDOM(listaTemporal); // Esto crea 'informe_flota.xml' (Parte A)
            gestorXml.leerXMLSAX();                 // Esto lee el XML y saca las categorías únicas (Parte B)
        } catch (Exception e) {
            System.out.println("Error XML: " + e.getMessage());
        }
        
    }
}