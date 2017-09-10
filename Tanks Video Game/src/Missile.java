
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yudhi
 */
class Missile implements Targetable, Movable {

    static int width = 3, height = 11;//facing up
    int x, y, speed, direction;
    boolean active;
    Color color;
    Tank source;

    Missile(Color c, Tank src) {
        color = c;
        active = true;
        source = src;
    }

    @Override
    public int getX() {
        int x1 = 0;
        if (direction == Direction.UP || direction == Direction.DOWN) {
            x1 = x - 1;
        } else {
            x1 = x - 5;
        }
        return x1;
    }

    @Override
    public int getY() {
        int x1 = 0;
        if (direction == Direction.UP || direction == Direction.DOWN) {
            x1 = y - 5;
        } else {
            x1 = y - 1;
        }
        return x1;
    }

    @Override
    public int getWidth() {
        if (direction % 2 == 0) {
            return width;
        } else {
            return height;
        }
    }

    @Override
    public int getHeight() {
        if (direction % 2 != 0) {
            return width;
        } else {
            return height;
        }
    }

    Tank getSource() {
        return source;
    }

    @Override
    public boolean intersect(Targetable target) {
        if (!active) {
            return false;
        }
        if (target.getX() <= getX() && target.getX() + target.getWidth() >= getX() && target.getY() <= getY() && target.getY() + target.getHeight() >= getY()) {
            return true;
        }
        if (target.getX() <= getX() && target.getX() + target.getWidth() >= getX() && target.getY() <= getY() + getHeight() && target.getY() + target.getHeight() >= getY() + getHeight()) {
            return true;
        }
        if (target.getX() <= getX() + getWidth() && target.getX() + target.getWidth() >= getX() + getWidth() && target.getY() <= getY() && target.getY() + target.getHeight() >= getY()) {
            return true;
        }
        if (target.getX() <= getX() + getWidth() && target.getX() + target.getWidth() >= getX() + getWidth() && target.getY() <= getY() + getHeight() && target.getY() + target.getHeight() >= getY() + getHeight()) {
            return true;
        }
        return false;
    }

    @Override
    public void hit() {
        active = false;
    }

    @Override
    public int getTargetableType() {
        return TargetableType.MISSILE;
    }

    @Override
    public void setStart(int X, int Y) {
        if (X > 0 && X < 1050) {
            x = X;
        } else {
            hit();
        }
        if (Y > 0 && Y < 700) {
            y = Y;
        } else {
            hit();
        }
    }

    @Override
    public void setSpeed(int Speed, int Direction) {
        speed = Speed;
        direction = Direction;
    }

    @Override
    public void updatePosition(Terrain[][] Map) {
        if (active) {
            Missile t = new Missile(color, source);
            int x1 = x;
            int y1 = y;
            switch (direction) {
                case Direction.DOWN:
                    x1 = x;
                    y1 = y + speed;
                    break;
                case Direction.UP:
                    x1 = x;
                    y1 = y - speed;
                    break;
                case Direction.LEFT:
                    x1 = x - speed;
                    y1 = y;
                    break;
                case Direction.RIGHT:
                    x1 = x + speed;
                    y1 = y;
                    break;

            }
            t.setStart(x1, y1);
            t.setSpeed(speed, direction);
            boolean chk = true;
            outer:
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 30; j++) {
                    if ((Map[i][j] instanceof Stone|| Map[i][j] instanceof Brick||Map[i][j] instanceof Eagle) && t.intersect(Map[i][j])) {
                        chk = false;
                        hit();
                        Map[i][j].hit();
                        if (Map[i][j] instanceof Brick) {
                            Map[i][j] = new Empty(j * 35, i * 35);
                        }
                        break outer;
                    }
                }
            }
            if (chk) {
                this.setStart(x1, y1);
            }
        }
    }

    @Override
    public int getMovableType() {
        return MovableType.MISSILE;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
