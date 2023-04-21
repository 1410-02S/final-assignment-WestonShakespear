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

    private void created(List<Object> created_list, int x, int y)
    {
        for (Object created : created_list)
        {
            this.created(created, x, y);
        }
    }

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

    private void destroyed(List<Object> destroy_list, int x, int y)
    {
        for (Object destroy : destroy_list)
        {
            this.destroyed(destroy, x, y);
        }
    }

    private void destroyed(Object destroyed, int x, int y)
    {
        System.out.println("    A " + destroyed.type + " named " + destroyed.name + " has died");
    }

    public static int generateRandom(int lower, int upper)
    {
        int bound = (upper - lower) + 1;
        
        return ThreadLocalRandom.current().nextInt(bound) + lower;
    }

    private int getRandomIndex(int length)
    {
        return ThreadLocalRandom.current().nextInt(length);
    }

    public String getRandomName()
    {
        int a = this.getRandomIndex(this.names.size());
        return this.names.get(a);
    }

    public String getRandomFood()
    {
        int a = this.getRandomIndex(this.foods.size());
        return this.foods.get(a);
    }

    public String[] getFileList(String filename)
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

    public void step()
    {
        int y = 0;
        int x = 0;

        for (Tile[] tileY : this.tiles)
        {
            for (Tile tileX : tileY)
            {
                List<Object> created = tileX.element.creation();
                this.created(created, x, y);

                List<Object> destroyed = tileX.element.destruction();
                this.destroyed(destroyed, x, y);

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
