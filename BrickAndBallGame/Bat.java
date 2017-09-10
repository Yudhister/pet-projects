import java.awt.*;
public class Bat extends RectSprite //implements Moveable
{
	
	int xmin,xmax;
	Bonus bonus;
	public Bat(int x,int y,int w,int h,Color c,int wd)
	{
		super(x,y,w,h,c);
		xmin=5;xmax=wd-5;
		bonus=null;
	}
	public void setPosition(int x)
	{
		if(x<xmin+width/2) locx=xmin;
		else if(x>xmax-width/2)locx=xmax-width;
		else locx=x-width/2;
	}
	public void paint(Graphics g)
	{
	    if(bonus==null)super.paint(g);
	    else super.paint(g);
	}
}