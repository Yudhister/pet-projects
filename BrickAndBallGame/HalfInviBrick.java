
/**
 * alternates look b/w a brick and paler brick
 * can be suspended only if hit in brick state
 */
import java.awt.*;
public class HalfInviBrick extends Bricks
{
    boolean state=true;//true for brick false for invisible
    public HalfInviBrick(int x,int y,Color c)
    {
        super(x,y,c);
        this.hitscore=250;
        brickCount=1;
    }
    public void hit()
    {
        suspend();score+=hitscore;
    }
    public void paint(Graphics g)
    {
        state=(count%100>=50);
        if(state)super.paintRound(g,30,20);
    }
}
