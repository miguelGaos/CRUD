public class Vehiculo {
    private int id_vehiculo;
    private String marca;
    private String color;
    private String fecha_fabricacion;

    public Vehiculo() {}

    public Vehiculo(int id_vehiculo, String marca, String color, String fecha_fabricacion) {
        this.id_vehiculo = id_vehiculo;
        this.marca = marca;
        this.color = color;
        this.fecha_fabricacion = fecha_fabricacion;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getmarca() {
        return marca;
    }

    public void setmarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFecha_fabricacion() {
        return fecha_fabricacion;
    }

    public void setFecha_fabricacion(String fecha_fabricacion) {
        this.fecha_fabricacion = fecha_fabricacion;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id_vehiculo=" + id_vehiculo +
                ", marca='" + marca + '\'' +
                ", color='" + color + '\'' +
                ", fecha_fabricacion='" + fecha_fabricacion + '\'' +
                '}';
    }
}