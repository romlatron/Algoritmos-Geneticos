/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos.item;

/**
 *
 * @author v1nkey
 */
public class Item 
{
    protected int id;
    public double strength;
    public double agility;
    public double skill;
    public double resistance;
    public double life;

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