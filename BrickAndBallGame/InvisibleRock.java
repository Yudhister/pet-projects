
/**
 * otherwise invisible rocks
 * become active if hit at a particular edge
 */
import java.awt.*;
public class InvisibleRock extends Bricks
{
    public InvisibleRock(int x,int y,Color c)
    {
        super(x,y,c);
		this.hitscore=0;
        setVisible(false);
		brickCount=0;
    }
	public void hit()
	{
		if((!visible)&&active){setVisible(true);}
	}
	public void paint(Graphics g)
	{
		setColor(Color.gray);
	    super.paint3D(g,true);
		if(visible){
		g.setColor(Color.black);
		for(int i=0;i<13;i+=2){g.fillOval(locx+4*(i+1),locy+2,4,4);g.fillOval(locx+4*(i+1),locy+13,4,4);}}
	}
}
