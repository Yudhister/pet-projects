
/**
 * The super class
 * static score;
 * functions edge,intersect,hit(might be overriden)
 * use dynamic method binding
 * paint function to be tested
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.io.*;
import java.util.*;

public class Bricks extends RectSprite
{
    static int score=0;
    static int level=1;
    int hitscore;
    int brickCount=0;
    int count=0;
    Bonus bonus;
    Bricks(int x,int y,Color c){super(x,y,59,20,c);setFill(true);}
    static Bricks create(int x,int y,Color c,int t)
    {
        Bricks s=new Bricks(x,y,c);
        
        switch(t)
        {
            case 1:s= new BrickHit1(x,y,c);break;
            case 2:s= new BrickHit2(x,y,c);break;
            case 3:s= new BrickHit3(x,y,c);break;
            case 4:s= new Rock(x,y,c);break;
            case 5:
                if(y<=7)s=new BrickHit1(x,y,c);
                else    s= new BrickTopEdge(x,y,c);
                break;
            case 6:
                if(x>=1135)s=new BrickHit1(x,y,c);
                else    s= new BrickRightEdge(x,y,c);
                break;
            case 7:
                //if(y<=7)s=new BrickHit1(x,y,c);
                //else
                s= new BrickBottomEdge(x,y,c);
                break;
            case 8:
                if(x<=7)s=new BrickHit1(x,y,c);
                else    s= new BrickLeftEdge(x,y,c);
                break;
                
            case 9:s= new Explosive(x,y,c);break;
            case 10:s= new InvisibleBrick(x,y,c);break;
            case 11:s= new InvisibleRock(x,y,c);break;
            case 12:s= new HalfRockBrick(x,y,c);break;
            case 13:s= new HalfInviBrick(x,y,c);break;
            case 14:s= new WormHole(x,y,c);break;
            default: s= new BrickHit1(x,y,c);
        }
        s.bonus=null;
        return s;
    }
    
    public int edge(int x1,int y1,int x2,int y2)//defines brick.intersect(ball)
    {
        if(!active) return -1;
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
    public void hit(){}
    public void paint(Graphics g){super.paint(g);}
    public void paint3D(Graphics g,boolean risen){super.paint3D(g,risen);}
    public void paintRound(Graphics g,int arcx,int arcy){super.paintRound(g,arcx,arcy);}
    public void update(){count++;}
    public static void reset(){score=0;}
    public static Bricks[] loadLevel(int l) throws IOException
    {
        level=l;
        Bricks[] b;
        BufferedReader level=new BufferedReader(new FileReader("SmashingLevels/level"+l+".dat"));
        int size=Integer.parseInt(level.readLine());
        b=new Bricks[size];
        for(int i=0;i<size;i++)
        {
            StringTokenizer t=new StringTokenizer(level.readLine());
            int x=Integer.parseInt(t.nextToken());
            int y=Integer.parseInt(t.nextToken());
            int c=Integer.parseInt(t.nextToken());
            int t1=Integer.parseInt(t.nextToken());
            //Color Coding
            //0-red,1-red,2-green,3- blue,4 -yellow,5- magenta,6- cyan,7- pink,8- orange
            Color cl=Color.gray;
            switch(c)
            {
                case 0:
                case 1:cl=Color.red;break;
                case 2:cl=Color.green;break;
                case 3:cl=Color.blue;break;
                case 4:cl=Color.yellow;break;
                case 5:cl=Color.magenta;break;
                case 6:cl=Color.cyan;break;
                case 7:cl=Color.pink;break;
                case 8:cl=Color.orange;break;
            }
            b[i]=Bricks.create((x-1)*(60)+5,(y-1)*(25)+5,cl,t1);
            b[i].setFill(true);
        }
        return b;
    }
    public void suspend()
    {
       setVisible(false);
       setActive(false);
    }
    public static void main()
    {
        Bricks b[]=new Bricks[3];
        for(int i=0;i<3;i++)b[i]=create(1,1,Color.green,i);
        for(int i=0;i<9;i++)b[i%3].hit();
        System.out.println("final score"+score);
        reset();
    }
}