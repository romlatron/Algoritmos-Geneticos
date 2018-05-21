/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import reemplazo.ReemplazoKMutados;
import reemplazo.ReemplazoMutarTodos;
import seleccion.Seleccion;
import seleccion.SeleccionElite;

/**
 *
 * @author v1nkey
 */
public class AlgoritmosGeneticos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {

        // Logic should look like this. Uncomment when its functional.

        // Reemplazo reemplazar = new ReemplazoKMutados(new SeleccionElite(0)); // 10 is param, 0 is not important since it gets overwritten in replacement.
        // Seleccion seleccionar = new SeleccionElite(10); // 10 is param
        // chromosomes = new ArrayList();

        // Boolean rmt = true;
        // int x = 20;
        // // Iterate to stop condition
        // while (x-- >= 0) {

        //     // If the replacement strategy is mutate all
        //     // Do some extra logic.
        //     if (rmt) {
        //         seleccionar.setTake(2);
        //         List<Chromosome> aux = new ArrayList<>();
        //         List<Chromosome> chroms = chromosomes.clone();
        //         do {
        //             aux = reemplazar.apply(mutar.apply(recombinar.apply(seleccionar.apply(chromosomes)), chroms));
        //             chroms.remove(aux.get(0));
        //             chroms.remove(aux.get(1));
        //         } while (aux.size() < chromosomes.size());
        //         chromosomes = aux;
        //     } else {
        //         chromosomes = reemplazar.apply(mutar.apply(recombinar.apply(seleccionar.apply(chromosomes)), chromosomes));
        //     }
		// }
    }
    
}
