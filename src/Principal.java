import java.util.Scanner;

static Scanner scanner = new Scanner(System.in);
static Conversor conversorService = new Conversor();

public static void main(String[] args) {
    mostrarBienvenida();
    mostrarMonedas();
    while (true) {

        mostrarMenuPrincipal();
        int opcion = leerOpcion();

        switch (opcion) {
            case 1:
                realizarConversion();
                break;
            case 2:
                mostrarMonedas();
                break;
            case 3:
                salir();
                return;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}



public static void mostrarBienvenida() {
    System.out.println("====================================");
    System.out.println("  CONVERSOR DE MONEDAS - ALURA LATAM");
    System.out.println("====================================");
}

public static void mostrarMenuPrincipal() {
    System.out.println("\nMenú Principal:");
    System.out.println("1. Realizar conversión");
    System.out.println("2. Ver monedas disponibles");
    System.out.println("3. Salir");
    System.out.print("Seleccione una opción: ");
}

public static int leerOpcion() {
    while (!scanner.hasNextInt()) {
        System.out.print("Por favor, ingrese un número válido (1-3): ");
        scanner.next();
    }
    return scanner.nextInt();
}

 public static void realizarConversion() {
    System.out.println("\n=== Conversión de Monedas ===");

    String monedaOrigen = leerMoneda("origen");
    String monedaDestino = leerMoneda("destino");
    double cantidad = leerCantidad();

    try {
        double resultado = conversorService.convertir(cantidad, monedaOrigen, monedaDestino);
        mostrarResultado(cantidad, monedaOrigen, resultado, monedaDestino);
    } catch (APIexcepcion e) {
        System.out.println("\nError: " + e.getMessage());
        System.out.println("Por favor, intente nuevamente.");
    }
}

private static String leerMoneda(String tipo) {
    while (true) {
        System.out.printf("Ingrese código de moneda de %s (ej: USD, EUR): ", tipo);
        String codigo = scanner.next().toUpperCase();

        if (conversorService.existeMoneda(codigo)) {
            return codigo;
        }

        System.out.println("Moneda no válida. Códigos soportados:");
        conversorService.mostrarMonedasDisponibles();
    }
}

private static double leerCantidad() {
    System.out.print("Ingrese la cantidad a convertir: ");
    while (!scanner.hasNextDouble()) {
        System.out.print("Por favor, ingrese un número válido: ");
        scanner.next();
    }
    return scanner.nextDouble();
}

private static void mostrarResultado(double cantidad, String origen, double resultado, String destino) {
    System.out.println("\n=== Resultado ===");
    System.out.printf("%.2f %s (%s) = %.2f %s (%s)\n",
            cantidad,
            origen,
            conversorService.obtenerNombreMoneda(origen),
            resultado,
            destino,
            conversorService.obtenerNombreMoneda(destino));
}

 public static void mostrarMonedas() {
    conversorService.mostrarMonedasDisponibles();
}

 public static void salir() {
    System.out.println("\nGracias por usar el Conversor de Monedas. ¡Hasta pronto!");
    scanner.close();
}



