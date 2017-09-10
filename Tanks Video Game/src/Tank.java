
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yudhi
 */
public class Tank implements Targetable, Movable {

    static int width = 29, height = 29;// facing left
    int x, y, speed, direction, missileSpeed;
    boolean active;
    Color  missileColor;

    Tank( Color MissileColor, int MissileSpeed) {
        active = true;
        missileColor = MissileColor;
        missileSpeed = MissileSpeed;
    }

    @Override
    public int getX() {
        int x1 = 0;
        if (direction == Direction.UP || direction == Direction.DOWN) {
            x1 = x - 14;
        } else {
            x1 = x - 14;
        }
        return x1;
    }

    @Override
    public int getY() {
        int x1 = 0;
        if (direction == Direction.UP || direction == Direction.DOWN) {
            x1 = y - 14;
        } else {
            x1 = y - 14;
        }
        return x1;
    }

    @Override
    public int getWidth() {
        if (direction == Direction.UP || direction == Direction.DOWN) {
            return height;
        } else {
            return width;
        }
    }

    @Override
    public int getHeight() {
        if (direction == Direction.UP || direction == Direction.DOWN) {
            return width;
        } else {
            return height;
        }
    }

    @Override
    public boolean intersect(Targetable target) {
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

    public void reborn(){
        active=true;
    }
    @Override
    public int getTargetableType() {
        return TargetableType.TANK;
    }

    @Override
    public void setStart(int X, int Y) {
        if (X > 0 && X < 1050) {
            x = X;
        } else if (X > 0) {
            x = 1050;
        } else {
            x = 0;
        }
        if (Y > 0 && Y < 700) {
            y = Y;
        } else if (Y > 0) {
            y = 700;
        } else {
            y = 0;
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
            Tank t = new Tank(missileColor, missileSpeed);
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
                    if (Map[i][j].getTerrainType() > 0 && t.intersect(Map[i][j])) {
                        chk = false;
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
        return MovableType.TANK;
    }

    Missile shoot() {
        Missile m = new Missile(missileColor, this);
        m.setStart(x, y);
        m.setSpeed(missileSpeed, direction);
        return m;
    }

    public void paint(Graphics g) throws IOException {
        /*g.setColor(color);
        g.fillRect(getX(), getY(), width, height);
        g.setColor(Color.BLACK);*/
        if(missileColor.equals(GameManager.enemyMissileColor))
        g.drawImage(GameManager.TankImages[direction+3], getX(), getY(), null);
        else g.drawImage(GameManager.TankImages[direction-1], getX(), getY(), null);
        
    }
}
