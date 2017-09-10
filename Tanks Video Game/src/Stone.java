
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
public class Stone extends Terrain {

    public Stone(int x, int y) {
        super(x, y);
    }

    @Override
    int getTerrainType() {
        return TerrainType.STONE;
    }

    @Override
    public void hit() {
    }

    @Override
    public int getTargetableType() {
        return TargetableType.STONE;
    }

    @Override
    void paint(Graphics g) {
        g.drawImage(GameManager.Stone, x, y, null);
    }
}
