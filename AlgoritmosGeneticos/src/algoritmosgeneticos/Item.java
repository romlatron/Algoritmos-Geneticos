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
public class Item 
{
    protected int id;
    protected double strength;
    protected double agility;
    protected double skill;
    protected double resistance;
    protected double life;

    public Item(int id, double strength, double agility, double skill, double resistance, double life) 
    {
        this.id = id;
        this.strength = strength;
        this.agility = agility;
        this.skill = skill;
        this.resistance = resistance;
        this.life = life;
    }
}