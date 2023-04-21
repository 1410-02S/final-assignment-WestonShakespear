package genetic.logic;

public class Fire extends Element
{
    final int[] color = {203, 75, 22};

    @Override
    public void generate()
    {
        super.chanceDanger = 100;
        super.chanceFood = 0;
        super.chanceLife = 5;

        super.name = "Fire";

        super.color = this.color;
    }

    @Override
    public int destruction(int size)
    {
        if (World.generateRandom() < super.danger)
        {
            return World.generateRandom(0, size);
        }
        return 0;
    }
}
