/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stop_condition;

import algoritmosgeneticos.Chromosome;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class StructureCondition implements StopCondition{
    
    int n; //the number of times the structure does not change in a row
    int maxN; // the maximum number of n before we stop the algorithm
    int changement; //the maximum number of same chromosomes to not consider a changement
    List<Chromosome> previousGeneration;
            
    public StructureCondition (int changement, int maxN) {
        n = 0;
        this.changement = changement;
        this.maxN = maxN;
    }
    
    @Override
    public boolean stop(List<Chromosome> generation) {
        int notChanged = 0;
        if (previousGeneration == null) return false;
        for (Chromosome chromosome : generation) {
            for (Chromosome prevChromosome : previousGeneration) {
                if (chromosome.equals(prevChromosome)) {
                    if (++notChanged > changement) {
                        if (++n == maxN) return true;
                        this.previousGeneration = generation;
                        return false;
                    }
                    previousGeneration.remove(prevChromosome);
                    break;
                }
            }
        }
        n = 0;
        return false;
    }
}
