/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

/**
 *
 * @author PocyxDesigner
 */
public final class Sprite {

    private final int lado;

    private int x;
    private int y;

    public int[] pixeles;
    private  HojaSprites hoja;

    //coleccion de sprites
    public static final Sprite VACIO = new Sprite(32, 0x000000);
    public static final Sprite ASFALTO = new Sprite(32, 0, 0, HojaSprites.desierto);
    //fin de la coleccion

    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja) {
        this.lado = lado;

        pixeles = new int[lado * lado];

        this.x = columna * lado;
        this.y = fila * lado;
        this.hoja = hoja;

        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.obtenAncho()];
            }
        }
    }

    public Sprite(final int lado, final int color) {
        this.lado = lado;
        pixeles = new int[lado * lado];

        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = color;
        }

    }

    public int obtenLado() {
        return lado;
    }
}
