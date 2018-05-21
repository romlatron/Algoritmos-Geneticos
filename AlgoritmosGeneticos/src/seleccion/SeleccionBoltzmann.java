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
public class SeleccionBoltzmann implements Seleccion<Chromosome> {
    private int temperature;
    private int take;

    public SeleccionBoltzmann (int take, int temperature) {
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
        double fitnessAcc = 0;
        List<Chromosome> orderedChromosomes = Collections.sort(chromosomes);
        List<Chromosome> selectedChromosomes = new ArrayList<>();
        
        // Generate the accumulated fitness list
        List<Double> accumulatedFitnessList = orderedChromosomes
        .stream()
        .map(chromosome -> fitnessAcc += Math.exp(chromosome.getFitness() / this.temperature))
        .collect(Collectors.toList());
        
        // Get `this.take` elements from the chromosome list
        for (int i = 0; i < this.take; i++) {
            double randomNum = Math.random() * fitnessAcc;
            
            // Find the first element greater than randomNum
            Chromosome selectedChromosome = orderedChromosomes.get(
                accumulatedFitnessList.indexOf(
                    accumulatedFitnessList
                    .stream()
                    .filter(fitness -> fitness > randomNum)
                    .findFirst()
                )
            );

            selectedChromosomes.add(selectedChromosome);
        }
        
        return selectedChromosomes;
    }    
}
