public class Producto {

  int id;
  String nombre;
  double precioP;
  int cantP;

  // metodo constructor
  public Producto(int idProducto, String nombreProducto, double precioProducto,int cantidadStock) {
    id = idProducto;
    nombre = nombreProducto;
    precioP = precioProducto;
    cantP = cantidadStock;
  }
}