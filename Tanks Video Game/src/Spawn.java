
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
public class Spawn extends Terrain {

    public Spawn(int x, int y) {
        super(x, y);
    }

    @Override
    int getTerrainType() {
        return TerrainType.SPAWN;
    }

    @Override
    public void hit() {
    }

    @Override
    public int getTargetableType() {
        return TargetableType.EMPTY;
    }

    @Override
    void paint(Graphics g) {
    }
}
