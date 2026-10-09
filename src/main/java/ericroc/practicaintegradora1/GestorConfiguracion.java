/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ericroc.practicaintegradora1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class GestorConfiguracion {
    
    private String nombreEmpresa;
    private String VersionSoftware;
    private Integer totalVehiculosActivos;
    private Integer kmTotal;
    private static GestorConfiguracion configuracion;

    public GestorConfiguracion() {
    }

    public GestorConfiguracion(String nombreEmpresa, String VersionSoftware) {
        this.nombreEmpresa = nombreEmpresa;
        this.VersionSoftware = VersionSoftware;
        this.totalVehiculosActivos = 0;
        this.kmTotal = 0;
        
        this.IntroducirConfig();
    }
    
    
        private void IntroducirConfig(){
            try(DataOutputStream ds = new DataOutputStream(new FileOutputStream("datos/config/empresa.dat", false))) {
                
                
                ds.writeUTF(this.nombreEmpresa);
                ds.writeUTF(this.VersionSoftware);
                ds.writeInt(this.totalVehiculosActivos);
                ds.writeInt(this.kmTotal);
                
            }catch(IOException e) {
                System.out.println(e.getMessage());
              }   
        }
        
        private void leerConfig(){
            try(DataInputStream is = new DataInputStream(new FileInputStream("datos/config/empresa.dat"))){
                
                while(true){
                this.nombreEmpresa = is.readUTF();
                this.VersionSoftware = is.readUTF();
                this.totalVehiculosActivos = is.readInt();
                this.kmTotal = is.readInt();
                }
                
            } catch (EOFException ex) {
                System.out.println("Configuración cargada correctamente.");
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getVersionSoftware() {
        return VersionSoftware;
    }

    public void setVersionSoftware(String VersionSoftware) {
        this.VersionSoftware = VersionSoftware;
    }

    public Integer getTotalVehiculosActivos() {
        return totalVehiculosActivos;
    }

    public void setTotalVehiculosActivos(Integer totalVehiculosActivos) {
        this.totalVehiculosActivos = totalVehiculosActivos;
    }

    public Integer getKmTotal() {
        return kmTotal;
    }

    public void setKmTotal(Integer kmTotal) {
        this.kmTotal = kmTotal;
    }

    public static GestorConfiguracion getConfiguracion() {
        
        if(configuracion == null){
            configuracion = new GestorConfiguracion("Eric", "1.0.1");
        }
        return configuracion;
    }

    public static void setConfiguracion(GestorConfiguracion configuracion) {
        
        GestorConfiguracion.configuracion = configuracion;
    }
        
        
}
