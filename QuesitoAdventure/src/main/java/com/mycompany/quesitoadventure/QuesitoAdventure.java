/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quesitoadventure;

/**
 *
 * @author Usuario
 */

import Clases.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import modulos.*;

public class QuesitoAdventure {

static boolean infinityEnding=false;
static int opcion;
static boolean palanca=false;
static boolean llaveTaquilla=false;
static String leerCombEstanteria; 
static String leerCodPuerta;
static boolean llaveSalida=false;   //llave falsa
static String leerContraseñaBaño;
static boolean llaveSalidaReal=false;    //llave real
static boolean fin=false;
static long t1,t2;
static String nombreJug=new String();
static ArrayList<Player> hallOfFame;
static File datosJugadores=new File("src/main/java/Ficheros/datos.dat");

    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        
        boolean resp;
        
        cargarDatos();
        
        if(hallOfFame==null) hallOfFame=new ArrayList<>();
        
        
        do{
            nombreJug=FuncionesGraficas.pedirDatos("Nombre del jugador", "Ingresa tu nombre:");
            
            t1=System.currentTimeMillis();
            
            
            //inicio juego
            do{
                escenaPresentacion();
            
                if (infinityEnding==true){
                    escenaInicioBúnkerFinalBueno();
                    break;
                }else{
                    escenaInicioBunker();
                    escenaZombie();
                    escenaPasilloInstituto();
                }
            }while(true);
            //fin juego
            
            
            
            t2=System.currentTimeMillis();
            
            FuncionesGraficas.mostrarDatos("Tiempo transcurrido", "Han transcurrido: " + (float)((t2-t1)/1000) + " segundos");
            
            Calendar fecha=Calendar.getInstance();
            
            Player jug=new Player(nombreJug, (t2-t1), fecha);
            
            hallOfFame.add(jug);
            
            resp=FuncionesGraficas.pedirBool("Atención", "¿Quieres jugar de nuevo?");
            
        }while(resp);
        
        
        for(int i=0; i<hallOfFame.size(); i++){
            System.out.println("Nombre: " + hallOfFame.get(i).getNombre() + "    Tiempo: " + hallOfFame.get(i).getTiempo() + "    Fecha: " + hallOfFame.get(i).getFecha().getTime());
        }
        
        salvarDatos();
        
        
    //final del main    
    }
    
    //inicio de las funciones (escenas)
    public static void escenaPresentacion(){
        
        FuncionesGraficas.FotoMensajeSonido("Quesito Adventure", "src/main/java/Img/Intro-1.gif", "", "src/main/java/Sonidos/intro_scooby_doo.wav", 1, 0, false);
        FuncionesGraficas.FotoMensajeSonido("Quesito durmiendo", "src/main/java/Img/quesito_durmiendo.png", "-Quesito: ZZZZZZZZZ", "src/main/java/Sonidos/morning_roncar.wav", 0.5, 0, false);
        FuncionesGraficas.FotoMensajeSonido("Quesito despierto", "src/main/java/Img/quesito_despierto.png", "-Quesito: AAAAHHHHHHHHH, qué buen día hace hoy.", "src/main/java/Sonidos/bostezo.mp3", 0.75, 1, false); 
        FuncionesGraficas.FotoMensajeSonido("Quesito viendo a su crush", "src/main/java/Img/escena_calle.png", "Quesito partió al instituto, como un día cualquiera, y se encontró con su crush por la calle.\n-Quesito: Madre mía, qué quesito acabo de ver", "src/main/java/Sonidos/quesitoLove.mp3", 0.5, 1, false); 
        FuncionesGraficas.FotoMensajeSonido("Instituto", "src/main/java/Img/Instituto.jpg", "Llegó al instituto, pero no sabía lo que le esperaba ese día...", "src/main/java/Sonidos/timbre.mp3", 1, 1, false);  
        FuncionesGraficas.FotoMensajeSonido("Clase Quesito", "src/main/java/Img/Clase 1.jpg", "Al llegar a clase, Quesito escuchó a sus compañeros murmurando sobre un apocallipsis que vieron en las noticias.\n*Quesito entra en pánico al escucharlo y sale corriendo por el pasillo*.", "src/main/java/Sonidos/cuchicheo.mp3", 0.6, 1, false);   
        FuncionesGraficas.FotoMensajeSonido("Quesito cayendo", "src/main/java/Img/Quesito-cayendo.gif", "-Quesito: Debo salir de aquí...  AAHHHHH!!", "src/main/java/Sonidos/infinity_castle_final.wav", 0.75, 0, false);  
        return;
    }
    
    
    public static void escenaInicioBunker(){
        
        do{
            String[] opcionesBunker={"1. Puerta grande","2. Puerta izquierda","3. Abrir taquilla","4. Inspeccionar cadáver"};
            opcion=FuncionesGraficas.FotoMensajeMenuSonido("Búnker", opcionesBunker, "src/main/java/Img/Muerto y pasillo búnker.png", "-Quesito: *mareado*  Ohhh, mi cabeza. ¿Qué hago aquí? ¿Dónde estoy...?", "src/main/java/Sonidos/bunker.mp3", 0.5, 1, false);  
        
        switch (opcion){
            case 0:
                leerCodPuerta=FuncionesGraficas.pedirDatos("Código puerta grande", "Escribe el código de la puerta: ");
                if (leerCodPuerta.equals("?>427&42")){
                    return;
                }else{
                    FuncionesGraficas.mostrarMensaje("Advertencia", "Código erróneo", 1);
                    break;
                }
                
            case 1:
                escenaDespacho();
                break;
                
                
            case 2:
                if (llaveTaquilla==true){
                    FuncionesGraficas.FotoMensajeSonido("Código Puerta", "src/main/java/Img/Nota 5.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.5, 1, false);
                    break;
                }else{
                    FuncionesGraficas.mostrarMensaje("Advertencia", "Necesitas llave", 1);
                    break;
                }
                
            case 3:
                if (palanca==true){
                    FuncionesGraficas.mostrarMensaje("Vacío", "No hay nada", 1);
                    break;
                }else{
                    FuncionesGraficas.FotoMensajeSonido("Palanca", "src/main/java/Img/palanca.png", "Obtienes una palanca", "src/main/java/Sonidos/itemGrab.mp3", 1, 1, false); 
                    palanca=true;
                    break;
                }
        }
        }while (true);
    }
    
    public static void escenaDespacho(){
        
    do{
        String[] opcionesDespacho={"1. Ver mesa","2. Inspeccionar estantería","3. Consultar cajón","4. Ver debajo de la alfombra", "5. Mirar en la papelera", "6. Salir del despacho"};
        opcion=FuncionesGraficas.FotoMensajeMenuSonido("Despacho", opcionesDespacho, "src/main/java/Img/Despacho Viejo.png", "-Quesito: *cof cof* Cuánto polvo hay aquí...", "src/main/java/Sonidos/despacho.mp3", 0.7, 1, false);  
        
        switch(opcion){
            case 0:
                FuncionesGraficas.FotoMensajeSonido("Nota 1", "src/main/java/Img/Nota 1.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.5, 1, false);
                    break;
            case 1:
                if(llaveTaquilla==true){
                    FuncionesGraficas.mostrarMensaje("Advertencia", "Ya tienes la llave de la taquilla", 1);
                    break;
                }else{
                    leerCombEstanteria=FuncionesGraficas.pedirDatos("Combinación libros", "¿Cuál es la combinación? (V --> Verde  AM --> Amarillo  R --> Rojo  AZ --> Azul)");
                    if(leerCombEstanteria.equals("RVAMAZ")){
                        FuncionesGraficas.FotoMensajeSonido("Llave taquilla", "src/main/java/Img/llaveTaquilla.png", "Obtienes la llave de la taquilla", "src/main/java/Sonidos/itemGrab.mp3", 0.4, 1, false); 
                        llaveTaquilla=true;
                        break;
                    }else {
                        FuncionesGraficas.mostrarMensaje("Advertencia", "Combinación incorrecta", 1);
                        break;
                    }
                }
            case 2:
                if(palanca==true){
                    FuncionesGraficas.FotoMensajeSonido("Nota 4", "src/main/java/Img/Nota 4.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.5, 1, false);
                    FuncionesGraficas.FotoMensajeSonido("Código estantería", "src/main/java/Img/Combinación libros.png", "-Quesito: ¿Para qué sirve esto...?", "src/main/java/Sonidos/paperFlip.mp3", 0.5, 1, false);
                    break;
                }else{
                    FuncionesGraficas.mostrarMensaje("Advertencia", "Está bloqueado", 1);
                    break;
                }
            case 3:
                FuncionesGraficas.FotoMensajeSonido("Nota 2", "src/main/java/Img/Nota 2.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.5, 1, false);
                    break;
            case 4:
                FuncionesGraficas.FotoMensajeSonido("Nota 3", "src/main/java/Img/Nota 3.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.5, 1, false);
                    break;
            case 5:
                return;
        }
    }while(true);    
        
    }
    
    public static void escenaZombie(){
        String[] opcionesZombie={"1. Intentar huir","2. Luchar contra el zombie"};
        String[] opcionesZombieLucha={"1. Pelear a puñetazo limpio","2. Utilizar palanca"};
        opcion=FuncionesGraficas.FotoMensajeMenuSonido("Zombie", opcionesZombie, "src/main/java/Img/zombie.png", "-Quesito: *austado* ¿Eso es un zombie? Me va a matar, ¿qué hago?", "src/main/java/Sonidos/zombie.mp3", 0.5, 1, false);  //poner música
        
        switch (opcion){
            case 0:
                FuncionesGraficas.FotoMensajeSonido("Game Over", "src/main/java/Img/Pantalla muerte.gif", "", "src/main/java/Sonidos/gameOver.mp3", 1, 1, false);  
                System.exit(0);
                
            case 1:
                opcion=FuncionesGraficas.menuDesplegable("Quesito vs. Zombie", "¿Con qué vas a luchar?", opcionesZombieLucha);
                
                switch (opcion){
                    case 0:
                        FuncionesGraficas.FotoMensajeSonido("Game Over", "src/main/java/Img/Pantalla muerte.gif", "", "src/main/java/Sonidos/gameOver.mp3", 1, 1, false);  
                        System.exit(0);
                        
                    case 1:
                        FuncionesGraficas.FotoMensajeSonido("Subir escaleras", "src/main/java/Img/quesitoEscaleras.gif", "", "src/main/java/Sonidos/escalerasZombie.mp3", 0.6, 1, false);
                        return;
                }
        }
    }
    
    
    public static void escenaPasilloInstituto(){
        
        do{
            if (fin==true) break;
            String[] opcionesPasillo={"1. Baño","2. Biblioteca", "3. Girar esquina del pasillo"};
            opcion=FuncionesGraficas.FotoMensajeMenuSonido("Pasillo instituto", opcionesPasillo, "src/main/java/Img/Pasillo puertas.jpg", "-Quesito: Menos mal que salí con vida. ¿A dónde debería ir?", "src/main/java/Sonidos/musicaPasillo.mp3", 0.75, 1, false);
            
            switch(opcion){
                case 0:
                    escenaBaño();
                    break;
                
                case 1:
                    escenaBiblioteca();
                    break;
                    
                case 2:
                    escenaEsquinaPasilloInstituto();
                    break;
                }
            
        }while(true);
    }
    
    public static void escenaBiblioteca(){
        
        do{
            boolean salir=false;
            String[] opcionesBiblioteca={"1. Inspeccionar librería","2. Inspeccionar piano", "3. Volver"};
            opcion=FuncionesGraficas.FotoMensajeMenuSonido("Biblioteca", opcionesBiblioteca, "src/main/java/Img/Biblioteca.png", "-Quesito: ¿Habrá algo por aquí que me ayude a salir?", "src/main/java/Sonidos/musicaBiblioteca.mp3", 0.6, 1, false);
           
            switch(opcion){
                case 0:
                    FuncionesGraficas.warning("Muerte", "Se te ha caído la librería buscando la SALIDA");
                    FuncionesGraficas.FotoMensajeSonido("Game Over", "src/main/java/Img/Pantalla muerte.gif", "", "src/main/java/Sonidos/Muerte libreria.wav", 1, 0, false);
                    System.exit(0);
                   
                case 1:
                    FuncionesGraficas.warning("Muerte", "Te ha comido el piano buscando la SALIDA");
                    FuncionesGraficas.FotoMensajeSonido("Game Over", "src/main/java/Img/Pantalla muerte.gif", "", "src/main/java/Sonidos/Muerte piano.wav", 1, 0, false);
                    System.exit(0);
                    
                case 2:
                    salir=true;
                    break;
           }
            
            if(salir==true) break;
        }while(true);
    }
    
    public static void escenaBaño(){
        
        do{
            boolean salir=false;
            String[] opcionesBaño={"1. Inspeccionar basura","2. Mirar espejo", "3. Abrir puerta baño", "4. Volver atrás"};
            opcion=FuncionesGraficas.FotoMensajeMenuSonido("Baño", opcionesBaño, "src/main/java/Img/Baño.jpg", "-Quesito: UGH... Qué baño más sucio...", "src/main/java/Sonidos/musicaBaño.mp3", 1, 1, false);
           
            switch(opcion){
                case 0:
                    if (llaveSalida==true){
                        FuncionesGraficas.mostrarMensaje("Advertencia", "Ya has mirado aquí", 1);
                        break;
                    }else{
                        FuncionesGraficas.FotoMensajeSonido("Llave salida", "src/main/java/Img/llaveSalidaFalsa.png", "Has conseguido una llave", "src/main/java/Sonidos/itemGrab.mp3", 0.5, 1, false); 
                        llaveSalida=true;
                        break;
                    }
                   
                case 1:
                    FuncionesGraficas.FotoMensajeSonido("Espejo", "src/main/java/Img/Quesito loco espejo.jpg", "", "src/main/java/Sonidos/quesitoBaño.mp3", 0.6, 1, false);
                    FuncionesGraficas.warning("Muerte", "Te volviste loco buscando la SALIDA");
                    FuncionesGraficas.FotoMensajeSonido("Game Over", "src/main/java/Img/Pantalla muerte.gif", "", "src/main/java/Sonidos/gameOver.mp3", 1, 1, false);  
                    System.exit(0);
                   
                case 2:
                    leerContraseñaBaño=FuncionesGraficas.pedirDatos("Contraseña baño", "Escribe la contraseña");
                    if(leerContraseñaBaño.equals("54/164")){
                        if (llaveSalidaReal==true){
                            FuncionesGraficas.mostrarMensaje("Advertencia", "Para qué vuelves si no hay nada", 1);
                            break;
                        }else{
                            FuncionesGraficas.FotoMensajeSonido("Llave salida", "src/main/java/Img/LlaveReal Váter.jpg", "Has conseguido una llave", "src/main/java/Sonidos/itemGrab.mp3", 0.4, 1, false); 
                            llaveSalidaReal=true;
                            break;
                        }
                    }else {
                        FuncionesGraficas.mostrarMensaje("Advertencia", "Contraseña incorrecta", 1);
                        break;
                    }
                    
                case 3:
                    salir=true;
                    break;
           }
            
            if(salir==true) break;
            
        }while(true);
    }
    
    public static void escenaEsquinaPasilloInstituto(){
        
        do{
            boolean salir=false;
            String[] opcionesEsquina={"1. Aula 1","2. Aula 2", "3. Puerta salida", "4. Volver atrás"};
            opcion=FuncionesGraficas.FotoMensajeMenuSonido("Esquina del pasillo", opcionesEsquina, "src/main/java/Img/Salidn_t.png", "-Quesito: Aún hay más aulas por ver", "src/main/java/Sonidos/musicaPasillo.mp3", 0.6, 1, false); 
            
            switch(opcion){
                case 0:
                    escenaAula1();
                    break;
                    
                case 1:
                    escenaAula2();
                    break;
                    
                case 2:
                    if (llaveSalida==true){
                        FuncionesGraficas.mostrarMensaje("Advertencia", "¿Tengo una llave falsa? No fastidies...", 1);
                        llaveSalida=false;
                        escenaEsquinaPasilloInstituto();
                        break;
                    }else{
                        if (llaveSalidaReal==true){
                            infinityEnding=true;
                            palanca=false;
                            fin=true;
                            FuncionesGraficas.FotoMensajeSonido("Escaleras salida", "src/main/java/Img/Escaleras.jpg", "-Quesito: ¿Otra vez aquí? ¿Por qué estas escaleras no tienen fin? No consigo llegar a ningún lado...", "src/main/java/Sonidos/infStairs.mp3", 0.5, 1, false);
                            FuncionesGraficas.FotoMensajeSonido("Bad Ending", "src/main/java/Img/badEnding.png", "Te quedaste atrapado para siempre", "src/main/java/Sonidos/badEnding.mp3", 1, 1, false);  //poner imagen
                            salir=true;
                            break;
                            
                        }else{
                            FuncionesGraficas.mostrarMensaje("Advertencia", "Está bloqueada, necesitas una llave", 1);
                            escenaEsquinaPasilloInstituto();
                            break;
                        }
                    }
                    
                case 3:
                    salir=true;
                    break;
            }
            
            if(salir==true) break;
            
        }while(true);
    }
    
    public static void escenaAula1(){
        
        do{
            boolean salir=false;
            String[] opcionesAula1={"1. Mesa 1","2. Mesa 2", "3. Mesa 3", "4. Volver atrás"};
            opcion=FuncionesGraficas.FotoMensajeMenuSonido("Aula 1", opcionesAula1, "src/main/java/Img/Clase 3.jpg", "-Quesito: ¿Habrá algo en las mesas que me ayuden?", "src/main/java/Sonidos/musicaPasillo.mp3", 0.6, 1, false);
            
            switch (opcion){
                case 0:
                    FuncionesGraficas.mostrarMensaje("Mesa", "Aquí no hay nada", 1);
                    break;
                    
                case 1:
                    FuncionesGraficas.FotoMensajeSonido("Nota 8", "src/main/java/Img/Nota 8.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.6, 1, false);
                    break;
                    
                case 2:
                    FuncionesGraficas.FotoMensajeSonido("Nota 9", "src/main/java/Img/Nota 9.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.6, 1, false);
                    break;
                    
                case 3:
                    salir=true;
                    break;
            }
            
            if(salir==true) break;
            
        }while(true);  
    }
    
    public static void escenaAula2(){
        
        do{
            boolean salir=false;
            String[] opcionesAula2={"1. Mesa 1","2. Mesa 2", "3. Mesa 3", "4. Volver atrás"};
            opcion=FuncionesGraficas.FotoMensajeMenuSonido("Aula 2", opcionesAula2, "src/main/java/Img/Clase 4.jpg", "-Quesito: ¿Otra aula con mesas?", "src/main/java/Sonidos/musicaPasillo.mp3", 0.6, 1, false);  
            
            switch (opcion){
                case 0:
                    FuncionesGraficas.FotoMensajeSonido("Nota 6", "src/main/java/Img/Nota 6.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.6, 1, false);
                    break;
                    
                case 1:
                    FuncionesGraficas.FotoMensajeSonido("Nota 7", "src/main/java/Img/Nota 7.jpg", "Has encontrado una nota", "src/main/java/Sonidos/paperFlip.mp3", 0.6, 1, false);
                    break;
                    
                case 2:
                    FuncionesGraficas.mostrarMensaje("Mesa", "Ugh, qué asco, un chicle", 1);
                    break;
                    
                case 3:
                    salir=true;
                    break;
            }
            
            if(salir==true) break;
            
        }while(true);  
    }
    
    
    public static void escenaInicioBúnkerFinalBueno(){
        
        do{
            String[] opcionesBunkerFinal={"1. Puerta grande","2. Puerta izquierda","3. Abrir taquilla","4. Inspeccionar cadáver"};
            opcion=FuncionesGraficas.FotoMensajeMenuSonido("Búnker", opcionesBunkerFinal, "src/main/java/Img/Muerto y pasillo búnker.png", "-Quesito: *mareado*  Ohhh, mi cabeza. ¿Qué hago aquí? ¿Dónde estoy...?", "src/main/java/Sonidos/", 0.5, 1, false);  //poner sonido
        
        switch (opcion){
            case 0:
                FuncionesGraficas.mostrarMensaje("Advertencia", "Está bloqueado", 1);
                break;
                
            case 1:
                FuncionesGraficas.mostrarMensaje("Advertencia", "Está bloqueado", 1);
                break;
                
            case 2:
                FuncionesGraficas.mostrarMensaje("Advertencia", "Está bloqueado", 1);
                break;
                
            case 3:
                FuncionesGraficas.mostrarMensaje("Palanca", "Obtienes una palanca", 1);
                FuncionesGraficas.FotoMensajeSonido("Trampilla", "src/main/java/Img/Quesito trampilla muerto.png", "Notas que hay una rendija debajo del cadáver y la abres", "src/main/java/Sonidos/bunker.mp3", 0.5, 1, false);  
                FuncionesGraficas.FotoMensajeSonido("Túnel", "src/main/java/Img/tunel.gif", "Atraviesas un túnel y ves una luz al final", "src/main/java/Sonidos/tunel.mp3", 0.5, 1, false);  
                FuncionesGraficas.FotoMensajeSonido("Ending", "src/main/java/Img/quesito_durmiendo.png", "Todo fue un sueño y nada pasó realmente", "src/main/java/Sonidos/morning_roncar.wav", 0.5, 0, false); 
                return;
        }
        }while (true);
    }
    //fin de las funciones (escenas)
    
    
    
    
    //cargar datos (leer tabla)
    public static void cargarDatos() throws IOException, FileNotFoundException, ClassNotFoundException{
        
        try{
            hallOfFame=(ArrayList<Player>) Ficheros.leerTabla(datosJugadores);
            
        }catch (IOException e){
            System.out.println("Error de lectura" + e.getMessage());
        }
        
    }
    
    
    //guardar datos (escribir tabla)
    public static void salvarDatos() throws IOException{
        
        Ficheros.escribirTabla(hallOfFame, datosJugadores);
        
    }
    
    
}
