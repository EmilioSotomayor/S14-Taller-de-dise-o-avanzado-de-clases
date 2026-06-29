import java.util.ArrayList;

public class Socio {

    public static final double FONDOS_INICIALES_REGULARES = 50000;
    public static final double FONDOS_INICIALES_VIP       = 100000;
    public static final double MONTO_MAXIMO_REGULARES     = 1000000;
    public static final double MONTO_MAXIMO_VIP           = 5000000;

    private String cedula;
    private String nombre;
    private double fondos;
    private Tipo tipoSuscripcion;
    private ArrayList<Factura> facturas;
    private ArrayList<String>  autorizados;

    public Socio(String cedula, String nombre, Tipo tipo) {
        this.cedula          = cedula;
        this.nombre          = nombre;
        this.tipoSuscripcion = tipo;
        this.fondos = (tipo == Tipo.VIP)
                ? FONDOS_INICIALES_VIP
                : FONDOS_INICIALES_REGULARES;
        this.facturas    = new ArrayList<>();
        this.autorizados = new ArrayList<>();
    }

    public String darNombre()                  { return nombre; }
    public String darCedula()                  { return cedula; }
    public double darFondos()                  { return fondos; }
    public Tipo   darTipo()                    { return tipoSuscripcion; }
    public ArrayList<Factura> darFacturas()    { return facturas; }
    public ArrayList<String>  darAutorizados() { return autorizados; }

    public boolean existeAutorizado(String nombreAut) {
        return autorizados.contains(nombreAut);
    }

    public boolean tieneFacturaAsociada(String nombreAut) {
        for (Factura f : facturas)
            if (f.darNombre().equalsIgnoreCase(nombreAut)) return true;
        return false;
    }

    public void aumentarFondo(double monto) {
        double max = (tipoSuscripcion == Tipo.VIP)
                ? MONTO_MAXIMO_VIP
                : MONTO_MAXIMO_REGULARES;
        if (fondos + monto > max)
            throw new RuntimeException(
                    "Supera el monto maximo permitido para el tipo de suscripcion.");
        fondos += monto;
    }

    public void registrarConsumo(String concepto, String nombreConsumidor, double valor) {
        if (fondos < valor)
            throw new RuntimeException(
                    "Fondos insuficientes para registrar el consumo.");
        facturas.add(new Factura(concepto, nombreConsumidor, valor));
    }

    public void agregarAutorizado(String nombreAut) {
        if (fondos <= 0)
            throw new RuntimeException(
                    "El socio no cuenta con fondos para agregar un autorizado.");
        if (existeAutorizado(nombreAut))
            throw new RuntimeException("El autorizado ya existe en la lista.");
        autorizados.add(nombreAut);
    }

    public void eliminarAutorizado(String nombreAut) {
        if (!existeAutorizado(nombreAut))
            throw new RuntimeException("El autorizado no existe.");
        if (tieneFacturaAsociada(nombreAut))
            throw new RuntimeException(
                    "El autorizado tiene facturas pendientes, no puede eliminarse.");
        autorizados.remove(nombreAut);
    }

    public void pagarFactura(int indice) {
        if (indice < 0 || indice >= facturas.size())
            throw new RuntimeException("Indice de factura invalido.");
        Factura f = facturas.get(indice);
        if (fondos < f.darValor())
            throw new RuntimeException(
                    "Fondos insuficientes para pagar la factura.");
        fondos -= f.darValor();
        facturas.remove(indice);
    }

    @Override
    public String toString() {
        return "Socio{cedula='" + cedula + "', nombre='" + nombre
                + "', fondos=" + fondos + ", tipo=" + tipoSuscripcion + "}";
    }
}
