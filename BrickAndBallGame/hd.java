import java.awt.*;
import java.awt.event.*;
class hd extends Frame implements MouseListener{
Image i;Graphics g1;
//Bricks b[];
Bonus b;
hd(){
super("trying");
setVisible(true);setSize(900,800);i=createImage(850,700);g1=i.getGraphics();
addMouseListener(this);
b=Bonus.ar[1];
}
public void paint(Graphics g)
{
setBackground(Color.black);
//for(int i=0;i<10;i++)
//b[i].paint(g1);
//b
/*b[1].paint(g1);
b[2].paint(g1);
b[3].paint(g1);
b[4].paint(g1);
*/

g.drawImage(i,10,10,this);
}
public void mouseClicked(MouseEvent e){dispose();}
public void mousePressed(MouseEvent e){}
public void mouseReleased(MouseEvent e){}
public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
static void main(){hd h=new hd();}
}