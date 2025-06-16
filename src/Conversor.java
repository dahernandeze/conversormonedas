import java.util.Map;
import java.util.HashMap;

public class Conversor {
    private final ConsultarMonedas api;
    private final Map<String, MonedaBasica> monedas;

    public Conversor() {
        this.api = new ConsultarMonedas();
        this.monedas = inicializarMonedas();
    }

    private Map<String, MonedaBasica> inicializarMonedas() {
        Map<String, MonedaBasica> monedas = new HashMap<>();
        monedas.put("USD", new MonedaBasica("USD", "Dólar estadounidense"));
        monedas.put("EUR", new MonedaBasica("EUR", "Euro"));
        monedas.put("BRL", new MonedaBasica("BRL", "Real brasileño"));
        monedas.put("MXN", new MonedaBasica("MXN", "Peso mexicano"));
        monedas.put("COP", new MonedaBasica("COP", "Peso colombiano"));
        monedas.put("ARS", new MonedaBasica("ARS", "Peso argentino"));
        monedas.put("CLP", new MonedaBasica("CLP", "Peso chileno"));
        monedas.put("PEN", new MonedaBasica("PEN", "Sol peruano"));
        monedas.put("GBP", new MonedaBasica("GBP", "Libra esterlina"));
        monedas.put("JPY", new MonedaBasica("JPY", "Yen japonés"));
        monedas.put("KRW", new MonedaBasica("KRW", "Won Surcoreano"));
        monedas.put("CNY", new MonedaBasica("CNY", "Renminbi Chino"));
        monedas.put("CRC", new MonedaBasica("CRC", "Colon Costarricense"));
        return monedas;
    }


    public double convertir(double cantidad, String monedaOrigen, String monedaDestino) throws APIexcepcion {
        try {
            double tasa = api.obtenerTasaCambio(monedaOrigen, monedaDestino);
            return cantidad * tasa;
        } catch (APIexcepcion e) {
            throw new APIexcepcion("Error al convertir de " + monedaOrigen + " a " +
                    monedaDestino + ": " + e.getMessage(), e);
        }
    }
    public void mostrarMonedasDisponibles() {
        System.out.println("\n=== Monedas Disponibles ===");
        monedas.values().forEach(moneda ->
                System.out.printf("%s (%s)\n", moneda.getCodigo(), moneda.getNombre())
        );
    }
    public String obtenerNombreMoneda(String codigo) {
        MonedaBasica moneda = monedas.get(codigo.toUpperCase());
        return moneda != null ? moneda.getNombre() : "Desconocida";
    }
    public boolean existeMoneda(String codigo) {
        return monedas.containsKey(codigo.toUpperCase());
    }


}
