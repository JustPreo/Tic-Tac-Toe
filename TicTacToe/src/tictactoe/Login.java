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
        mostrarEstadoSistema();
    }
    
    public Login(JFrame VentanaAnterior) {
        this.VentanaAnterior = VentanaAnterior;
        initComponents();
        configurarEventos();
        configurarInterfaz();
        mostrarEstadoSistema();
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
        PasswordField.addActionListener(e -> ProcesarLogin());
    }
     
    private void configurarInterfaz() {
        setTitle("Iniciar Sesion - TicTacTOE");
        setLocationRelativeTo(null);
        setResizable(false);
        
        configurarPlaceholder(LoginField, "Usuario");
    }

    configurarPlaceHolder(JTextField Field, String Placeholder) {
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
        String username = UsernameField.getText().trim();
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
                Usuario usuarioEncontrado = RegistroIntegrado.validarCredenciales(username, password);
                
                if (usuarioEncontrado != null) {
                    usuarioLogueado = usuarioEncontrado;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JTextField LoginField;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton ReturnButton;
    private javax.swing.JLabel TituloLabel;
    // End of variables declaration//GEN-END:variables
}
