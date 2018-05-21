/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleccion;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.Item;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Acer
 */
public class SeleccionRuleta implements Seleccion<Chromosome> {
	private int take;
    private int round;

    public SeleccionRuleta (int take, int round) {
		this.take = take;
		this.round = round;
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
			List<Chromosome> shuffledChromosomes = Collections.shuffle(chromosomes);
			List<Chromosome> roundChromosomes = shuffledChromosomes.subList(0, this.round);
			Chromosome selectedChromosome = Collections.sort(roundChromosomes).get(0);
	
			selectedChromosome.add(selectedChromosome);
		} 
		
		return selectedChromosome;
    }    
}
