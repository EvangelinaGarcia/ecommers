import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    ArrayList<Producto> productosDB = obtenerProductosDecoracion();
    int idSiguiente = productosDB.size() + 1;
    int opcionUsuario;

//indice de acciones que el usuario puede elegir
    System.out.println("Bienvenidos a la app de compras 🛒");
    System.out.println(" ");
    label:
    while (true) {
      System.out.println("""
          Eliga la opción deseada ingresando el número:
          
          0 - Finaliza el programa
          1 - Crea un Producto
          2 - Listar Productos
          3 - Búsqueda por nombre
          4 - Editar nombre producto
          5 - Borrar producto
          6 - nueva funcion
          """);
      opcionUsuario = entrada.nextInt();

      switch (opcionUsuario) {
        case 1 -> {
          crearProducto (idSiguiente, productosDB);
          idSiguiente += 1;
        }
        case 2 -> listarProductos(productosDB);
        case 3 -> buscarProductoPorNombre(productosDB);
        case 4 -> editarProducto(productosDB);
        case 5 -> borrarProducto(productosDB);
        case 6 -> System.out.println("Crear pedido Función en proceso...");
        case 7 -> System.out.println("Listar pedidos Función en proceso...");
        case 0 -> {
          System.out.println("Gracias por utilizar nuestro e-commerce!");
          break label; // corta el bucle donde se ejecuta
        }
        default -> System.out.println("Opción incorrecta, intente de nuevo");
      }
    }
  }

  public static void crearProducto (int id, ArrayList<Producto> productos) {
    Scanner entrada = new Scanner(System.in);
    System.out.println("Creando Nuevo Producto");
    System.out.print("Ingrese el nombre del nuevo producto: ");

    String nombre = entrada.nextLine();

    System.out.print("Precio: ");
    double precioP = entrada.nextDouble();
    System.out.print("Stock inicial: ");
    int cantP = entrada.nextInt();

    productos.add(new Producto(id, nombre, precioP, cantP));
    System.out.println("El articulo ingresado es "+ nombre + " " );
    System.out.println("El precio es:  "+ precioP+ " " );
    System.out.println("Cantidad incial ingresada: "+ cantP + " " );

    System.out.println("=======================================");
    pausa();
  }

  public static void listarProductos(ArrayList<Producto> productos) {
    System.out.println("=======================================================================================================");
    System.out.println("                                          LISTA DE PRODUCTOS                                           ");
    System.out.println("=======================================================================================================");
    System.out.println("=======================================================================================================");
    // Encabezado
    System.out.printf("%-5s | %-70s | %-12s | %-8s%n", "ID", "Nombre", "Precio", "Cant.");
    System.out.println("-----------------------------------------------------------------------------------------------");

    if (productos == null || productos.isEmpty()) {
      System.out.println("⚠️  No hay productos para mostrar.");
    } else {
      int contador = 1;
     for (Producto producto : productos) {
        System.out.printf("%-5d | %-70s | %-12.2f | %-8d%n", producto.id, producto.nombre , producto.precioP, producto.cantP);
      }
    }

    System.out.println("=======================================");
    pausa();
  }

  public static void buscarProductoPorNombre(ArrayList<Producto> productos) {
    Scanner entrada = new Scanner(System.in);
    System.out.print("Ingrese un nombre de un producto: ");
    String busqueda = entrada.nextLine();
    ArrayList<Producto> productoEncontrados = new ArrayList<>();

    for (Producto producto : productos) {
      if (estaIncluido(producto.nombre, busqueda)) {
        productoEncontrados.add(producto);
      }
    }

    listarProductos(productoEncontrados);
  }

  public static void editarProducto(List<Producto> productos) {
    System.out.println("ingrese id del producto a editar");
    Scanner entrada = new Scanner(System.in);
    Producto producto = obtenerProductoPorId(productos);


    String nombreOriginal = producto.nombre;
    double precioOriginal = producto.precioP;


    System.out.println("Producto a editar:");
    System.out.println(nombreOriginal);
    // TODO: validar que el usuario quiere editar el producto que se encontro
    System.out.print("Ingrese el nuevo nombre: ");
    String nuevoNombre = entrada.nextLine();
    System.out.println(precioOriginal);
    // TODO: validar que el usuario quiere editar el producto que se encontro
    System.out.print("Ingrese el nuevo precio: ");
    double nuevoPrecio = entrada.nextDouble();



    producto.nombre = nuevoNombre;
    producto.precioP = nuevoPrecio;

    System.out.printf("El nombre del producto cambio de %s a %s", nombreOriginal, nuevoNombre);
  }

  public static void borrarProducto(List<Producto> productos) {
    System.out.println("ingrese id del producto a borrar");
    Scanner entrada = new Scanner(System.in);
    Producto producto = obtenerProductoPorId(productos);

    // TODO: validar que encontramos el indice
    String nombreOriginal = producto.nombre;
    System.out.println("Producto ELIMINADO:");
    System.out.println(nombreOriginal);
    // TODO: validar que el usuario quiere borrar el producto que se encontro

    productos.remove(producto);

    System.out.printf("El producto %s se borro", nombreOriginal);

    pausa();
  }

  /* UTILIDADES */
  /* Busqueda por id - ahora mismo solo funciona con el indice, en el futuro se va a cambiar */
  public static Producto obtenerProductoPorId(List<Producto> productos) {
    Scanner entrada = new Scanner(System.in);
    // TODO: validacion de datos
    System.out.println("Ingrese el id del producto: ");
    int idBusqueda = entrada.nextInt();

    for (Producto producto : productos) {
      if (producto.id == idBusqueda) {
        return producto;
      }
    }
    return null; // el null representa que no encontramos el producto
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


  }

  public static ArrayList<Producto> obtenerProductosDecoracion() {
    ArrayList<Producto> productos = new ArrayList<>();

    productos.add(new Producto(1,"Mesa TV+ Escritorio con tapa en madera clara",25000.40,50));
    productos.add(new Producto(2,"Comoda Vajillero modelo Pampa",150000,15));
    productos.add(new Producto(3,"Panel flotante de TV revistido en madera",80000,25));
    productos.add(new Producto(4,"Biblioteca Modelo kenia de melamina 18MM",250000,10));
    productos.add(new Producto(5,"Placard Nordico MODELO MONACO Escandinavo ",350000,17));
    productos.add(new Producto(6,"Biblioteca Paraiso Laqueado y Melamina Modelo Montevideo",290000,7));
    productos.add(new Producto(7,"lampara de pie, negra articulada",55000,75));
    productos.add(new Producto(8,"juego de mesa con 4 sillas estilo Nordico",135000,9));
    productos.add(new Producto(9,"sillon 2 cuerpos gamuza ",450000,5));
    productos.add(new Producto(10,"ALACENA superior JUANA puertas revatibles",147000,10));
    productos.add(new Producto(11,"Cama Rusticda de cedro moderna matrimonial",360000,25));

    return productos;
  }
}
