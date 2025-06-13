/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Positions {

    private int[][] botonesPosiciones = new int[3][3];

    public boolean validar(int p, int p2) {
        return botonesPosiciones[p][p2] == 0;
    }

    public void ponerBoton(int posicion, int posicion2, boolean jugador) {
        if (validar(posicion, posicion2)) {
            if (jugador) {
                botonesPosiciones[posicion][posicion2] = 1;//X
            } else if (!jugador) {
                botonesPosiciones[posicion][posicion2] = 2;//O
            }
        }
    }

    public int Ganador() {
        for (int i = 0; i < 3; i++) {
            int x = 1;
            int o = 2;
            boolean posiciones = (botonesPosiciones[0] == botonesPosiciones[1] && botonesPosiciones[0] == botonesPosiciones[2]);
            if (botonesPosiciones[i][0] != 0 && botonesPosiciones[i][0] == botonesPosiciones[i][1] && botonesPosiciones[i][0] == botonesPosiciones[i][2]) {//Misma fila
                JOptionPane.showMessageDialog(null, "HORIZONTAL");
                reiniciarBotones();
                return 1;
            }

            if (botonesPosiciones[0][i] != 0 && botonesPosiciones[0][i] == botonesPosiciones[1][i] && botonesPosiciones[0][i] == botonesPosiciones[2][i])//Misma columna
            {
                JOptionPane.showMessageDialog(null, "VERTICAL");
                reiniciarBotones();
                return 1;
            }
            

        }//For
        
        if (botonesPosiciones[0][0] != 0 && botonesPosiciones[0][0] == botonesPosiciones[1][1] && botonesPosiciones[0][0] == botonesPosiciones[2][2]) {//Diagonal 1
                System.out.println("DIAGONAL");
                 JOptionPane.showMessageDialog(null, "diagonal");
                 reiniciarBotones();
                return 1;

            }
            if (botonesPosiciones[0][2] != 0 && botonesPosiciones[0][2] == botonesPosiciones[1][1] && botonesPosiciones[0][2] == botonesPosiciones[2][0]) {//Diagonal 2
                JOptionPane.showMessageDialog(null, "diagonal");
                System.out.println("DIAGONAL");
                reiniciarBotones();
                return 1;
            }
        int contador = 0;
        for (int rows = 0; rows < 3; rows++) {
            for (int cols = 0; cols < 3; cols++) {
                if (botonesPosiciones[rows][cols] != 0) {
                    contador++;
                }
            }
        }
        
        if (contador == 9)
        {
            JOptionPane.showMessageDialog(null, "EMPATE");
            reiniciarBotones();
            return 2;
        }
        return 0;

    }

    public void reiniciarBotones() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                botonesPosiciones[r][c] = 0;
            }

        }
    }

}
