package genetic.logic;

public class Water extends Element
{
    final int[] color = {38, 139, 210};

    @Override
    public void generate()
    {
        super.chanceDanger = 10;
        super.chanceFood = 90;
        super.chanceLife = 80;

        super.name = "Water";

        super.color = this.color;
    }
}
