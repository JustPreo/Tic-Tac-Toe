/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class GenerarTablero extends JPanel {
    //Manejo de clicks
    private Juego juego;
    JButton[][] Tablero;
    static boolean turno = true;//Empieza con turno de x
    
        
    
    public GenerarTablero(Juego juego)
    {
    this.juego = juego;
    Tablero = juego.getBotones();
    Positions positions = new Positions();
    for (int r = 0;r < 3;r++)
    {
    for (int c = 0;c<3;c++)
    {
        int fila = r;
        int columna = c;
        Tablero[r][c].addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent click) {
                JButton botonOprimido = (JButton) click.getSource();
                Image ruta;
                
                
                
                System.out.println("F "+fila + "|columna " + columna);
                if (turno && botonOprimido.getIcon()==null){
                    
                ImageIcon icono = new ImageIcon(getClass().getResource("/tictactoe/X.png"));
                Image imagen = icono.getImage().getScaledInstance(botonOprimido.getWidth(), botonOprimido.getHeight(), Image.SCALE_SMOOTH);
                botonOprimido.setIcon(new ImageIcon(imagen));
                    System.out.println("A");
                    positions.ponerBoton(fila, columna, turno);
                    turno = false;
                    juego.getLabel().setText("Turno de O");
                    
                    if (positions.Ganador() == 1)
                    {
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                        System.out.println("B");
                    juego.dispose();
                    
                    }
                    else if (positions.Ganador()==2)
                    {
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.setVisible(true);
                        System.out.println("EMPATE");
                    juego.dispose();
                    
                    }
                    else if (positions.Ganador()==0)
                    {
                        System.out.println("Womp Womp");
                    }
                    
                    
                    
                }
                else if (!turno && botonOprimido.getIcon()==null)
                {
                ImageIcon icono = new ImageIcon(getClass().getResource("/tictactoe/O.png"));
                Image imagen = icono.getImage().getScaledInstance(botonOprimido.getWidth(), botonOprimido.getHeight(), Image.SCALE_SMOOTH);
                botonOprimido.setIcon(new ImageIcon(imagen));
                    System.out.println("B");
                    positions.ponerBoton(fila, columna, turno);
                    turno = true;
                    juego.getLabel().setText("Turno de X");
                    positions.Ganador();
                }
                
                else if (botonOprimido.getIcon() != null)
                {
                JOptionPane.showMessageDialog(null, "Seleccione otro boton");
                }
                
            }
        });
        
    }
    }
    
    
    }

    
    
    }//Fin
