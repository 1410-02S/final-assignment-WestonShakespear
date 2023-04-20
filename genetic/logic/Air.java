package genetic.logic;

public class Air extends Element
{
    final int[] color = {108, 113, 196};

    @Override
    public void generate()
    {
        super.chanceDanger = 20;
        super.chanceFood = 30;
        super.chanceLife = 15;

        super.name = "Air";

        super.color = this.color;
    }
}
