/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reemplazo;

import algoritmosgeneticos.Chromosome;
import seleccion.Seleccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class ReemplazoMixMutados implements Reemplazo {
    private Seleccion seleccion;

    public ReemplazoMixMutados (Seleccion seleccion) {
        this.seleccion = seleccion;
    }
    
    @Override
    public List<Chromosome> apply (List<Chromosome> mutated, List<Chromosome> oldGeneration) {
        List<Chromosome> replaced = new ArrayList<>();
        replaced.addAll(mutated);
        replaced.addAll(oldGeneration);

        seleccion.setTake(oldGeneration.size());
        return seleccion.apply(replaced);
    }    
}

// select.setTake(reemplazo.TAKE_K);
// newGeneration = new ArrayList();
// do {
//     List<> aux = Reemplazo(newGeneration, oldgeneration);
//     select.next();
//     newGeneration.addAll(aux);
// } while (newGeneration.size() != N);