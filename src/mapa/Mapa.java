/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import graficos.Pantalla;
import mapa.cuadro.Cuadro;

/**
 *
 * @author PocyxDesigner
 */
public abstract class Mapa {
    protected int ancho;
    protected int alto;
    
    protected int[] cuadros;
    
    public Mapa(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        cuadros = new int[ancho * alto];
        generarMapa();
    }
    
    public Mapa(String ruta){
        cargarMapa(ruta);
    }
    
    protected void generarMapa(){
        
    }
    
    private void cargarMapa(String ruta){
        
    }
    
    public void actualizar(){
        
    }
    
    public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla){
        //bit shifting
        int o = compensacionX >> 5; // igual que dividir entre 32
        int e = (compensacionX + pantalla.obtenAncho()) >> 5;
        int n = compensacionY >> 5;
        int s = (compensacionY + pantalla.obtenAlto()) >> 5;
    }
    
    public Cuadro obtenCuadro(final int x,final int y){
        switch(cuadros[x + y * ancho]){
            case 0:
                return Cuadro.ASFALTO;
            case 1:
                
            case 2:
            case 3:
            default:
                return null;
        }
    }
}
