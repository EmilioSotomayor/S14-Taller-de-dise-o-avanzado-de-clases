public class Main {
    public static void main(String[] args) {

        Club club = new Club();
       
        club.afiliarSocio("001", "Ana Lopez",    Tipo.REGULAR);
        club.afiliarSocio("002", "Juan Perez",   Tipo.REGULAR);
        club.afiliarSocio("003", "Maria Ruiz",   Tipo.VIP);
        club.afiliarSocio("010", "Pedro VIP",    Tipo.VIP);
        club.afiliarSocio("020", "Luis Regular", Tipo.REGULAR);
        club.afiliarSocio("030", "Sara Multi",   Tipo.REGULAR);
        club.afiliarSocio("040", "Eve Limpia",   Tipo.REGULAR);
        club.afiliarSocio("050", "Tom Uno",      Tipo.REGULAR);

        club.registrarConsumo("002", "Restaurante",   "Juan Perez", 15000);
        club.registrarConsumo("003", "Bar",            "Maria Ruiz", 10000);
        club.registrarConsumo("003", "Piscina",        "Maria Ruiz", 20000);
        club.registrarConsumo("003", "Cancha tenis",   "Maria Ruiz",  5000);
        club.registrarConsumo("020", "Restaurante",   "Luis Regular", 8000);

       
        club.agregarAutorizadoSocio("030", "Autorizado1");
        club.agregarAutorizadoSocio("030", "Autorizado2");
        club.agregarAutorizadoSocio("050", "Autorizado1");

        System.out.println("========== METODO 1: totalConsumosSocio ==========");

      
        System.out.println("CP-01: " + club.metodo1("001"));

        System.out.println("CP-02: " + club.metodo1("002"));

        System.out.println("CP-03: " + club.metodo1("003"));

    
        try {
            club.metodo1("999");
        } catch (RuntimeException e) {
            System.out.println("CP-04 (excepcion): " + e.getMessage());
        }

        System.out.println("\n========= METODO 2: sePuedeEliminarSocio =========");

  
        try {
            club.metodo2("999");
        } catch (RuntimeException e) {
            System.out.println("CP-05 (excepcion): " + e.getMessage());
        }

        System.out.println("CP-06: " + club.metodo2("010"));

        System.out.println("CP-07: " + club.metodo2("020"));

        System.out.println("CP-08: " + club.metodo2("030"));

        System.out.println("CP-09: " + club.metodo2("040"));

        System.out.println("CP-10: " + club.metodo2("050"));
    }
}
