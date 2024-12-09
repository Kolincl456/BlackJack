package blackjack;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
/**
 *
 * @author SrKolin.
 */
public class GUI extends JFrame{
    
    //Variable para generar números aleatorios para las cartas que toma el jugador.
    int rand = new Random().nextInt(52);
    
    //
    
    
    /*aW = Variable de ancho de la ventana.
      aH = Variable de largo de la ventana.
    */ 
    int aW = 1250;
    int aH = 800;
    
    //Booleanos para las fases del juego.
    boolean bool_tomar_quedarse = true;
    boolean bool_dealer_turno = false;
    boolean bool_juego_mas = false;
    
    //Color de fondo de la tabla.
    Color colorFondo = new Color(39, 119, 20);
    Color colorBoton = new Color(204, 204, 0);
   
    //Declaración de los botones.
    JButton bTomar = new JButton();
    JButton bQuedarse = new JButton();
    JButton bSi = new JButton();
    JButton bNo = new JButton();
    
    //Dimensiones y posiciones de la cuadrícula de las cartas.
    int gridX = 30;
    int gridY = 30;
    int gridW = 900;
    int gridH = 400;
    
    //Dimensiones y posiciones de los espacios de las cartas.
    int cartaEspacio = 10;
    int cartaCorte = 7;
    int cartaTAncho = gridW/6;
    int cartaTLargo = gridH/2;
    int cartaAAncho = cartaTAncho-2*cartaEspacio;
    int cartaALargo = cartaTLargo-2*cartaEspacio;
    
    //Dimensiones y posiciones de la cuadricula de los puntos y botones tomar y quedarse.
    int hsX = gridX + gridW + 50;
    int hsY = gridY;
    int hsW = 230;
    int hsH = 400;
    
    //Dimensiones y posiciones de la sección de preguntas para jugar.
    int pmX = hsX;
    int pmY = hsY + hsH + 50;
    int pmW = hsW;
    int pmH = 200;
    
    //arryaList que contiene las cartas.
    ArrayList<Cartas> lasCartas = new ArrayList<Cartas>();
    ArrayList<Cartas> jugadorCartas = new ArrayList<Cartas>();
    ArrayList<Cartas> dealerCartas = new ArrayList<Cartas>();
    
    
    
    //Fuentes
    Font FuenteBoton = new Font("Times New Roman", Font.PLAIN, 30);
    Font FuenteCarta = new Font("Times New Roman", Font.BOLD, 40);
    Font FuentePregunta = new Font("Times New Roman", Font.PLAIN, 40);
    
    //Preguntas
    String jugar_nuevamente_p = "¿Jugar otra vez?";
    
    
    public GUI(){
        //this.setSize(aW+6, aH+29);
        this.setBounds(120, 0, aW+6, aH+29);
        this.setTitle("BlackJack");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Tablero tablero = new Tablero();
        this.setContentPane(tablero);
        this.setLayout(null);
        
        //Configuración incial de los botones.
        ActTomar aTomar = new ActTomar();
        bTomar.addActionListener(aTomar);
        bTomar.setBounds(hsX+50, hsY+40, 142, 80);
        bTomar.setBackground(colorBoton);
        bTomar.setText("TOMAR");
        bTomar.setFont(FuenteBoton);
        tablero.add(bTomar);
        
        ActQuedarse aQuedarse = new ActQuedarse();
        bQuedarse.addActionListener(aQuedarse);
        bQuedarse.setBounds(hsX+20, hsY+260, 195, 80);
        bQuedarse.setBackground(colorBoton);
        bQuedarse.setText("QUEDARSE");
        bQuedarse.setFont(FuenteBoton);
        tablero.add(bQuedarse);
        
        ActSi aSi = new ActSi();
        bSi.addActionListener(aSi);
        bSi.setBounds(pmX+10, pmY+110, 100, 80);
        bSi.setBackground(colorBoton);
        bSi.setText("Si");
        bSi.setFont(FuenteBoton);
        tablero.add(bSi);
        
        ActNo aNo = new ActNo();
        bNo.addActionListener(aNo);
        bNo.setBounds(pmX+120, pmY+110, 100, 80);
        bNo.setBackground(colorBoton);
        bNo.setText("No");
        bNo.setFont(FuenteBoton);
        tablero.add(bNo);
        
        String formaF1 = null;
        int id_setter = 0;
        for(int s = 0; s<4; s++){
            if(s == 0){
                formaF1 = "Espada";
            }else if(s == 1){
                formaF1 = "Corazon";
            }else if(s == 2){
                formaF1 = "Diamante";
            }else{
                formaF1 = "Trebol";
            }
            for(int i = 0; i<15; i++){
                lasCartas.add(new Cartas(i, formaF1, id_setter));
                id_setter++;
            }
        }
        rand = new Random().nextInt(52);
        jugadorCartas.add(lasCartas.get(rand));
        lasCartas.get(rand).CartaUsada = true;
        
        rand = new Random().nextInt(52);
        while(true){
            if(lasCartas.get(rand).CartaUsada == false){
                dealerCartas.add(lasCartas.get(rand));
                lasCartas.get(rand).CartaUsada = true;
                break;
            }else{
                rand = new Random().nextInt(52);
            }
        }
        rand = new Random().nextInt(52);
        while(true){
            if(lasCartas.get(rand).CartaUsada == false){
                jugadorCartas.add(lasCartas.get(rand));
                lasCartas.get(rand).CartaUsada = true;
                break;
            }else{
                rand = new Random().nextInt(52);
            }
        }
        rand = new Random().nextInt(52);
        while(true){
            if(lasCartas.get(rand).CartaUsada == false){
                dealerCartas.add(lasCartas.get(rand));
                lasCartas.get(rand).CartaUsada = true;
                break;
            }else{
                rand = new Random().nextInt(52);
            }
        }
        
        for(Cartas c : jugadorCartas){
            System.out.println("El jugador tiene: " + c.nombre + " de " + c.forma);
        }
        for(Cartas c : dealerCartas){
            System.out.println("El Dealer tiene: " + c.nombre + " de " + c.forma);
        }
    }
    
    public void refresher(){
        if(bool_tomar_quedarse == true){
            bTomar.setVisible(true);
            bQuedarse.setVisible(true);
            bSi.setVisible(false);
            bNo.setVisible(false);
        }else if(bool_dealer_turno == true){
            bTomar.setVisible(false);
            bQuedarse.setVisible(false);
            bSi.setVisible(false);
            bNo.setVisible(false);
        }else if(bool_juego_mas == true){
            bTomar.setVisible(false);
            bQuedarse.setVisible(false);
            bSi.setVisible(true);
            bNo.setVisible(true);
        }
    }
    
    public void dealerTomarQuedarse(){
        
    }
    
    public class Tablero extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(colorFondo);
            g.fillRect(0, 0, aW, aH);
            //Temporal
            g.setColor(Color.black);
            g.drawRect(gridX, gridY, gridW, gridH);
            //Temporal
            g.drawRect(gridX, gridY+400+30, gridW, gridH);
            //Temporal
            g.drawRect(hsX, hsY, hsW, hsH);
            //Temporal
            g.drawRect(pmX, pmY, pmW, pmH);
            
            if(bool_juego_mas == true){
                g.setFont(FuentePregunta);
                g.drawString(jugar_nuevamente_p, pmX+26, aW+60);
            }
            
            g.setFont(FuentePregunta);
            g.drawString(jugar_nuevamente_p, pmX-10, pmY+60);
            
            /*for(int i=0; i<6; i++){
                g.drawRect(gridX+i*cartaTAncho+cartaEspacio, gridY+cartaEspacio, cartaAAncho, cartaALargo);
                g.drawRect(gridX+i*cartaTAncho+cartaEspacio, gridY+cartaEspacio+cartaTLargo, cartaAAncho, cartaALargo);
            }*/
            
            //Dibujando contorno de las cartas
            //Cartas del jugador
            int index = 0;
            for(Cartas c : jugadorCartas){
                g.setColor(Color.white);
                g.fillRect(gridX+index*cartaTAncho+cartaEspacio, gridY+cartaEspacio+cartaCorte, cartaAAncho, cartaALargo-2*cartaCorte);
                g.fillRect(gridX+index*cartaTAncho+cartaEspacio+cartaCorte, gridY+cartaEspacio, cartaAAncho-2*cartaCorte, cartaALargo);
                g.fillOval(gridX+index*cartaTAncho+cartaEspacio, gridY+cartaEspacio, 2*cartaCorte, 2*cartaCorte);
                g.fillOval(gridX+index*cartaTAncho+cartaEspacio+cartaAAncho-2*cartaCorte, gridY+cartaEspacio, 2*cartaCorte, 2*cartaCorte);
                g.fillOval(gridX+index*cartaTAncho+cartaEspacio, gridY+cartaEspacio+cartaALargo-2*cartaCorte, 2*cartaCorte, 2*cartaCorte);
                g.fillOval(gridX+index*cartaTAncho+cartaEspacio+cartaAAncho-2*cartaCorte, gridY+cartaEspacio+cartaALargo-2*cartaCorte, 2*cartaCorte, 2*cartaCorte);
                g.setColor(Color.black);
                if(c.forma.equalsIgnoreCase("Corazon") || c.forma.equalsIgnoreCase("Diamante")){
                    g.setColor(Color.red);
                }
                g.setFont(FuenteCarta);
                g.drawString(c.simbolo, gridX+index*cartaTAncho+cartaEspacio*2, gridY+cartaALargo);
                
                if(c.forma.equalsIgnoreCase("Espada")){
                    g.setColor(Color.black);
                    g.drawString(c.simbolo, gridX+index*cartaTAncho+cartaEspacio*2, gridY+cartaALargo);
                    g.fillOval(gridX+index*cartaTAncho+40, gridY+85, 40, 40);
                    g.fillOval(gridX+index*cartaTAncho+70, gridY+85, 40, 40);
                    g.fillArc(gridX+index*cartaTAncho+30, gridY+28, 90, 70, 230, 80);
                    g.fillRect(gridX+index*cartaTAncho+70, gridY+90, 10, 50);
                }else if(c.forma.equalsIgnoreCase("Corazon")){
                    g.setColor(Color.red);
                    g.fillOval(gridX+index*cartaTAncho+40, gridY+70, 40, 40);
                    g.fillOval(gridX+index*cartaTAncho+70, gridY+70, 40, 40);
                    g.fillArc(gridX+index*cartaTAncho+30, gridY+96, 90, 70, 50, 80);
                }else if(c.forma.equalsIgnoreCase("Diamante")){
                    //Puntos para realizar la forma del diamante
                    g.setColor(Color.red);
                    int x1, x2, x3, x4, y1, y2, y3, y4;
                    x1 = 75 + gridX + index*cartaTAncho;
                    y1 = 60 + gridY;
                    x2 = 50 + gridX + index*cartaTAncho;
                    y2 = 100 + gridY;
                    x3 = 75 + gridX + index*cartaTAncho;
                    y3 = 140 + gridY;;
                    x4 = 100 + gridX + index*cartaTAncho;
                    y4 = 100 + gridY;; 
                    int[] poloX = {x1, x2, x3, x4};
                    int[] poloY = {y1, y2, y3, y4};
                    g.fillPolygon(poloX, poloY, 4);
                }else{
                    g.setColor(Color.black);
                    g.fillOval(gridX+index*cartaTAncho+35, gridY+85, 40, 40);
                    g.fillOval(gridX+index*cartaTAncho+75, gridY+85, 40, 40);
                    g.fillOval(gridX+index*cartaTAncho+55, gridY+55, 40, 40);
                    g.fillRect(gridX+index*cartaTAncho+70, gridY+90, 10, 50);
                }
                index++;
            }
            //Cartas del Dealer
            if(bool_dealer_turno == true){
                index = 0;
                for(Cartas c : dealerCartas){
                    g.setColor(Color.white);
                    g.fillRect(gridX+index*cartaTAncho+cartaEspacio, gridY+cartaTLargo+cartaEspacio+cartaCorte, cartaAAncho, cartaALargo-2*cartaCorte);
                    g.fillRect(gridX+index*cartaTAncho+cartaEspacio+cartaCorte, gridY+cartaTLargo+cartaEspacio, cartaAAncho-2*cartaCorte, cartaALargo);
                    g.fillOval(gridX+index*cartaTAncho+cartaEspacio, gridY+cartaTLargo+cartaEspacio, 2*cartaCorte, 2*cartaCorte);
                    g.fillOval(gridX+index*cartaTAncho+cartaEspacio+cartaAAncho-2*cartaCorte, gridY+cartaTLargo+cartaEspacio, 2*cartaCorte, 2*cartaCorte);
                    g.fillOval(gridX+index*cartaTAncho+cartaEspacio, gridY+cartaTLargo+cartaEspacio+cartaALargo-2*cartaCorte, 2*cartaCorte, 2*cartaCorte);
                    g.fillOval(gridX+index*cartaTAncho+cartaEspacio+cartaAAncho-2*cartaCorte, gridY+cartaTLargo+cartaEspacio+cartaALargo-2*cartaCorte, 2*cartaCorte, 2*cartaCorte);
                    g.setColor(Color.black);
                    if(c.forma.equalsIgnoreCase("Corazon") || c.forma.equalsIgnoreCase("Diamante")){
                        g.setColor(Color.red);
                    }
                    g.setFont(FuenteCarta);
                    g.drawString(c.simbolo, gridX+index*cartaTAncho+cartaEspacio*2, gridY+cartaTLargo+cartaALargo);

                    if(c.forma.equalsIgnoreCase("Espada")){
                        g.setColor(Color.black);
                        g.drawString(c.simbolo, gridX+index*cartaTAncho+cartaEspacio*2, gridY+cartaALargo);
                        g.fillOval(gridX+index*cartaTAncho+40, gridY+cartaTLargo+85, 40, 40);
                        g.fillOval(gridX+index*cartaTAncho+70, gridY+cartaTLargo+85, 40, 40);
                        g.fillArc(gridX+index*cartaTAncho+30, gridY+cartaTLargo+28, 90, 70, 230, 80);
                        g.fillRect(gridX+index*cartaTAncho+70, gridY+cartaTLargo+90, 10, 50);
                    }else if(c.forma.equalsIgnoreCase("Corazon")){
                        g.setColor(Color.red);
                        g.fillOval(gridX+index*cartaTAncho+40, gridY+cartaTLargo+70, 40, 40);
                        g.fillOval(gridX+index*cartaTAncho+70, gridY+cartaTLargo+70, 40, 40);
                        g.fillArc(gridX+index*cartaTAncho+30, gridY+cartaTLargo+96, 90, 70, 50, 80);
                    }else if(c.forma.equalsIgnoreCase("Diamante")){
                        //Puntos para realizar la forma del diamante
                        g.setColor(Color.red);
                        int x1, x2, x3, x4, y1, y2, y3, y4;
                        x1 = 75 + gridX + index*cartaTAncho;
                        y1 = 60 + gridY+cartaTLargo;
                        x2 = 50 + gridX + index*cartaTAncho;
                        y2 = 100 + gridY+cartaTLargo;
                        x3 = 75 + gridX + index*cartaTAncho;
                        y3 = 140 + gridY+cartaTLargo;;
                        x4 = 100 + gridX + index*cartaTAncho;
                        y4 = 100 + gridY+cartaTLargo;; 
                        int[] poloX = {x1, x2, x3, x4};
                        int[] poloY = {y1, y2, y3, y4};
                        g.fillPolygon(poloX, poloY, 4);
                    }else{
                        g.setColor(Color.black);
                        g.fillOval(gridX+index*cartaTAncho+35, gridY+cartaTLargo+85, 40, 40);
                        g.fillOval(gridX+index*cartaTAncho+75, gridY+cartaTLargo+85, 40, 40);
                        g.fillOval(gridX+index*cartaTAncho+55, gridY+cartaTLargo+55, 40, 40);
                        g.fillRect(gridX+index*cartaTAncho+70, gridY+cartaTLargo+90, 10, 50);
                    }
                    index++;
                }
            }
        }
    }
    
    public class ActTomar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Has presionado el botón Tomar.");
            rand = new Random().nextInt(52);
            while(true){
                if(lasCartas.get(rand).CartaUsada == false){
                    jugadorCartas.add(lasCartas.get(rand));
                    lasCartas.get(rand).CartaUsada = true;
                    break;
                }else{
                    rand = new Random().nextInt(52);
                }
            }
        }  
    }
    public class ActQuedarse implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Has presionado el botón Quedarse.");
            bool_tomar_quedarse = false;
            bool_dealer_turno = true;
            dealerTomarQuedarse();
        }
    }
    public class ActSi implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Has presionado el botón Si.");
        }  
    }
    public class ActNo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Has presionado el botón No.");
        }  
    }
}
