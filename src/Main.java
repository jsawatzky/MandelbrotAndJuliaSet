import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Jacob on 2016-05-09.
 */
public class Main extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {

    Mandelbrot m = new Mandelbrot();
    Julia j = new Julia();

    private int mouseX = 0, mouseY = 0;

    public static void main(String[] args) {

        JFrame f = new JFrame("Mandelbrot and Julia Sets");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(new Dimension(720, 840));
        f.setResizable(false);

        Main main = new Main();
        f.setContentPane(main);

        f.addMouseListener(main);
        f.addMouseMotionListener(main);
        f.addMouseWheelListener(main);

        f.setVisible(true);
        f.setLocationRelativeTo(null);

        main.init();

    }

    public Main() {

        super();

        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(720, 840));

    }

    public void init() {

        this.getGraphics().drawImage(m.generate(640, 360), 40, 40, null);
        this.getGraphics().drawImage(j.generate(640, 360, 0, 0), 40, 440, null);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();

        if (mouseX - 40 >= 0 && mouseX - 40 <= 640 && mouseY - 40 >= 0 && mouseY - 40 <= 360) {
            this.getGraphics().drawImage(j.generate(640, 360, m.getPointX(mouseX-40), m.getPointY(mouseY-40)), 40, 440, null);
        }

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        if (mouseX - 40 >= 0 && mouseX - 40 <= 640 && mouseY - 40 >= 0 && mouseY - 40 <= 360) {
            if (e.getPreciseWheelRotation() < 0) {
                m.zoomIn(mouseX - 40, mouseY - 40);
            } else if (e.getPreciseWheelRotation() > 0) {
                m.zoomOut(mouseX - 40, mouseY - 40);
            }

            this.getGraphics().drawImage(m.generate(640, 360), 40, 40, null);

        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

//        if (mouseX - 40 >= 0 && mouseX - 40 <= 640 && mouseY - 40 >= 0 && mouseY - 40 <= 360) {
//
//            m.move(e.getX()-mouseX, e.getY()-mouseY);
//            this.getGraphics().drawImage(m.generate(640, 360), 40, 40, null);
//
//        }

        mouseX = e.getX();
        mouseY = e.getY();

    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
