/**
 * Created by Катя on 20.01.2018.
 */

import javax.swing.*;
import java.awt.*;
/**
 * Требования к входным данным симуляции
 Задается размер шара (r), исходное положение центра шара (x,y),
 исходная скорость движения мяча задается смещением по осям в плоскости (dx, dy).
 Задается временной интервалл симуляции (dt), с какой периодичностью делаются замеры,
 снимки состояния. Задается время симуляции t в секундах. Задается коэффициент упругости k,
 влияет на отскок.
 */
public class Main extends JPanel{
    int i=-1;

    public double  x0,y0,r = 50,
            x = 100,
            y = 100,
            dx = 15,
            dy = 15,
            dt = 5,
            t = 10000,
            u = 0.9,
            d = r*2,
            g = 9.80665;
    public static void main(String[] args) {
        JFrame fr = new JFrame("Modeling the physics of ball motion");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(700, 550);
        fr.setResizable(false);
        // fr.setContentPane(panel);
        //fr.setLocationRelativeTo();
        fr.setLocation(10,10);
        fr.setContentPane(new Main());
        fr.setBackground(Color.white);
        fr.setVisible(true);

    }


    @Override
    public void paint(Graphics gr) {
        super.paint(gr);
        //  gr.drawImage(getToolkit().getImage("G:\\CM - Ball 1\\pic.jpg"),(int)(x - r), (int)(y - r), (int)d, (int)d,this);
        gr.fillOval((int)(x - r), (int)(y - r), (int)d, (int)d);
    }

    public Main() {

        Thread thread = new Thread() {
            public void run() {
                while (t>0) {i++;
                    double width = getWidth();
                    double height = getHeight();
                    x0=x;
                    y0=y;
                    x = x + dx ;
                    y = y + dy;
                    if (x - r < 0) {
                        x=r; dx=-dx; dy*=u;
                    }
                    if (x + r > width) {
                        x = width - r;  dx = -dx; dy*=u;
                    }
                    if (y - r < 0) {
                        y=r; dy = -dy; dy*=u;
                    }
                    if (y + r > height) {
                        y = height - r; dy = -dy; dy*=u;
                    }
                    if (y == 465) dx *= 0.9;
                    if (y0==y & Math.round(x0)==Math.round(x)) t=-1;

                    repaint();
                    try {
                        Thread.sleep(90);
                    } catch (InterruptedException ex) {
                    }
                    //   t0: (x0, y0) ->  (dx0, dy0)
                    System.out.print("t" + String.valueOf(i)+": ("+Math.round(x0)+", "+Math.round(y0)+") - > ("+Math.round(x)+", "+Math.round(y)+"), speed = "+Math.round(Math.sqrt(dx*dx+dy*dy))+", height = "+String.valueOf(Math.round(Math.sqrt(height-y-r)))+"\n");
                    t=t-dt;
                    dy+=(g/2);
                }
            }
        };
        thread.start();
    }

//    public void actionPerformed(ActionEvent e)
}
