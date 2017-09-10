
/**
 * hide bonus behind a brick
 * when the brick is suspended the show id function is called
 * if ball bonus it stays in position
 * if bat bonus it falls untill it reaches bat.locx
 * if bonus is hit by it is activated and sent to ball
 * if bonus lands on bat it is activated and sent to bat else made null
 * Bonus class with fixed bonus ids
 * slow=1
 * fast=2
 * multiball=3
 * powerball=4
 * goldenball=5
 * 
 * enlarge bat=6
 * small bat=7
 * fire bat=8
 * sticky bat=9
 * 
 * extra pad/life=10
 * level up=11
 * bonus that exists from start till hit
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public class Bonus
{
    static Bonus ar[]={new BallBonus(1),new BallBonus(2),new BallBonus(3),new BallBonus(4),new BallBonus(5),new BatBonus(6),new BatBonus(7),new BatBonus(8),new BatBonus(9)};
    Bricks b;
    int id,locx,locy,start;
    boolean visible,caught;
    Bonus()
    {
        locx=0;
        locy=0;
        visible=false;
        caught=false;
        start=-1;
        
    }
    void setPosition(Bricks br)
    {
        b=br;
        locx=b.locx;
        locy=b.locy;
    }
    public int edge(int x1,int y1,int x2,int y2)//defines brick.intersect(ball)
    {
        if(!visible) return -1;
        int xmp=(x1+x2)/2;
        int ymp=(y1+y2)/2;
        //0 up,1 right,2 bottom, 3 left
        if(xmp>=locx&&xmp<=locx+60&&y2>=locy&&y1<locy)return 0;
        if(ymp>=locy&&ymp<=locy+20&&x1<=locx+60&&x2>locx+60)return 1;
        if(xmp>=locx&&xmp<=locx+60&&y1<=locy+20&&y2>locy+20)return 2;
        if(ymp>=locy&&ymp<=locy+20&&x2>=locx&&x1<locx)return 3;
        return -1;
    }
    public boolean intersect(int x1,int y1,int x2,int y2)
    {
        if((edge(x1,y1,x2,y2)!=-1))
        return true;
        else return false;
    }
    void start()
    {
        start=b.count;
        visible=true;
        caught=false;
    }
    
    void suspend()
    {
        visible=false;
        caught=true;
    }
    void update()
    {
        
    }
    void paint(Graphics g)
    {
        
    }
}