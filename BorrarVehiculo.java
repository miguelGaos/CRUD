import java.util.Scanner;

public class BorrarVehiculo {
    private VehiculoService vehiculoService;
    private Scanner scanner;

    public BorrarVehiculo(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("\n=== BORRAR VEHÍCULO ===");
        System.out.println("1. Borrar por ID");
        System.out.println("2. Borrar por color");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el ID del vehículo: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("¿Está seguro de borrar el vehículo? (s/n): ");
                String confirmacion1 = scanner.nextLine();

                if (confirmacion1.equalsIgnoreCase("s")) {
                    vehiculoService.borrarVehiculo(id);
                } else {
                    System.out.println("Operación cancelada.");
                }
                break;

            case 2:
                System.out.print("Ingrese el color del vehículo: ");
                String color = scanner.nextLine();

                Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorColor(color);

                if (vehiculo != null) {
                    System.out.println("Vehículo encontrado:");
                    System.out.println("ID: " + vehiculo.getId_vehiculo());
                    System.out.println("Marca: " + vehiculo.getmarca());

                    System.out.print("¿Está seguro de borrarlo? (s/n): ");
                    String confirmacion2 = scanner.nextLine();

                    if (confirmacion2.equalsIgnoreCase("s")) {
                        vehiculoService.borrarVehiculo(vehiculo.getId_vehiculo());
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                } else {
                    System.out.println("Vehículo no encontrado.");
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }
}