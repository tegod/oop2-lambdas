package oop2.lambdas.permissions1;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Productos {
    public static final String SIN_PERMISOS = "No tiene los permisos necesarios";
    private List<Producto> productos;
    private SecuritySubSystem security;

    public Productos(List<Producto> productos, SecuritySubSystem security) {
        this.security = security;
        this.productos = productos;
    }

    private <T> T chequeo(Supplier<T> T, String userId) {
        if (!this.security.checkPermission(userId)) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return T.get();
    }


    public void addProducto(String userId, Producto producto) {
        chequeo(() -> this.productos.add(producto), userId);
    }

    public void removeProducto(String userId, Producto producto) {
        chequeo(() -> this.productos.remove(producto), userId);
        this.productos.remove(producto);
    }

    public List<Producto> listAll(String userId) {
        return chequeo(() -> Collections.unmodifiableList(this.productos), userId);
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}
