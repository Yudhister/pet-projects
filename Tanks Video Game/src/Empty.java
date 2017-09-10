
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
public class Empty extends Terrain {

    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    int getTerrainType() {
        return TerrainType.EMPTY;
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
