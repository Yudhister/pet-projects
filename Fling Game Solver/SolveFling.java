import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class SolveFling extends JApplet implements MouseListener,ActionListener
{
	JFrame f;
	JPanel p;
	Graphics gr;
	JButton b1,b2,b3;
	String l="Put Balls";
	int ballId=0;
	int stateId=-1;
	String s="";
	StringTokenizer t;
	String cord[];
	Ball ar[][];
	public void start(){}
	public void stop(){}
	public void init()
	{
		//Container main=getContentPane();
		//main.setSize(800,800);
		setLayout(new FlowLayout());
		p=new JPanel(new BorderLayout(3,3));
		b1=new JButton("Solve");
		b1.addActionListener(this);
		b2=new JButton("Reset");
		b2.addActionListener(this);
		b3=new JButton("Back");
		b3.addActionListener(this);
		b3.setVisible(false);
		addMouseListener(this);
		p.add(b1,"North");
		p.add(b2,"Center");
		p.add(b3,"South");
		add(p,"NorthEast");
		gr=this.getGraphics();
		
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0,0,350,450);
		g.setColor(Color.BLACK);
		
		for(int i=0;i<8;i++)
			{
				g.drawLine(50*i,0,50*i,400);
				g.drawLine(0,50*i,350,50*i);
			}
		g.drawLine(0,50*8,350,50*8);
		g.drawString(l,10,420);
		if(stateId==-1)
		{
			
		}
		else
		{
			
			for(int i=0;i<ballId;i++)
			{
				g.setColor(Color.BLACK);
				g.fillOval(50*(ar[stateId][i]).y,50*(ar[stateId][i]).x,50,50);
				g.setColor(Color.WHITE);
				g.drawString((new Integer(i)).toString(),50*(ar[stateId][i]).y+20,50*(ar[stateId][i]).x+30);
			}
		}
		g.setColor(Color.white);
		g.fillRect(500,0,50,700);
	}
	public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
  	public void mouseReleased(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e)
	{
	   String cmd=b1.getText();
	   if(cmd.equals("Solve"))
	   {
	   int x=(e.getX()/50)*50;
	   int y=(e.getY()/50)*50;
	   if(x<350&&y<400){
	   gr.setColor(Color.BLACK);
	   gr.fillOval(x,y,50,50);
	   gr.setColor(Color.WHITE);
	   gr.drawString((new Integer(ballId++)).toString(),x+20,y+30);
	   s+=(y/50)+" "+(x/50)+" ";}
	   }
   }
	public void actionPerformed(ActionEvent e)
	{
		gr.setColor(Color.BLACK);
	    String cmd=e.getActionCommand();
		if("Solve".equals(cmd))
	   {
	       t=new StringTokenizer(s);
		   cord=new String[2*ballId];
		   String s2="ab";
		   for(int i=0;i<ballId;i++)
		    {
	           cord[2*i]=t.nextToken();
			   cord[2*i+1]=t.nextToken();
			   s2+=cord[2*i]+cord[2*i+1]+" ";
			}
			// gr.drawString(s2,10,400);
	        TheFling.main(cord);
			
	        //gr.fillOval(TheFling.sol*100,400,50,50);
			if(TheFling.sol==1)
			{
				
				l="Solution Obtained\nstate 0";
				stateId=0;
				b1.setText("Next");
				ar=TheFling.state;
				repaint();
				b3.setVisible(true);
			}
			else
			{
				l="No Solution, Put Balls again";
				s="";
				ballId=0;
				stateId=0;
				repaint();
			}
		}
		else if("Next".equals(cmd))
		{
			stateId++;
			if(stateId>=ballId)stateId--;
			l="Solution Obtained State "+stateId;
			repaint();
		}
		else if("Back".equals(cmd))
		{
			stateId--;
			l="Solution Obtained State "+stateId;
			repaint();
		}
		else if(cmd.equals("Reset"))
		{
			l="Put Balls";
			b1.setText("Solve");
			b3.setVisible(false);
			s="";
			stateId=-1;
			ballId=0;
			repaint();
		}
    }
}
