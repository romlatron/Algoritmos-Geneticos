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

    public SeleccionRuleta (int take, int temperature) {
        this.take = take;
    }

    @Override
    public void next () {}

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
		
		// TODO: Check this algorithm is OK.

		// Generate the accumulated fitness list
		// In this case, the fitness is given only by its position (It's ranking)
		List<Double> accumulatedFitnessList = orderedChromosomes
		.stream()
		.map(chromosome -> fitnessAcc += 1)
		.collect(Collectors.toList());
		
		// Get `this.take` elements from the chromosome list
		for (int i = 0; i < this.take; i++) {
			double randomNum = Math.random() * fitnessAcc;
			
			// Find the first element greater than randomNum
			// TODO: Check this snippet is OK in all the selection algorithms.
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
