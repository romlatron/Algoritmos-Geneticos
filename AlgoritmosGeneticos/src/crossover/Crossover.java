/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crossover;

import algoritmosgeneticos.Chromosome;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface Crossover {
    public List <Chromosome> apply (List <Chromosome> chromosomes);
    
}
