    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
import java.io.Serializable;
import java.util.Calendar;

public class Player implements Serializable{
    
 private String nombre;
 private long tiempo;
 private Calendar fecha;
 private int edad;
 
 public Player(String nombre, long tiempo, Calendar fecha){
  this.nombre = nombre;
  this.tiempo = tiempo;
  this.fecha = fecha;
 }
 
 public String getNombre() {
        return nombre;
    }
 
 public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
 public long getTiempo() {
        return tiempo;
    }

    
    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    
    public Calendar getFecha() {
        return fecha;
    }

    
    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    
    public int getEdad() {
        return edad;
    }

   
    public void setEdad(int edad) {
        this.edad = edad;
    }
  
  
}
