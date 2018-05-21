/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossover;

import algoritmosgeneticos.Chromosome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Acer
 */
public abstract class CrossoverAbstract implements Crossover {

    
    public abstract List<Chromosome> apply(Chromosome c1, Chromosome c2);
    
    @Override
    public List <Chromosome> apply(List <Chromosome> chromosomes) {
        Collections.shuffle(chromosomes);
        List <Chromosome> newSons = new ArrayList<>();
        for (int i=0; i<chromosomes.size(); i+=2) {
            newSons.addAll(apply(chromosomes.get(i), chromosomes.get(i+1)));
        }
        return newSons;
    }
    
}
