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
public class ReplaceKMutated implements Replacement {
    private Selection selection;

    public ReplaceKMutated (Selection selection) {
        this.selection = selection;
    }
    
    @Override
    public List<Chromosome> apply (List<Chromosome> mutated, List<Chromosome> oldGeneration) {
        List<Chromosome> replaced = new ArrayList<>();
        replaced.addAll(mutated);
        
        selection.setTake(oldGeneration.size() - mutated.size());
        
        replaced.addAll(selection.apply(oldGeneration));
        return replaced;
    }    
}

// select.setTake(reemplazo.TAKE_K);
// newGeneration = new ArrayList();
// do {
//     List<> aux = Replacement(newGeneration, oldgeneration);
//     select.next();
//     newGeneration.addAll(aux);
// } while (newGeneration.size() != N);