/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleccion;

import algoritmosgeneticos.Chromosome;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Acer
 */
public class SeleccionElite implements Seleccion {
    private int take;
    
    public SeleccionElite () {
    }

    public SeleccionElite (int take) {
        this.take = take;
    }

    @Override
    public void next() {}
    
    @Override
    public void setTake(int take) {
        this.take = take;
    }

    @Override
    public List<Chromosome> apply (List<Chromosome> chromosomes) {
        Collections.sort(chromosomes);
        return chromosomes.subList(0, this.take);
    }    
}
