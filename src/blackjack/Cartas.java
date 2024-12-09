package blackjack;
/**
 *
 * @author Srkolin
 */
public class Cartas {
    int valor, id;
    String forma, simbolo, nombre;
    boolean CartaUsada = false;  
    public Cartas(int n, String f, int id){
        if (n > 1 && n < 11) {
            this.nombre = Integer.toString(n);
            this.valor = n;
            this.simbolo = this.nombre;
        }else if(n > 10){
            this.valor = 10;
            if (n == 11){
                simbolo = "J";
                nombre = "Joker";
            }if (n== 12){
                simbolo = "Q";
                nombre = "Reina";
            }  if (n == 13){
                simbolo = "K";
                nombre = "Rey";
            } 
        }else if (n == 1){
            this.valor = 1; 
            this.simbolo = "A";
            this.nombre = "As";
        }
        this.forma = f;
        this.id = id;
        //System.out.println("La carta número " + nombre + " de " + forma + " fue creada. ID: " + this.id);
    }
    public void setUsada(){
       CartaUsada = true; 
    }
    public void setNotUsada(){
       CartaUsada = false;
    }
}