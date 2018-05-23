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
public class TwoPointsCrossover  extends CrossoverAbstract {

    public TwoPointsCrossover(double prob) {
        super(prob);
    }

    @Override
    public List <Chromosome> apply(Chromosome c1, Chromosome c2) {
        int rand1 = (int)(Math.random()*5);
        int rand2 = (int)(Math.random()*(5-rand1)) + rand1 + 1; //rand between rand1 + 1 and 5
        List <Item> items1 = new ArrayList();
        List <Item> items2 = new ArrayList();
        for (int i=0; i<rand1; i++) {
            items1.add(c1.items.get(i));
            items2.add(c2.items.get(i));
        }
        for (int i=rand1; i<=rand2; i++) {
            items1.add(c2.items.get(i));        
            items2.add(c1.items.get(i));
        }
        for (int i=rand2; i<5; i++) {
            items1.add(c1.items.get(i));
            items2.add(c2.items.get(i));
        }
        
        double height1, height2;
        
        if (rand2 == 5) {
            height1 = c2.height;
            height2 = c1.height;
        }
        else {
            height1 = c1.height;
            height2 = c2.height;
        }
        Chromosome new1 = new Chromosome (c1.character, items1, height1);
        Chromosome new2 = new Chromosome (c1.character, items2, height2);
        List <Chromosome> newChromosomes = new ArrayList();
        newChromosomes.add(new1);
        newChromosomes.add(new2);
        
        return newChromosomes;
    }
    
}
