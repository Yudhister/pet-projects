
/**
 brick that has 1 dot initially
 if hit the dot vanishes and it becomes BrickHit1
 */
import java.awt.*;
public class BrickHit2 extends Bricks
{
	int hits=0;
	public BrickHit2(int x,int y,Color c)
	{
		super(x,y,c);
		this.hitscore=100;
		brickCount=1;
	}
	public void hit()
	{
		hits++;
		if(hits==2){score+=hitscore;suspend();}
		
		
	}
	public void paint(Graphics g)
	{
		super.paintRound(g,30,20);
		if(visible){
		g.setColor(Color.black);
		if(hits==0)g.fillOval(locx+25,locy+5,10,10);}
		
	}
}
