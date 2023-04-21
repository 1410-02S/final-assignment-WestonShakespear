package genetic.runtime;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Color;

public class Screen extends JPanel
{
    private int x;
    private int y;
    private int resolution;

    private int[][][] screen;

    private int[][][] screenFoods;
    private int[][][] screenCreatures;

    int[] bg = {255, 0, 0};

    Color creatureColor = new Color(238, 232, 213);
    Color foodColor = new Color(7, 54, 66);   


    public Screen(int x, int y, int resolution) {
        this.x = x;
        this.y = y;
        this.resolution = resolution;

        this.screen = new int[this.y][this.x][3];

        this.screenCreatures = new int[this.y][this.x][];
        this.screenFoods = new int[this.y][this.x][];

        this.initScreen();
    }

    public void refresh() {
        this.repaint();
    }

    public void populateScreenObjects(int[][][] creatureColor, int[][][] foodColor) {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                this.screenCreatures[y][x] = creatureColor[y][x];
                this.screenFoods[y][x] = foodColor[y][x];
            }
        }
    }

    public void populateScreenTiles(int[][][] colors) {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                this.screen[y][x] = colors[y][x];
            }
        }
    }

    public void populateScreenTiles(int[] color) {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                this.screen[y][x] = color;
            }
        }
    }

    private void initScreen() {
        this.populateScreenTiles(bg);

        this.setFocusable(true);
        this.setSize(x*resolution, y*resolution);
    }


    private void drawTiles(Graphics2D g2d)
    {
        Color temp;
        for (int y = 0; y < this.y; y += 1) {
            for (int x = 0; x < this.x; x += 1) {

                int[] color = screen[y][x];
                temp = new Color(color[0], color[1], color[2]);

                g2d.setColor(temp);
                g2d.fillRect(x * this.resolution, y * this.resolution, this.resolution, this.resolution);

                this.drawTileObjects(g2d, x, y);
            }
        }
    }

    private void drawTileObjects(Graphics2D g2d, int x, int y) {

        this.drawTileGrid(g2d, x, y, this.screenCreatures[y][x], 0);

        // int foods = this.screenFoods[y][x];
        // this.drawTileGrid(g2d, this.foodColor, x, y, foods, this.resolution / 2);
        // System.out.printf("x:%d y:%d   %d", x, y, creatures);

        
    }


    private void drawTileGrid(Graphics2D g2d, int x, int y, int[] colors, int offset)
    {
        Color temp;

        int baseX = x * this.resolution;
        int baseY = y * this.resolution + offset;

        int mod = 10;
        int pad = 1;
        int size = this.resolution / mod;

        int cX = baseX;
        int cY = baseY;

        if (colors != null)
        {
            for (int i = 0; i < colors.length; i++)
        {
            temp = new Color(colors[i], colors[i], colors[i]);
            g2d.setColor(temp);
            
            g2d.fillRect(cX, cY, size, size);
            cX += size + pad;

            if (cX + size >= baseX + this.resolution)
            {
                cY += size + pad;
                cX = baseX;
            }
        }
        }
        
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.drawTiles(g2d);  
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
