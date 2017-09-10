import java.awt.*;
public class BallBonus extends Bonus
{
//int time=0;
 /* slow=1
 * fast=2
 * multiball=3
 * powerball=4
 * goldenball=5
 * 
 */ 
    BallBonus(int id)
    {
        super();
        this.id=id;
        
    }
    void update()
    {
        if(visible)
        {
            int time=b.count-start;
            if(time>300)visible=false;
        }
    }
    public void paint(Graphics g)
    {
        //g.setColor(new Color(100,100,100));
        //g.fillRect(locx,locy,60,20);
        switch(id)
        {
            case 1:
            g.setColor(Color.green);
            g.fillRect(locx,locy,60,20);
            g.setColor(Color.black);
            for(int i=0;i<3;i++)
            {
                g.fillOval(locx+10+10*i+8,0,4,4);
                g.fillOval(locx+10+10*i+4,4,4,4);
                g.fillOval(locx+10+10*i,8,4,4);
                g.fillOval(locx+10+10*i+4,12,4,4);
                g.fillOval(locx+10+10*i+8,16,4,4);
            }
            break;
            case 2:
            g.setColor(Color.red);
            g.fillRect(locx,locy,60,20);
            g.setColor(Color.black);
            for(int i=0;i<3;i++)
            {
                g.fillOval(locx+10+10*i,0,4,4);
                g.fillOval(locx+10+10*i+4,4,4,4);
                g.fillOval(locx+10+10*i+8,8,4,4);
                g.fillOval(locx+10+10*i+4,12,4,4);
                g.fillOval(locx+10+10*i,16,4,4);
            }
            break;
            case 3:
            
        }
    }
}