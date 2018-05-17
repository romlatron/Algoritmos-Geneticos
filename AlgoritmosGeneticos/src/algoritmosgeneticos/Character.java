/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

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
    
    //protected List<Item> items;

    public Character() {}
    
    public void updateATMDEM()
    {
        ATM = 0.5 - Math.pow((3 * height - 5), 4) + Math.pow((3 * height - 5), 2) + height/2;
        DEM = 2 + Math.pow((3 * height - 5), 4) - Math.pow((3 * height - 5), 2) + height/2;
    }
    
    public void updateAttDef()
    {
        attack = (agility + skill) * strength * ATM;
        defense = (resistance + skill) * life * DEM;
    }
    
    public void updateRating() {}
}
