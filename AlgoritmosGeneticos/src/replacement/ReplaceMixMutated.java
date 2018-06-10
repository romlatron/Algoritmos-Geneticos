/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replacement;

import algoritmosgeneticos.Chromosome;
import java.util.ArrayList;
import java.util.List;
import selection.Selection;

/**
 *
 * @author Acer
 */
public class ReplaceMixMutated implements Replacement {
    private Selection selection;

    public ReplaceMixMutated (Selection selection) {
        this.selection = selection;
    }
    
    @Override
    public List<Chromosome> apply (List<Chromosome> mutated, List<Chromosome> oldGeneration) {
        List<Chromosome> replaced = new ArrayList<>();
        List<Chromosome> chromosomePool = new ArrayList<>();
        
        // Mix old generation with mutated generation
        chromosomePool.addAll(mutated);
        chromosomePool.addAll(oldGeneration);
        
        // N-k from old generation is cloned without mutations
        selection.setTake(oldGeneration.size() - mutated.size());
        replaced.addAll(selection.apply(oldGeneration));
        
        // k from chromosome pool are selected into the new generation
        selection.setTake(mutated.size());
        replaced.addAll(selection.apply(chromosomePool));
                
        return replaced;
    }    
}