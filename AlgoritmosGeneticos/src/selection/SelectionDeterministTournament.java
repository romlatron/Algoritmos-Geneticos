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

/**
 *
 * @author Acer
 */
public class SelectionDeterministTournament implements Selection {
	private int take;
    private int round;

    public SelectionDeterministTournament (int take, int round) {
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
		for (int i = 0; i < this.take; i++) {
			List<Chromosome> shuffledChromosomes = new ArrayList<>(chromosomes);
                        Collections.shuffle(shuffledChromosomes);
			List<Chromosome> roundChromosomes = shuffledChromosomes.subList(0, this.round);
                        Collections.sort(roundChromosomes);
			Chromosome selectedChromosome = roundChromosomes.get(0);
	
			selectedChromosomes.add(selectedChromosome);
		} 
		
		return selectedChromosomes;
    }    
}
