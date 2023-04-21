package genetic.logic;

public class Creature extends Object
{
    @Override
    public void generate()
    {
        super.totalHealth = World.generateRandom();
        super.willPower = World.generateRandom();
        super.health = 100;
        super.hunger = 0;

        super.type = "Bacteria";

        this.initColor();
    }

    @Override
    public boolean move()
    {
        if (World.generateRandom() < super.willPower)
        {
            return true;
        }

        return false;
    }
}
