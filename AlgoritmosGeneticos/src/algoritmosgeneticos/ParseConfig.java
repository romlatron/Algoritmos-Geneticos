/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import Files.FileReader;
import algoritmosgeneticos.character.*;
import algoritmosgeneticos.item.*;
import crossover.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import mutation.*;
import replacement.*;
import selection.*;
import stop_condition.*;

/**
 *
 * @author v1nkey
 */
public class ParseConfig 
{
    private static ParseConfig pc;
    
    //Config file attributes
    private int populationSize;
    
    //Character
    private String characterRole;
    private int characterType;
    
    //Crossover
    private String crossoverType;
    private double crossoverProb;
    private double crossoverProbCruce;
    
    //Mutation
    private String mutationType;
    private double mutationProb;
    private double mutationAlpha;
    
    //Selection
    private String selectionType;
    private int selectionTake;
    private String selectionMethodA;
    private int selectionValue2A;
    private String selectionMethodB;
    private int selectionValue2B;
    private double selectionMixedProba;
    
    //Replacement
    private String replacementType;
    private int replacementValue;
    private String replacementSelection;
    private int replacementSelectionTake;
    private String replacementSelectionMethodA;
    private int replacementSelectionValue2A;
    private String replacementSelectionMethodB;
    private int replacementSelectionValue2B;
    private double replacementSelectionMixedProba;
    
    //Stop condition
    private String stopCondition;
    private int stopConditionValue;
    private double scOptimumLevel;
    
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
        
        populationSize = Integer.valueOf("populationSize");
        
        //Character
        characterRole = prop.getProperty("character.role");
        characterType = Integer.valueOf(prop.getProperty("character.type"));
        
        //Crossover
        crossoverType = prop.getProperty("crossover.type");
        crossoverProb = Double.valueOf(prop.getProperty("crossover.prob"));
        crossoverProbCruce = Double.valueOf(prop.getProperty("crossover.probCruce"));
        
        //Mutation
        mutationType = prop.getProperty("mutation.type");
        mutationProb = Double.valueOf(prop.getProperty("mutation.prob"));
        mutationAlpha = Double.valueOf(prop.getProperty("mutation.alpha"));
        
        //Selection
        selectionType = prop.getProperty("selection.type");
        selectionTake = Integer.valueOf(prop.getProperty("selection.take"));
        //Method A in case of mixed selection
        selectionMethodA = prop.getProperty("selection.methodA");
        selectionValue2A = Integer.valueOf(prop.getProperty("selection.value2A"));
        //Method B in case of mixed selection (if not, we only consider type, takeA and value2A
        selectionMethodB = prop.getProperty("selection.methodB");
        selectionValue2B = Integer.valueOf(prop.getProperty("selection.value2B"));
        //Probability in case of mixed selection
        selectionMixedProba = Double.valueOf(prop.getProperty("selection.proba"));
        
        //Replacement
        replacementType = prop.getProperty("replacement.type");
        replacementValue = Integer.valueOf(prop.getProperty("replacement.value"));
        //Replacement selection
        replacementSelection = prop.getProperty("replacement.selection.type");
        replacementSelectionTake = Integer.valueOf(prop.getProperty("replacement.selection.take"));
        //Method A in case of mixed selection
        replacementSelectionMethodA = prop.getProperty("replacement.selection.methodA");
        replacementSelectionValue2A = Integer.valueOf(prop.getProperty("replacement.selection.value2A"));
        //Method B in case of mixed selection
        replacementSelectionMethodB = prop.getProperty("replacement.selection.methodB");
        replacementSelectionValue2B = Integer.valueOf(prop.getProperty("replacement.selection.value2B"));
        //Probability in case of mixed selection
        replacementSelectionMixedProba = Double.valueOf(prop.getProperty("replacement.selection.proba"));
        
        //Stop condition
        stopCondition = prop.getProperty("stopCondition.type");
        stopConditionValue = Integer.valueOf(prop.getProperty("stopCondition.value"));
        scOptimumLevel = Double.valueOf(prop.getProperty("stopCondition.optimum.level"));
        
        //Read items from item files (*.tsv)
        FileReader fileReader = new FileReader();
        boots = fileReader.readItems("boots.tsv", "BOOTS");
        gloves = fileReader.readItems("gloves.tsv", "GLOVES");
        armor = fileReader.readItems("armor.tsv", "ARMOR");
        weapon = fileReader.readItems("weapon.tsv", "WEAPON");
        helmet = fileReader.readItems("helmet.tsv", "HELMET");
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
    
    //Selecting the right functions
    public Crossover selectCrossover()
    {
        Crossover crossover = null;
        switch(crossoverType.toLowerCase())
        {
            case "onepoint":
                crossover = new OnePointCrossover(crossoverProb);
                break;
                
            case "twopoints":
                crossover = new TwoPointsCrossover(crossoverProb);
                break;
                
            case "uniform":
                crossover = new UniformCrossover(crossoverProb, crossoverProbCruce);
                break;
                
            case "anular":
                crossover = new AnnularCrossover(crossoverProb);
                break;
                
            default:
                break;
        }
        return crossover;
    }
    
    public Mutation selectMutation() throws IOException
    {
        Mutation mutation = null;
        switch(mutationType.toLowerCase())
        {
            case "uniform":
                mutation = new BitMutation(mutationProb);
                break;
                
            case "nouniform":
                mutation = new BitMutation(mutationProb, mutationAlpha);
                break;
                
            default:
                break;
        }
        return mutation;
    }
    
    private Selection selectSelection(String type, int take, int value)
    {
        Selection selection = null;
        switch(type.toLowerCase())
        {
            case "boltzmann":
                selection = new SelectionBoltzmann(take, value);
                break;
                
            case "determinist":
                selection = new SelectionDeterministTournament(take, value);
                break;
                
            case "elite":
                selection = new SelectionElite(take);
                break;
                
            case "probabilistic":
                selection = new SelectionProbabilisticTournament(take);
                break;
                
            case "ranking":
                selection = new SelectionRanking(take, value);
                break;
                
            case "roulette":
                selection = new SelectionRoulette(take);
                break;
                
            case "universal":
                selection = new SelectionUniversal(take);
                break;
                
            default :
                break;
        }
        return selection;
    }
    private SelectionMixed createSelectionMixed(String selectionA, int value2A, String selectionB, int value2B, int take, double proba)
    {
        Selection methodA = selectSelection(selectionA, take, value2A);
        Selection methodB = selectSelection(selectionB, take, value2B);
        
        return new SelectionMixed(methodA, methodB, proba, take);
    }
    
    public Selection selectSelection()
    {
        Selection selection = null;
        switch(selectionType.toLowerCase())
        {
            case "boltzmann":
                selection = new SelectionBoltzmann(selectionTake, selectionValue2A);
                break;
                
            case "determinist":
                selection = new SelectionDeterministTournament(selectionTake, selectionValue2A);
                break;
                
            case "elite":
                selection = new SelectionElite(selectionTake);
                break;
                
            case "mixed":
                selection = createSelectionMixed(selectionMethodA, selectionValue2A, selectionMethodB, selectionValue2B, selectionTake, selectionMixedProba);
                break;
                
            case "probabilistic":
                selection = new SelectionProbabilisticTournament(selectionTake);
                break;
                
            case "ranking":
                selection = new SelectionRanking(selectionTake, selectionValue2A);
                break;
                
            case "roulette":
                selection = new SelectionRoulette(selectionTake);
                break;
                
            case "universal":
                selection = new SelectionUniversal(selectionTake);
                break;
                
            default :
                break;
        }
        return selection;
    }
    
    public Replacement selectReplacement()
    {
        Replacement replacement = null;
        Selection rSelection = null;
        if (replacementSelection.equals("mixed"))
            rSelection = createSelectionMixed(replacementSelectionMethodA, replacementSelectionValue2A, replacementSelectionMethodB, replacementSelectionValue2B, replacementSelectionTake, replacementSelectionMixedProba);
        else
            rSelection = selectSelection(replacementSelection, replacementSelectionTake, replacementSelectionValue2A);
        
        switch(replacementType)
        {
            case "mix":
                replacement = new ReplaceMixMutated(rSelection);
                break;
                
            case "k":
                replacement = new ReplaceKMutated(rSelection);
                break;
                
            case "all":
                replacement = new ReplaceAllMutated();
                break;
                
            default :
                break;
        }
        return replacement;
    }
    
    public StopCondition selectStopCondition()
    {
        StopCondition sc = null;
        switch(stopCondition.toLowerCase())
        {
            case "max" :
                sc = new MaximumGenCondition(stopConditionValue);
                break;
                
            case "content":
                sc = new ContentCondition(stopConditionValue);
                break;
                
            case "optimum" :
                sc = new SolucionOptimaCondition(scOptimumLevel);
                break;
                
            case "structure" :
                break;
                
            default :
                break;
        }
        return sc;
    }

    //Items getter
    public List<Item> getBoots() { return boots; }
    public List<Item> getGloves() { return gloves; }
    public List<Item> getArmor() { return armor; }
    public List<Item> getWeapon() { return weapon; }
    public List<Item> getHelmet() { return helmet; }
}
