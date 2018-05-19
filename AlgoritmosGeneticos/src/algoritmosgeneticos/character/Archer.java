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
public class Archer extends Character
{
    public Archer(int typeOfArcher)
    {
        switch(typeOfArcher)
        {
            case 1:
                strengthRatio = 0.8;
                agilityRatio = 1.1;
                skillRatio = 1.1;
                resistanceRatio = 0.9;
                lifeRatio = 0.7;
                break;
                
            case 2:
                strengthRatio = 0.9;
                agilityRatio = 1.1;
                skillRatio = 1;
                resistanceRatio = 0.9;
                lifeRatio = 0.8;
                break;
                
            case 3:
                strengthRatio = 0.8;
                agilityRatio = 0.8;
                skillRatio = 0.8;
                resistanceRatio = 1.1;
                lifeRatio = 1.2;
                break;
                
            default:
                break;
        }
        
        attackRatio = 0.9;
        defenseRatio = 0.1;       
    }
}
