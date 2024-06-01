package IGU;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fondo extends JPanel{
       private Image fondo;
       JFrame ventana;
       String img;
       
       public Fondo(String imagen, JFrame ventana){ 
           img = imagen;
           this.ventana = ventana;
       }
    @Override
    public void paint(Graphics g){
        try{
            fondo = new ImageIcon(getClass().getResource("/imagenes/"+img)).getImage();
            g.drawImage(fondo,0,0, getWidth(),getHeight(),this);
            setOpaque(false);
            super.paint(g);
        }catch(NullPointerException e){
            ventana.dispose();               
        }
    }
}
