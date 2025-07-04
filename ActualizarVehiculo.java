//import conectsupabase.models.Vehiculo;
import java.util.Scanner;

public class ActualizarVehiculo {
    private VehiculoService vehiculoService;
    private Scanner scanner;

    public ActualizarVehiculo(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("\n=== ACTUALIZAR VEHÍCULO ===");
        System.out.print("Ingrese el ID del vehículo a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return;
        }

        System.out.println("\nInformación actual del vehículo:");
        System.out.println("1. Marca: " + vehiculo.getmarca());
        System.out.println("2. Color: " + vehiculo.getColor());
        System.out.println("3. Fecha de Fabricación: " + vehiculo.getFecha_fabricacion());

        System.out.print("\nSeleccione qué desea actualizar (ej: 1,3): ");
        String seleccion = scanner.nextLine();

        String[] opciones = seleccion.split(",");

        for (String opcionStr : opciones) {
            try {
                int opcion = Integer.parseInt(opcionStr.trim());

                switch (opcion) {
                    case 1:
                        System.out.print("Nueva Marca: ");
                        vehiculo.setmarca(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Nuevo Color: ");
                        vehiculo.setColor(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Nueva Fecha de Fabricación: ");
                        vehiculo.setFecha_fabricacion(scanner.nextLine());
                        break;
                    default:
                        System.out.println("Opción " + opcion + " no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: " + opcionStr);
            }
        }

        vehiculoService.actualizarVehiculo(vehiculo);
    }
}
