package genetic.runtime;

import genetic.logic.World;

public class Instance
{
    public World world;

    public Instance(int width, int height, int max)
    {
        this.world = new World(width, height, max);
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

        if (args.length != 4) {
            ret = new String[4];
            ret[0] = "24";
            ret[1] = "24";
            ret[2] = "50";
            ret[3] = "20";
        } else {
            System.out.println("accepted");
            ret = args;
        }

        return ret;
    }

    
}