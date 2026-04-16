package Controlador;

import Modelo.Usuario;
import Modelo.Ruleta;

public class SessionController {
    private Usuario usuarioActual;
    private Ruleta ruletaActual = new Ruleta(5000);

    public SessionController() {
        this.usuarioActual = new Usuario("admin", "123", "Administrador");
    }

    public void registrarUsuario(String u, String p, String n) {
        this.usuarioActual = new Usuario(u, p, n);
    }

    public boolean iniciarSesion(String u, String p) {
        if (usuarioActual != null) {
            return usuarioActual.validarCredenciales(u, p);
        }
        return false;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public String getNombreUsuario() {
        return (usuarioActual != null) ? usuarioActual.getNombre() : "";
    }

    public Ruleta getRuletaActual() {
        return ruletaActual;
    }
}