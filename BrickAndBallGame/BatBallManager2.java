import java.awt.*;
//import java.applet.*;
public class BatBallManager2
{
    Balls ball;
    Bat bat;
    int ballwidth,ballheight;
    int batwidth,batheight;
    int width,height;//window
    int batPrevX;

	public BatBallManager2(Balls b1,Bat b2,int width,int height)
    {
        ball=b1;
        bat=b2;
        batPrevX=bat.locx;
        this.width=width;
        this.height=height;
    }
   
    public void update()
    {
        
        if(ball.active)
        {
            if(ball.intersect(bat.locx,bat.locy,bat.locx+bat.width,bat.locy+bat.height)){
                if(bat.locx>batPrevX)
                    ball.hit(1);
                else if(bat.locx<batPrevX)
                    ball.hit(-1);
                else
                    ball.hit(0);
            }
            ball.update();  
        }
        else
        {
            ball.setPosition(bat.locx+bat.width/2,bat.locy-ball.height-5);
            ball.setVelocity(ball.vx,ball.vy);
        }        
    }
}

