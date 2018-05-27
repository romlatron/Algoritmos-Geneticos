/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 *
 * @author rama
 */
public class Plotter {
    public static void plot(List<Double> ys, String titleX, String titleY, String title) {
        double[] x = new double[ys.size()];
        double[] y = new double[ys.size()];

        Plot2DPanel plot = new Plot2DPanel();

        for(int i = 0 ; i < ys.size() ; i++ ) {
            x[i] = i+1;
            y[i] = ys.get(i);
        }

        plot.addLinePlot(title, x, y);

        BaseLabel labelTitle = new BaseLabel(title, Color.BLACK, 0.5, 1.1);
        labelTitle.setFont(new Font("Courier", Font.BOLD, 20));
        plot.addPlotable(labelTitle);
        
        // put the PlotPanel in a JFrame, as a JPanel
//        JFrame frame = new JFrame(title + " plot");
//        frame.setSize(600, 600);
//        frame.setContentPane(plot);
//        frame.setVisible(true);
//        
        plot.plotCanvas.setSize(600, 600);
        
        BufferedImage bufferedImage = new BufferedImage(plot.plotCanvas.getWidth(), plot.plotCanvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        plot.plotCanvas.paint(g);
        g.dispose();
        try {
            ImageIO.write((RenderedImage) bufferedImage, "PNG", new File("plots/"+ title + ".png"));
            System.out.println("Save plot " + title);
        } catch (Exception e) {}
    }
}
