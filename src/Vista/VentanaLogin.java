package Vista;

import Controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private SessionController session;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public VentanaLogin(SessionController session) {
        this.session = session;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void intentarLogin() {
        String u = txtUser.getText().trim();
        String p = new String(txtPass.getPassword()).trim();

        if (session.iniciarSesion(u, p)) {
            new VentanaMenu(session).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }

    private void initComponents() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        txtUser = new JTextField(15);
        txtPass = new JPasswordField(15);
        btnLogin = new JButton("Entrar");

        add(new JLabel("Usuario:"));
        add(txtUser);
        add(new JLabel("Clave:"));
        add(txtPass);
        add(btnLogin);

        btnLogin.addActionListener(e -> intentarLogin());
    }
}