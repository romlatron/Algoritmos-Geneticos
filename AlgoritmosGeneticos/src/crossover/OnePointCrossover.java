/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossover;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class OnePointCrossover extends CrossoverAbstract {

    public OnePointCrossover(double prob) {
        super(prob);
    }

    @Override
    public List <Chromosome> apply(Chromosome c1, Chromosome c2) {
        int rand = (int)(Math.random()*5) + 1;
        List <Item> items1 = new ArrayList();
        List <Item> items2 = new ArrayList();
        items1.add(c1.items.get(0));
        items2.add(c2.items.get(0));
        for (int i=1; i<rand; i++) {
            items1.add(c1.items.get(i));        
            items2.add(c2.items.get(i));
        }
        for (int i=rand; i<5; i++) {
            items1.add(c2.items.get(i));
            items2.add(c1.items.get(i));
        }
        Chromosome new1 = new Chromosome (c1.character, items1, c2.height);
        Chromosome new2 = new Chromosome (c1.character, items2, c1.height);
        List <Chromosome> newChromosomes = new ArrayList();
        newChromosomes.add(new1);
        newChromosomes.add(new2);
        
        return newChromosomes;
    }
    
}
