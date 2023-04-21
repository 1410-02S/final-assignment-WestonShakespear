package genetic.logic;

public class Creature extends Object
{
    @Override
    public void generate()
    {
        super.totalHealth = World.generateRandom(0, 99);
        super.health = 100;
        super.hunger = 0;

        super.type = "Bacteria";
    }
}
