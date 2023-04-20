package genetic.runtime;

import genetic.logic.World;
import genetic.logic.Tile;

public class Instance
{
    public World world;

    public Instance(int width, int height)
    {
        this.world = new World(width, height);
    }

    public void start()
    {
        this.world.generate();
    }

    public void step()
    {
        this.world.step();
    }

    public static String[] checkArgs(String[] args)
    {
        String[] ret;

        if (args.length != 3) {
            ret = new String[3];
            ret[1] = "8";
            ret[2] = "8";
        } else {
            ret = args;
        }

        return ret;
    }

    
}