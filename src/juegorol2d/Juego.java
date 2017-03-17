/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegorol2d;
import control.Teclado;
import graficos.Pantalla;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author PocyxDesigner
 */
public class Juego extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 1L;
    
    private static final int ANCHO = 400;
    private static final int ALTO = 300;
    
    private static volatile boolean enFuncionamiento = false;
    
    private static final String NOMBRE = "Juego"; 
    
    private static int aps = 0;
    private static int fps = 0;
    
    private static int x = 0;
    private static int y = 0;
    
    private static JFrame ventana;
    private static Thread thread;
    private static Teclado teclado;
    private static Pantalla pantalla;
    
    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
    private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono/icono.png"));
    
    
    
    
    private Juego(){
        setPreferredSize(new Dimension(ANCHO,ALTO));
        
        pantalla = new Pantalla(ANCHO, ALTO);
        
        teclado = new Teclado();
        addKeyListener(teclado);
        
        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //al clicar cerrar ventana cierra aplicacion.
        ventana.setResizable(false); //para que el usuario no pueda cambiar el tamaño de la ventana.
        ventana.setIconImage(icono.getImage());
        ventana.setLayout(new BorderLayout()); //diseño de la ventana
        ventana.add(this, BorderLayout.CENTER); //el usuario no sabra que tenemos un cambas dentro de la ventana.
        ventana.pack(); //todo el contenido de la pantallas se adapta al tamaño
        ventana.setLocationRelativeTo(null); //posicion de ventana en el centro escritorio.
        ventana.setVisible(true); //la ventana se vera al iniciar la aplicacióin.
        
    }
    
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }
    
    public synchronized void iniciar(){
        enFuncionamiento = true;
        thread =new Thread(this, "Graficos");
        thread.start();
    }
    
    public synchronized void detener(){
        enFuncionamiento = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void actualizar(){
        teclado.actualizar();
        
        if (teclado.arriba){
            y++;
        }
        if (teclado.abajo){
            y--;
        }
        if (teclado.izquierda){
            x++;
        }
        if (teclado.derecha){
            x--;
        }
        
        aps++;
    }
    
    private void mostrar(){
        BufferStrategy estrategia = getBufferStrategy();
        
        if (estrategia == null){
            createBufferStrategy(3);
            return;
        }
        
        pantalla.limpiar();
        pantalla.mostrar(x, y);
        
        //for (int i=0;i<pixeles.length; i++){
        //    pixeles[i] = pantalla.pixeles[i];
        //}
        
        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);//menos costoso q el comentado para el pc
        
        Graphics g = estrategia.getDrawGraphics();
        
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        //cuadrado rojoen el centro(personaje)
        g.setColor(Color.red);
        g.fillRect(ANCHO/2, ALTO/2, 32, 32);
        //
        g.dispose(); //destrulle la memoria q g estaba utilizando
        
        estrategia.show();
        
        fps++;
    }
    
    public void run(){
        //System.nanoTime();
        final int NS_POR_SEGUNDO = 1000000000; //nanosegundos por minuto
        final byte APS_OBJETIVO = 60; //actualizaciones por segundo(buen numero 30)
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime(); //cantidad de tiempo en nanosegundos
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0; //cantidad de tiempo hasta que se realiza la actualizacion
        
        requestFocus(); //focaliza la pantalla para que funcione el teclado sin tener que pinchar con raton.
        
        
        while (enFuncionamiento){
            final long inicioBucle = System.nanoTime();
            
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            
            while(delta >= 1){
                actualizar();
                delta--;
            }
            
            mostrar();
            
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                ventana.setTitle(NOMBRE + " -- APS:" + aps + " -- FPS: "+fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
    
}
