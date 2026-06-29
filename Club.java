import java.util.ArrayList;

public class Club {

    public static final int MAXIMO_VIP = 3;
    private ArrayList<Socio> socios;

    public Club() {
        socios = new ArrayList<>();
    }

    public ArrayList<Socio> darSocios() { return socios; }

    // ── Búsqueda ──────────────────────────────────────────────────────────
    public Socio buscarSocio(String cedula) {
        for (Socio s : socios)
            if (s.darCedula().equals(cedula)) return s;
        return null;
    }

    public int contarSociosVIP() {
        int c = 0;
        for (Socio s : socios)
            if (s.darTipo() == Tipo.VIP) c++;
        return c;
    }

    public void afiliarSocio(String cedula, String nombre, Tipo tipo) {
        if (buscarSocio(cedula) != null)
            throw new RuntimeException("Ya existe socio con cedula: " + cedula);
        if (tipo == Tipo.VIP && contarSociosVIP() >= MAXIMO_VIP)
            throw new RuntimeException("Maximo de socios VIP alcanzado.");
        socios.add(new Socio(cedula, nombre, tipo));
    }

    public void agregarAutorizadoSocio(String cedula, String nombreAut) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException("No existe socio con cedula: " + cedula);
        s.agregarAutorizado(nombreAut);
    }

    public void pagarFacturaSocio(String cedula, int indice) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException("No existe socio con cedula: " + cedula);
        s.pagarFactura(indice);
    }

    public void registrarConsumo(String cedula, String concepto,
                                 String nombreConsumidor, double valor) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException("No existe socio con cedula: " + cedula);
        s.registrarConsumo(concepto, nombreConsumidor, valor);
    }

    public void aumentarFondosSocio(String cedula, double monto) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException("No existe socio con cedula: " + cedula);
        s.aumentarFondo(monto);
    }

    public void eliminarAutorizadoSocio(String cedula, String nombreAut) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException("No existe socio con cedula: " + cedula);
        s.eliminarAutorizado(nombreAut);
    }

    public ArrayList<Factura> darFacturasSocio(String cedula) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException("No existe socio con cedula: " + cedula);
        return s.darFacturas();
    }

    public ArrayList<String> darAutorizadosSocio(String cedula) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException("No existe socio con cedula: " + cedula);
        return s.darAutorizados();
    }


    public double metodo1(String cedula) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException(
                    "No existe un socio afiliado con la cedula: " + cedula);
        double total = 0;
        for (Factura f : s.darFacturas())
            total += f.darValor();
        return total;
    }


    public String metodo2(String cedula) {
        Socio s = buscarSocio(cedula);
        if (s == null)
            throw new RuntimeException(
                    "No existe un socio con la cedula: " + cedula);
        if (s.darTipo() == Tipo.VIP)
            return "El socio no puede ser eliminado: es de tipo VIP.";
        if (!s.darFacturas().isEmpty())
            return "El socio no puede ser eliminado: tiene "
                    + s.darFacturas().size() + " factura(s) pendiente(s) de pago.";
        if (s.darAutorizados().size() > 1)
            return "El socio no puede ser eliminado: tiene mas de un autorizado ("
                    + s.darAutorizados().size() + ").";
        return "El socio SI puede ser eliminado.";
    }
}
