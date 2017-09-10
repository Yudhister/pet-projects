import java.awt.*;
class OvalSprite extends Sprite2D {

  protected int width, height;    // dimensions of Oval

   public OvalSprite(int w,int h,Color c) {
    locx = 0;
    locy = 0;
    width = w;
    height = h;
    color = c;
    restore();
  }

  public OvalSprite(int x,int y,int w,int h,Color c) {
    locx = x;
    locy = y;
    width = w;
    height = h;
    color = c;
    fill = false;                 // default: don't fill
    restore();                    // restore the sprite
  }

  // provide implementation of abstract methods:

  public void update() {

    // does nothing

  }

  // check if sprite's visible before painting
  public void paint(Graphics g) {
    if (visible) {
      g.setColor(color);

      if (fill) {
       g.fillOval(locx,locy,width,height);
      }

      else {
       g.drawOval(locx,locy,width,height);
      }

    }

  }

}
