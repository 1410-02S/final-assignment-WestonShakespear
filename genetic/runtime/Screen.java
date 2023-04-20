package genetic.runtime;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Screen extends JPanel
{
    private int x;
    private int y;
    private int resolution;

    private int[][][] screen;

    int[] bg = {255, 0, 0};


    public Screen(int x, int y, int resolution) {
        this.x = x;
        this.y = y;
        this.resolution = resolution;

        screen = new int[this.y][this.x][];

        initScreen();
        buildScreen();
    }

    public void refresh() {
        this.repaint();
    }

    public void populateScreen(int[][][] colors) {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                screen[y][x] = colors[y][x];
            }
        }
    }

    public void populateScreen(int[] color) {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                screen[y][x] = color;
            }
        }
    }

    private void initScreen() {
        
        this.populateScreen(bg);
    }

    private void buildScreen() {
        setFocusable(true);
        setSize(x*resolution, y*resolution);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        Color temp;

        for (int y = 0; y < this.y * this.resolution; y += this.resolution) {
            for (int x = 0; x < this.x * this.resolution; x += this.resolution) {

                int[] color = screen[x / this.resolution][y / this.resolution];
                temp = new Color(color[0], color[1], color[2]);

                g2d.setColor(temp);
                g2d.fillRect(x, y, this.resolution, this.resolution);
            }
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
