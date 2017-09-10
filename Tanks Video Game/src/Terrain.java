
import java.awt.Graphics;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yudhi
 */
public abstract class Terrain implements Targetable {

    int x, y, width, height;

    public Terrain(int X, int Y) {
        x = X;
        y = Y;
        width = 35;
        height = 35;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean intersect(Targetable target) {

        if (target.getX() <= x && target.getX() + target.getWidth() >= x && target.getY() <= y && target.getY() + target.getHeight() >= y) {
            return true;
        }
        if (target.getX() <= x && target.getX() + target.getWidth() >= x && target.getY() <= y + height && target.getY() + target.getHeight() >= y + height) {
            return true;
        }
        if (target.getX() <= x + width && target.getX() + target.getWidth() >= x + width && target.getY() <= y && target.getY() + target.getHeight() >= y) {
            return true;
        }
        if (target.getX() <= x + width && target.getX() + target.getWidth() >= x + width && target.getY() <= y + height && target.getY() + target.getHeight() >= y + height) {
            return true;
        }
        return false;
    }

    abstract int getTerrainType();

    abstract void paint(Graphics g);
}
