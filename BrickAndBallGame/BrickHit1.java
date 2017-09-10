
/**
 * normal brick
 * brick that is suspended once it is hit
 */
import java.awt.*;
public class BrickHit1 extends Bricks
{
    
	BrickHit1(int x,int y,Color z){super(x,y,z);this.hitscore=50;brickCount=1;}
	public void hit()
	{
		score+=hitscore;
		suspend();
	}
	public void paint(Graphics g)
	{
		super.paintRound(g,30,20);
	}
}