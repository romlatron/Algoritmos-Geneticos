/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import java.util.List;

/**
 *
 * @author Acer
 */
public class Chromosome {
    List <Object> items;
    double height;
    Character character;
    
    double getFitness () {
        return character.calcularFitness(this);
    }
}
