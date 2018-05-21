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
public class Reemplazo1 implements Reemplazo<Chromosome> {

    double prob;
    List <Item> repo;
    
    public Reemplazo1 (double prob) {
        this.prob = prob;
    }
    
    @Override
    public List<Chromosome> apply (List<Chromosome> chromosomes) {
    }    
}
