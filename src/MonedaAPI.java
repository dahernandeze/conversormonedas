import java.util.Map;

public class MonedaAPI {
    private String result;
    private String documentation;
    private String terms_of_use;
    private long time_last_update_unix;
    private String time_last_update_utc;
    private String time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;
    private Map<String, Double> conversion_rates;

    // Getters y Setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBase_code() {
        return base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    // Método para obtener tasa específica
    public double obtenerTasa(String currency) {
        if (conversion_rates == null || !conversion_rates.containsKey(currency)) {
            throw new IllegalArgumentException("Moneda no encontrada: " + currency);
        }
        return conversion_rates.get(currency);
    }
}
