public class APIexcepcion extends Exception {
    public APIexcepcion(String message) {
        super(message);
    }

    public APIexcepcion(String message, Throwable cause) {
        super(message, cause);
    }
}