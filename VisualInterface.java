import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

public class VisualInterface extends JPanel{

    int height ;
    int width ;
    int padding;
    int paintedfirst=0;
    public VisualInterface(Game game) {
        _game = game;

        //padding corresponds to number of offscreen cells that are calculated but not shown on each side of the screen
        padding = _game.offscreenMargins/2;

        int height = _game.HEIGHT;
        int width = _game.WIDTH;
        this.setPreferredSize(new Dimension(width, height));
        JFrame frame = new JFrame("life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void display(Graphics g) {
        //Draw the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, _game.WIDTH, _game.HEIGHT);

        //Iterate over all cells
        for (int row = padding; row < _game.getRows()-padding; row++) {
            for (int column = padding; column < _game.getColumns()-padding; column++) {

                    //Draw the grey grid
                    int initx = (column - padding) * _game.boxsize;
                    int inity = (row - padding) * _game.boxsize;
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(initx, inity, _game.boxsize, _game.boxsize);

                    //Fill a cell with color depending on its state
                    Cell cell = _game.getCell(row, column);

                    if (cell._wasEverAlive) {
                        g.setColor(new Color(193, 234, 170));
                        g.fillRect(initx + 1, inity + 1, _game.boxsize - 2, _game.boxsize - 2);
                    }
                    if (cell.isAlive()) {
                        g.setColor(Color.BLUE);
                        if (!cell._willBeAlive) {
                            g.setColor(Color.RED); // or new Color(0,0,255,70)
                            g.fillRect(initx + 1, inity + 1, _game.boxsize - 2, _game.boxsize - 2);
                        }
                        g.fillRect(initx + 1, inity + 1, _game.boxsize - 2, _game.boxsize - 2);
                    }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        display(g);
    }

    Game _game;
}