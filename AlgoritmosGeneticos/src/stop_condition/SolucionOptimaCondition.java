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
public class SolucionOptimaCondition implements StopCondition {

    private double level;
    
    public SolucionOptimaCondition (double level) {
        this.level = level;
    }
    
    @Override
    public boolean stop(List<Chromosome> generation) {
        for (Chromosome chromosome : generation) {
            if (chromosome.getFitness() >= level) {
                return true;
            }
        }
        return false;
    }
    
}
