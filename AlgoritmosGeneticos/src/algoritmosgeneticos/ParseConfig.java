/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import Files.FileReader;
import algoritmosgeneticos.character.*;
import algoritmosgeneticos.item.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author v1nkey
 */
public class ParseConfig 
{
    private static ParseConfig pc;
    
    //Config file attributes
    private int populationSize;
    
    private String characterRole;
    private int characterType;
    
    private String crossoverType;
    private Double crossoverValue;
    private String mutationType;
    private String selectionType;
    private String criteriaType;
    
    //Item files attributes
    private List<Item> boots;
    private List<Item> gloves;
    private List<Item> armor;
    private List<Item> weapon;
    private List<Item> helmet;
    
    private ParseConfig(String fileName) throws IOException
    {
        //Read configuration from configuration file (config.properties)
        Properties prop = new Properties();
        
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is != null)
            prop.load(is);
        else
            throw new FileNotFoundException("config file '" + fileName + "'not found");
        
        populationSize = Integer.valueOf(prop.getProperty("populationSize"));
        characterRole = prop.getProperty("character.role");
        characterType = Integer.valueOf(prop.getProperty("character.type"));
        crossoverType = prop.getProperty("crossover.type");
        crossoverValue = Double.valueOf(prop.getProperty("crossover.value"));
        mutationType = prop.getProperty("mutation.type");
        selectionType = prop.getProperty("selection.type");
        criteriaType = prop.getProperty("criteria");
        
        //Read items from item files (*.tsv)
        FileReader fileReader = new FileReader();
        boots = fileReader.readItems("botas.tsv", "BOOTS");
        gloves = fileReader.readItems("guantes.tsv", "GLOVES");
        armor = fileReader.readItems("pecheras.tsv", "ARMOR");
        weapon = fileReader.readItems("armas.tsv", "WEAPON");
        helmet = fileReader.readItems("cascos.tsv", "HELMET");
    }
    
    public static ParseConfig getInstance(String fileName) throws IOException
    {
        if (pc == null)
            return new ParseConfig(fileName);
        
        return pc;
    }
    
    public List<Chromosome> generatePopulation()
    {
        CharacterFactory cf = new CharacterFactory();
        List<Chromosome> chromosomes = new ArrayList<>();
        
        algoritmosgeneticos.character.Character c;
        c = cf.createCharacter(characterRole, characterType);
        
        for (int i = 0; i < populationSize; i++) {
            List<Item> items = new ArrayList<>();
            
            items.add(boots.get((int) (Math.random() * boots.size())));
            items.add(gloves.get((int) (Math.random() * gloves.size())));
            items.add(armor.get((int) (Math.random() * armor.size())));
            items.add(weapon.get((int) (Math.random() * weapon.size())));
            items.add(helmet.get((int) (Math.random() * helmet.size())));

            Chromosome chromosome = new Chromosome(c, items, Math.random() * 0.2 + 1);
            chromosomes.add(chromosome);
        }
        
        return chromosomes;
    }

    //Items getter
    public List<Item> getBoots() { return boots; }
    public List<Item> getGloves() { return gloves; }
    public List<Item> getArmor() { return armor; }
    public List<Item> getWeapon() { return weapon; }
    public List<Item> getHelmet() { return helmet; }
    
    //Algorithms config getter
    public int getPopulationSize() { return populationSize; }
    public String getCrossoverType() { return crossoverType; }
    public Double getCrossoverValue() { return crossoverValue; }
    public String getMutationType() { return mutationType; }
    public String getSelectionType() { return selectionType; }
    public String getCriteriaType() { return criteriaType; }
}
