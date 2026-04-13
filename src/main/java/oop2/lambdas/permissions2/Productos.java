package oop2.lambdas.permissions2;

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

    private <T> T extracted(Supplier<Boolean> CheckIf, Supplier<T> runnable) {
        if (!CheckIf.get()) {
            throw new RuntimeException(SIN_PERMISOS);
        }
        return runnable.get();
    }

    public void addProducto(String userId, Producto producto) {
        extracted(() -> this.security.checkAddPermission(userId), () -> this.productos.add(producto));
    }

    public void removeProducto(String userId, Producto producto) {
        extracted(() -> this.security.checkRemovePermission(userId), () -> this.productos.remove(producto));
    }

    public List<Producto> listAll(String userId) {
        return extracted(() -> this.security.checkListPermission(userId), () -> this.productos);
    }

    int cantidad() {
        return this.productos.size();
    }

    boolean contiene(Producto unProducto) {
        return this.productos.contains(unProducto);
    }
}
