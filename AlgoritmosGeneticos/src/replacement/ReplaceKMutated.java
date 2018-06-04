/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replacement;

import algoritmosgeneticos.Chromosome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import selection.Selection;

/**
 *
 * @author Acer
 */
public class ReplaceKMutated implements Replacement {
    private Selection selection;

    public ReplaceKMutated (Selection selection) {
        this.selection = selection;
    }
    
    @Override
    public List<Chromosome> apply (List<Chromosome> mutated, List<Chromosome> oldGeneration) {
        List<Chromosome> replaced = new ArrayList<>();
        replaced.addAll(mutated);
        
        System.out.println("START K MUTATED");
        Collections.sort(mutated);
        System.out.println("best mutated: " + mutated.get(0).getFitness());
        
        selection.setTake(oldGeneration.size() - mutated.size());
        
        replaced.addAll(selection.apply(oldGeneration));
        Collections.sort(replaced);
        System.out.println("best replaced: " + mutated.get(0).getFitness());
        return replaced;
    }    
}