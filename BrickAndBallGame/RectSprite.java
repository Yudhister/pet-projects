import java.awt.*;
class RectSprite extends Sprite2D {

  protected int width, height;    // dimensions of rectangle
  
   public RectSprite(int w,int h,Color c) {
    locx = 0;
    locy = 0;
    width = w;
    height = h;
    color = c;
    restore();
  }

  public RectSprite(int x,int y,int w,int h,Color c) {
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
  public void paint3D(Graphics g,boolean risen) {
    if (visible) {
      g.setColor(color);

      if (fill) {
       g.fill3DRect(locx,locy,width,height,risen);
      }

      else {
       g.draw3DRect(locx,locy,width,height,risen);
      }

    }

  }
  public void paint(Graphics g) {
    if (visible) {
      g.setColor(color);

      if (fill) {
       g.fillRect(locx,locy,width,height);
      }

      else {
       g.drawRect(locx,locy,width,height);
      }

    }

  }
  
  public void paintRound(Graphics g,int arcW,int arcH) {
    if (visible) {
      g.setColor(color);

      if (fill) {
       g.fillRoundRect(locx,locy,width,height,arcW,arcH);
      }

      else {
       g.drawRoundRect(locx,locy,width,height,arcW,arcH);
      }

    }

  }
}
