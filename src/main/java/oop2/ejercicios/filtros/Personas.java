package oop2.ejercicios.filtros;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Personas {
    private static List<Persona> buscarPersonaCon(Predicate<Persona> predicate, List<Persona> p) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona persona : p) {
            if (predicate.test(persona)) {
                resultado.add(persona);
            }
        }
        return resultado;
    }

    //filtra la lista de personas devolviendo otra lista con
    //solo aquellas cuyo nombre comienza con E
    public List<Persona> nombresQueEmpiezanConE(List<Persona> p) {
        return buscarPersonaCon(persona -> persona.nombre().startsWith("E"), p);
    }

    public List<Persona> nombresCuyaCantidadDeLetrasEsPar(List<Persona> p) {
        return buscarPersonaCon(persona -> persona.nombre().length() % 2 == 0, p);
    }
}
