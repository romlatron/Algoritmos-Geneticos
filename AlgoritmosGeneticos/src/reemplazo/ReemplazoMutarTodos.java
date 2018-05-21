/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reemplazo;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.Item;
import java.util.List;

/**
 *
 * @author Acer
 */
public class ReemplazoMutarTodos implements Reemplazo<Chromosome> {
    public ReemplazoMutarTodos () {}
    
    @Override
    public List<Chromosome> apply (List<Chromosome> mutated, List<Chromosome> oldGeneration) {
        return mutated;
    }    
}

// select.setTake(reemplazo.TAKE_K);
// newGeneration = new ArrayList();
// do {
//     List<> aux = Reemplazo(newGeneration, oldgeneration);
//     newGeneration.addAll(aux);
// } while (newGeneration.size() != N);