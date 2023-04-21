package genetic.logic;

import java.util.*;

public class Tile {
    
    private int x;
    private int y;

    public Element element;
    private List<Object> objects;

    private int tileType;

    

    

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;

        this.createType();

        //System.out.println("tile x:" + x + " y: " + y + " ran: " + this.element.name);
        this.objects = new ArrayList<>();
    }

    private void createType()
    {
        this.tileType = World.generateRandom(0, 3);

        this.element = switch (this.tileType)
        {
            default -> new Earth();
            case 1  -> new Water();
            case 2  -> new Air();
            case 3  -> new Fire();
        };

        this.element.generate();
    }

    public List<Object> initializeInhabitants()
    {
        List<Object> created = new ArrayList<>();

        int life = World.generateRandom(0, 99);
        int food = World.generateRandom(0, 99);

        if (life < element.chanceLife) created.add(new Creature());

        if (food < element.chanceFood) created.add(new Food());

        return created;
    }

    public void addObjects(List<Object> objects)
    {
        for (Object object : objects)
        {
            this.objects.add(object);
        }
    }

    public void addObject(Object object)
    {
        this.objects.add(object);
        //System.out.printf("    A %s named %s has spawned%n", object.type, object.name);
    }


}
