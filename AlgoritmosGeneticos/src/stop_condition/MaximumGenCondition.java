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
public class MaximumGenCondition implements StopCondition{

    int i, maxNumber;
    
    public MaximumGenCondition(int i, int n) {
        this.i = i;
        this.maxNumber = n;
    }
    
    @Override
    public boolean stop(List<Chromosome> generation) {
        i++;
        if (i == maxNumber) return true;
        return false;
    }
    
}
