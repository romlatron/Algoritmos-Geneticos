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
import java.io.FileInputStream;
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
    private String replacementSelection;
    private String replacementSelectionMethodA;
    private int replacementSelectionValue2A;
    private String replacementSelectionMethodB;
    private int replacementSelectionValue2B;
    private double replacementSelectionMixedProba;
    
    //Stop condition
    private String stopCondition;
    private int stopConditionValue;
    private int structureConditionValue;
    private double scOptimumLevel;
    
    //Item files attributes
    private List<Item> boots;
    private List<Item> gloves;
    private List<Item> armor;
    private List<Item> weapon;
    private List<Item> helmet;
    
    public void loadConfig(String fileName) throws IOException {
        //Read configuration from configuration file (config.properties)
        Properties prop = new Properties();
        
        FileInputStream fis = new FileInputStream(fileName);
        if (fis != null)
            prop.load(fis);
        else
            throw new FileNotFoundException("config file '" + fileName + "'not found");
        
        //Character
        populationSize = Integer.valueOf(prop.getProperty("populationSize"));
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
        //Replacement selection
        replacementSelection = prop.getProperty("replacement.selection.type");
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
        structureConditionValue = Integer.valueOf(prop.getProperty("stopCondition.structure.changement"));
        
        fis.close();
    }
    
    private ParseConfig(String fileName) throws IOException
    {
        loadConfig(fileName);
        
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
            return pc = new ParseConfig(fileName);
        
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

            Chromosome chromosome = new Chromosome(c, items, Math.random() * 0.7 + 1.3);
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
    
    private Selection selectSelection(String type, int value)
    {
        Selection selection = null;
        switch(type.toLowerCase())
        {
            case "boltzmann":
                System.out.println("Replacement Boltzmann");
                selection = new SelectionBoltzmann(value);
                break;
                
            case "determinist":
                System.out.println("Replacement Determinist");
                selection = new SelectionDeterministTournament(value);
                break;
                
            case "elite":
                System.out.println("Replacement Elite");
                selection = new SelectionElite();
                break;
                
            case "probabilistic":
                System.out.println("Replacement Proba");
                selection = new SelectionProbabilisticTournament();
                break;
                
            case "ranking":
                System.out.println("Replacement Ranking");
                selection = new SelectionRanking();
                break;
                
            case "roulette":
                System.out.println("Replacement Roulette");
                selection = new SelectionRoulette();
                break;
                
            case "universal":
                System.out.println("Replacement Universal");
                selection = new SelectionUniversal();
                break;
                
            default :
                break;
        }
        return selection;
    }
    
    private Selection selectSelection(String type, int take, int value)
    {
        Selection s = selectSelection(type, value);
        s.setTake(take);
        
        return s;
    }
    
    private SelectionMixed createSelectionMixed(String selectionA, int value2A, String selectionB, int value2B, double proba)
    {
        Selection methodA = selectSelection(selectionA, value2A);
        Selection methodB = selectSelection(selectionB, value2B);
        
        return new SelectionMixed(methodA, methodB, proba);
    }
    
    private SelectionMixed createSelectionMixed(String selectionA, int value2A, String selectionB, int value2B, int take, double proba)
    {
        Selection methodA = selectSelection(selectionA, value2A);
        Selection methodB = selectSelection(selectionB, value2B);
        
        return new SelectionMixed(methodA, methodB, proba, take);
    }
    
    public Selection selectSelection()
    {
        Selection selection = null;
        switch(selectionType.toLowerCase())
        {
            case "boltzmann":
                System.out.println("Selection Boltzmann");
                selection = new SelectionBoltzmann(selectionTake, selectionValue2A);
                break;
                
            case "determinist":
                System.out.println("Selection Determinist");
                selection = new SelectionDeterministTournament(selectionTake, selectionValue2A);
                break;
                
            case "elite":
                System.out.println("Selection Elite");
                selection = new SelectionElite(selectionTake);
                break;
                
            case "mixed":
                System.out.println("Selection Mixed");
                selection = createSelectionMixed(selectionMethodA, selectionValue2A, selectionMethodB, selectionValue2B, selectionTake, selectionMixedProba);
                break;
                
            case "probabilistic":
                System.out.println("Selection Proba");
                selection = new SelectionProbabilisticTournament(selectionTake);
                break;
                
            case "ranking":
                System.out.println("Selection Ranking");
                selection = new SelectionRanking(selectionTake);
                break;
                
            case "roulette":
                System.out.println("Selection Roulette");
                selection = new SelectionRoulette(selectionTake);
                break;
                
            case "universal":
                System.out.println("Selection Universal");
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
            rSelection = createSelectionMixed(replacementSelectionMethodA, replacementSelectionValue2A, replacementSelectionMethodB, replacementSelectionValue2B, replacementSelectionMixedProba);
        else
            rSelection = selectSelection(replacementSelection, replacementSelectionValue2A);
        
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
                sc = new OptimalSolutionCondition(scOptimumLevel);
                break;
                
            case "structure" :
                sc = new StructureCondition(structureConditionValue, stopConditionValue);
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
