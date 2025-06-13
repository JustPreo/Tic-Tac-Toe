/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tictactoe;

/**
 *
 * @author Hp
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends javax.swing.JFrame {

    private Usuario UsuarioLogueado;
    
    private JFrame VentanaAnterior;
    private JFrame VentanaSiguiente;
    
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        configurarEventos();
        configurarInterfaz();
    }
    
    public Login(JFrame VentanaAnterior) {
        this.VentanaAnterior = VentanaAnterior;
        initComponents();
        configurarEventos();
        configurarInterfaz();
    }
    
     private void configurarEventos() {
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcesarLogin();
            }
        });
        
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VolverVentanaAnterior();
            }
        });
        
        LoginField.addActionListener(e -> PasswordField.requestFocus());
        PasswordField.addActionListener(e -> procesarLogin());
    }
     
    private void configurarInterfaz() {
        setTitle("Iniciar Sesion - TicTacTOE");
        setLocationRelativeTo(null);
        setResizable(false);
        
        configurarPlaceholder(LoginField, "Usuario");
    }

    private void configurarPlaceholder(JTextField Field, String Placeholder) {
        Field.setForeground(Color.GRAY);
        Field.setText(Placeholder);
        
        Field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (Field.getText().equals(Placeholder)) {
                    Field.setText("");
                    Field.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (Field.getText().isEmpty()) {
                    Field.setForeground(Color.GRAY);
                    Field.setText(Placeholder);
                }
            }
        });
    }
    
    private void procesarLogin() {
        String username = LoginField.getText().trim();
        String password = new String(PasswordField.getPassword());
        
        // Limpiar placeholder si existe
        if (username.equals("Usuario")) {
            username = "";
        }
        
        // Validar campos vacíos
        if (username.isEmpty() || password.isEmpty()) {
            mostrarMensajeEstado("Por favor complete todos los campos", Color.RED);
            return;
        }
        
        // Verificar si hay usuarios registrados
        if (RegistroIntegrado.getTotalUsuarios() == 0) {
            mostrarMensajeEstado("No hay usuarios registrados en el sistema", Color.RED);
            int opcion = JOptionPane.showConfirmDialog(
                this,
                "No hay usuarios registrados.\n¿Desea ir a la pantalla de registro?",
                "Sin usuarios",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (opcion == JOptionPane.YES_OPTION) {
                abrirRegistro();
            }
            return;
        }
        
        // Mostrar mensaje de validación
        mostrarMensajeEstado("Validando credenciales...", Color.BLUE);
        
        // Simular tiempo de procesamiento con Timer
        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Usar el método POO para validar credenciales
                Usuario usuarioEncontrado = Registro.ValidarCredenciales(username, password);
                
                if (usuarioEncontrado != null) {
                    UsuarioLogueado = usuarioEncontrado;
                    mostrarMensajeEstado("¡Acceso autorizado!", new Color(34, 139, 34));
                    
                    // Esperar un momento antes de mostrar bienvenida
                    Timer timer2 = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mostrarBienvenida();
                            ((Timer)e.getSource()).stop();
                        }
                    });
                    timer2.setRepeats(false);
                    timer2.start();
                    
                } else {
                    mostrarMensajeEstado("Usuario o contraseña incorrectos", Color.RED);
                    PasswordField.setText("");
                    PasswordField.requestFocus();
                }
                ((Timer)e.getSource()).stop();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void mostrarMensajeEstado(String mensaje, Color color) {
        // Buscar el label de estado
        Component[] components = getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                Component[] subComponents = panel.getComponents();
                for (Component subComp : subComponents) {
                    if (subComp instanceof JLabel && subComp.getName() != null && subComp.getName().equals("labelEstado")) {
                        JLabel labelEstado = (JLabel) subComp;
                        labelEstado.setText(mensaje);
                        labelEstado.setForeground(color);
                        return;
                    }
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TituloLabel = new javax.swing.JLabel();
        LoginField = new javax.swing.JTextField();
        PasswordField = new javax.swing.JPasswordField();
        LoginButton = new javax.swing.JButton();
        ReturnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TituloLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        TituloLabel.setText("INICIO DE SESION");

        LoginButton.setText("INICIAR SESION");

        ReturnButton.setText("VOLVER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(TituloLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PasswordField)
                            .addComponent(LoginField, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(ReturnButton)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(TituloLabel)
                .addGap(42, 42, 42)
                .addComponent(LoginField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ReturnButton)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JTextField LoginField;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton ReturnButton;
    private javax.swing.JLabel TituloLabel;
    // End of variables declaration//GEN-END:variables
}
