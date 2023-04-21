package genetic.logic;

import java.util.*;

public class Tile {
    
    public int x;
    public int y;

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


    // public void removeObjects(List<Object> objects)
    // {
    //     for (Object object : objects)
    //     {
    //         this.objects.add(object);
    //     }
    // }

    public void removeObject(Object object)
    {
        this.objects.remove(object);
        //System.out.printf("    A %s named %s has spawned%n", object.type, object.name);
    }

    public void removeRandomObject()
    {
        int index = World.generateRandom(0, this.objects.size() - 1);
        Object dead = this.objects.get(index);    

        System.out.printf("    A %s named %s has perished in the fire realm%n", dead.type, dead.name); 
        this.objects.remove(dead);
    }

    public void removeRandomObject(int num)
    {
        for (int i = 0; i < num; i++)
        {
            this.removeRandomObject();
        }
    }

    

    public List<Object> moveObjects()
    {
        List<Object> ret = new ArrayList<>();
        //move
        for(Object object : this.objects)
        {
            if (object.move())
            {
                ret.add(object);
            }
        }

        return ret;
    }


    public List<Object> getObjects()
    {
        return this.objects;
    }


}
