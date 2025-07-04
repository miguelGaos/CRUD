// import conectsupabase.models.Vehiculo;
import java.util.Scanner;

public class CrearVehiculo {
    private VehiculoService vehiculoService;
    private Scanner scanner;

    public CrearVehiculo(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("\n=== CREAR NUEVO VEHÍCULO ===");

        Vehiculo nuevoVehiculo = new Vehiculo();

        System.out.print("marca: ");
        nuevoVehiculo.setmarca(scanner.nextLine());

        System.out.print("Color: ");
        nuevoVehiculo.setColor(scanner.nextLine());

        System.out.print("Fecha de Fabricación: ");
        nuevoVehiculo.setFecha_fabricacion(scanner.nextLine());

        vehiculoService.crearVehiculo(nuevoVehiculo);
    }
}