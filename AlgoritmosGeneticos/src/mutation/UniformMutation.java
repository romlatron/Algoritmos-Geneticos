/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutation;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.Item;
import java.util.List;

/**
 *
 * @author Acer
 */
public class UniformMutation implements Mutation{

    double prob;
    List <Item> repo;
    
    public UniformMutation (double prob) {
        this.prob = prob;
    }
    
    @Override
    public List<Chromosome> apply(List<Chromosome> chromosomes) {
        for (Chromosome chromosome : chromosomes) {
            for (Item item : chromosome.items) {
                if (Math.random() < prob)
                    item = repo.get((int) (repo.size() * Math.random()));                
            }
            if (Math.random() < prob)
                chromosome.height = Math.random() * 0.7 + 1.3;
        }
        return chromosomes;
    }    
}
