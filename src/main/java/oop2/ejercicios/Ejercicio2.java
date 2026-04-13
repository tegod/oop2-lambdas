package oop2.ejercicios;

public class Ejercicio2 {
    public static void main(String[] args) {
        AprendiendoLambdas c = new AprendiendoLambdas();
        c.unMetodo((String variable) -> variable.length() % 2 == 0);
        /*c.unMetodo((String b) -> {
            if (b.length() % 2 == 0) {
                return true;
            }
            return false;
        });*/

        c.unMetodo((String variable) -> variable.startsWith("a"));
    }
}
