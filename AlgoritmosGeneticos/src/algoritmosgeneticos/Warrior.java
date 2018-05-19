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
public class Warrior extends Character 
{
    public Warrior(int typeOfWarrior)
    {
        switch(typeOfWarrior)
        {
            case 1:
                strengthRatio = 1.1;
                agilityRatio = 0.9;
                skillRatio = 0.8;
                resistanceRatio = 1;
                lifeRatio = 0.9;
                break;
                
            case 2:
                strengthRatio = 1.2;
                agilityRatio = 1;
                skillRatio = 0.8;
                resistanceRatio = 0.8;
                lifeRatio = 0.8;
                break;
                
            case 3:
                strengthRatio = 0.8;
                agilityRatio = 0.9;
                skillRatio = 0.9;
                resistanceRatio = 1.2;
                lifeRatio = 1.1;
                break;
                
            default:
                break;
        }
    }
    
    @Override
    public void updateRating() 
    { 
        super.updateRating();
        rating = 0.6 * attack + 0.4 * defense; 
    }
}
