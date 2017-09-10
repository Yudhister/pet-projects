
/**
 * looks like a rock with 1 soft edge
 * suspends only if hit from that edge
 * takes just 1 hit
 */
import java.awt.*;
public class BrickBottomEdge extends Bricks
{
    int activeEdge=2;
	Color edgeColor;
	//0 up,1 right,2 bottom, 3 left
	public BrickBottomEdge(int x,int y,Color c)
    {
        super(x,y,Color.gray);
		this.hitscore=150;
		edgeColor=c;
        brickCount=1;
    }
	public void hit()
	{
		if(Balls.edge==activeEdge){score+=hitscore;suspend();}
	}
	public void paint(Graphics g)
	{
		super.paint3D(g,true);
		if(visible){
		g.setColor(edgeColor);
		//if(activeEdge==0)
		g.fillArc(locx,locy+15,width,10,0,180);		
		}
	}
}
