import java.util.Scanner;

public class Main {
    private static VehiculoService VehiculoService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehiculoService = new VehiculoService(); // ¡Instanciado correctamente!

        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE VEHÍCULOS ===");
            System.out.println("1. Crear nuevo vehículo");
            System.out.println("2. Leer información de vehículo");
            System.out.println("3. Actualizar vehículo");
            System.out.println("4. Borrar vehículo");
            System.out.println("5. Listar todos los vehículos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    new CrearVehiculo(VehiculoService).ejecutar();
                    break;
                case 2:
                    new LeerVehiculo(VehiculoService).ejecutar();
                    break;
                case 3:
                    new ActualizarVehiculo(VehiculoService).ejecutar();
                    break;
                case 4:
                    new BorrarVehiculo(VehiculoService).ejecutar();
                    break;
                case 5:
                    VehiculoService.listarTodosVehiculos();
                    break;
                case 6:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}
