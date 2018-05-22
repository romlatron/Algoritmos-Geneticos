/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reemplazo;

import algoritmosgeneticos.Chromosome;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface Reemplazo {
    public List <Chromosome> apply (List <Chromosome> chromosomes);
}
