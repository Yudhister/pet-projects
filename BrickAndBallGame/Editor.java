import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;

public class Editor extends JFrame implements MouseListener,ActionListener
{
    CheckboxGroup r1,r2;
    String s2;
    Label color,type;
    Button save,reset,erase;
    boolean fill=true;
    int x=0,y=0,cId=0,tId=0;
    Mode ar[][];
    Checkbox[] c,t;
    JPanel colorPanel,typePanel,buttonPanel,southPanel;
    Image i;Graphics g;
    JPanel deadPanel;
    Label status;
    Button back;
    
    public Editor()
    {
        super("Level Editor");
		setVisible(true);
		setSize(1200,700);
		setResizable(false);
		s2="hi";
		ar=new Mode[20][20];
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                ar[i][j]=new Mode(0,1,false);
            }
        }
        c=new Checkbox[9];
        t=new Checkbox[4];
        southPanel=new JPanel(new GridLayout(4,1));
        buttonPanel=new JPanel(new GridLayout(1,3));
        typePanel=new JPanel(new GridLayout(1,4));
        colorPanel=new JPanel(new GridLayout(1,10));
        deadPanel=new JPanel(new GridLayout(1,2));
        r1=new CheckboxGroup();
        r2=new CheckboxGroup();
        c[0]=new Checkbox("gray",true,r1);
        c[1]=new Checkbox("red",false,r1);
        c[2]=new Checkbox("green",false,r1);
        c[3]=new Checkbox("blue",false,r1);
        c[4]=new Checkbox("yellow",false,r1);
        c[5]=new Checkbox("magenta",false,r1);
        c[6]=new Checkbox("cyan",false,r1);
        c[7]=new Checkbox("pink",false,r1);
        c[8]=new Checkbox("orange",false,r1);
        t[0]=new Checkbox("1 hit",true,r2);
        t[1]=new Checkbox("2 hit",false,r2);
        t[2]=new Checkbox("3 hit",false,r2);
        t[3]=new Checkbox("rock",false,r2);
        
        color=new Label("Color");
        type=new Label("Type");
        status=new Label("Create Level");
        save=new Button("Save");save.addActionListener(this);
        reset=new Button("Reset");reset.addActionListener(this);
        erase=new Button("Erase");erase.addActionListener(this);
        back=new Button("Back To Main Menu");back.addActionListener(this);
        
        
        setLayout(new BorderLayout());
        buttonPanel.add(save);
        buttonPanel.add(reset);
        buttonPanel.add(erase);
        colorPanel.add(color);
        colorPanel.add(c[0]);
        colorPanel.add(c[1]);
        colorPanel.add(c[2]);
        colorPanel.add(c[3]);
        colorPanel.add(c[4]);
        colorPanel.add(c[5]);
        colorPanel.add(c[6]);
        colorPanel.add(c[7]);
        colorPanel.add(c[8]);
        typePanel.add(type);
        typePanel.add(t[0]);
        typePanel.add(t[1]);
        typePanel.add(t[2]);
        typePanel.add(t[3]);
        deadPanel.add(status);
        deadPanel.add(back);
        southPanel.add(colorPanel);
        southPanel.add(typePanel);
        southPanel.add(buttonPanel);
        
        southPanel.add(deadPanel);
        add(southPanel,"South");
        addMouseListener(this);
        i=createImage(1200,700);
        g=i.getGraphics();
        
    }
    public void update(Graphics g1){paint(g1);}
    public void paint(Graphics g1)
    {
        g1.setColor(Color.white);
        g1.fillRect(0,0,1200,40);
        g.setColor(Color.white);
        g.fillRect(0,0,1200,700);
        g.setColor(Color.black);
        g.drawLine(0,0,1200,0);
        for(int i=1;i<=20;i++)
        {
            g.drawLine(i*60,0,i*60,400);
        }
        for(int i=1;i<=20;i++)
        {
            g.drawLine(0,i*20,1200,i*20);
        }
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                if(ar[i][j]!=(null)&&ar[i][j].fill)
                {
                    Brick b=new Brick(i*60,j*20,59,20,chooseColor(ar[i][j].cId),ar[i][j].tId);
                    b.setFill(true);
                    b.paint(g);
                    if(ar[i][j].tId==4)
                    showStatus("a rock");
                }
            }
        }
        g1.drawImage(i,0,40,this);
    }
    
    public void mousePressed(MouseEvent e)
    {
        int x=e.getX()/60;int y=(e.getY()>40)?(e.getY()-40)/20:32;
        int cN=0,tN=1;
        for(int i=0;i<9;i++)if(c[i].getState())cN=i;
        for(int i=0;i<4;i++)if(t[i].getState()){tN=i+1;}
        if(x<20&&y<20&&y>=0)ar[x][y]=new Mode(cN,tN,fill);
        //showStatus(x+" "+y+" "+cN+" "+tN+" "+fill);
        repaint();
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void actionPerformed(ActionEvent e)
    {
        String c=e.getActionCommand();
        if(c.equals("Save"))
        {
            int n=0;
            try
			{
				n=Mode.save(ar,this);
			}catch(Exception e2)
				{
					showStatus(e2.toString());
				}
            c="Reset";
            showStatus("Saved as level"+n+".dat  , Create Next Level");
        }
        if(c.equals("Reset"))
        {
            ar=new Mode[20][20];
            for(int i=0;i<20;i++)
            {
                for(int j=0;j<20;j++)
                {
                    ar[i][j]=new Mode(0,1,false);
                }
            }
        }
        if(c.equals("Erase"))
        {
            erase.setLabel("Paint");
            fill=false;
            showStatus("erase a brick");
        }
        if(c.equals("Paint"))
        {
            erase.setLabel("Erase");
            fill=true;
            showStatus("keep painting");
        }
        if(c.equals("Back To Main Menu"))
        {
            dispose();
            MainClass cx=new MainClass();
        }
            
        repaint();
    }
    public Color chooseColor(int n)
    {
        Color cl=Color.gray;
        switch(n)
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
        return cl;
    }
	public void showStatus(String s)
	{status.setText(s);}
	public static void main(String args[])
	{
		Editor l=new Editor();
	}
}



class Mode
{
    int cId,tId;
    boolean fill;
    Mode(int x,int y,boolean z)
    {
        cId=x;
        tId=y;
        fill=z;
    }
    static int save(Mode a[][],Container ap) throws Exception
    {   
        String s="",s1="";
		int nextLevel=1;
		BufferedReader r2=new BufferedReader(new FileReader("SmashingLevels/level0.dat"));
		s=r2.readLine();
		s=r2.readLine();
		s1=r2.readLine();
		for(;;nextLevel++)
        {
            try{
                BufferedReader r=new BufferedReader(new FileReader("SmashingLevels/level"+nextLevel+".dat"));
                String s2=r.readLine();
                r.close();
            }catch(FileNotFoundException f){break;}
        }
        BufferedWriter w;
        File f;
        f=new File("SmashingLevels/level"+nextLevel+".dat");
        w=new BufferedWriter(new FileWriter(f));
        int bricksN=0;
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                if(a[i][j].fill)bricksN++;
            }
        }
        w.write(bricksN+"\n");
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                if(a[i][j].fill)
                {
					w.write((i+1)+"\t"+(j+1)+"\t"+a[i][j].cId+"\t"+a[i][j].tId+"\n");
                }
            }
        }
        w.close();
        BufferedWriter w2=new BufferedWriter(new FileWriter("SmashingLevels/level0.dat"));
        w2.write(nextLevel+"\n"+s+"\n"+s1);
        w2.close();
        return nextLevel;
    }
}