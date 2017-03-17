/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

/**
 *
 * @author PocyxDesigner
 */
public class CuadroVacio extends Cuadro{
    
    public CuadroVacio(Sprite sprite) {
        super(sprite);
    }
    
    public void mostrar(int x, int y, Pantalla pantalla){
        pantalla.mostrarCuadro(x, y, this);
    }
    
}
