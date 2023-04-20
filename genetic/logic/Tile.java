package genetic.logic;

public class Tile {
    
    private int x;
    private int y;

    public Element element;
    private Object[] objects;

    private int tileType;

    

    

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;

        this.tileType = World.generateRandom(0, 3);

        

        this.element = switch (this.tileType)
        {
            default -> new Earth();
            case 1  -> new Water();
            case 2  -> new Air();
            case 3  -> new Fire();
        };

        System.out.println("tile x:" + x + " y: " + y + " ran: " + this.element.chanceDanger);

    }
}
