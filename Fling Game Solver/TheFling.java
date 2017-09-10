public class TheFling
{
	static int n;//total no. of balls
	static Ball state[][];
	static int sol=0;
	static void main(String args[])
	{
		n=args.length/2;
		state=new Ball[n][n];
		for(int i=0;i<2*n;i+=2)
		{
			state[0][i/2]=new Ball(Integer.parseInt(args[i]),Integer.parseInt(args[i+1]));
		}
		sol=run(0);
		System.out.println("sol= "+sol);
	}
	static int run(int t)//t= current state, n-(no. of balls remaining)
	{
		System.out.println("running state "+t);
		if(t==(n-1))
		{
			System.out.println("Solution obtained");
			//sol=1;
			System.exit(1);
			return 1;
		}
		int tick=0;
		for(int i=0;i<n;i++)
		{
			Ball b=state[t][i];
			System.out.println("moving ball "+i+" in state "+t);
			if(b.x==10){System.out.println("ball "+i+" is off board");
				continue;}
			if(b.validLeft(state[t],n))
			{
				System.out.println("moving left");
				state[t+1]=b.moveLeft(state[t],n,i);
				tick=1;
				//run(t+1);
				if(run(t+1)==1)return 1;
			}
			if(b.validUp(state[t],n))
			{
				System.out.println("moving up");
				state[t+1]=b.moveUp(state[t],n,i);
				tick=1;
				//run(t+1);
				if(run(t+1)==1)return 1;
			}
			if(b.validRight(state[t],n))
			{
				System.out.println("moving right");
				state[t+1]=b.moveRight(state[t],n,i);
				tick=1;
				//run(t+1);
				if(run(t+1)==1)return 1;
			}
			if(b.validDown(state[t],n))
			{
				System.out.println("moving down");
				state[t+1]=b.moveDown(state[t],n,i);
				tick=1;
				//run(t+1);
				if(run(t+1)==1)return 1;
			}
			System.out.println("ball "+i+" has no valid move");
		}
		System.out.println("state "+t+" has no valid move\ngoing back to state "+(t-1));
		return 0;
	}
}
class Ball
{
	int x,y;
	Ball()
	{
		x=0;y=0;
	}
	Ball(int a, int b)
	{
		x=a; y=b;
	}
	boolean validLeft(Ball[] b, int n)
	{
		int f=0;
		for(int i=0;i<n;i++)
		{
			if((b[i].x==x)&&(b[i].y>=0)&&(b[i].y<=y-1))
			{
				if(b[i].y==y-1)
					f+=100;
				f++;
			}
		}
		if(f>0&&f<99)
			return true;
		else
			return false;
	}
	Ball[] moveLeft(Ball[] b, int n,int currentId)
	{
		Ball a[]=new Ball[n];
		for(int i=0;i<n;i++)
		{
			a[i]=new Ball(b[i].x,b[i].y);
		}
		int f=0;//no. of balls in required space
		int id[];//ids of those balls
		//counting no. of balls
		for(int i=0;i<n;i++)
		{
			if((b[i].x==x)&&(b[i].y>=0)&&(b[i].y<y-1))//the required space if move is valid
			{
				f++;
			}
		}
		id=new int[f];
		f=0;
		//saving the id of required balls
		for(int i=0;i<n;i++)
		{
			if((b[i].x==x)&&(b[i].y>=0)&&(b[i].y<y-1))
			{
				id[f++]=i;
			}
		}
		//sorting these on basis of y coordinate of ball[id[i]]
		//after sort b[id[0]] represents closest ball 
		for(int i=0;i<f;i++)
		{
			int max=b[id[i]].y;
			int maxid=i;
			for(int j=i;j<f;j++)
			{
				if(b[id[j]].y>max)
				{
					max=b[id[j]].y;
					maxid=j;
				}
			}
			int t=id[maxid];
			id[maxid]=id[i];
			id[i]=t;
		}
		//moving current ball
		a[currentId]=new Ball(b[currentId].x,b[id[0]].y+1);
		//moving all balls to left of current ball
		for(int i=0;i<f-1;i++)
		{
			a[id[i]]=new Ball(b[id[i]].x,b[id[i+1]].y+1);
		}
		//moving last ball off board
		a[id[f-1]]=new Ball(10,10);
		return a;
	}
	boolean validRight(Ball[] b, int n)
	{
		int f=0;
		for(int i=0;i<n;i++)
		{
			if((b[i].x==x)&&(b[i].y>=y+1)&&(b[i].y<=6))
			{
				if(b[i].y==y+1)
					f+=100;
				f++;
			}
		}
		if(f>0&&f<99)
			return true;
		else
			return false;
	}
	Ball[] moveRight(Ball[] b, int n,int currentId)
	{
		Ball a[]=new Ball[n];
		for(int i=0;i<n;i++)
		{
			a[i]=new Ball(b[i].x,b[i].y);
		}
		int f=0;//no. of balls in required space
		int id[];//ids of those balls
		//counting no. of balls
		for(int i=0;i<n;i++)
		{
			if((b[i].x==x)&&(b[i].y>y+1)&&(b[i].y<7))//the required space if move is valid
			{
				f++;
			}
		}
		id=new int[f];
		f=0;
		//saving the id of required balls
		for(int i=0;i<n;i++)
		{
			if((b[i].x==x)&&(b[i].y>y+1)&&(b[i].y<7))
			{
				id[f++]=i;
			}
		}
		//sorting these on basis of y coordinate of ball[id[i]]
		//after sort b[id[0]] represents closest ball 

		for(int i=0;i<f;i++)
		{
			int min=b[id[i]].y;
			int minid=i;
			for(int j=i;j<f;j++)
			{
				if(b[id[j]].y<min)
				{
					min=b[id[j]].y;
					minid=j;
				}
			}
			int t=id[minid];
			id[minid]=id[i];
			id[i]=t;
		}
		//moving current ball
		a[currentId]=new Ball(b[currentId].x,b[id[0]].y-1);
		//moving all balls to left of current ball
		for(int i=0;i<f-1;i++)
		{
			a[id[i]]=new Ball(b[id[i]].x,b[id[i+1]].y-1);
		}
		//moving last ball off board
		a[id[f-1]]=new Ball(10,10);
		return a;
	}
	boolean validUp(Ball[] b, int n)
	{
		int f=0;
		for(int i=0;i<n;i++)
		{
			if((b[i].y==y)&&(b[i].x>=0)&&(b[i].x<=x-1))
			{
				if(b[i].x==x-1)
					f+=100;
				f++;
			}
		}
		if(f>0&&f<99)
			return true;
		else
			return false;
	}
	Ball[] moveUp(Ball[] b, int n,int currentId)
	{
		Ball a[]=new Ball[n];
		for(int i=0;i<n;i++)
		{
			a[i]=new Ball(b[i].x,b[i].y);
		}
		int f=0;//no. of balls in required space
		int id[];//ids of those balls
		//counting no. of balls
		for(int i=0;i<n;i++)
		{
			if((b[i].y==y)&&(b[i].x>=0)&&(b[i].x<x-1))//the required space if move is valid
			{
				f++;
			}
		}
		id=new int[f];
		f=0;
		//saving the id of required balls
		for(int i=0;i<n;i++)
		{
			if((b[i].y==y)&&(b[i].x>=0)&&(b[i].x<x-1))
			{
				id[f++]=i;
			}
		}
		//sorting these on basis of x coordinate of ball[id[i]]
		//after sort b[id[0]] represents closest ball 
		for(int i=0;i<f;i++)
		{
			int max=b[id[i]].x;
			int maxid=i;
			for(int j=i;j<f;j++)
			{
				if(b[id[j]].x>max)
				{
					max=b[id[j]].x;
					maxid=j;
				}
			}
			int t=id[maxid];
			id[maxid]=id[i];
			id[i]=t;
		}
		//moving current ball
		a[currentId]=new Ball(b[id[0]].x+1,b[currentId].y);
		//moving all balls to left of current ball
		for(int i=0;i<f-1;i++)
		{
			a[id[i]]=new Ball(b[id[i+1]].x+1,b[id[i]].y);
		}
		//moving last ball off board
		a[id[f-1]]=new Ball(10,10);
		return a;
	}
	boolean validDown(Ball[] b, int n)
	{
		int f=0;
		for(int i=0;i<n;i++)
		{
			if((b[i].y==y)&&(b[i].x>=x+1)&&(b[i].x<8))
			{
				if(b[i].x==x+1)
					f+=100;
				f++;
			}
		}
		if(f>0&&f<99)
			return true;
		else
			return false;
	}
	Ball[] moveDown(Ball[] b, int n,int currentId)
	{
		Ball a[]=new Ball[n];
		for(int i=0;i<n;i++)
		{
			a[i]=new Ball(b[i].x,b[i].y);
		}
		int f=0;//no. of balls in required space
		int id[];//ids of those balls
		//counting no. of balls
		for(int i=0;i<n;i++)
		{
			if((b[i].y==y)&&(b[i].x>x+1)&&(b[i].x<8))//the required space if move is valid
			{
				f++;
			}
		}
		id=new int[f];
		f=0;
		//saving the id of required balls
		for(int i=0;i<n;i++)
		{
			if((b[i].y==y)&&(b[i].x>x+1)&&(b[i].x<8))
			{
				id[f++]=i;
			}
		}
		//sorting these on basis of x coordinate of ball[id[i]]
		//after sort b[id[0]] represents closest ball 
		for(int i=0;i<f;i++)
		{
			int min=b[id[i]].x;
			int minid=i;
			for(int j=i;j<f;j++)
			{
				if(b[id[j]].x<min)
				{
					min=b[id[j]].x;
					minid=j;
				}
			}
			int t=id[minid];
			id[minid]=id[i];
			id[i]=t;
		}
		//moving current ball
		a[currentId]=new Ball(b[id[0]].x-1,b[currentId].y);
		//moving all balls down of current ball
		for(int i=0;i<f-1;i++)
		{
			a[id[i]]=new Ball(b[id[i+1]].x-1,b[id[i]].y);
		}
		//moving last ball off board
		a[id[f-1]]=new Ball(10,10);
		return a;
	}
}