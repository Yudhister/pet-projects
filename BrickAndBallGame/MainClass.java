import java.awt.*;
import java.awt.event.*;
public class MainClass extends Frame implements ActionListener,WindowListener
{
	Button b1,b2;
	
	public MainClass()
	{
		super("Welcome to Smashing!! by Yudhi");
		setVisible(true);
		setSize(600,200);
		setResizable(false);
		setLayout(new GridLayout(2,1));
		b1=new Button("Smashing Game");
		b2=new Button("Smashing Level Editor");
		b1.addActionListener(this);
		b2.addActionListener(this);
		add(b1);
		add(b2);
		addWindowListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		String c=e.getActionCommand();
		String c1=new String();
		dispose();
		if(c.equals("Smashing Game"))
			createGame();
		else if(c.equals("Smashing Level Editor"))
			createEditor();
	}
	public void createGame(){BrickFrame.help();}
	public void createEditor(){LevelEditor l1=new LevelEditor();}
	public static void main(String args[])
	{
		MainClass c=new MainClass();
	}
	public void windowDeactivated(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	public void windowClosing(WindowEvent e){dispose();}
	public void windowClosed(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	
}