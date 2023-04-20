package genetic.runtime;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;

public class GUI extends JFrame implements ActionListener
{
    private int x;
    private int y;
    private int resolution;

    private Screen screen;
    private Control control;
    private Instance game;

    private Timer timer;

    private int year = 0;

    public GUI(int x, int y, int resolution, Instance game)
    {
        this.x = x;
        this.y = y;
        this.resolution = resolution;
        this.game = game;

        GridLayout gLayoutMain = new GridLayout(1, 2);

        this.setLayout(gLayoutMain);

        this.screen = new Screen(x, y, resolution);
        this.add(this.screen);

        this.control = new Control(this.game);
        this.add(this.control);

        this.setSize(2*(this.x*this.resolution), this.y*this.resolution + 100);
        this.setTitle("CS 1410 Final Project: Evolution");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(rootPane);

        this.timer = new Timer(100, this);
        this.timer.start();

        this.game.start();
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.screen.populateScreen(this.game.world.getTileColors());
        this.screen.refresh();
        
        
        int[] data = this.control.refresh(this.year);

        int speed = ((100 - data[4]) * 1000) / 100;

        this.timer.setDelay(speed);
        int play = data[5];

        if (play == 1)
        {
            this.year += 1;
            this.game.step();
        }

        int birth = data[0];
        int death = data[1];
        int food = data[2];
        int waste = data[3];

        this.game.world.setVariables(birth, death, food, waste);
    }

    public static void main(String[] args)
    {
        String[] checkedArgs = Instance.checkArgs(args);
        int x = Integer.parseInt(checkedArgs[1]);
        int y = Integer.parseInt(checkedArgs[2]);
        int res = 100;

        Instance game = new Instance(x, y);

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run()
            {
                GUI gui = new GUI(x, y, res, game);
                gui.setVisible(true);
            }
        });

    }
}
