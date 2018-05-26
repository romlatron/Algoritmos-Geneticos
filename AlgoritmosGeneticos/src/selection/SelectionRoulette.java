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
public class SelectionRoulette implements Selection {
    private int take;
    private double fitnessAcc = 0;

    public SelectionRoulette () {}
    
    public SelectionRoulette (int take) { this.take = take; }

    @Override
    public void next () {}

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
        
        // Generate the accumulated fittness List
        List<Double> accumulatedFitnessList = orderedChromosomes
        .stream()
        .map(chromosome -> this.fitnessAcc += chromosome.getFitness())
        .collect(Collectors.toList());
        
        // Get `this.take` elements from the chromosome list
        for (int i = 0; i < this.take; i++) {
            double randomNum = Math.random() * this.fitnessAcc;
            
            // Find the first element greater than randomNum
            Chromosome selectedChromosome = orderedChromosomes.get(
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
