package genetic.logic;

import java.util.concurrent.ThreadLocalRandom;

public class World {

    private int width;
    private int height;

    private int birth;
    private int death;
    private int food;
    private int waste;

    private Tile[][] tiles;

    public boolean generated;

    public World(int width, int height) {
        System.out.println("this is the world!");
        this.width = width;
        this.height = height;

        this.tiles = new Tile[this.width][this.height];


    }

    public Tile[][] getTiles()
    {
        return this.tiles;
    }

    public boolean generate()
    {
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                this.tiles[y][x] = new Tile(x, y);
                this.tiles[y][x].element.generate();
            }
        }
        return this.generated = true;
    }

    public void createCreature(int x, int y)
    {
        
    }

    public void destoryCreature(int x, int y)
    {

    }

    public void createFood(int x, int y)
    {

    }

    public void destroyFood(int x, int y)
    {

    }

    public static int generateRandom(int lower, int upper)
    {
        int bound = (upper - lower) + 1;
        
        return ThreadLocalRandom.current().nextInt(bound) + lower;
    }

    public void step()
    {
        int y = 0;
        int x = 0;

        for (Tile[] tileY : this.tiles)
        {
            for (Tile tileX : tileY)
            {
                Object[] created = tileX.element.creation();

                if (created != null)
                {

                }

                Object[] destroyed = tileX.element.destruction();
                if (destroyed != null)
                {

                }

                x++;
            }
            y++;
        }
    }




    public int[][][] getTileColors()
    {
        int[][][] colors = new int[this.width][this.height][3];

        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                colors[y][x] = this.tiles[y][x].element.color;
            }
        }

        return colors;
    }

    public void setVariables(int birth, int death, int food, int waste)
    {
        this.birth = birth;
        this.death = death;
        this.food = food;
        this.waste = waste;
    }
    
}
