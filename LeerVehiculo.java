//import conectsupabase.models.Vehiculo;
import java.util.Scanner;

public class LeerVehiculo {
    private VehiculoService vehiculoService;
    private Scanner scanner;

    public LeerVehiculo(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("\n=== CONSULTAR VEHÍCULO ===");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por color");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Vehiculo vehiculo = null;

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el ID del vehículo: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                vehiculo = vehiculoService.obtenerVehiculoPorId(id);
                break;
            case 2:
                System.out.print("Ingrese el color del vehículo: ");
                String color = scanner.nextLine();
                vehiculo = vehiculoService.obtenerVehiculoPorColor(color);
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (vehiculo != null) {
            System.out.println("\n=== INFORMACIÓN DEL VEHÍCULO ===");
            System.out.println("ID: " + vehiculo.getId_vehiculo());
            System.out.println("marca: " + vehiculo.getmarca());
            System.out.println("Color: " + vehiculo.getColor());
            System.out.println("Fecha de Fabricación: " + vehiculo.getFecha_fabricacion());
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }
}