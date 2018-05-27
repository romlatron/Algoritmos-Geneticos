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
import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        ParseConfig pc = ParseConfig.getInstance("config/config.properties");
        List<Chromosome> chromosomes = pc.generatePopulation();
        
        // Define methods

        Crossover crossover = pc.selectCrossover();
        Mutation mutation = pc.selectMutation();
        StopCondition stopCondition = pc.selectStopCondition();
        
        Selection seleccionar = new SelectionMixed(new SelectionBoltzmann(400), new SelectionElite(), 0.8, 10); // N is the total number of chromosomes. gap is a parameter between 0 and 1.
        Replacement reemplazar = new ReplaceKMutated(seleccionar); // 10 is param, 0 is not important since it gets overwritten in replacement.
        Crossover recombinar = new UniformCrossover(0.5, 0.5);
        
        Selection findBestSelection = new SelectionElite(1);

        System.out.println(findBestSelection.apply(chromosomes));

        // Iterate to stop condition
        Boolean rmt = false;
        while (!stopCondition.stop(chromosomes)) {

            // If the replacement strategy is mutate all, do some extra logic
            if (rmt) {
                seleccionar.setTake(2);
                List<Chromosome> aux = new ArrayList<>();
                List<Chromosome> chroms = new ArrayList<>(chromosomes);
                
                do {
                    List<Chromosome> temp = reemplazar.apply(mutation.apply(recombinar.apply(seleccionar.apply(chromosomes))), chroms);

                    chroms.remove(temp.get(0));
                    chroms.remove(temp.get(1));
                    aux.addAll(temp);
                } while (aux.size() < chromosomes.size());
                chromosomes = aux;
            } else {
                chromosomes = reemplazar.apply(mutation.apply(recombinar.apply(seleccionar.apply(chromosomes))), chromosomes);
                System.out.println(findBestSelection.apply(chromosomes));
            }
        }
    }
    
    // TODO: Add Generational gap
    // TODO: Solve issue w/ReplacementAllMutated. (Is this just a special case for ReplaceAllMutated?)
    // TODO: Check Boltzmann and Ranking
    
}
