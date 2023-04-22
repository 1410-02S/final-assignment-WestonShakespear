package genetic.logic;

import java.util.*;

public class Tile {
    
    public int x;
    public int y;
    private int max;

    public Element element;
    private List<Object> objects;

    private int tileType;

    

    

    public Tile(int x, int y, int max)
    {
        this.x = x;
        this.y = y;
        this.max = max;

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

        if (life < element.life) created.add(new Creature());

        //if (food < element.food) created.add(new Food());

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
    }

    public void removeObject(Object object)
    {
        this.objects.remove(object);
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

    public List<Object> reproduction()
    {
        int creatureCount = this.getObjectCount(true);

        List<Object> born = new ArrayList<>();

        if (creatureCount > this.max)
        {
            this.removeRandomObject(this.max);
            return born;
        }

        for (int i = 0; i < creatureCount; i += 2)
        {
            int chance = World.generateRandom();
            if (chance < this.element.chanceLife)
            {
                Creature creature = new Creature();
                creature.generate();
                creature.setColor(this.objects.get(i).color);
                born.add(new Creature());
            }    
        }

        return born;
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

    public int[] getObjectColors(boolean creatures)
    {
        List<Object> objs = new ArrayList<>();

        for (Object object : this.objects)
        {
            if (object.type == "Bacteria" && creatures)
            {
                objs.add(object);
            }
        }

        int[] ret = new int[objs.size()];

        for (int i = 0; i < objs.size(); i++)
        {
            ret[i] = objs.get(i).getColorValue();
        }

        return ret;
    }

    public int getObjectCount(boolean creatures)
    {
        int ret = 0;

        for (Object object : this.objects)
        {
            if (object.type == "Bacteria" && creatures)
            {
                ret += 1;
            }
        }

        return ret;
    }


    public List<Object> getObjects()
    {
        return this.objects;
    }


}
