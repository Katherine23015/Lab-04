package Vista;

import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private SessionController session;
    private JTextField txtUser, txtNombre;
    private JPasswordField txtPass;
    private JButton btnRegistrar, btnVolver;

    public VentanaRegistro(SessionController session) {
        this.session = session;
        initComponents();
    }

    private void ejecutarRegistro() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());
        String nombre = txtNombre.getText();

        try {
            session.registrarUsuario(user, pass, nombre);

            JOptionPane.showMessageDialog(this, "Registro exitoso. Ahora puede iniciar sesión.");
            volverAlLogin();

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverAlLogin() {
        new VentanaLogin(session).setVisible(true);
        this.dispose();
    }

    private void initComponents() {
    }
}
