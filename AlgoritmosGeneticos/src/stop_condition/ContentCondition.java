/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stop_condition;

import algoritmosgeneticos.Chromosome;
import java.util.List;

/**
 *
 * @author Acer
 */
public class ContentCondition implements StopCondition{
    
    int n; //the number of times the best fitness does not evolve in a row
    int maxN; // the maximum number of n before we stop the algorithm
    double bestFitness;
    
    public ContentCondition (int maxN) {
        bestFitness = 0;
        n = 0;
        this.maxN = maxN;
    }
    
    @Override
    public boolean stop(List<Chromosome> generation) {
        for (Chromosome chromosome : generation) {
            if (chromosome.getFitness() > this.bestFitness) {
                this.bestFitness = chromosome.getFitness();
                n = 0;
                return false;
            }
        }
        n++;
        return n==maxN ;
    }
}
