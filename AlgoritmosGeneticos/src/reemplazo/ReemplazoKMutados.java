/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reemplazo;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.Item;
import seleccion.Seleccion;
import algoritmosgeneticos.seleccion;
import java.util.List;

/**
 *
 * @author Acer
 */
public class ReemplazoKMutados implements Reemplazo<Chromosome> {
    private int take;
    private Seleccion seleccion;

    @Override
    public int TAKE() { return take; }

    public ReemplazoKMutados (int take, Seleccion seleccion) {
        this.take = take;
        this.seleccion = seleccion;
    }
    
    @Override
    public List<Chromosome> apply (List<Chromosome> mutated, List<Chromosome> oldGeneration) {
        List<Chromosome> replaced = new ArrayList<>();
        replaced.addAll(mutated);
        
        seleccion.setTake(oldGeneration.size() - mutated.size());
        
        replaced.addAll(seleccion.apply(oldGeneration));
        return replaced;
    }    
}

// select.setTake(reemplazo.TAKE_K);
// newGeneration = new ArrayList();
// do {
//     List<> aux = Reemplazo(newGeneration, oldgeneration);
//     select.next();
//     newGeneration.addAll(aux);
// } while (newGeneration.size() != N);