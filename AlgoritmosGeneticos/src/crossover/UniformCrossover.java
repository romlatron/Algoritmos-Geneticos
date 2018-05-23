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
public class UniformCrossover  extends CrossoverAbstract {

    double probCruce;
    
    public UniformCrossover (double prob, double probCruce) {
        super(prob);
        this.probCruce = probCruce;
    }
    
    @Override
    public List <Chromosome> apply(Chromosome c1, Chromosome c2) {
        List <Item> items1 = new ArrayList();
        List <Item> items2 = new ArrayList();
        for (int i=0; i<5; i++) {
            if (Math.random() < probCruce) { //if rand is smaller than p then we cross
                items1.add(c2.items.get(i));
                items2.add(c1.items.get(i));
            }
            else {
                items1.add(c1.items.get(i));
                items2.add(c2.items.get(i));
            }
        }
        double height1, height2;
        
        if (Math.random() < probCruce) {
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
