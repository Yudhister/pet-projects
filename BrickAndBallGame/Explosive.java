
/**
 *looks like lightining
 *explodes and kills bricks with x,y in a particular range
 */
import java.awt.*;
import java.io.*;

public class Explosive extends Bricks
{
    int rangex,rangey;
    int start=-1,time;
    static int id=0;
    public Explosive(int x,int y,Color c)
    {
        super(x,y,c);
        this.hitscore=0;
        setColor(new Color(40,40,40));
        rangex=50;
        rangey=18;
        brickCount=1;
    }
    /*public void explode(Bricks[] b)
    {
        start=count;time=0;    
        Bricks[] b1=new Bricks[b.length];//active bomb array
        Bricks[] b2=new Bricks[b.length];//active bombs in range
        int c1=0,c2=0;
        
        
       try{
           int i=0;
       for(i=0;i<b.length;i++){
            if(!(b[i].isActive())){continue;}
            b1[c1++]=b[i];
            if((b[i].locx<locx+60+rangex)&&(b[i].locx+60>locx-rangex)&&(b[i].locy<locy+20+rangey)&&(b[i].locy+20>locy-rangey))
            {
                //if(b[i] instanceof Explosive)
                b2[c2++]=b[i];
                //else
                //b[i].suspend();
                //hitscore+=b[i].hitscore;
               }
           }
           suspend();
       //for(i=0;i<b.length;i++)if(b2[i]==null)break;
       for(int j=0;j<c2;j++)((Explosive)b2[j]).explode(b1);
       }catch(Exception e){System.out.println(e.toString());}
       
        
        //Bricks.score+=hitscore;
       }
    public void hit(Bricks[] b)
    {
       start=count;time=0; 
       int c1=0;
       int c2=0;
       Bricks[] b1=new Bricks[b.length];//active bomb array;
       Bricks[] b2=new Bricks[b.length];//active bombs in range;
        try{
       for(int i=0;i<b.length;i++){
            if(!b[i].isActive()){continue;}
            if((b[i] instanceof Explosive))b1[c1++]=b[i];
            if((b[i].locx<locx+60+rangex)&&(b[i].locx+60>locx-rangex)&&(b[i].locy<locy+20+rangey)&&(b[i].locy+20>locy-rangey))
            {
                if(b[i] instanceof Explosive)
                   b2[c2++]=b[i];
                //((Explosive)b[i]).explode(b);
                else
                b[i].suspend();
                hitscore+=b[i].hitscore;
               }
           }
           suspend();
           for(int i=0;i<c2;i++)((Explosive)b2[i]).explode(b1);
       }catch(Exception e){System.out.println(e.toString());}
        //suspend();
        Bricks.score+=hitscore;
       }*/
       public void explode(Explosive ex[],Bricks b[])
       {
           start=count+id;time=-1;
           setActive(false);
           for(int i=0;i<b.length;i++)
           {
                if((b[i].locx<locx+60+rangex)&&(b[i].locx+60>locx-rangex)&&(b[i].locy<locy+20+rangey)&&(b[i].locy+20>locy-rangey))
                {
                    if(!(b[i] instanceof Explosive)&&(b[i].isActive())){b[i].hit();}
                }
           }
           Bricks.score+=hitscore;
           id+=3;
           for(int i=0;i<ex.length;i++)
           {
                if((ex[i].locx<locx+60+rangex)&&(ex[i].locx+60>locx-rangex)&&(ex[i].locy<locy+20+rangey)&&(ex[i].locy+20>locy-rangey))
                {
                    if(ex[i].isActive()){ex[i].explode(ex,b);}
                }
           }
       }
    public void paint(Graphics g)
    {
        //if(start!=0)
        time=count-start;
        if(time==0)setVisible(false);
        super.paint(g);
        if(visible){
        int cx=locx+30;
        int cy=locy+10;
        for(int i=0;i<6;i++)
        {
            g.setColor(Color.yellow);
            g.fillArc(cx-10,cy-10,20    ,20,i*60,30);
            g.setColor(Color.black);
            g.fillArc(cx-10,cy-10,20,20,i*60+30,30);
        }
        }
        
        else 
        switch(time)
        {
            case 0:
            g.setColor(Color.yellow);    
            g.fillRect(locx-3,locy-2,66,24);
            break;
            case 1:
            g.setColor(Color.gray);    
            g.fillRect(locx-6,locy-4,72,28);
            break;
            case 2:
            g.setColor(Color.yellow);    
            g.fillRect(locx-9,locy-6,78,32);
            break;
            case 3:
            g.setColor(Color.gray);    
            g.fillRect(locx-12,locy-8,84,36);
            break;
            case 4:
            g.setColor(Color.yellow);    
            g.fillRect(locx-15,locy-10,90,40);
            break;
            default:break;
        }
    }
}
