package genetic.runtime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Control extends JPanel {

    private JButton playButton;
    private JButton pauseButton;

    private Instance game;

    private JLabel yearLabel;
    private int year;

    private int birth = 50;
    private int death = 50;
    private int food = 50;
    private int waste = 50;
    private int speed = 50;

    private boolean play = false;


    private JLabel[] labels = new JLabel[6];
    private JSlider[] sliders = new JSlider[5];

    public Control(Instance game) {
        this.game = game;
        this.buildControl();
    }

    private void buildControl() {
        GridLayout gridLayout = new GridLayout(7,2);
        this.setLayout(gridLayout);

        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                play = true;
            } 
          } );

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                play = false;
            } 
          } );

        this.add(playButton);
        this.add(pauseButton);

        this.labels[0] = new JLabel("Year:");
        this.add(this.labels[0]);

        this.yearLabel = new JLabel("0");
        this.add(this.yearLabel);

        String[] labels = {"Birth", "Death", "Food", "Waste", "Speed"};
        int r = 1;

        for (String label : labels)
        {
            this.labels[r] = new JLabel(label + ":");
            this.add(this.labels[r]);

            this.sliders[r - 1] = new JSlider();
            this.add(this.sliders[r - 1]);

            r++;
        }
        setFocusable(true);

    }

    private void getSliderValues()
    {
        this.birth = this.sliders[0].getValue();
        this.death = this.sliders[1].getValue();
        this.food = this.sliders[2].getValue();
        this.waste = this.sliders[3].getValue();
        this.speed = this.sliders[4].getValue();
    }

    public int[] refresh(int year) {
        this.getSliderValues();

        int[] ret = {this.birth, this.death, this.food, this.waste, this.speed, this.play ? 1 : 0};
        this.year = year;

        this.yearLabel.setText(Integer.toString(year));
        return ret;
    }
    
}
