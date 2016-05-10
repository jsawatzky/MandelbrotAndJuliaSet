import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Jacob on 2016-05-09.
 */
public class Mandelbrot {

    private final int max_iteration = 1000;

    private int size;
    private int xOffset;
    private int yOffset;
    private double xMove = 0;
    private double yMove = 0;
    private double zoom = 0;

    public BufferedImage generate(int w, int h) {

        if (w != 0 && h != 0) {

            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) image.getGraphics();

            size = Math.min(w, h);
            xOffset = (w - size) / 2;
            yOffset = (h - size) / 2;

            if (zoom == 0.0) {
                zoom = size / 3.0;
            }

            for (int Px = 0; Px < w; Px++) {
                for (int Py = 0; Py < h; Py++) {

                    double cA = getPointX(Px);
                    double cB = getPointY(Py);

                    double zA = 0.0;
                    double zB = 0.0;

                    float iteration = 0;

                    while (zA * zA + zB * zB < (1 << 16) && iteration < max_iteration) {
                        double zATemp = zA * zA - zB * zB + cA;
                        double zBTemp = 2 * zA * zB + cB;
                        if (zA == zATemp && zB == zBTemp) {
                            iteration = max_iteration;
                            break;
                        }
                        zA = zATemp;
                        zB = zBTemp;
                        iteration += 1;
                    }

                    if (iteration < max_iteration) {
                        double log_zn = Math.log(Math.abs(Math.sqrt(zA * zA + zB * zB))) / 2;
                        double nu = Math.log(log_zn) / Math.log(2);
                        iteration += 1 - nu;
                        g.setColor(new Color(Color.HSBtoRGB(iteration / 256f, 1, iteration / (iteration + 8f))));
                    } else {
                        g.setColor(Color.BLACK);
                    }

                    g.fillRect(Px, Py, 1, 1);

                }
            }

            return image;

        }

        return null;

    }

    public void move(int Px, int Py) {

        xMove += getPointX(Px);
        yMove += getPointY(Py);

    }

    public void zoomIn(int Px, int Py) {

        xMove = getPointX(Px);
        yMove = getPointY(Py);

        zoom *= 2;

    }

    public void zoomOut(int Px, int Py) {

        xMove = getPointX(Px);
        yMove = getPointY(Py);

        zoom /= 2;

    }

    public double getPointX(int Px) {
        return (((Px-xOffset) - (size/2)) / zoom) + xMove;
    }

    public double getPointY(int Py) {
        return (((Py-yOffset) - (size/2)) / zoom) + yMove;
    }


}
