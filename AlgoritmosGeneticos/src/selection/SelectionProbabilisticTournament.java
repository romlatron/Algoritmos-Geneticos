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
public class SelectionProbabilisticTournament implements Selection {
    private int take;

    public SelectionProbabilisticTournament (int take) {
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
        for (int i = 0; i < this.take; i++) {
            Boolean bestFit = Math.random() < 0.75;
            List<Chromosome> roundChromosomes = new ArrayList<>();

            for(int j = 0; j < 2; j++) {
                int randomIndex = (int) (Math.random() * (chromosomes.size() - 1));
                roundChromosomes.add(chromosomes.get(randomIndex));
            }
            
            Collections.sort(roundChromosomes);
            Chromosome selectedChromosome = roundChromosomes.get(bestFit ? 0 : 1);

            selectedChromosomes.add(selectedChromosome);
        } 

        return selectedChromosomes;
    }    
}
