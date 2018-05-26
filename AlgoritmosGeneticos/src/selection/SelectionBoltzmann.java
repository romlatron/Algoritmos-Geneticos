/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selection;

import algoritmosgeneticos.Chromosome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Acer
 */
public class SelectionBoltzmann implements Selection {
    private int temperature;
    private int take;
    private double fitnessAcc = 0;

    public SelectionBoltzmann (int take, int temperature) {
        this.take = take;
        this.temperature = temperature;
    }

    @Override
    public void next () {
        this.temperature = Math.max(this.temperature - 1, 1);
    }

    @Override
    public void setTake (int take) {
        this.take = take;
    }
    
    @Override
    public List<Chromosome> apply (List<Chromosome> chromosomes) {
        // Set local variables
        List<Chromosome> orderedChromosomes = new ArrayList<>(chromosomes);
        Collections.sort(orderedChromosomes);
        List<Chromosome> selectedChromosomes = new ArrayList<>();
        
        // Generate the accumulated fitness list
        List<Double> accumulatedFitnessList = chromosomes
        .stream()
        .map(chromosome -> this.fitnessAcc += Math.exp(chromosome.getFitness() / this.temperature))
        .collect(Collectors.toList());
        
        // Get `this.take` elements from the chromosome list
        for (int i = 0; i < this.take; i++) {
            double randomNum = Math.random() * fitnessAcc;
            
            // Find the first element greater than randomNum
            Chromosome selectedChromosome = chromosomes.get(
                accumulatedFitnessList.indexOf(
                    accumulatedFitnessList
                    .stream()
                    .filter(fitness -> fitness > randomNum)
                    .findFirst().get()
                )
            );

            selectedChromosomes.add(selectedChromosome);
        }
        
        return selectedChromosomes;
    }    
}
