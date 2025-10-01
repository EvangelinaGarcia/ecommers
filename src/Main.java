import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    ArrayList<String> productosDB = ProductosDecoracion();
    //eleccion de funcionalidad
    int opcionUsuario;
    System.out.println("Bienvenido a tu ecommerce ");
    label:


    while (true) {
      System.out.println("""
        
        Ingrese la opcion de desea realizar:
         0 - Finaliza el programa
         1 - Crea un producto
         2 - Listar productos
         3 - Busqueda por nombre
         4 - Editar nombre producto
         5 - Borrar productos
         """);
      opcionUsuario = entrada.nextInt();
      switch (opcionUsuario) {
        case 1 -> crearArticulo(productosDB); // - >
        case 2 -> listarArticulos(productosDB);
        case 3 -> buscarArticuloPorNombre(productosDB);
        case 0 -> {
          System.out.println("Gracias por usar la app!");
          break label; // corta el bucle donde se ejecuta
        }
        default -> System.out.println("Opción incorrecta, intente de nuevo");
      }
    }
  }
  public static void crearArticulo(ArrayList<String> productos) {
    Scanner entrada = new Scanner(System.in);
    System.out.println("Creando nuevo Artículo");
    System.out.print("Ingrese el nombre del nuevo producto: ");
    String nombre = entrada.nextLine();
    System.out.println("se creo Articulo " + nombre);

    productos.add(nombre);
    pausa();
  }
  public static void listarArticulos(ArrayList<String> articulos) {
    System.out.println("#######################################");
    System.out.println("        LISTA DE PRODUCTOS");
    System.out.println("#######################################");

    if (articulos == null || articulos.isEmpty()) {
      System.out.println("⚠️  No hay articulos para mostrar.");
    } else {
      int contador = 1;
      for (String articulo : articulos) {

        System.out.printf(" %2d. %s%n", contador++, articulo);
      }

    }

    System.out.println("=======================================");
    pausa();
  }
  public static void buscarArticuloPorNombre(ArrayList<String> articulos) {
    Scanner entrada = new Scanner(System.in);
    System.out.println("Ingrese un nombre de un producto: ");
    String busqueda = entrada.nextLine();
    ArrayList<String> articuloEncontrados = new ArrayList<>();

    for (String articulo : articulos) {
      if (estaIncluido(articulo, busqueda)) {
        articuloEncontrados.add(articulo);
      }
    }

    listarArticulos(articuloEncontrados);
  }
  public static boolean estaIncluido(String nombreCompleto, String nombreParcial) {
    String nombreCompletoFormateado = formatoBusqueda(nombreCompleto);

    return nombreCompletoFormateado.contains(formatoBusqueda(nombreParcial));
  }

  public static String formatoBusqueda(String texto) {
    return texto.trim().toLowerCase();
  }
  public static void pausa() {
    Scanner entrada = new Scanner(System.in);
    System.out.println("Pulse ENTER para continuar...");
    entrada.nextLine();
    for (int i = 0; i < 20; ++i) {
      System.out.println();
    }
  }
  public static ArrayList<String> ProductosDecoracion() {
    ArrayList<String> productos = new ArrayList<>();

    productos.add("Mesa TV+ Escritorio con tapa en madera clara");
    productos.add("Comoda Vajillero modelo Pampa");
    productos.add("Panel flotante de TV revistido en madera");
    productos.add("Biblioteca Modelo kenia de melamina 18MM");
    productos.add("Placard Nordico MODELO MONACO Escandinavo Paraiso Laqueado Puertas Melamina blanca");
    productos.add("Biblioteca Paraiso Laqueado y Melamina Modelo Montevideo");
    productos.add("lampara de pie, negra articulada");
    productos.add("juego de mesa con 4 sillas estilo Nordico");
    productos.add("sillon 2 cuerpos gamuza");
    productos.add("ALACENA superior JUANA puertas revatibles");
    productos.add("Cama Rusticda de cedro moderna matrimonial");


    return productos;
  }
}

