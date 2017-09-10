
/**
 * invisible in beginning
 * becomes visible at first hit
 */
import java.awt.*;
public class InvisibleBrick extends Bricks
{
    public InvisibleBrick(int x,int y,Color c)
    {
        super(x,y,c);
		this.hitscore=200;
        setVisible(false);
		brickCount=1;
    }
	public void hit()
	{
		if(visible&&active){suspend();score+=hitscore;}
		if(active&&!visible)setVisible(true);
	}
	public void paint(Graphics g)
	{
		super.paintRound(g,30,20);
	}
}
