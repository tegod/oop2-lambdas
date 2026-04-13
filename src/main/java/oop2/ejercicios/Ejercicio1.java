package oop2.ejercicios;

@FunctionalInterface
interface A {
    void metodo();
}

@FunctionalInterface
interface B {
    void metodo(String b);
}

@FunctionalInterface
interface C {
    boolean metodo(String c);
}

@FunctionalInterface
interface D<T, R> {
    R metodo(T c);
}

class AprendiendoLambdas {
    //Runnable, ejecuta codigo sin pasar parametros ni recibir datos.
    public void unMetodo(A a) {
        a.metodo();
    }

    //Consumer, recibe algun dato por parametro para utilizarlo sin devolver nada.
    public void unMetodo(B b) {
        b.metodo("unString");
    }

    //Predicate, recibe un parametro y devuelve un dato booleano.
    public void unMetodo(C c) {
        System.out.println(c.metodo("otroString") ? "true" : "false");
    }

    //Function, recibe un dato T devolviendo como resultado un dato tipo R.
    public void unMetodo(D<Long, Long> d) {
        d.metodo(10L);
    }
}

public class Ejercicio1 {
    public static void main(String[] args) {
        AprendiendoLambdas a = new AprendiendoLambdas();
        //este lambda invoca al unMetodo B, ya que es el que usa el dato recibido por parametro
        a.unMetodo((b) -> {
            System.out.println("abcd" + b);
        });
        //aqui se invoca el unMetodo A, que es el que no le pasa nada y aun asi realiza alguna accion.
        a.unMetodo(() -> System.out.println("abcd"));
        //esta lambda invoca al unMetodo C ya que es el que al recibir un dato es el que devuelve un booleano.
        a.unMetodo((String variable) -> {
            System.out.println("abcd");
            return true;
        });
        //esta invoca el metodo D, el cual se encarga de recibir un dato y retornar otro, en este caso 10L.
        a.unMetodo((Long variable) -> {
            System.out.println("abcd");
            return 10L;
        });
    }
}

