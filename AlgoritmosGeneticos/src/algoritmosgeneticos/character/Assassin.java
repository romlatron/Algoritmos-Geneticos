/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos.character;

import algoritmosgeneticos.Chromosome;

/**
 *
 * @author v1nkey
 */
public class Assassin extends Character 
{
    public Assassin(int typeOfAssassin)
    {
        switch(typeOfAssassin)
        {
            case 1:
                strengthRatio = 0.8;
                agilityRatio = 1.2;
                skillRatio = 1.1;
                resistanceRatio = 1;
                lifeRatio = 0.8;
                break;
                
            case 2:
                strengthRatio = 0.9;
                agilityRatio = 1;
                skillRatio = 1.1;
                resistanceRatio = 1.0;
                lifeRatio = 0.9;
                break;
                
            case 3:
                strengthRatio = 0.9;
                agilityRatio = 0.9;
                skillRatio = 1;
                resistanceRatio = 1.1;
                lifeRatio = 1;
                break;
                
            default:
                break;
        }
        
        attackRatio = 0.7;
        defenseRatio = 0.3;       
    }
    
}
