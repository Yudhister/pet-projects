/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yudhi
 */
public class Dimension {

    static final int WIDTH = 1050;
    static final int HEIGHT = 750;
}

class Direction {

    static final int LEFT = 1;
    static final int UP = 2;
    static final int RIGHT = 3;
    static final int DOWN = 4;
}

class TerrainType {

    static final int STONE = 1;
    static final int BRICK = 2;
    static final int EMPTY = 0;
    static final int SPAWN = -1;
    static final int EAGLE = 3;
    static final int WATER = 4;
    static final int GRASS = -2;
}

class MovableType {

    static final int TANK = 1;
    static final int MISSILE = 2;
}

class TargetableType {

    static final int TANK = 1;
    static final int MISSILE = 2;
    static final int BRICK = 3;
    static final int STONE = 4;
    static final int EMPTY = 5;
    static final int EAGLE = 6;
    static final int WATER = 7;
    static final int GRASS = 8;
}

interface Movable {

    abstract void setStart(int x, int y);

    /** range 0 to 1*/
    abstract void setSpeed(int speed, int direction);

    /** range 0 to 1, and dir from 1-4*/
    abstract void updatePosition(Terrain[][] Map);

    /** 20x20, each terrain is 40x30*/
    abstract int getMovableType();
}

interface Targetable {

    abstract int getX();

    abstract int getY();

    abstract int getWidth();

    abstract int getHeight();

    abstract boolean intersect(Targetable target);

    abstract void hit();

    abstract int getTargetableType();
}
