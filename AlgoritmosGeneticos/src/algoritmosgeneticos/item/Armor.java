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
public class Armor extends Item
{    
    public Armor(int id, double strength, double agility, double skill, double resistance, double life) 
    {
        super(id, strength, agility, skill, resistance, life);
    }
    
    @Override
    public String getType() {
        return "ARMOR";
    }
    
    @Override
    public boolean equals (Object o) {
        if (!(o instanceof Armor)) return false;
        Armor i = (Armor) o;
        return i.id == this.id;
    }
}
