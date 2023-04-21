package genetic.logic;

import java.util.*;

public class Element {
    protected int chanceDanger;
    protected int chanceFood;
    protected int chanceLife;

    protected int danger;
    protected int food;
    protected int life;

    protected String name;

    protected int[] color;

    protected World world;

    public void generate()
    {

    }

    protected void initStats()
    {
        this.danger = this.chanceDanger;
        this.food = this.chanceFood;
        this.life = this.chanceLife;
    }

    public void updateStats(int birth, int death, int food)
    {
        this.life = (int)this.percent(birth, this.chanceLife) * this.chanceLife;
    }

    public Object creation()
    {
        return null;
    }

    public int destruction(int size)
    {
        return 0;
    }




    private double percent(int number, int upper)
    {
        double ret = number;
        ret = ret / upper;

        return ret;
    }

    private int percent(double number, int upper)
    {
        double ret = number * upper;
        return (int)ret;
    }
    
}
