package ericroc.practicaintegradora1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Eric
 */
public class GestorBackup {
    
    public void crearBackup(ArrayList<Vehiculo> flota){
        
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("datos/backup/flota_backup.ser"))) {
            
            os.writeObject(flota);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<Vehiculo> restaurarBackup(){
    
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("datos/backup/flota_backup.ser"))) {
            
            return (ArrayList<Vehiculo>)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
