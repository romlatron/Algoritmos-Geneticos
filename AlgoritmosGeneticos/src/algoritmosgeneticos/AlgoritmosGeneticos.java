/*v
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import selection.*;
import replacement.*;
import mutation.*;
import crossover.*;
import java.io.File;
import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author v1nkey
 */
public class AlgoritmosGeneticos {
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException
    {
        JFileChooser configFileChooser = new JFileChooser(new File(".."));
        configFileChooser.setFileFilter(new FileNameExtensionFilter("Config files", "properties"));
        File configFile = null;
        
        if (configFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            configFile = configFileChooser.getSelectedFile();
        else 
            return;
        
        ParseConfig pc = ParseConfig.getInstance(configFile.getAbsolutePath());

        List<Double> fitnessHistory = new ArrayList<>();
        List<Double> fitnessMin = new ArrayList<>();
        List<Double> fitnessAvg = new ArrayList<>();
        Plotter plotter = new Plotter();

        List<Chromosome> chromosomes = pc.generatePopulation();

        // Define methods

        Crossover crossover = pc.selectCrossover();
        Mutation mutation = pc.selectMutation();
        StopCondition stopCondition = pc.selectStopCondition();
        Selection select = pc.selectSelection();
        Replacement replace = pc.selectReplacement();

        Selection findBestSelection = new SelectionElite(1);

        //System.out.println(findBestSelection.apply(chromosomes));

        // Iterate to stop condition
        /*while (!stopCondition.stop(chromosomes)) {

            // If the replacement strategy is mutate all, do some extra logic
            if (replace instanceof ReplaceAllMutated) {
                select.setTake(2);
                List<Chromosome> aux = new ArrayList<>();
                List<Chromosome> chroms = new ArrayList<>(chromosomes);

                do {
                    List<Chromosome> temp = replace.apply(mutation.apply(crossover.apply(select.apply(chromosomes))), chroms);

                    chroms.remove(temp.get(0));
                    chroms.remove(temp.get(1));
                    aux.addAll(temp);
                } while (aux.size() < chromosomes.size());
                chromosomes = aux;
            } else {
                chromosomes = replace.apply(mutation.apply(crossover.apply(select.apply(chromosomes))), chromosomes);
                fitnessHistory.add(findBestSelection.apply(chromosomes).get(0).getFitness());
            }
        }*/

        while (!stopCondition.stop(chromosomes))
        {
            if (replace instanceof ReplaceAllMutated)
                chromosomes = replace.apply(mutation.apply(crossover.apply(chromosomes)), chromosomes);
            else
                chromosomes = replace.apply(mutation.apply(crossover.apply(select.apply(chromosomes))), chromosomes);

            fitnessHistory.add(findBestSelection.apply(chromosomes).get(0).getFitness());
            double fitnessSum = 0;
            double minFitness = Integer.MAX_VALUE;
            for (Chromosome c: chromosomes) {
                fitnessSum += c.getFitness();
                minFitness = minFitness < c.getFitness() ? minFitness : c.getFitness();
            }
            fitnessMin.add(minFitness); // red
            fitnessAvg.add(fitnessSum / chromosomes.size()); // green
            plotter.plotRealTime(fitnessHistory, fitnessMin, fitnessAvg);
        }

        System.out.println(findBestSelection.apply(chromosomes).get(0));
        Plotter.plot(fitnessHistory, fitnessMin, fitnessAvg);
    }
}
