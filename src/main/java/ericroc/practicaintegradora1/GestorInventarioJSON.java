/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ericroc.practicaintegradora1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Eric
 */
public class GestorInventarioJSON {
    
    private ArrayList<Vehiculo> listaVehiculos;
    private HashMap<String, Vehiculo> mapaVehiculo;

    public GestorInventarioJSON() {
        this.listaVehiculos = new ArrayList<>();
        this.mapaVehiculo = new HashMap<>();
    }
    
    public void registrarVehiculo(Vehiculo v){
        
        this.listaVehiculos.add(v);
        this.mapaVehiculo.put(v.getMatricula(), v);
        
    }

    public void exportarJSON(){
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try(FileWriter fw = new FileWriter("datos/json/flota.json");) {
            gson.toJson(this.listaVehiculos, fw);
        } catch (IOException e) {
            System.out.println("Error al guardar JSON: " + e.getMessage());
        }
    }
    
    public void importarJSON(){
        
       java.lang.reflect.Type tipoLista = new TypeToken<ArrayList<Vehiculo>>(){}.getType();
    
       Gson gson = new GsonBuilder().setPrettyPrinting().create();
       
        try(FileReader fr = new FileReader("datos/json/flota.json")) {
            
            this.listaVehiculos = gson.fromJson(fr, tipoLista);
            
            this.mapaVehiculo.clear();
            for (Vehiculo vehiculo : this.listaVehiculos) {
                this.mapaVehiculo.put(vehiculo.getMatricula(), vehiculo);
            }
        } catch (IOException e) {
            System.out.println("Error al leer JSON: " + e.getMessage());
        }
    }
    
}
