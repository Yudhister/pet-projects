import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class BrickNBallFrame extends Frame implements ActionListener,MouseListener,MouseMotionListener,WindowListener,Runnable//,MouseListener,KeyListener
{
    Thread animation;
    Image i;
    Graphics g;
    MainClass mc;
    static int REFRESH_RATE = 1; // in ms
    static int TOT_LEVELS=3;
    static int batSize=100;
    //int gameOn=1,levelChange=2,newLevelSet=3,allLevelsCleared=4;
    int state;
    Brick bricks[];
    boolean unpause,running,active;
    int level,bricksLeft,score,scorePrevLevel;
    Bat bat;
    Ball ball;
    BatBallManager bb;
    Button pause,back,reset,load;
    Label status;
    TextField loader;
    Panel p,p1;
    
    public BrickNBallFrame()
    {
        super("Smashing is on");
    
        setSize(1220,750);
        setVisible(true);
        setResizable(false);
        
        try{
            BufferedReader b=new BufferedReader(new FileReader("SmashingLevels/level0.dat"));
            TOT_LEVELS=Integer.parseInt(b.readLine());
            batSize=Integer.parseInt(b.readLine());
            REFRESH_RATE=Integer.parseInt(b.readLine());
        }
        catch(Exception e){showStatus(e.toString()+" hi");System.out.println(e.toString()+" hi");
        }
        
        
        
        
        status=new Label("Status");
        pause=new Button("Pause");pause.addActionListener(this);
        back=new Button("Back to main window");back.addActionListener(this);
        reset=new Button("Reset this level");reset.addActionListener(this);
        load=new Button("Load level");load.addActionListener(this);
        loader=new TextField(3);
        loader.setText("1");
        p=new Panel(new GridLayout(2,1));
        p1=new Panel(new GridLayout(1,5));
        p1.add(pause);
        p1.add(back);
        p1.add(reset);
        p1.add(loader);
        p1.add(load);
        p.add(p1);
        p.add(status);
        setLayout(new BorderLayout());
        add(p,"South");
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(this);
        i=createImage(1220,650);
        g=i.getGraphics();
        
        unpause=true;
        running=true;
        active=false;
        level=1;
        score=0;
        scorePrevLevel=0;
        state=3;
        
        try{
        bricks=BrickManager.loadLevel(1,1220,650);
        }catch(Exception e){showStatus(e.toString());System.out.println(e.toString()+" 1");}
        bricksLeft=bricks.length;
        bat=new Bat((10)/2,650-45,batSize,40,Color.blue,1220);
        bat.setFill(true); 
        ball=new Ball(0,0,10,10,Color.white,1220,650,(Intersect[])bricks);
        ball.setPosition(bat.locx+bat.width/2,bat.locy-ball.height-5);
        ball.setFill(true);
        bb=new BatBallManager(ball,bat,1220,650);
        animation = new Thread(this);
        if (animation != null)
        {
            
            animation.start();
            
        }
        
    }
    public void showStatus(String s)
    {
        status.setText(s+"");
        repaint();
    }
    public void run()
    {
        while (true)
        {
            if(unpause)
            {
                updateManagers();
                repaint();
                Thread.currentThread().yield();
                
                try 
                {
                    Thread.sleep (REFRESH_RATE);
                }catch (Exception exc) {showStatus(exc.toString());System.out.println(exc.toString()+" 2");}
                
            }
            if(!(running&&active))break;
        }
        if (animation != null) {
            animation.stop();
            animation = null;
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        String c=e.getActionCommand();
        if(c.equals("Back to main window"))
        {
            running=false;
            mc=new MainClass();
            dispose();
        }
        if(c.equals("Pause"))
        {
            pause.setLabel("Unpause");
            unpause=false;
        }
        if(c.equals("Unpause"))
        {
            pause.setLabel("Pause");
            unpause=true;
        }
        if(c.equals("Reset this level"))
        {
            try{
            bricks=BrickManager.loadLevel(level,1220,650);
            }catch(Exception e2){showStatus(e2.toString());}
            score=scorePrevLevel;
            ball.setTarget(bricks);
            ball.setActive(false);
            state=3;
            bricksLeft=bricks.length;
        }
        if(c.equals("Load level"))
        {
            try{
                int l=Integer.parseInt(loader.getText());
                if(l>TOT_LEVELS)throw new Exception("Invalid Level Input");
                level=l;
                bricks=BrickManager.loadLevel(level,1220,650);
                score=0;scorePrevLevel=0;;
            ball.setTarget(bricks);
            ball.setActive(false);
            state=3;
            bricksLeft=bricks.length;
            }catch(Exception e2){showStatus(e2.toString());}
        }
    }
    
    
    public void mouseMoved(MouseEvent e)
    {
        int x=e.getX();
        bat.setPosition(x);
        //showStatus(x+" ");
        //repaint();
        //return true;
    }
    public void mouseDragged(MouseEvent e){mouseMoved(e);}
    public void mousePressed(MouseEvent e)
    {
        
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e)
    {
        if(!active){active=true;animation=new Thread(this);animation.start();}
        if(state==2)state=3;
        else if(state==3)
        {
            int vx=(int)(Math.random()*20)-10;
            int vy=(int)(Math.random()*20)-10;
            ball.setVelocity(vx,vy);
            state=1;
        }
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void updateManagers()
    {
        if(ball.hit>0)
        {
            bricksLeft=0;
            for(int i=0;i<bricks.length;i++)if(bricks[i].isActive()&&bricks[i].type!=4)bricksLeft++;
            score+=level;
            ball.hit=0;
            
        }
        
        ball.setActive(state==1);
        bb.update();
        if(state==1&&bricksLeft==0)
        {
            state=2;
            if(level<TOT_LEVELS) level++;
            else state=4;
        }
        if(state==2)
        {
            try{
            bricks=BrickManager.loadLevel(level,1220,650);
            }catch(Exception e){showStatus(e.toString());}
            scorePrevLevel=score;
            ball.setTarget(bricks);
            bricksLeft=bricks.length;
        }
    }
    public void update(Graphics g1){paint(g1);}
    public void paint(Graphics g1)
    {
        g.setColor(Color.black);
        g.fillRect(0,0,1220,650);
        g.setFont(new Font("TimesRoman",Font.BOLD,61));
        switch(state)
        {
            case 1:
            case 3:
                if(ball.out==0)//0 indicates ball still in
                {
                    for(int i=0;i<bricks.length;i++)
                    {
                        bricks[i].paint(g);
                    }
                    bat.paint(g);
                    ball.paint(g);
                    
                }
                else
                {
                    running=false;
                    g.setColor(Color.red);
                    g.drawString("Your final Score is "+score,1220/2-300,650/2+650/10);
                    g.drawString("Game Over, Ball Lost",1220/2-300,650/2);
                }
                break;
            case 2:
                g.setColor(Color.red);
                g.drawString("Congrats,LevelUp",1220/2-300,650/2-650/10);
                g.drawString("Your Current Score is "+score,1220/2-300,650/2);
                g.drawString("Click to continue",1220/2-300,650/2+650/10);
                break;
            case 4:
                g.setColor(Color.red);
                g.drawString("Game Over, All Levels Cleared",1220/2-300,650/2);
                g.drawString("Your final Score is "+score,1220/2-300,650/2+650/10);
                break;
        }
        showStatus("bricks left = "+bricksLeft+" Level = "+level+" Score = "+score);
        g1.drawImage(i,0,30,this);
    }
    public void windowDeactivated(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowOpened(WindowEvent e){}
    public void windowClosing(WindowEvent e){dispose();running=false;}
    public void windowClosed(WindowEvent e){}
    public void windowActivated(WindowEvent e){}
    public static void main(String args[])
    {
        BrickNBallFrame f=new BrickNBallFrame();
    }
    public static void help(){BrickNBallFrame f=new BrickNBallFrame();}
}
