
/**
 *a pair of illusion-like bricks
 *transport from 1 to another
 */
import java.awt.*;
public class WormHole extends Bricks
{
    public WormHole(int x,int y,Color c)
    {
        super(x,y,new Color(10,10,10));
        this.hitscore=50;
		brickCount=1;
    }
	public void hit()
	{
	   suspend();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		if(visible){
        int cx=locx+30;
        int cy=locy+10;
        for(int i=0;i<6;i++)
        {
            g.setColor(new Color(0,150,0));
            g.fillArc(cx-10,cy-10,20,20,i*60+count,30);
            g.setColor(Color.white);
            g.fillArc(cx-10,cy-10,20,20,i*60+30+count,30);
        }
        }
	}
}
