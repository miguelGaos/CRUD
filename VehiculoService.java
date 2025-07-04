//import conectsupabase.models.Vehiculo;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class VehiculoService {
    private static final String SUPABASE_URL = "https://wsbbkykplsmlfeaglmad.supabase.co/rest/v1/vehiculos";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndzYmJreWtwbHNtbGZlYWdsbWFkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTA3OTM5NjMsImV4cCI6MjA2NjM2OTk2M30.EhQ8hD_h_6n7_DaP7-91CTqKrk3Nk0w2Rni_4XtJAt4";

    private Scanner scanner = new Scanner(System.in);

    public void crearVehiculo(Vehiculo vehiculo) {
        try {
            JSONObject vehiculoJson = new JSONObject();
            vehiculoJson.put("marca", vehiculo.getmarca());
            vehiculoJson.put("color", vehiculo.getColor());
            vehiculoJson.put("fecha_fabricacion", vehiculo.getFecha_fabricacion());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=representation")
                    .POST(HttpRequest.BodyPublishers.ofString(vehiculoJson.toString()))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("Vehículo creado exitosamente!");
            } else {
                System.out.println("Error al crear vehículo: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Vehiculo obtenerVehiculoPorId(int id_vehiculo) {
        try {
            String url = SUPABASE_URL + "?id_vehiculo=eq." + id_vehiculo;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 && !response.body().equals("[]")) {
                JSONArray jsonArray = new JSONArray(response.body());
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                return new Vehiculo(
                        jsonObject.getInt("id_vehiculo"),
                        jsonObject.getString("marca"),
                        jsonObject.getString("color"),
                        jsonObject.getString("fecha_fabricacion")
                );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void actualizarVehiculo(Vehiculo vehiculo) {
        try {
            JSONObject vehiculoJson = new JSONObject();
            vehiculoJson.put("Marca", vehiculo.getmarca());
            vehiculoJson.put("color", vehiculo.getColor());
            vehiculoJson.put("fecha_fabricacion", vehiculo.getFecha_fabricacion());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + "?id_vehiculo=eq." + vehiculo.getId_vehiculo()))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(vehiculoJson.toString()))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 204) {
                System.out.println("Vehículo actualizado exitosamente!");
            } else {
                System.out.println("Error al actualizar vehículo: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void borrarVehiculo(int id_vehiculo) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL + "?id_vehiculo=eq." + id_vehiculo))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .DELETE()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 204) {
                System.out.println("Vehículo borrado exitosamente!");
            } else {
                System.out.println("Error al borrar vehículo: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listarTodosVehiculos() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SUPABASE_URL))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray jsonArray = new JSONArray(response.body());
                System.out.println("\n=== LISTA DE VEHÍCULOS ===");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println("ID: " + jsonObject.getInt("id_vehiculo"));
                    System.out.println("marca: " + jsonObject.getString("marca"));
                    System.out.println("Color: " + jsonObject.getString("color"));
                    System.out.println("-----------------------------");
                }
            } else {
                System.out.println("Error al obtener vehículos: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Vehiculo obtenerVehiculoPorColor(String color) {
        try {
            String url = SUPABASE_URL + "?color=eq." + color;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 && !response.body().equals("[]")) {
                JSONArray jsonArray = new JSONArray(response.body());
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                return new Vehiculo(
                        jsonObject.getInt("id_vehiculo"),
                        jsonObject.getString("marca"),
                        jsonObject.getString("color"),
                        jsonObject.getString("fecha_fabricacion")
                );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
