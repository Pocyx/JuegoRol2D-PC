/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author PocyxDesigner
 */
public final class Teclado implements KeyListener {

    private final static int numeroTeclas =120;
    private final boolean[] teclas = new boolean[numeroTeclas];
    
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    
    public void actualizar(){
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_D];
    }
    
    @Override //pulsada sin soltar
    public void keyPressed(KeyEvent e) {
        teclas[e.getKeyCode()] = true;
    }

    @Override //soltar tecla
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
