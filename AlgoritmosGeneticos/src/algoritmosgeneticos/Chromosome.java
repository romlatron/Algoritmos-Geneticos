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
public class Chromosome {
    public List <Item> items;
    public double height;
    Character character;
    
    double getFitness () {
        return character.calcularFitness(this);
    }
    
    public Chromosome (Character character, List <Item> items) {
        this.character = character;
        this.items = items;
        height = Math.random()*0.7 + 1.3;
    }
}
