/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutation;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.ParseConfig;
import algoritmosgeneticos.item.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Acer
 */
public class BitMutation implements Mutation{

    double prob;
    double alpha; //for uniform mutation just set alpha to 1
    List <Item> boots;
    List <Item> gloves;
    List <Item> helmets;
    List <Item> armors;
    List <Item> weapons;
    
    public BitMutation (double prob) throws IOException {
        this.prob = prob;
        this.alpha = 1;
        boots = ParseConfig.getInstance("config/config.properties").getBoots();
        gloves = ParseConfig.getInstance("config/config.properties").getGloves();
        helmets = ParseConfig.getInstance("config/config.properties").getHelmet();
        armors = ParseConfig.getInstance("config/config.properties").getArmor();
        weapons = ParseConfig.getInstance("config/config.properties").getWeapon();        
    }
    
    public BitMutation (double prob, double alpha) throws IOException {
        this.prob = prob;
        this.alpha = alpha;
        boots = ParseConfig.getInstance("config/config.properties").getBoots();
        gloves = ParseConfig.getInstance("config/config.properties").getGloves();
        helmets = ParseConfig.getInstance("config/config.properties").getHelmet();
        armors = ParseConfig.getInstance("config/config.properties").getArmor();
        weapons = ParseConfig.getInstance("config/config.properties").getWeapon();        
    }
    
    @Override
    public List<Chromosome> apply(List<Chromosome> chromosomes) {
        for (Chromosome chromosome : chromosomes) {
            for (Item item : chromosome.items) {
                if (Math.random() < prob) {
                    if (item instanceof Boot)
                        Collections.replaceAll (chromosome.items, item, boots.get((int) (boots.size() * Math.random())));
                    
                    else if (item instanceof Glove)
                        Collections.replaceAll (chromosome.items, item, gloves.get((int) (gloves.size() * Math.random())));
                    
                    else if (item instanceof Helmet)
                        Collections.replaceAll (chromosome.items, item, helmets.get((int) (helmets.size() * Math.random())));
                    
                    else if (item instanceof Armor)
                        Collections.replaceAll (chromosome.items, item, armors.get((int) (armors.size() * Math.random())));
                    
                    else
                        Collections.replaceAll (chromosome.items, item, weapons.get((int) (weapons.size() * Math.random())));
                }
            }
            if (Math.random() < prob)
                chromosome.height = Math.random() * 0.7 + 1.3;
        }
        prob = alpha * prob;
        return chromosomes;
    }    
}
