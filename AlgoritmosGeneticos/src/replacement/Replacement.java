/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replacement;

import algoritmosgeneticos.Chromosome;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface Replacement {
    public List <Chromosome> apply (List <Chromosome> mutated, List <Chromosome> oldGeneration);
}
