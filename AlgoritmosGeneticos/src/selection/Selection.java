/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selection;

import algoritmosgeneticos.Chromosome;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface Selection {
    public List<Chromosome> apply (List <Chromosome> chromosomes);
    public void next();
    public void setTake(int n);
}
