import java.io.*;
import java.awt.*;
import java.util.*;
public class BrickManager
{
	public static Brick[] loadLevel(int l,int width,int height) throws IOException
	{
		Brick[] b;
		BufferedReader level=new BufferedReader(new FileReader("SmashingLevels/level"+l+".dat"));
		int size=Integer.parseInt(level.readLine());
		b=new Brick[size];
		int w=(width-20)/20-1;//max bricks=20
		int h=20;
		for(int i=0;i<size;i++)
		{
			StringTokenizer t=new StringTokenizer(level.readLine());
			int x=Integer.parseInt(t.nextToken());
			int y=Integer.parseInt(t.nextToken());
			int c=Integer.parseInt(t.nextToken());
			int t1=Integer.parseInt(t.nextToken());
			//Color Coding
			//0-gray,1-red,2-green,3- blue,4 -yellow,5- magenta,6- cyan,7- pink,8- orange
			Color cl=Color.gray;
			switch(c)
			{
				case 0:cl=Color.gray;break;
				case 1:cl=Color.red;break;
				case 2:cl=Color.green;break;
				case 3:cl=Color.blue;break;
				case 4:cl=Color.yellow;break;
				case 5:cl=Color.magenta;break;
				case 6:cl=Color.cyan;break;
				case 7:cl=Color.pink;break;
				case 8:cl=Color.orange;break;
			}
			if(t1<4)
			b[i]=new Brick((x-1)*(w+1)+5,(y-1)*(h+5)+5,w,h,cl,t1);
			else
			b[i]=new Brick((x-1)*(w+1)+5,(y-1)*(h+5)+5,w+1,h+5,cl,t1);
			b[i].setFill(true);
		}
		return b;
	}
}