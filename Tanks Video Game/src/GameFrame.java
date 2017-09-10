
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yudhi
 */
public class GameFrame extends JFrame implements KeyListener, WindowListener, Runnable {

    private Thread anim;
    private GameManager gm;
    private long sleepTime = 50;
    Image i;
    Graphics g1;

    GameFrame() throws Exception {
        super("Tanks!");
        setSize(1050, 750);
        setResizable(false);
        setVisible(true);

        addKeyListener(this);
        addWindowListener(this);

        i = createImage(1050, 700);
        g1 = i.getGraphics();

        gm = new GameManager();

        anim = new Thread(this);
        if (anim != null) {
            anim.start();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (anim != null) {
            anim.stop();
        }
        dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_DOWN) {
            gm.move(Direction.DOWN);
            //System.out.println("down");
        }
        if (key == KeyEvent.VK_UP) {
            gm.move(Direction.UP);
            //System.out.println("up");
        }
        if (key == KeyEvent.VK_LEFT) {
            gm.move(Direction.LEFT);
            //System.out.println("left");
        }
        if (key == KeyEvent.VK_RIGHT) {
            gm.move(Direction.RIGHT);
            //System.out.println("right");
        }
        if (key == KeyEvent.VK_SPACE) {
            try {
                gm.shoot();
                //System.out.println("space");
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while (gm.state < 3) {
            gm.update();
            repaint();
            if (anim != null) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        if (anim != null) {
            anim.stop();
        }
    }

    /*@Override
    public void update(Graphics g){
    g.setColor(Color.BLACK);
    g.fillRect(0,50,1300,750);
    
    }*/
    @Override
    public void paint(Graphics g) {
        if (gm != null) {
            g1.setColor(Color.BLACK);
            g1.fillRect(0, 0, 1050, 700);
            gm.paint(g1);
        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 50, 1050, 700);

        g.drawImage(i, 0, 50, this);
    }

    public static void main(String args[]) throws Exception {
        //create1();
        GameFrame f = new GameFrame();
    }
}
