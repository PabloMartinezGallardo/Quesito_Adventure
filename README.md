# 🧀 Quesito Adventure

> Novela visual y *escape room* en la que cada decisión cuenta: investiga, resuelve los enigmas y sal vivo del instituto... si es que se puede salir.

Proyecto desarrollado **en parejas** en **Java**, con interfaces gráficas basadas en **Swing** (a través de una librería propia de funciones gráficas) y gestionado con **Maven**.

---

## 📖 Historia

Quesito se despierta un día cualquiera, se cruza con su *crush* por la calle y llega al instituto. En clase oye a sus compañeros cuchichear sobre un apocalipsis que han visto en las noticias, entra en pánico y sale corriendo por el pasillo... hasta que cae por un agujero y despierta en un **búnker** bajo el instituto, junto a un cadáver y un par de puertas cerradas.

A partir de ahí tendrás que explorar, leer notas, descifrar códigos, esquivar la muerte (hay varias formas de palmarla) y encontrar la salida.

## 🎮 Cómo se juega

- Todo el juego se controla con **ventanas de diálogo**: cada escena muestra una imagen, un texto y, cuando toca decidir, un **menú de opciones numeradas**.
- Hay **objetos** que se recogen y se usan (palanca, llaves), **notas** con pistas y **cerraduras** que piden un código o contraseña escrito a mano.
- Algunas decisiones son **mortales** y terminan el juego al instante (*Game Over*).
- Se **cronometra** tu partida desde que pones tu nombre hasta que terminas.
- Al acabar puedes **jugar de nuevo** sin cerrar el programa.

### 🏆 Hall of Fame

Cada partida completada guarda un registro con:

| Campo   | Descripción                          |
|---------|--------------------------------------|
| Nombre  | El que introduces al empezar         |
| Tiempo  | Duración de la partida (en ms)       |
| Fecha   | Momento en que terminaste            |

Los registros se serializan en `src/main/java/Ficheros/datos.dat` (clase `Player`) y se cargan al arrancar. Al cerrar el juego, el listado completo se imprime por consola.

---

## 🗺️ Escenarios

| Escena | Qué puedes hacer |
|--------|------------------|
| **Introducción** | Escena cinemática: Quesito durmiendo, camino al instituto, la clase y la caída. |
| **Búnker** | Puerta grande (con código), puerta izquierda (despacho), taquilla y cadáver. |
| **Despacho** | Mesa, estantería de libros, cajón, alfombra y papelera. Aquí hay muchas pistas. |
| **Zombie** | Un encuentro inesperado en el que tendrás que decidir rápido. |
| **Pasillo del instituto** | Baño, biblioteca y esquina del pasillo. |
| **Baño** | Basura, espejo, y una puerta que pide contraseña. |
| **Biblioteca** | Una librería y un piano... que quizá no convenga tocar. |
| **Esquina del pasillo** | Aula 1, Aula 2 y la puerta de salida. |

Cada escena tiene su propia **música/ambiente** y efectos de sonido (timbre, cuchicheos, objetos, papeles, zombie, etc.).

---

## ▶️ Cómo ejecutarlo

### Requisitos

- **JDK 25** (el `pom.xml` compila con `maven.compiler.release = 25`).
- **Maven**.
- Las dos librerías locales que usa el proyecto, que **no están en Maven Central** y deben estar instaladas en tu repositorio local de Maven:

  | Librería | groupId / artifactId | Versión | Uso (deducido del código) |
  |----------|----------------------|---------|---------------------------|
  | `MiLibreria` | `MiLibreria:MiLibreria` | 1.0 | Paquete `modulos`: `FuncionesGraficas` (diálogos Swing con imagen, menús, entrada de datos y sonido) y `Ficheros` (lectura/escritura de datos serializados). |
  | `JackMp3` | `JackMp3:JackMp3` | 1.0 | Reproducción de audio MP3. |

  Si los `.jar` no están instalados, puedes hacerlo así:

  ```bash
  mvn install:install-file -Dfile=MiLibreria.jar -DgroupId=MiLibreria -DartifactId=MiLibreria -Dversion=1.0 -Dpackaging=jar
  mvn install:install-file -Dfile=JackMp3.jar   -DgroupId=JackMp3   -DartifactId=JackMp3   -Dversion=1.0 -Dpackaging=jar
  ```

### Compilar y lanzar

Ejecuta **siempre desde la raíz del proyecto** (`QuesitoAdventure/`), porque las rutas a imágenes, sonidos y al fichero de datos son relativas (`src/main/java/Img/...`):

```bash
mvn compile exec:java -Dexec.mainClass=com.mycompany.quesitoadventure.QuesitoAdventure
```

También puedes abrirlo con **NetBeans** y pulsar *Run*.

---

## 🧩 Estructura del proyecto

```
QuesitoAdventure/
├── pom.xml
└── src/main/java/
    ├── com/mycompany/quesitoadventure/
    │   └── QuesitoAdventure.java   # Lógica del juego: una función por escena
    ├── Clases/
    │   └── Player.java             # Registro del Hall of Fame (Serializable)
    ├── Ficheros/
    │   └── datos.dat               # Datos persistentes de los jugadores
    ├── Img/                        # Fondos, objetos, notas, GIFs y pantallas de muerte
    └── Sonidos/                    # Música ambiente y efectos (.mp3 / .wav)
```

### Diseño del código

- **`QuesitoAdventure.main`** pide el nombre, arranca el cronómetro y encadena las escenas dentro de un bucle de partida, con opción de repetir.
- Cada escena es un método estático (`escenaBunker`, `escenaDespacho`, `escenaZombie`...) que muestra un menú y decide con un `switch`.
- El **estado del juego** se guarda en variables estáticas booleanas: `palanca`, `llaveTaquilla`, `llaveSalida` (falsa), `llaveSalidaReal`, `infinityEnding` y `fin`.
- Los **finales** y las **muertes** se resuelven con escenas propias; las muertes llaman a `System.exit(0)`.

---

## 🕹️ Controles

Todo se maneja con el ratón y el teclado a través de ventanas:

- **Botones / opciones del menú** para elegir acción.
- **Cuadros de texto** para escribir códigos y contraseñas (¡respeta mayúsculas y símbolos!).
- **Desplegables** para ciertas decisiones (por ejemplo, con qué luchar).

---

<details>
<summary>⚠️ <strong>SPOILERS — Guía completa y finales</strong> (despliega solo si estás atascado)</summary>

### 🔑 Ruta de resolución

1. **Búnker → Inspeccionar cadáver**: consigues una **palanca**.
2. **Búnker → Puerta izquierda (Despacho)**:
   - *Mesa*, *alfombra* y *papelera* contienen notas (1, 2 y 3) con pistas sobre un **código secreto** de sustitución de letras por números y símbolos.
   - *Cajón*: está bloqueado hasta que tengas la palanca. Contiene una nota y una imagen con la **combinación de colores** de los libros.
   - *Estantería*: introduce la combinación **`RVAMAZ`** (Rojo, Verde, Amarillo, Azul) para obtener la **llave de la taquilla**.
3. **Búnker → Abrir taquilla** (con la llave): contiene una nota que revela que la contraseña de la puerta es el **nombre del profesor de programación**.
4. **Descifrar el código**: con las notas del despacho y las de las aulas se reconstruye la tabla de sustitución:

   | Letra | A | D | F | I | J | L | N | O | R | S | U |
   |-------|---|---|---|---|---|---|---|---|---|---|---|
   | Símbolo | `4` | `6` | `7` | `1` | `?` | `/` | `2` | `0` | `&` | `5` | `>` |

5. **Búnker → Puerta grande**: el código es **`?>427&42`** (= *JUANFRAN*).
6. **Zombie**: la única opción que te salva es **Luchar → Utilizar palanca**. Huir o pelear a puñetazos es muerte segura.
7. **Pasillo del instituto**:
   - **Aula 1 y Aula 2**: mesas con las notas que completan la tabla del código (por ejemplo, para deducir `S`, `D`, `A`, `R`...).
   - **Baño → Inspeccionar basura**: encuentras una **llave falsa** (¡no sirve para salir!).
   - **Baño → Abrir puerta del baño**: pide una contraseña. Es **`54/164`** (= *SALIDA*) y te da la **llave real**.
8. **Esquina del pasillo → Puerta de salida**: con la llave real, ¡intentas salir!

### 💀 Formas de morir

| Dónde | Decisión mortal |
|-------|-----------------|
| Zombie | Intentar huir |
| Zombie | Pelear a puñetazo limpio |
| Biblioteca | Inspeccionar la librería (se te cae encima) |
| Biblioteca | Inspeccionar el piano (te come) |
| Baño | Mirar el espejo (te vuelves loco) |

### 🌀 Finales

- **Salida con la llave falsa**: Quesito se da cuenta de que la llave no es la buena y la pierdes; tendrás que seguir buscando.
- **Salida con la llave real**: subes unas escaleras que **no tienen fin** → pantalla de *Bad Ending* ("Te quedaste atrapado para siempre"). Pero la historia **vuelve a empezar**: en este nuevo bucle todas las puertas del búnker están bloqueadas salvo el cadáver, bajo el cual hay una trampilla que lleva a un **túnel con luz al final**.
- **Final bueno**: Quesito despierta en su cama y descubre que *todo fue un sueño*.

</details>

---

## 🛠️ Detalles técnicos y limitaciones conocidas

Cosas que conviene saber si vas a seguir desarrollando el proyecto:

- Las **rutas son relativas** al directorio de trabajo; si lanzas el juego desde otra carpeta, no encontrará imágenes, sonidos ni `datos.dat`.
- Las **muertes** usan `System.exit(0)`: la partida no se registra en el Hall of Fame.
- En la escena del zombie, la opción **"Utilizar palanca" no comprueba** que el jugador tenga realmente la palanca.
- En la escena final del búnker (`escenaInicioBúnkerFinalBueno`) la ruta de sonido apunta a la carpeta `Sonidos/` y no a un fichero concreto (hay un comentario `//poner sonido` pendiente).
- El Hall of Fame **solo se muestra por consola**, no en una ventana del juego.
- `Player` tiene un atributo `edad` que actualmente no se utiliza.
- El tiempo mostrado al terminar usa división entera de milisegundos entre 1000, así que se redondea a segundos enteros.
- Los **recursos pesan mucho** (varios GIF superan los 20 MB y el proyecto completo ronda los 235 MB), y algunos ficheros de `Img/` y `Sonidos/` no se usan en el código actual.

---

## 👥 Autores

Proyecto realizado en parejas por:

- Pablo
- Eva

---

## 📄 Licencia

Proyecto académico. Licencia CC BY-NC-SA 4.0
