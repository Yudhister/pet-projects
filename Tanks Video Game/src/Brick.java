
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
public class Brick extends Terrain {

    boolean active;

    public Brick(int x, int y) {
        super(x, y);
        active = true;
    }

    @Override
    int getTerrainType() {
        return TerrainType.BRICK;
    }

    @Override
    public void hit() {
        active = false;
        GameManager.stopSounds();
        GameManager.sounds[5].play();
    }

    @Override
    public int getTargetableType() {
        return TargetableType.BRICK;
    }

    @Override
    void paint(Graphics g) {
        if (active) {
            g.drawImage(GameManager.Brick, x, y, null);
        }
    }
}
