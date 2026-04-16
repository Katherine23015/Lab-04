package Vista;

import Controlador.SessionController;
import Modelo.TipoApuesta; // IMPORTANTE: Para que no salga en rojo
import javax.swing.*;
import java.awt.*;

public class VentanaRuleta extends JFrame {
    private SessionController session;

    private JLabel lblSaldo = new JLabel();
    private JTextField txtMontoApuesta = new JTextField(10);
    private JComboBox<TipoApuesta> cboTipoApuesta = new JComboBox<>(TipoApuesta.values());
    private JButton btnGirar = new JButton("Girar Ruleta");

    public VentanaRuleta(SessionController session) {
        this.session = session;
        setupLayout();
        refrescarSaldo();
    }

    private void refrescarSaldo() {
        lblSaldo.setText("Saldo disponible: $" + session.getRuletaActual().getSaldo());
    }

    private void ejecutarGiro() {
        try {
            int monto = Integer.parseInt(txtMontoApuesta.getText());
            TipoApuesta seleccion = (TipoApuesta) cboTipoApuesta.getSelectedItem();

            if (session.getRuletaActual().descontarApuesta(monto)) {
                int numeroGanador = (int) (Math.random() * 37);
                boolean gano = session.getRuletaActual().evaluarResultado(numeroGanador, seleccion);

                if (gano) {
                    int premio = monto * 2;
                    session.getRuletaActual().depositar(premio);
                    JOptionPane.showMessageDialog(this, "¡Salió el " + numeroGanador + "! Ganaste: $" + premio);
                } else {
                    JOptionPane.showMessageDialog(this, "Salió el " + numeroGanador + ". Perdiste.");
                }

                refrescarSaldo();
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente o monto inválido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un monto válido.");
        }
    }

    private void setupLayout() {
        this.setLayout(new FlowLayout());
        this.add(new JLabel("Tu apuesta:"));
        this.add(txtMontoApuesta);
        this.add(cboTipoApuesta);
        this.add(btnGirar);
        this.add(lblSaldo);

        btnGirar.addActionListener(e -> ejecutarGiro());

        this.setTitle("Juego de Ruleta");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
