/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ericroc.practicaintegradora1;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


/**
 *
 * @author Eric
 */
public class Vehiculo implements Serializable{
    
    
     private static Integer totalVehiculos = 0;
     private static Integer totalKm = 0;
     private String matricula;
     private Marca marca;
     private String modelo;
     private int año;
     @SerializedName("kms_recorridos")
     private int km;
     private CategoriaMan categoriaMan;
     

    public Vehiculo() {
        totalVehiculos ++;
    }

    public Vehiculo(String matricula, Marca marca, String modelo, int año, Integer km, CategoriaMan categoriaMan) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.km = km;
        this.categoriaMan = categoriaMan;
        this.totalVehiculos ++;
        this.totalKm += km;
        
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(int km) {
        if (this.km != 0) {
        totalKm -= this.km;
    }
    // Asignamos los nuevos kilómetros al vehículo
    this.km = km;
    // Sumamos los nuevos kilómetros al total global
    totalKm += this.km;
    }
    

    public CategoriaMan getCategoriaMan() {
        return categoriaMan;
    }

    public void setCategoriaMan(CategoriaMan categoriaMan) {
        this.categoriaMan = categoriaMan;
    }

    public static Integer getTotalVehiculos() {
        return totalVehiculos;
    }


    public static Integer getTotalKm() {
        return totalKm;
    }

     
    
     
}
