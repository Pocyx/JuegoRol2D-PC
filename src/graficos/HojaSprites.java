/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author PocyxDesigner
 */
public class HojaSprites {
    private final int ancho;
    private final int alto;
    
    public final int[] pixeles;
    
    //coleccion de hojas de sprites
    public static HojaSprites desierto = new HojaSprites("/texturas/desierto.png", 320, 320);
    //fin de la coleccion
    
    public HojaSprites(final String ruta, final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        pixeles = new int[ancho * alto];
        
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            
            imagen.getRGB(0, 0, alto, alto, pixeles, 0, ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public int obtenAncho() {
        return ancho;
    }       
}
