/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

/**
 *
 * @author Hp
 */
public class Usuario {
    private String Nombre;
    private String Username;
    private String Password;
    private int Puntos;
    
    public Usuario(String Nombre, String Username, String Password, int Puntos) {
        this.Nombre = Nombre;
        this.Username = Username;
        this.Password = Password;
        this.Puntos = Puntos;
    }
    
    public Usuario(String Username, String Password) {
        this("", Username, Password, 0);
    }
    
    public String getNombre() {
        return Nombre;
    }
    
    public String getUsername() {
        return Username;
    }
    
    public String getPassword() {
        return Password;
    }
    
    public int getPuntos() {
        return Puntos;
    }
    
    /*
        Setters
    */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public void setUsername(String Username) {
        this.Username = Username;
    }
    
    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public void setPuntos(int Puntos) {
        this.Puntos = Puntos;
    }
    
    
    public boolean ValidarPassword(String PasswordInput) {
        return this.Password.equals(Password);
    }
    
    public boolean ValidarCredenciales(String Username, String Password) {
        return this.Username.equals(Username) && this.Password.equals(Password);
    }
    
    public void ganarPartida() {
        this.Puntos += 10;
    }
    
    public void incrementarPuntos(int Cantidad) {
        this.Puntos += Cantidad;
    }
    
    public String obtenerInformacion() {
        return String.format("Usuario: %s | Puntos: %d", this.Username, this.Puntos);
    }
    
    public String obtenerInfoRanking() {
        String NombreMostrar = Nombre.isEmpty() ? Username : Nombre;
        return String.format("%s (%s) - %d puntos", NombreMostrar, Username, Puntos);
    }
    
    
    public static boolean esUsernameValido(String Username) {
        if (Username == null || Username.trim().isEmpty()) {
            return false;
        }
        
        Username = Username.trim();
        
        if (Username.length() < 3 || Username.length() > 15) {
            return false;
        }
        
        return Username.matches("[a-zA-Z0-9]+");
    }
    
    public static boolean esPasswordValido(String Password) {
        return Password != null && Password.length() == 5;
    }
    
    public static boolean esNombreValido(String Nombre) {
        if (Nombre == null) {
            return true;
        }
        
        Nombre = Nombre.trim();
        if (Nombre.isEmpty()) {
            return true;
        }
        
        return Nombre.length() >= 2 && Nombre.matches("[a-zA-Z]+");
    }
    
    public String toString() {
        return String.format("Usuario{Username='%s', }", args)
    }
}
