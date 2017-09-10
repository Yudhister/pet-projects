import java.awt.*;
public class Brick extends RectSprite implements Intersect
{
    int type;
    int hits=0;
    public Brick(int x,int y, int w,int h,Color c,int t)
    {
        super(x,y,w,h,c);
        if(t>4)t=4;
        type=t;
        if(type==4)setColor(Color.gray);
    }
    public boolean intersect(int x1,int y1,int x2,int y2)
    {
        if((edge(x1,y1,x2,y2)!=-1))
        return true;
        else return false;
    }
    public void hit()
    {
        hits++;
        if(hits>=type&&type!=4)
            suspend();
        
    }
    public int edge(int x1,int y1,int x2,int y2)//defines brick.intersect(ball)
    {
        if(!active) return -1;
        int xmp=(x1+x2)/2;
        int ymp=(y1+y2)/2;
        //0 up,1 right,2 bottom, 3 left
        if(xmp>=locx&&xmp<=locx+width&&y2>=locy&&y1<locy)return 0;
        if(ymp>=locy&&ymp<=locy+height&&x1<=locx+width&&x2>locx+width)return 1;
        if(xmp>=locx&&xmp<=locx+width&&y1<=locy+height&&y2>locy+height)return 2;
        if(ymp>=locy&&ymp<=locy+height&&x2>=locx&&x1<locx)return 3;
        return -1;
    }
    public void update(){}
    public void paint(Graphics g)
    {
        if(type<4)
        super.paintRound(g,30,20);
        else
        super.paint(g);
        g.setColor(Color.black);
        if(type==4){g.drawLine(locx,locy,locx+width,locy+height);g.drawLine(locx+width,locy,locx,locy+height);}
        else{
        switch(type-hits)
        {           
            case 0:break;
            case 1: break;
            case 2:g.fillOval(locx+(4*(width+1))/10,locy+5,10,10);break;
            case 3:g.fillOval(locx+(2*(width+1))/10,locy+5,10,10);g.fillOval(locx+(7*(width+1))/10,locy+5,10,10);break;
            default :break;
        }
        }
    }
}