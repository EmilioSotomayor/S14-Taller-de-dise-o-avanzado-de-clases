public class Factura {
    private String concepto;
    private double valor;
    private String nombre;

    public Factura(String concepto, String nombre, double valor) {
        this.concepto = concepto;
        this.nombre   = nombre;
        this.valor    = valor;
    }

    public String darConcepto() { return concepto; }
    public double darValor()    { return valor;    }
    public String darNombre()   { return nombre;   }

    @Override
    public String toString() {
        return "Factura{concepto='" + concepto + "', valor=" + valor
                + ", nombre='" + nombre + "'}";
    }
}
