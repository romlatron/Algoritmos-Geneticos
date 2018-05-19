/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos.character;

import algoritmosgeneticos.Chromosome;
import algoritmosgeneticos.item.Item;
import java.util.List;

/**
 *
 * @author v1nkey
 */
public abstract class Character 
{
    protected double strengthRatio;
    protected double agilityRatio;
    protected double skillRatio;
    protected double resistanceRatio;
    protected double lifeRatio;
    
    protected double attackRatio;
    protected double defenseRatio;
        
    public double calcularFitness(Chromosome chromosome) {
        List <Item> items = chromosome.items;
        double height = chromosome.height;
        
        double strengthItem = 0;
        double agilityItem = 0;
        double skillItem = 0;
        double resistanceItem = 0;
        double lifeItem = 0;
        
        for (Item i : items)
        {
            strengthItem += i.strength;
            agilityItem += i.agility;
            skillItem += i.skill;
            resistanceItem += i.resistance;
            lifeItem += i.life;
        }
        
        double strength = 100 * Math.tanh(0.01 * strengthItem * strengthRatio);
        double agility = Math.tanh(0.01 * agilityItem * agilityRatio);
        double skill = 0.6 * Math.tanh(0.01 * skillItem * skillRatio);
        double resistance = Math.tanh(0.01 * resistanceItem * resistanceRatio);
        double life = 100 * Math.tanh(0.01 * lifeItem * lifeRatio);
        
        double ATM = 0.5 - Math.pow((3 * height - 5), 4) + Math.pow((3 * height - 5), 2) + height/2;
        double DEM = 2 + Math.pow((3 * height - 5), 4) - Math.pow((3 * height - 5), 2) - height/2;
        
        double attack = (agility + skill) * strength * ATM;
        double defense = (resistance + skill) * life * DEM;
        
        double fitness = attackRatio*attack + defenseRatio*defense;
        
        return fitness;
    }
    
}
