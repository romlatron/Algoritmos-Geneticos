/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author v1nkey
 */
public class Character 
{
    protected double strengthRatio;
    protected double agilityRatio;
    protected double skillRatio;
    protected double resistanceRatio;
    protected double lifeRatio;
    
    protected double strength;
    protected double agility;
    protected double skill;
    protected double resistance;
    protected double life;
    protected double height;
    
    protected double ATM;
    protected double DEM;
    
    protected double attack;
    protected double defense;
    
    protected double rating;
    
    protected List<Item> items = new ArrayList<>();

    public Character() {}
    
    private void updateATMDEM()
    {
        ATM = 0.5 - Math.pow((3 * height - 5), 4) + Math.pow((3 * height - 5), 2) + height/2;
        DEM = 2 + Math.pow((3 * height - 5), 4) - Math.pow((3 * height - 5), 2) + height/2;
    }
    
    private void updateAttDef()
    {
        attack = (agility + skill) * strength * ATM;
        defense = (resistance + skill) * life * DEM;
    }
    

    double calcularFitness(Chromosome chromosome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void updateRating() 
    {
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
        
        strength = 100 * Math.tanh(0.01 * strengthItem * strengthRatio);
        agility = Math.tanh(0.01 * agilityItem * agilityRatio);
        skill = 0.6 * Math.tanh(0.01 * skillItem * skillRatio);
        resistance = 100 * Math.tanh(0.01 * resistanceItem * resistanceRatio);
        life = 100 * Math.tanh(0.01 * lifeItem * lifeRatio);
        
        updateATMDEM();
        updateAttDef();
    }
}
