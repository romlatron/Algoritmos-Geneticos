/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos.character;

/**
 *
 * @author v1nkey
 */
public class Defender extends Character
{
    public Defender(int typeOfDefender)
    {
        switch(typeOfDefender)
        {
            case 1:
                strengthRatio = 1;
                agilityRatio = 0.9;
                skillRatio = 0.7;
                resistanceRatio = 1.2;
                lifeRatio = 1.1;
                break;
                
            case 2:
                strengthRatio = 1.1;
                agilityRatio = 0.8;
                skillRatio = 0.8;
                resistanceRatio = 1.1;
                lifeRatio = 1.1;
                break;
                
            case 3:
                strengthRatio = 0.9;
                agilityRatio = 0.9;
                skillRatio = 0.9;
                resistanceRatio = 1;
                lifeRatio = 1.3;
                break;
                
            default:
                break;
        }
        
        attackRatio = 0.1;
        defenseRatio = 0.9;       
    }
    
}
