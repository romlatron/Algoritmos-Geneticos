/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleccion;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.Item;
import java.util.List;

/**
 *
 * @author Acer
 */
public class SeleccionRuleta implements Seleccion<Chromosome> {
    private int take;

    public SeleccionRuleta (int take) {
        this.take = take;
    }

    @Override
    public void next () {}

    @Override
    public void setTake (int take) {
        this.take = take;
    }

    // TODO: Change it

    @Override
    public List<Chromosome> apply (List<Chromosome> chromosomes) {
        // Set local variables
        double fitnessAcc = 0;
        List<Chromosome> orderedChromosomes = Collections.sort(chromosomes);
        List<Chromosome> selectedChromosomes = new ArrayList<>();
        
        // Generate the accumulated fittness List
        List<Double> accumulatedFitnessList = orderedChromosomes
        .stream()
        .map(chromosome -> fitnessAcc += chromosome.getFitness())
        .collect(Collectors.toList());
        
        // Get `this.take` elements from the chromosome list
        double randomNum = Math.random() * fitnessAcc;
        for (int i = 0; i < this.take; i++) {
            double rate = (randomNum + i) / this.take;

            // Find the first element greater than `rate`
            Chromosome selectedChromosome = orderedChromosomes.get(
                accumulatedFitnessList.indexOf(
                    accumulatedFitnessList
                    .stream()
                    .filter(fitness -> fitness > rate)
                    .findFirst()
                )
            );

            selectedChromosomes.add(selectedChromosome);
        }

        return selectedChromosomes;
    }    
}
