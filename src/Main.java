import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static final ArrayList<Producto> productosDB = obtenerProductosDecoracion();
  static Scanner entrada = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Te damos la bienvenida a la app de compras 🛒");
    label:
    while (true) {
      System.out.println("""
                    Ingrese el número equivalente a la opción:
                    0 - Finaliza el programa
                    1 - Crea un Producto
                    2 - Listar Productos
                    3 - Búsqueda por nombre
                    4 - Editar nombre producto
                    5 - Borrar producto
                    6 - nueva funcion
                    """);
      int opcionUsuario = entrada.nextInt();

      switch (opcionUsuario) {
        case 1 -> crearProducto(productosDB);
        case 2 -> listarProductos(productosDB);
        case 3 -> buscarProductoPorNombre(productosDB);
        case 4 -> editarProducto(productosDB);
        case 5 -> borrarProducto(productosDB);
        case 6 -> filtroPorPrecio(productosDB);
        case 0 -> {
          System.out.println("Gracias por usar nuestro e-commerce!");
          break label; // corta el bucle donde se ejecuta
        }
        default -> System.out.println("Opción incorrecta, intente de nuevo");
      }
    }
  }


  public static void crearProducto (ArrayList<Producto> productos) {
    System.out.println("Creando Nuevo Producto");
    entrada.nextLine();
    System.out.print("Ingrese el nombre del producto: ");
    String nombre = entrada.nextLine();


    System.out.print("Ingrese el precio: ");
    double precio = entrada.nextDouble();
    entrada.nextLine(); // limpiar el salto de línea

    System.out.print("Ingrese cantidad inicial: ");
    int cant = entrada.nextInt();
    entrada.nextLine(); // limpiar el salto de línea

    System.out.print("Ingrese descripción: ");
    String descripcion = entrada.nextLine();

    System.out.print("Ingrese categoría: ");
    String categoria = entrada.nextLine();

    productos.add(new Producto(nombre, precio, cant, descripcion, categoria));

    System.out.println("=======================================");
    System.out.println("✅ Producto creado exitosamente:");
    System.out.println("Nombre: " + nombre);
    System.out.println("Precio: " + precio);
    System.out.println("Cantidad: " + cant);
    System.out.println("Descripción: " + descripcion);
    System.out.println("Categoría: " + categoria);

    pausa();
  }


  public static void listarProductos(ArrayList<Producto> productos) {
    System.out.println("============================================================================================================================================");
    // Encabezado
    System.out.println("                                          LISTA DE PRODUCTOS                                           ");
    System.out.println("============================================================================================================================================");

    if (productos == null || productos.isEmpty()) {
      System.out.println("⚠️  No hay productos para mostrar.");
    } else {
      System.out.printf("%-5s | %-60s | %-12s | %-10s | %-20s | %-15s%n", "ID", "Nombre", "Precio", "Cant", "Descripción","Categoría");
      System.out.println("-------------------------------------------------------------------------------------------------------");
      //System.out.println("                                          LISTA DE PRODUCTOS                                           ");
      //System.out.println("=======================================================================================================");

      for (Producto producto : productos) {
        System.out.printf("%-5s | %-60s | %-12s | %-10s | %-20s | %-15s%n", producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getCant(), producto.getDescripcion(),producto.getCategoria(),acortarDescripcion(producto.getDescripcion(), 15));
      }
    }


    System.out.println("============================================================================================================================================");
    pausa();
  }
  private static String acortarDescripcion(String descripcion, int maxLength) {
    if (descripcion.length() <= maxLength) {
      return descripcion;
    } else {
      return descripcion.substring(0, maxLength - 3) + "...";
    }
  }
  public static void buscarProductoPorNombre(ArrayList<Producto> productos) {
    entrada.nextLine();
    System.out.println("Ingrese un nombre de un producto: ");
    String busqueda = entrada.nextLine().trim().toLowerCase();

    ArrayList<Producto> productoEncontrados = new ArrayList<>();

    for (Producto producto : productos) {
       if (estaIncluido(producto.getNombre(),busqueda)) {
           productoEncontrados.add(producto);
        }
    }

    listarProductos(productoEncontrados);

  }

  public static void editarProducto(List<Producto> productos) {

    Producto producto = obtenerProductoPorId(productos);
    if (producto == null) {
      System.out.println("No se puede editar el producto.");
      pausa();
      return; // cuando hacemos el return en una funcion void, estamos cortando la ejecucion de la funcion
    }

    String nombreOriginal = producto.getNombre();
    double precioOriginal = producto.getPrecio();
    String descripcionOriginal = producto.getDescripcion();
    String categoriaOriginal = producto.getCategoria();
    System.out.println("ingrese id del producto a editar");

    System.out.println(nombreOriginal);
    // TODO: validar que el usuario quiere editar el producto que se encontro
    System.out.print("Ingrese el nuevo nombre: ");
    entrada.nextLine();
    String nuevoNombre = entrada.nextLine();
    producto.setNombre(nuevoNombre);
    entrada.nextLine();
    System.out.println(precioOriginal);
    // TODO: validar que el usuario quiere editar el producto que se encontro
    System.out.print("Ingrese el nuevo precio: ");

    double nuevoPrecio = entrada.nextDouble();
    producto.setPrecio(nuevoPrecio);

    System.out.println(descripcionOriginal);
    entrada.nextLine();
    System.out.print("Ingrese el nuevo Descripcion: ");
    String nuevaDescripcion = entrada.nextLine();
    producto.setDescripcion(nuevaDescripcion);
    entrada.nextLine();
    System.out.println(categoriaOriginal);
    System.out.print("Ingrese el nuevo categoria: ");
    String nuevaCategoria = entrada.nextLine();
    producto.setCategoria(nuevaCategoria);

    producto.setNombre(nuevoNombre);
    producto.setPrecio(nuevoPrecio);
    producto.setDescripcion(nuevaDescripcion);
    producto.setCategoria(nuevaCategoria);
    System.out.printf("El nombre del producto cambio de %s a %s", nombreOriginal, nuevoNombre, nuevaDescripcion, nuevaCategoria);
  }

  public static void borrarProducto(List<Producto> productos) {
    System.out.println("ingrese id del producto a borrar");
    Producto producto = obtenerProductoPorId(productos);
    if (producto == null) {
      System.out.println("No pudimos borrar el producto");
      pausa();
      return; //
    }

    // TODO: validar que encontramos el indice
    String nombreOriginal = producto.getNombre();
    System.out.println("Producto a borrar:");
    System.out.println(nombreOriginal);
    productos.remove(producto);
    System.out.println("Producto ELIMINADO:");
    System.out.println(nombreOriginal);
    // TODO: validar que el usuario quiere borrar el producto que se encontro

    System.out.printf("El producto %s se borro", nombreOriginal);

    pausa();
  }

  /* UTILIDADES */
  /* Busqueda por id - ahora mismo solo funciona con el indice, en el futuro se va a cambiar */
  public static void filtroPorPrecio(List<Producto> productos) {
    double precioFiltro = entrada.nextDouble();

    ArrayList<Producto> productosFiltrados = new ArrayList<>();

    for (Producto producto : productos) {
      if (producto.getPrecio() <= precioFiltro) {
        productosFiltrados.add(producto);
      }
      listarProductos(productosFiltrados);
      }
    }


  public static Producto obtenerProductoPorId(List<Producto> productos) {
        // TODO: validacion de datos
    System.out.println("Ingrese el id del producto: ");
    int idBusqueda = entrada.nextInt();

    for (Producto producto : productos) {
      if (producto.coincideId(idBusqueda)) {
        return producto;
      }
    }
    System.out.println("No pudimos encontrar el producto con el id: " + idBusqueda);
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
    System.out.println("Pulse ENTER para continuar...");
    entrada = new Scanner(System.in);
    entrada.nextLine();
    for (int i = 0; i < 3; ++i) {
      System.out.println();
    }
    // TODO: limpiar la pantalla de la consola
  }


  public static ArrayList<Producto> obtenerProductosDecoracion() {
    ArrayList<Producto> productos = new ArrayList<>();

    productos.add(new Producto("Mesa TV+ Escritorio con tapa en madera clara",25000,40,"Mesa TV+ Escritorio", "living"));
    productos.add(new Producto("Comoda Vajillero modelo Pampa",150000,15,"vajillero","living"));
    productos.add(new Producto("Panel flotante de TV revistido en madera",80000,25,"rack television", "living"));
    productos.add(new Producto("Biblioteca Modelo kenia de melamina 18MM",250000,10,"biblioteca", "living"));
    productos.add(new Producto("Placard Nordico MODELO MONACO Escandinavo ",350000,17, "placard" , "dormitorio"));
    productos.add(new Producto("Biblioteca Paraiso Laqueado y Melamina Modelo Montevideo",290000,7, "biblioteca", "living"));
    productos.add(new Producto("lampara de pie, negra articulada",55000,75, "iluminacion" , "artefacto"));
    productos.add(new Producto("juego de mesa con 4 sillas estilo Nordico",135000,9, "mesa y sillas", "cocina"));
    productos.add(new Producto("sillon 2 cuerpos gamuza ",450000,5, "sillon", "living"));
    productos.add(new Producto("ALACENA superior JUANA puertas revatibles",147000,10, "alacena superior" , "cocina"));
    productos.add(new Producto("Cama Rustica de cedro moderna matrimonial",360000,25 ,"cama de madera" , "dormitorio"));

    return productos;
    }
  }

