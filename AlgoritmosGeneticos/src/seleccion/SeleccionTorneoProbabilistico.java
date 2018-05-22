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

    @Override
    public List<Chromosome> apply (List<Chromosome> chromosomes) {
        List<Chromosome> selectedChromosomes = new ArrayList<>();

		// Select `this.take` elements
		for (int i; i < this.take; i++) {
			Boolean bestFit = Math.random() < 0.75;
			// TODO: Maybe optimize. This has O(n) but can be done in O(1) if we remove shuffle.
			
			List<Chromosome> shuffledChromosomes = Collections.shuffle(chromosomes);
			List<Chromosome> roundChromosomes = shuffledChromosomes.subList(0, 2);
			Chromosome selectedChromosome = Collections.sort(roundChromosomes).get(bestFit ? 0 : 1);
	
			selectedChromosome.add(selectedChromosome);
		} 
		
		return selectedChromosome;
    }    
}
