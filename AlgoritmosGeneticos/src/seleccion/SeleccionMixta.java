/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleccion;

import algoritmosgeneticos.Chromosome;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class SeleccionMixta implements Seleccion {
    private int take;
    private Seleccion methodA;
    private Seleccion methodB;
    private double probability;

    public SeleccionMixta (Seleccion methodA, Seleccion methodB, double probability, int take) {
        this.methodA = methodA;
        this.methodB = methodB;
        this.probability = probability;
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
        List<Chromosome> chromCopy = new ArrayList<>(chromosomes);

        // Define the amount of chromosomes each method should take
        methodA.setTake((int) (probability * this.take));
        methodB.setTake(this.take - (int) (probability * this.take));

        // Select chromosomes through method A
        selected.addAll(methodA.apply(chromosomes));

        // Select chromosomes through method B
        selected.addAll(methodB.apply(chromCopy));

        return selected;
    }    
}
