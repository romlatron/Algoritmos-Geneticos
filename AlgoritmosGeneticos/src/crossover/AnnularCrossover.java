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
public class AnnularCrossover extends CrossoverAbstract {

    public AnnularCrossover(double prob) {
        super(prob);
    }

    @Override
    public List <Chromosome> apply(Chromosome c1, Chromosome c2) {
        int rand = (int)(Math.random()*6);
        int l = (int)(Math.random()*3) + 1;
        List <Item> items1 = new ArrayList();
        List <Item> items2 = new ArrayList();
        double height1, height2;
                
        if (rand+l < 5) {
            for (int i=0; i < rand; i++) {
                items1.add(c1.items.get(i));        
                items2.add(c2.items.get(i));
            }
            for (int i=rand; i < rand + l; i++) { //aqui cruzamos
                items1.add(c2.items.get(i));        
                items2.add(c1.items.get(i));
            }
            for (int i=rand + l; i < 5; i++) {
                items1.add(c1.items.get(i));        
                items2.add(c2.items.get(i));
            }
            height1 = c1.height;
            height2 = c2.height;
        }
        else {
            for (int i=rand; i<5; i++) {
                items1.add(c2.items.get(i));
                items2.add(c1.items.get(i));
            }
            height1 = c2.height;
            height2 = c1.height;
            for (int i=0; i < l-(5-rand); i++) {
                items1.add(c2.items.get(i));
                items2.add(c1.items.get(i));
            }
            for (int i = l-(5-rand); i < rand; i++) {
                items1.add(c1.items.get(i));        
                items2.add(c2.items.get(i));
            }
        }
        Chromosome new1 = new Chromosome (c1.character, items1, height1);
        Chromosome new2 = new Chromosome (c1.character, items2, height2);
        List <Chromosome> newChromosomes = new ArrayList();
        newChromosomes.add(new1);
        newChromosomes.add(new2);
        
        return newChromosomes;
    }
    
}
