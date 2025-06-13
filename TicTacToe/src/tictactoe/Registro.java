/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tictactoe;

/**
 *
 * @author user
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends javax.swing.JFrame {
    
    private static Usuario[] UsuariosRegistrados;
    private static int TotalUsuarios;
    private static final int MAX_USUARIOS = 20;
    
    private JFrame VentanaAnterior;

    /**
     * Creates new form Interfaz
     */
    public Registro() {
        if (UsuariosRegistrados == null) {
            InicializarBD();
        }
       
        initComponents();
        
        ConfigurarEventos();
        ConfigurarInterfaz();
    }
    
    public Registro(JFrame VentanaAnterior) {
        this.VentanaAnterior = VentanaAnterior;
        
        if (UsuariosRegistrados == null) {
            InicializarBD();
            
            initComponents();
            ConfigurarEventos();
            ConfigurarInterfaz();
        }
    }
    
    private void InicializarBD() {
        UsuariosRegistrados = new Usuario[MAX_USUARIOS];
        TotalUsuarios = 0;
    }
    
    private static boolean AgregarUsuario(String Usuario, String Password) {
        if (TotalUsuarios >= MAX_USUARIOS) {
            return false;
        }
        
        for (int i = 0; i < TotalUsuarios; i++) {
            if (UsuariosRegistrados[i].getUsername().equals(Usuario)) {
                return false;
            }
        }
        
        Usuario NuevoUsuario = new Usuario(Usuario, Password);
        UsuariosRegistrados[TotalUsuarios] = NuevoUsuario;
        TotalUsuarios++;
        
        return true;
    }
    
    public static Usuario BuscarUsuario(String Username) {
        for (int i = 0; i < TotalUsuarios; i++) {
            if (UsuariosRegistrados[i].getUsername().equals(Username)) {
                return UsuariosRegistrados[i];
            }
        }
        return null;
    }
    
    public static Usuario ValidarCredenciales(String Username, String Password) {
        for (int i = 0; i < TotalUsuarios; i++) {
            if (UsuariosRegistrados[i].ValidarCredenciales(Username, Password)) {
                return UsuariosRegistrados[i];
            }
        }
        return null;
    }
    
    public static String obtenerEstadisticas() {
        return String.format("Usuarios registrados: %d/%d", TotalUsuarios, MAX_USUARIOS);
    }
    
    public static Usuario[] obtenerUsuarios() {
        Usuario[] Usuarios = new Usuario[TotalUsuarios];
            for (int i = 0; i < TotalUsuarios; i++) {
                Usuarios[i] = UsuariosRegistrados[i];
            }
            return Usuarios;
    }
    
    public static int getTotalUsuarios() {
        return TotalUsuarios;
    }
    
    private void ConfigurarEventos() {
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcesarRegistro();
            }
        });
        
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VolverVentanaAnterior();
            }
        });
        
        UsernameField.addActionListener(e -> PasswordField.requestFocus());
        PasswordField.addActionListener(e -> ProcesarRegistro());
    }
    
    private void ConfigurarInterfaz() {
        setTitle("Sistema de Login - TicTacToe");
        setLocationRelativeTo(null);
        setResizable(false);
        
        ConfigurarPlaceholder(UsernameField, "Ingrese Usuario");
    }
    
    private void ConfigurarPlaceholder(JTextField Field, String Placeholder) {
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
    
    private void ProcesarRegistro() {
        String Usuario = UsernameField.getText().trim();
        String Password = new String(PasswordField.getPassword());
        
        if (Usuario.equals("Ingrese usuario")) {
            Usuario = "";
        }
        
        if (Usuario.isEmpty() || Password.isEmpty()) {
            mostrarMensaje("Por favor complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (Usuario.length() < 3) {
            mostrarMensaje("El usuario debe tener al menos 3 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            UsernameField.requestFocus();
            return;
        }
        
        if (Password.length() != 5) {
            mostrarMensaje("La contraseña debe tener exactamente 5 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            PasswordField.setText("");
            PasswordField.requestFocus();
            return;
        }
        
        if (!Usuario.matches("[a-zA-Z0-9]")) {
            mostrarMensaje("El usuario solo puede contener letras y numeros", "Error", JOptionPane.ERROR_MESSAGE);
            UsernameField.requestFocus();
            return;
        }
        
        if (AgregarUsuario(Usuario, Password)) {
            mostrarMensaje(String.format("Usuario '%s' registrado exitosamente!\n\n%s", Usuario, obtenerEstadisticas()), "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            
            LimpiarCampos();
            
            int Opcion = JOptionPane.showConfirmDialog(this, "Desea registrar otro usuario?", "Continuar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (Opcion == JOptionPane.NO_OPTION) {
                VolverVentanaAnterior();
            }
            
        } else {
            if (TotalUsuarios >= MAX_USUARIOS) {
                mostrarMensaje("No se pueden registrar mas usuarios", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                mostrarMensaje("El usuario ya existe, elije otro usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                UsernameField.setText("");
                UsernameField.requestFocus();
            }
        }
    }
    
    private void mostrarMensaje(String Mensaje, String Titulo, int Tipo) {
        JOptionPane.showMessageDialog(this, Mensaje, Titulo, Tipo);
    }
    
    private void LimpiarCampos() {
        UsernameField.setText("");
        PasswordField.setText("");
        
        SwingUtilities.invokeLater(() -> {
            ConfigurarPlaceholder(UsernameField, "Ingrese usuario");
        });
    }
    
    private void VolverVentanaAnterior() {
        if (VentanaAnterior != null) {
            VentanaAnterior.setVisible(true);
        }
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CancelButton = new javax.swing.JButton();
        RegisterButton = new javax.swing.JButton();
        UsernameField = new javax.swing.JTextField();
        PasswordField = new javax.swing.JPasswordField();
        TituloLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CancelButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        CancelButton.setText("CANCELAR");
        CancelButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        RegisterButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        RegisterButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        RegisterButton.setLabel("REGISTRAR");

        PasswordField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        TituloLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        TituloLabel.setText("REGISTRO DE USUARIO");

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(TituloLabel)
                .addGap(92, 92, 92))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(PasswordField)
                            .addComponent(UsernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                        .addGap(112, 112, 112))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RegisterButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TituloLabel)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RegisterButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(CancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JLabel TituloLabel;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
