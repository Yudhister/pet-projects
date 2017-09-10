
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
public class Water extends Terrain {

    public Water(int x, int y) {
        super(x, y);
    }

    @Override
    int getTerrainType() {
        return TerrainType.WATER;
    }

    @Override
    public void hit() {
    }

    @Override
    public int getTargetableType() {
        return TargetableType.WATER;
    }

    @Override
    void paint(Graphics g) {
        g.drawImage(GameManager.Water, x, y, null);
    }
}
