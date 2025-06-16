import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultarMonedas {

    private static final String API_KEY = "f88945e13f8ad6b30972f6f5";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private final Gson gson = new Gson();

    public double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws APIexcepcion{
        try {
            String jsonResponse = hacerRequerimientoAPI(monedaOrigen);
            MonedaAPI response = parsearRespuesta(jsonResponse);

            validarRespuesta(response);
            return response.obtenerTasa(monedaDestino);
        }catch (IOException e) {
            throw new APIexcepcion("Error de conexión con la API: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new APIexcepcion("Error en los datos recibidos: " + e.getMessage(), e);
        }
    }

    private String hacerRequerimientoAPI(String monedaOrigen) throws IOException {
        URL url = new URL(BASE_URL + monedaOrigen);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Código de respuesta HTTP: " + responseCode);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
    private MonedaAPI parsearRespuesta(String json) {
        return gson.fromJson(json, MonedaAPI.class);
    }
    private void validarRespuesta(MonedaAPI response) throws APIexcepcion {
        if (response == null) {
            throw new APIexcepcion("Respuesta de la API es nula");
        }

        if (!"success".equalsIgnoreCase(response.getResult())) {
            throw new APIexcepcion("La API reportó un error en la respuesta");
        }

        if (response.getConversion_rates() == null || response.getConversion_rates().isEmpty()) {
            throw new APIexcepcion("No se encontraron tasas de conversión en la respuesta");
        }
}
}
