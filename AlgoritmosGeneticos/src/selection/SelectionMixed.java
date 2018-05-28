/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selection;

import algoritmosgeneticos.Chromosome;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class SelectionMixed implements Selection {
    private int take;
    private Selection methodA;
    private Selection methodB;
    private double probability;

    public SelectionMixed (Selection methodA, Selection methodB, double probability, int take) {
        this.methodA = methodA;
        this.methodB = methodB;
        this.probability = probability;
        this.take = take;
        
        // Define the amount of chromosomes each method should take
        int takeA = (int) (probability * this.take);
        int takeB = this.take - (int) (probability * this.take);
        methodA.setTake(takeA);
        methodB.setTake(takeB);
    }

    @Override
    public void next () {}

    @Override
    public void setTake (int take) {
        this.take = take;
    }

    @Override
    public List<Chromosome> apply (List<Chromosome> chromosomes) {
        // TODO: Check this method. Is it OK? Specially the remove elements part (Should I do that?).
        List<Chromosome> selected = new ArrayList<>();
        
        // Select chromosomes through method A
        selected.addAll(methodA.apply(chromosomes));
        
        // Select chromosomes through method B
        selected.addAll(methodB.apply(chromosomes));

        return selected;
    }    
}
