package blackjack;

/**
 *
 * @author SrKolin
 */
public class BlackJack implements Runnable{
    
    GUI gui = new GUI();
    
    public static void main(String[] args) {
        new Thread( new BlackJack() ).start();
    }

    @Override
    public void run() {
        while (true) {
            gui.refresher();
            gui.repaint();
        }
    }
    
}
