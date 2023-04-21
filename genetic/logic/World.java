package genetic.logic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

public class World {

    private int width;
    private int height;

    private int birth;
    private int death;
    private int food;
    private int waste;

    private Tile[][] tiles;

    public boolean generated;

    public List<String> names;
    public List<String> foods;

    public World(int width, int height) {
        System.out.println("this is the world!");
        this.width = width;
        this.height = height;

        this.tiles = new Tile[this.width][this.height];

        this.generateLists();
    }

    public void setVariables(int birth, int death, int food, int waste)
    {
        this.birth = birth;
        this.death = death;
        this.food = food;
        this.waste = waste;
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

                List<Object> intial = this.tiles[y][x].initializeInhabitants();
                this.created(intial, x, y);
            }
        }
        return this.generated = true;
    }

    // A year goes by...
    public void step()
    {
        for (Tile[] tileY : this.tiles)
        {
            for (Tile tileX : tileY)
            {
                Object created = tileX.element.creation();
                if (created != null)
                {
                    this.created(created, tileX.x, tileX.y);
                    System.out.println("spontanious");
                }
                

                List<Object> moved = tileX.moveObjects();
                this.moved(moved, tileX.x, tileX.y);

                int destroyed = tileX.element.destruction(tileX.getObjects().size());
                this.destroyed(destroyed, tileX.x, tileX.y);

                
            }
        }
    }

    // Iterate over created list
    private void created(List<Object> created_list, int x, int y)
    {
        for (Object created : created_list)
        {
            this.created(created, x, y);
        }
    }

    // Iterate over moved list
    private void moved(List<Object> moved_list, int x, int y)
    {
        for (Object move : moved_list)
        {
            this.moved(move, x, y);
        }
    }

    // Iterate over destroyed list
    private void destroyed(int num, int x, int y)
    {
        tiles[y][x].removeRandomObject(num);
    }


    // Take initilized object and create it
    private void created(Object created, int x, int y)
    {
        created.generate();
        created.name = this.getRandomName();

        if (created.type != "Bacteria")
        {
            created.type = this.getRandomFood();
        }
        this.tiles[y][x].addObject(created);
    }

    private void moved(Object moved, int x, int y)
    {
        int dir = generateRandom(0, 3);

        int[] dirX = { x-1, x+1, x, x };
        int[] dirY = { y, y, y-1, y+1 };

        int nX = dirX[dir];
        int nY = dirY[dir];

        if (nX < 0) { nX = width - 1; }

        if (nX >= width) { nX = 0; }

        if (nY < 0) { nY = height - 1; }

        if (nY >= height) { nY = 0; }

        this.tiles[y][x].removeObject(moved);
        this.tiles[nY][nX].addObject(moved);

        //System.out.printf("    A %s named %s has moved%d%n%n%n", moved.type, moved.name, dir);
    }

    

    private void destroyed(Object destroyed, int x, int y)
    {
        System.out.printf("    A %s named %s has died%n", destroyed.type, destroyed.name);
    }

    

    

    

    
    


    // Accessed by the screen for drawing tiles
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

    // Accessed by the screen for drawing creatures
    public int[][] getTileCreatureCount()
    {
        int[][] creatures = new int[this.width][this.height];

        for (Tile[] tileY : this.tiles)
        {
            for (Tile tileX : tileY)
            {
                List<Object> tileObjects = tileX.getObjects();
                for (int i = 0; i < tileObjects.size(); i++)
                {
                    if (tileObjects.get(i).type == "Bacteria")
                    {
                        creatures[tileX.y][tileX.x] += 1;
                    }
                }
                
            }
        }

        return creatures;
    }

    // Accessed by the screen for drawing food
    public int[][] getTileFoodCount()
    {
        int[][] creatures = new int[this.width][this.height];

        for (Tile[] tileY : this.tiles)
        {
            for (Tile tileX : tileY)
            {
                List<Object> tileObjects = tileX.getObjects();
                for (int i = 0; i < tileObjects.size(); i++)
                {
                    if (tileObjects.get(i).type != "Bacteria")
                    {
                        creatures[tileX.y][tileX.x] += 1;
                    }
                }
                
            }
        }

        return creatures;
    }

    // Static function for rolling dice inside of world
    public static int generateRandom(int lower, int upper)
    {
        int bound = (upper - lower) + 1;
        
        return ThreadLocalRandom.current().nextInt(bound) + lower;
    }

    public static int generateRandom()
    { 
        return ThreadLocalRandom.current().nextInt(100);
    }

    //-----------------------------------PRIVATE-----------------------------------//

    // Basically the same as above but for getting a random name or fruit
    private int getRandomIndex(int length)
    {
        return ThreadLocalRandom.current().nextInt(length);
    }

    private String getRandomName()
    {
        int a = this.getRandomIndex(this.names.size());
        return this.names.get(a);
    }

    private String getRandomFood()
    {
        int a = this.getRandomIndex(this.foods.size());
        return this.foods.get(a);
    }

    // Build name and food lists for random assignment
    private void generateLists()
    {
        this.names = new ArrayList<>();
        this.foods = new ArrayList<>();

        String[] names = this.getFileList("name.txt");
        if (names != null)
        {
            for (String name : names)
            {
                this.names.add(name);
            }
        }

        String[] foods = this.getFileList("fruit.txt");
        if (foods != null)
        {
            for (String food : foods)
            {
                this.foods.add(food);
            }
        }
    }
    
    // Load ing file from the current directory ie name or fruit
    private String[] getFileList(String filename)
    {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), filename);

        System.out.println(filePath.toString());

        try(BufferedReader in = new BufferedReader(new FileReader(filePath.toString()))) {
            String str = in.readLine();

            String[] firstSplit = str.split(",");

            for (int i = 0; i < firstSplit.length; i++)
            {
                firstSplit[i] = firstSplit[i].replace("\"", "");
            }
            return firstSplit;
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        
        return null;
    }
    
}
