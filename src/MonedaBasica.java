public class MonedaBasica  {

    private final String codigo;
    private final String nombre;

    public MonedaBasica(String codigo, String nombre) {
        this.codigo = codigo.toUpperCase();
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}
