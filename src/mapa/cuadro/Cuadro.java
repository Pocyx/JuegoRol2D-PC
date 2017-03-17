/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa.cuadro;

import graficos.Pantalla;
import graficos.Sprite;

/**
 *Plantilla para diseñas los tiles(cuadros en los que van los sprites)
 * @author PocyxDesigner
 */
public abstract class Cuadro {
    public int x;
    public int y;
    
    public Sprite sprite;
    
    //Coleccion de cuadros
    public static final Cuadro VACIO = new CuadroVacio(Sprite.VACIO);
    public static final Cuadro ASFALTO = new CuadroAsfalto(Sprite.ASFALTO);
    //fin de la coleccion de cuadros
    
    public Cuadro(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void mostrar(int x, int y, Pantalla pantalla){
        
    }
    
    public boolean solido(){
        return false;
    }
}
