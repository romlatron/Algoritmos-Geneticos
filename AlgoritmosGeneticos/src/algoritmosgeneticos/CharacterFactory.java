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
public class CharacterFactory
{
    public CharacterFactory() {}
    
    public Character createCharacter(String character, int typeOfChar) 
    {
        switch(character.toLowerCase())
        {
            case "archer":
                return new Archer(typeOfChar);
                
            case "assassin":
                return new Assassin(typeOfChar);
                
            case "defender":
                return new Defender(typeOfChar);
                
            case "warrior":
                return new Warrior(typeOfChar);
                
            default:
                return null;
        }
    }
}
