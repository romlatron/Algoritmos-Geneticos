/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleccion;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.Item;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Acer
 */
public class SeleccionRuleta implements Seleccion<Chromosome> {
    private int take;
    private Seleccion methodA;
    private Seleccion methodB;
    private double probability;

    public SeleccionRuleta (Seleccion methodA, Seleccion methodB, double probability, int take) {
        this.methodA = methodA;
        this.methodB = methodB;
        this.probA = probability;
        this.take = take;
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
        List<Chromosome> chromCopy = chromosomes.clone();

        // Define the amount of chromosomes each method should take
        methodA.setTake((int) (probability * this.take));
        methodB.setTake(this.take - (int) (probability * this.take));

        // Select first chromosomes
        selected.addAll(methodA.apply(chromosomes));

        // Remove already selected chromosomes
        for (Chromosome s: selected) chromCopy.remove(s);

        // Select last chromosomes
        selected.addAll(methodB.apply(chromCopy));

        return selected;
    }    
}
