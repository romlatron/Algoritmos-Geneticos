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
    private JFrame frame;
    Plot2DPanel plot;
    Boolean firstPass = true;
    public static int step = 0;
            
    public void plotRealTime(List<Double> ys, List<Double> minYs, List<Double> avgYs) {
        step++;
        if (!(step % 10 == 0)) return;
        step = 0;
        
        double[] x = new double[ys.size()];
        double[] y = new double[ys.size()];
        double[] minY = new double[ys.size()];
        double[] avgY = new double[ys.size()];
        
        Plot2DPanel plot = new Plot2DPanel();
        
        for(int i = 0 ; i < ys.size() ; i++ ) {
            x[i] = i+1;
            y[i] = ys.get(i);
            minY[i] = minYs.get(i);
            avgY[i] = avgYs.get(i);
        }
        
        plot.addLinePlot("Fitness through generations", x, y);
        plot.addLinePlot("test", x, minY);
        plot.addLinePlot("test", x, avgY);

//        BaseLabel labelTitle = new BaseLabel("Fitness through generations", Color.BLACK, 0.5, 1.1);
//        labelTitle.setFont(new Font("Courier", Font.BOLD, 20));
//        plot.addPlotable(labelTitle);
        
        // put the PlotPanel in a JFrame, as a JPanel
        this.frame = this.frame == null ? new JFrame("Plot") : this.frame;
        if (firstPass) {
            frame.setSize(1200, 600);
            frame.setVisible(true); 
            firstPass = false;
        }
        frame.setContentPane(plot);
        frame.invalidate();
        frame.validate();
        frame.repaint();

    }
    
    public static void plot(List<Double> ys, List<Double> minYs, List<Double> avgYs) {
        double[] x = new double[ys.size()];
        double[] y = new double[ys.size()];
        double[] minY = new double[ys.size()];
        double[] avgY = new double[ys.size()];
        
        Plot2DPanel plot = new Plot2DPanel();

        for(int i = 0 ; i < ys.size() ; i++ ) {
            x[i] = i+1;
            y[i] = ys.get(i);
            minY[i] = minYs.get(i);
            avgY[i] = avgYs.get(i);
        }

        plot.addLinePlot("Fitness through generations", x, y);
        plot.addLinePlot("test", x, minY);
        plot.addLinePlot("test", x, avgY);

        BaseLabel labelTitle = new BaseLabel("Fitness through generations", Color.BLACK, 0.5, 1.1);
        labelTitle.setFont(new Font("Courier", Font.BOLD, 20));
        plot.addPlotable(labelTitle);
        
        // put the PlotPanel in a JFrame, as a JPanel
//        JFrame frame = new JFrame("Plot");
//        frame.setSize(1200, 600);
//        frame.setContentPane(plot);
//        frame.setVisible(true);
        
        plot.plotCanvas.setSize(1200, 600);
        
        BufferedImage bufferedImage = new BufferedImage(plot.plotCanvas.getWidth(), plot.plotCanvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        plot.plotCanvas.paint(g);
        g.dispose();
        try {
            ImageIO.write((RenderedImage) bufferedImage, "PNG", new File("plots/"+ "Fitness through generations" + ".png"));
            System.out.println("Save plot Fitness through generations");
        } catch (Exception e) {}
    }
}
