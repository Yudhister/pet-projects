
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
public class Eagle extends Terrain {

    boolean active;

    public Eagle(int x, int y) {
        super(x, y);
        active = true;
    }

    @Override
    int getTerrainType() {
        return TerrainType.EAGLE;
    }

    @Override
    public void hit() {
        active=false;
        GameManager.stopSounds();
        GameManager.sounds[0].play();
        //System.out.println("eagle hit");
    }

    @Override
    public int getTargetableType() {
        return TargetableType.EAGLE;
    }

    @Override
    void paint(Graphics g) {
        if (active) {
            g.drawImage(GameManager.Base, x, y, null);
        }
    }
}
