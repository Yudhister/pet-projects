
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
public class Grass extends Terrain {

    public Grass(int x, int y) {
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
        g.setColor(Color.green);
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                g.fillOval(x+i*7+1, y+j*7+1, 4, 4);
            }
        }
        
    }
}
