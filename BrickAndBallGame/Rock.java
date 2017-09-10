
/**
 * gray brick with rock look
 * does get suspended ever
 * does not count in bricks left
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public class Rock extends Bricks
{
	public Rock(int x,int y,Color c)
    {
        super(x,y,c);
        this.hitscore=0;
		setColor(Color.gray);
		brickCount=0;
    }
	public void hit(){}
	public void paint(Graphics g)
	{
		super.paint3D(g,true);
		if(visible){
		g.setColor(Color.black);
		for(int i=0;i<13;i+=2){g.fillOval(locx+4*(i+1),locy+2,4,4);g.fillOval(locx+4*(i+1),locy+13,4,4);}}
	}
}
