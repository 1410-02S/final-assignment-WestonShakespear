package genetic.logic;

public class Earth extends Element
{
    final int[] color = {42, 161, 152};

    @Override
    public void generate()
    {
        super.chanceDanger = 20;
        super.chanceFood = 60;
        super.chanceLife = 80;

        super.name = "Earth";

        super.color = this.color;
    }

    @Override
    public void creation()
    {

    }
}
