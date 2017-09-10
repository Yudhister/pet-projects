
/**
 * alternates look b/w a rock and a brick
 * can be suspended only if hit in brick state
 */
import java.awt.*;
public class HalfRockBrick extends Bricks
{
    boolean state=true;//true for brick,false for rock
	Color brickColor;
	public HalfRockBrick(int x,int y,Color c)
    {
        super(x,y,c);
		brickColor=c;
        this.hitscore=250;
		brickCount=1;
    }
	public void hit()
	{
		if(state){suspend();score+=hitscore;}
	}
	public void paint(Graphics g)
	{
		state=(count%100>=50);
		if(state)
		{
			setColor(brickColor);
			super.paintRound(g,30,20);
		}
		else
		{
			setColor(Color.gray);
			super.paint3D(g,true);
			if(visible){
			g.setColor(Color.black);
			for(int i=0;i<13;i+=2){g.fillOval(locx+4*(i+1),locy+2,4,4);g.fillOval(locx+4*(i+1),locy+13,4,4);}}
		}
	}
}
