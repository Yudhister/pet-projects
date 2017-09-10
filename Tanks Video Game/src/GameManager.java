
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yudhi
 */
class GameManager {

    //to be read from data file
    static int playerMissileSpeed = 5;
    static int enemyMissileSpeed = 5;
    static Color playerMissileColor = Color.YELLOW;
    static Color enemyMissileColor = Color.PINK;
    static String levelFolder = "Levels/";
    static int playerSpeed = 2;
    static int enemySpeed = 2;
    static int directionDelay = 35;//no of cycles
    static int missisleDelay = 50;// no. of cycles
    static int spawnDelay = 100;// no. of cycles
    static int width = 1050;
    static int height = 700;
    static int score = 0;
    // states
    final int PLAYING = 1;
    final int LIVES_OVER = 5;
    final int EAGLE_DESTROYED = 3;
    final int LEVELS_OVER = 4;
    final int LEVEL_CLEAR = 2;
    int state;
    //Images and sounds
    static Image TankImages[] = new Image[8];
    static Image Brick, Stone, Base, Water;
    static AudioClip sounds[] = new AudioClip[10];
    //level specific objects
    Terrain map[][];
    ArrayList<Tank> me = new ArrayList<Tank>();
    ArrayList<String> meStart = new ArrayList<String>();
    ArrayList<Tank> you = new ArrayList<Tank>();
    ArrayList<Missile> missiles = new ArrayList<Missile>();
    ArrayList<Spawn> spawnPoints = new ArrayList<Spawn>();
    Eagle eagle;
    // level specific data
    int totalLevels = 1, count = 0;
    int players, lives, enemyCount, enemyLeft, enemySpawned, current;

    GameManager() throws IOException {
        TankImages[0] = ImageIO.read(new File("Images/playerTank_left.jpg"));
        TankImages[1] = ImageIO.read(new File("Images/playerTank_up.jpg"));
        TankImages[2] = ImageIO.read(new File("Images/playerTank_right.jpg"));
        TankImages[3] = ImageIO.read(new File("Images/playerTank_down.jpg"));
        TankImages[4] = ImageIO.read(new File("Images/enemyTank_left.jpg"));
        TankImages[5] = ImageIO.read(new File("Images/enemyTank_up.jpg"));
        TankImages[6] = ImageIO.read(new File("Images/enemyTank_right.jpg"));
        TankImages[7] = ImageIO.read(new File("Images/enemyTank_down.jpg"));
        Brick = ImageIO.read(new File("Images/brick.jpg"));
        Stone = ImageIO.read(new File("Images/stone.png"));
        Base = ImageIO.read(new File("Images/base.jpg"));
        Water = ImageIO.read(new File("Images/base.jpg"));
        try {
            sounds[0] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));// base destroyed
            sounds[1] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));//player shoots
            sounds[2] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));//enemy killed
            sounds[3] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));//player killed
            sounds[4] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));//enemy shoots
            sounds[5] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));//brick destroyed
            sounds[6] = Applet.newAudioClip(new URL("file:Sounds/yahoo2.au"));//level cleared
            sounds[7] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));//game over by any means
            sounds[8] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));//enemy spawns
            sounds[9] = Applet.newAudioClip(new URL("file:Sounds/tada.wav"));//enemy and player collide


        } catch (MalformedURLException m) {
        }
        startGame();
    }

    void loadLevel(int l) throws IOException {
        if (state == LEVEL_CLEAR) {


            if (l < 1 || l > totalLevels) {
                throw new IOException("level out of range");
            }
            BufferedReader b = new BufferedReader(new FileReader(levelFolder + l + ".txt"));
            String s = b.readLine();
            StringTokenizer st;
            lives = Integer.parseInt(s);
            s = b.readLine();
            players = Integer.parseInt(s);
            you = new ArrayList<Tank>();
            me = new ArrayList<Tank>();
            missiles = new ArrayList<Missile>();
            spawnPoints = new ArrayList<Spawn>();
            for (int i = 0; i < players; i++) {
                Tank t = new Tank(playerMissileColor, playerMissileSpeed);
                s = b.readLine();
                st = new StringTokenizer(s);
                t.setStart(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                meStart.add(s);
                t.setSpeed(playerSpeed, Direction.UP);
                //System.out.println(s);
                me.add(t);
            }
            enemyLeft = Integer.parseInt(b.readLine());
            map = new Terrain[20][30];
            for (int i = 0; i < 20; i++) {
                s = b.readLine();
                st = new StringTokenizer(s);
                for (int j = 0; j < 30; j++) {
                    int k = Integer.parseInt(st.nextToken());
                    //System.out.print(k);
                    switch (k) {
                        case TerrainType.STONE:
                            map[i][j] = new Stone(j * 35, i * 35);
                            //System.out.print("S ");
                            break;
                        case TerrainType.BRICK:
                            map[i][j] = new Brick(j * 35, i * 35);
                            //System.out.print("B ");
                            break;
                        case TerrainType.EAGLE:
                            map[i][j] = new Eagle(j * 35, i * 35);
                            //System.out.print("Eg ");
                            eagle = (Eagle) map[i][j];
                            break;
                        case TerrainType.SPAWN:
                            map[i][j] = new Spawn(j * 35, i * 35);
                            //System.out.print("Sp ");
                            spawnPoints.add((Spawn) map[i][j]);
                            break;
                        case TerrainType.WATER:
                            map[i][j] = new Water(j * 35, i * 35);
                            //System.out.print("B ");
                            break;
                        case TerrainType.GRASS:
                            map[i][j] = new Grass(j * 35, i * 35);
                            //System.out.print("B ");
                            break;
                        default:
                            map[i][j] = new Empty(j * 35, i * 35);
                        //System.out.print("E ");
                    }
                }
                //System.out.println();
            }
            enemyCount = enemyLeft;
            enemySpawned = 0;

        }
        state = PLAYING;
    }

    static void stopSounds() {
        for (int i = 0; i < sounds.length; i++) {
            sounds[i].stop();
        }
    }

    int getState() {
        return state;
    }

    void update() {
        count++;
        //move all movaable
        if (state == PLAYING) {
            for (int i = 0; i < you.size(); i++) {
                you.get(i).updatePosition(map);
            }
            for (int i = 0; i < missiles.size(); i++) {
                missiles.get(i).updatePosition(map);
            }
            //check missiles on all targetable and enemy count- and player lives -
            for (int i = 0; i < missiles.size(); i++) {
                Tank t = missiles.get(i).getSource();
                if (me.contains(t)) {
                    // we cant chk for t contained in you as the enemy tank may have died b4 its missile got destroyed
                    //also if no. of player>1, same problem
                    for (int j = 0; j < you.size(); j++) {
                        if (missiles.get(i).intersect(you.get(j))) {
                            missiles.get(i).hit();
                            you.get(j).hit();
                            System.out.println(enemyLeft);
                            stopSounds();
                            sounds[2].play();
                        }
                    }
                    for (int j = 0; j < missiles.size(); j++) {
                        if (missiles.get(i).intersect(missiles.get(j)) && !me.contains(missiles.get(j).getSource())) {
                            //System.out.println("missile overlap");
                            missiles.get(i).hit();
                            missiles.get(j).hit();
                        }
                    }
                } else {
                    for (int j = 0; j < me.size(); j++) {
                        if (missiles.get(i).intersect(me.get(j))) {
                            missiles.get(i).hit();
                            me.get(j).hit();
                            stopSounds();
                            sounds[3].play();
                            lives--;
                            if (lives > 0) {
                                me.get(j).reborn();
                                StringTokenizer st = new StringTokenizer(meStart.get(j));
                                me.get(j).setStart(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                                me.get(j).setSpeed(playerSpeed, Direction.UP);

                            }
                        }
                    }

                }
            }
            //check tank collision
            for (int i = 0; i < you.size(); i++) {
                if (you.get(i).intersect(me.get(0))) {
                    me.get(0).hit();
                    you.get(i).hit();
                    sounds[9].play();
                    lives--;
                    if (lives > 0) {
                        me.get(0).reborn();
                        StringTokenizer st = new StringTokenizer(meStart.get(0));
                        me.get(0).setStart(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        me.get(0).setSpeed(playerSpeed, Direction.UP);

                    }

                }
            }
            //update array lists
            for (int i = 0; i < you.size(); i++) {
                if (!you.get(i).active) {
                    you.remove(i);
                }
            }
            enemyLeft = enemyCount - enemySpawned + you.size();

            for (int i = 0; i < me.size(); i++) {
                if (!me.get(i).active) {
                    me.remove(i);

                }
            }
            for (int i = 0; i < missiles.size(); i++) {
                if (!missiles.get(i).active) {
                    missiles.remove(i);
                }
            }

            //spawn enemy and enemy direction and enemy missile shoot
            double r = Math.random();
            if (r == 1.0) {
                r = 0.0;
            }
            if (count % spawnDelay == 0 && enemySpawned < enemyCount) {
                Tank t = new Tank(enemyMissileColor, enemyMissileSpeed);
                int x = spawnPoints.get((int) (r * spawnPoints.size())).getX() + 14 + 2;
                int y = spawnPoints.get((int) (r * spawnPoints.size())).getY() + 14 + 2;
                t.setSpeed(enemySpeed, Direction.DOWN);
                t.setStart(x, y);
                you.add(t);
                enemySpawned++;
                stopSounds();
                sounds[8].play();

            }
            if (count % directionDelay == 0) {
                for (int i = 0; i < you.size(); i++) {
                    you.get(i).setSpeed(enemySpeed, (int) (Math.random() * 4 + 1));
                }
                // 1 random tank moves closer to player.
                int k = (int) (Math.random() * you.size());
                int x = me.get(0).getX();
                int y = me.get(0).getY();
                if (you.size() > 0) {
                    int x1 = you.get(k).getX();
                    int y1 = you.get(k).getY();

                    int dir = Direction.DOWN;
                    if (Math.abs(x - x1) > Math.abs(y - y1)) {
                        if (x > x1) {
                            dir = Direction.RIGHT;
                        } else {
                            dir = Direction.LEFT;
                        }
                    } else if (Math.abs(x - x1) < Math.abs(y - y1)) {
                        if (y > y1) {
                            dir = Direction.DOWN;
                        } else {
                            dir = Direction.UP;
                        }
                    } else {
                        dir = (int) (Math.random() * 4 + 1);
                    }
                    you.get(k).setSpeed(enemySpeed, dir);
                }
            }
            if (count % missisleDelay == 0) {
                if (you.size() > 0) {
                    missiles.add(you.get((int) (r * you.size())).shoot());
                    stopSounds();
                    sounds[4].play();
                }
            }

            //check for enemy count and eagle.active and current level and player lives
            if (enemyLeft == 0) {
                state = LEVEL_CLEAR;
                stopSounds();
                sounds[6].play();
            }
            if (!eagle.active) {
                state = EAGLE_DESTROYED;
                stopSounds();
                sounds[7].play();
            }
            if (state == LEVEL_CLEAR && current == totalLevels) {
                state = LEVELS_OVER;
                stopSounds();
                sounds[7].play();
            }
            if (lives == 0) {
                state = LIVES_OVER;
                stopSounds();
                sounds[7].play();
            }
        }
    }
    // these 2 methods will have to be adapted for multiple players, as per key press

    void shoot() throws IOException {
        if (state == LEVEL_CLEAR) {
            current++;
            loadLevel(current);
        } else {
            for (int i = 0; i < me.size(); i++) {
                missiles.add(me.get(i).shoot());
                stopSounds();
                sounds[1].play();
            }
        }


    }

    void move(int d) {
        //stopSounds();
        for (int i = 0; i < me.size(); i++) {
            me.get(i).setSpeed(playerSpeed, d);
            me.get(i).updatePosition(map);
        }
    }

    public void paint(Graphics g) {// as per game state

        g.setColor(Color.WHITE);
        if (state == PLAYING) {// tanks, then map, then missiles
            try {
                for (int i = 0; i < you.size(); i++) {
                    you.get(i).paint(g);
                }
                for (int i = 0; i < me.size(); i++) {
                    me.get(i).paint(g);
                }
            } catch (IOException e) {
                System.err.print(e);
            }
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 30; j++) {
                    map[i][j].paint(g);
                }
            }

            for (int i = 0; i < missiles.size(); i++) {
                missiles.get(i).paint(g);
            }
        } else if (state == LEVELS_OVER) {
            g.drawString("You Cleared All Levels", 500, 200);
        } else if (state == LEVEL_CLEAR) {
            g.drawString("Level Clear\nPress Space to continue", 500, 200);
        } else if (state == EAGLE_DESTROYED) {
            g.drawString("Game Over, You Lost\n Base Destroyed", 500, 200);
        } else if (state == LIVES_OVER) {
            g.drawString("Game Over, You Lost\n No lives Left", 500, 200);
        }
    }

    private void startGame() throws IOException {
        state = LEVEL_CLEAR;
        current = 1;
        loadLevel(current);
    }
}
