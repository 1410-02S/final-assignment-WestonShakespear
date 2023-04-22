package genetic.logic;



public class Object
{
    public String name;
    public String type;
    public String color;

    protected int totalHealth;
    protected int willPower;
    protected int health;
    protected int hunger;

    protected boolean generated;



    public void generate()
    {

    }

    public void roll()
    {

    }

    public boolean move()
    {
        return false;
    }

    public int getColorValue()
    {
        return Integer.parseInt(this.color, 2);
    }

    protected void initColor()
    {
        this.color = "";
        for (int i = 0;i < 8;i++)
        {
            this.color += World.generateRandom(0, 1);
        }
    }

    public void setColor(String color)
    {
        this.color = color;
    }





    public int getHealth()
    {
        return this.health;
    }

    public int getHunger()
    {
        return this.hunger;
    }
}
