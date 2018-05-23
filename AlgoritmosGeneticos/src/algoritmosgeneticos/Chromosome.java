/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import algoritmosgeneticos.character.Character;
import algoritmosgeneticos.item.Item;
import java.util.List;

/**
 *
 * @author Acer
 */
public class Chromosome implements Comparable<Chromosome> {
    public List <Item> items;
    public double height;

    public Character character;
    
    public double getFitness () {
        return character.calcularFitness(this);
    }

    @Override
    public int compareTo(Chromosome c) {
        // Greater comes first
        double diff = this.getFitness() - c.getFitness();

        if (diff == 0) return 0;
        else if (diff > 0) return -1;
        else return +1;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        Chromosome c = (Chromosome) o;

        if (c.height != this.height) return false;

        for (Item i: c.items) if (!this.items.contains(i)) return false;
        for (Item i: this.items) if (!c.items.contains(i)) return false;

        return true;
    }
    
    public Chromosome (Character character, List <Item> items, double height) {
        this.character = character;
        this.items = items;
        this.height = height;
    }
}
