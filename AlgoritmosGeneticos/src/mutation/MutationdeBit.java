/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutation;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.*;
import java.util.List;

/**
 *
 * @author Acer
 */
public class MutationdeBit implements Mutation{

    double prob;
    double alpha; //for uniform mutation just set alpha to 1
    List <Item> repo;
    List <Item> boots;
    List <Item> gloves;
    List <Item> helmets;
    List <Item> armors;
    List <Item> weapons;
    
    public MutationdeBit (double prob, double alpha) {
        this.prob = prob;
        this.alpha = alpha;
     /*   boots = ParseConfig.getInstance().getBoots();
        gloves = ParseConfig.getInstance().getGloves();
        helmets = ParseConfig.getInstance().getHelmets();
        armors = ParseConfig.getInstance().getArmors();
        weapons = ParseConfig.getInstance().getWeapons();*/
        
    }
    
    @Override
    public List<Chromosome> apply(List<Chromosome> chromosomes) {
        for (Chromosome chromosome : chromosomes) {
            for (Item item : chromosome.items) {
                if (Math.random() < prob) {
                    if (item instanceof Boot)
                        item = boots.get((int) (boots.size() * Math.random()));
                    
                    else if (item instanceof Glove)
                        item = boots.get((int) (gloves.size() * Math.random()));
                    
                    else if (item instanceof Helmet)
                        item = boots.get((int) (helmets.size() * Math.random()));
                    
                    else if (item instanceof Armor)
                        item = boots.get((int) (armors.size() * Math.random()));
                    
                    else
                        item = boots.get((int) (weapons.size() * Math.random()));
                }
            }
            if (Math.random() < prob)
                chromosome.height = Math.random() * 0.7 + 1.3;
        }
        prob = alpha * prob;
        return chromosomes;
    }    
}
