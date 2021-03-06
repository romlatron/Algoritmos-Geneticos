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
public class Helmet extends Item
{    
    public Helmet(int id, double strength, double agility, double skill, double resistance, double life) 
    {
        super(id, strength, agility, skill, resistance, life);
    }
    
    @Override
    public String getType() {
        return "HELMET";
    }
    
    @Override
    public boolean equals (Object o) {
        if (!(o instanceof Helmet)) return false;
        Helmet i = (Helmet) o;
        return i.id == this.id;
    }
    
}
