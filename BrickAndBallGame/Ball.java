import java.awt.*;
class Ball extends OvalSprite implements Moveable//,Intersect
{
    int vx,vy,out=0,xmax,xmin,ymax,ymin;
    Intersect bricks[];
    int hit=0;
    public Ball(int x,int y,int w,int h,Color c,int wd,int ht,Intersect b[])
    {
        super(x,y,w,h,c);
        xmax=wd-5;ymax=ht-5;xmin=5;ymin=5;
        active=false;
        bricks=b;
    }
    public static void main(String args[]){System.out.println("jx");}
    public void setPosition(int x,int y)
    {
        locx=x;locy=y;
    }
    public void setVelocity(int x,int y)
    {
        vx=x;vy=(y!=0)?y:5;
    }
    public void updatePosition()
    {
        locx+=vx;locy+=vy;
    }
    public boolean intersect(int x1,int y1,int x2,int y2)//ball.intersect(bat)
    {
        int x=locx+width/2;
        return(x<=x2&&x>=x1&&locy+height>=y1);
    }
    public void hit(int i)
    {
        vx+=1*i;
        if(vx>10)vx=10;
        if(vx<-10)vx=-10;
        vy=-(int)Math.abs(vy);
    }
    public void update()
    {
        
        if(locx<=xmin)vx=(int)Math.abs(vx);
        if(locx+width>=xmax)vx=-(int)Math.abs(vx);
        if(locy<=ymin)vy=(int)Math.abs(vy);
        updatePosition();
        if(locy+height>ymax)out=1;
        for (int i=0; i<bricks.length; i++) {
            int j=((Brick)bricks[i]).edge(locx,locy,locx+width,locy+height);
         if (j!=-1)
         {
           bricks[i].hit(); 
           
           hit=1;
           if(((Brick)bricks[i]).type==4)hit=0;
           changeDir(j);
         }
        }
    }
    public void changeDir(int i)
    {
        switch(i)//0 up,1 right,2 bottom, 3 left
        {
            case 0:vy=-(int)Math.abs(vy);break;
            case 1:vx=(int)Math.abs(vx);break;
            case 2:vy=(int)Math.abs(vy);break;
            case 3:vx=-(int)Math.abs(vx);break;
        }
    }
    void setTarget(Intersect b[]){bricks=b;}
}