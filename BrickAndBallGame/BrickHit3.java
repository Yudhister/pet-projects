
/**
 * Brick with 2 dots initially
 * if hit it becomes a BrickHit2
 */
import java.awt.*;
public class BrickHit3 extends Bricks
{
    int hits=0;
    public BrickHit3(int x,int y,Color c)
    {
        super(x,y,c);
		this.hitscore=150;
		brickCount=1;
    }
	public void hit()
	{
		hits++;
		if(hits==3){score+=hitscore;suspend();}
	}
	public void paint(Graphics g)
	{
		super.paintRound(g,30,20);
		if(visible){
		g.setColor(Color.black);
		if(hits==0){g.fillOval(locx+15,locy+5,10,10);g.fillOval(locx+35,locy+5,10,10);}
		else if(hits==1)g.fillOval(locx+25,locy+5,10,10);		}
	}
}