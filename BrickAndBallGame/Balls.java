import java.awt.*;
class Balls extends OvalSprite implements Moveable//,Intersect
{
    int vx,vy,out=0,xmax,xmin,ymax,ymin;
    Bricks bricks[];
    Explosive ex[];
    int hit=0;
    int wormCount=0;
    WormHole w[];
    static int edge=-1;
    public Balls(int x,int y,int w,int h,Color c,int wd,int ht,Bricks b[])
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
        vx+=3*i;
        if(vx>10)vx=10;
        if(vx<-10)vx=-10;
        vy=-(int)Math.abs(vy);
    }
    public void update()
    {
        
        if(locx<=xmin)vx=(int)Math.abs(vx);
        if(locx+width>=xmax)vx=-(int)Math.abs(vx);
        if(locy<=ymin)vy=(int)Math.abs(vy);
        if(out==0)updatePosition();
        if(locy+height>ymax)out=1   ;
        for (int i=0; i<bricks.length; i++)
        {
            int j=bricks[i].edge(locx,locy,locx+width,locy+height);
            edge=j;
            if(j!=-1)
            {
                if(bricks[i] instanceof HalfInviBrick)
                {if(((HalfInviBrick)bricks[i]).state)bricks[i].hit();changeDir(j);}
                else if(bricks[i] instanceof Explosive)
                {
                    changeDir(j);
                    ((Explosive)bricks[i]).explode(ex,bricks);
                    //Bricks.score+=bricks[i].hitscore;
                    Explosive.id=0;
                }
                else if(bricks[i] instanceof WormHole&&wormCount==2)
                {
                    for(int k=0;k<wormCount;k++)if(w[k]!=bricks[i]){setPosition(w[k].locx+30,w[k].locy+20);break;}
                    for(int k=0;k<wormCount;k++){w[k].suspend();}
                }
                else
                {
                bricks[i].hit();
                //hit=1;
                //if(((Brick)bricks[i]).type==4)hit=0;
                changeDir(j);
                }
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
    void setTarget(Bricks b[])
    {
        bricks=b;
        int l=0;
        for(int i=0;i<b.length;i++)
        {
            if(b[i] instanceof Explosive)l++;
            if(b[i] instanceof WormHole)wormCount++;
        }
        w=new WormHole[wormCount];
        ex=new Explosive[l];
        l=0;
        wormCount=0;
        for(int i=0;i<b.length;i++)
        {
            if(b[i] instanceof Explosive){ex[l++]=(Explosive)b[i];}
            if(b[i] instanceof WormHole){w[wormCount++]=(WormHole)b[i];}
        }
        
    }
}