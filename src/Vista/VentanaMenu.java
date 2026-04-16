package Vista;

import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {
    private SessionController session;
    private JLabel lblBienvenida;
    private JLabel lblSaldo;

    public VentanaMenu(SessionController session) {
        this.session = session;
        initComponents();
        setLocationRelativeTo(null); // Centra la ventana
    }

    private void initComponents() {
        setTitle("Menú Principal - Casino");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10)); // Layout organizado en filas

        lblBienvenida = new JLabel("Bienvenido: " + session.getNombreUsuario(), SwingConstants.CENTER);
        lblSaldo = new JLabel("Tu saldo es: $" + session.getRuletaActual().getSaldo(), SwingConstants.CENTER);

        JButton btnJugar = new JButton("Ir a la Ruleta");
        JButton btnSalir = new JButton("Cerrar Sesión");

        // ACCIÓN DEL BOTÓN JUGAR
        btnJugar.addActionListener(e -> {
            new VentanaRuleta(session).setVisible(true);
            // Si quieres que el menú se cierre al abrir la ruleta, usa: this.dispose();
        });

        // ACCIÓN DEL BOTÓN SALIR
        btnSalir.addActionListener(e -> {
            new VentanaLogin(session).setVisible(true);
            this.dispose();
        });

        add(lblBienvenida);
        add(lblSaldo);
        add(btnJugar);
        add(btnSalir);
    }

    // Método útil para cuando vuelvas de la ruleta y quieras ver tu nuevo saldo
    public void refrescarDatos() {
        lblSaldo.setText("Tu saldo es: $" + session.getRuletaActual().getSaldo());
    }
}