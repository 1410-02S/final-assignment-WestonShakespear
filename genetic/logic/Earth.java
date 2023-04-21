package genetic.logic;

import java.util.List;

public class Earth extends Element
{
    final int[] color = {42, 161, 152};

    @Override
    public void generate()
    {
        super.chanceDanger = 20;
        super.chanceFood = 60;
        super.chanceLife = 40;

        super.initStats();

        super.name = "Earth";

        super.color = this.color;
    }

    @Override
    public Object creation()
    {
        if (World.generateRandom() < super.life) {
            return new Creature();
        }
        return null;
    }
}
