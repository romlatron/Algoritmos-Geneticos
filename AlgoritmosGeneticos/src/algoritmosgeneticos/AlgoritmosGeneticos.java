/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import Files.FileReader;
import algoritmosgeneticos.character.Defender;
import algoritmosgeneticos.item.Item;
import java.util.ArrayList;
import java.util.List;
import mutation.*;
import crossover.*;
import reemplazo.*;
import seleccion.*;;
import stop_condition.*;

/**
 *
 * @author v1nkey
 */
public class AlgoritmosGeneticos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {

        // Read items from files
        FileReader fileReader = new FileReader();
        List<Item> boots = fileReader.readItems("boots.tsv", "BOOTS");
        List<Item> gloves = fileReader.readItems("gloves.tsv", "GLOVES");
        List<Item> armor = fileReader.readItems("armor.tsv", "ARMOR");
        List<Item> weapon = fileReader.readItems("weapon.tsv", "WEAPON");
        List<Item> helmet = fileReader.readItems("helmet.tsv", "HELMET");

        // Create chromosomes
        List<Chromosome> chromosomes = new ArrayList<>();
        
        for (int i = 0; i < 20; i++) {
            List<Item> items = new ArrayList<>();
            
            items.add(boots.get((int) (Math.random() * boots.size())));
            items.add(gloves.get((int) (Math.random() * gloves.size())));
            items.add(armor.get((int) (Math.random() * armor.size())));
            items.add(weapon.get((int) (Math.random() * weapon.size())));
            items.add(helmet.get((int) (Math.random() * helmet.size())));

            Chromosome chromosome = new Chromosome(new Defender(2), items, Math.random() * 0.2 + 1);
            chromosomes.add(chromosome);
        }
        
        // Define methods
        Reemplazo reemplazar = new ReemplazoKMutados(new SeleccionElite(0)); // 10 is param, 0 is not important since it gets overwritten in replacement.
        Seleccion seleccionar = new SeleccionElite((int) (10)); // N is the total number of chromosomes. gap is a parameter between 0 and 1.
        Mutation mutar = new MutationdeBit(0.2, 1);
        Crossover recombinar = new OnePointCrossover(0.8);
        StopCondition condicionCorte = new MaximumGenCondition(20);

        // Iterate to stop condition
        Boolean rmt = false;
        while (!condicionCorte.stop(chromosomes)) {

            // If the replacement strategy is mutate all, do some extra logic
            if (rmt) {
                seleccionar.setTake(2);
                List<Chromosome> aux = new ArrayList<>();
                List<Chromosome> chroms = new ArrayList<>(chromosomes);
                
                do {
                    List<Chromosome> temp = reemplazar.apply(mutar.apply(recombinar.apply(seleccionar.apply(chromosomes))), chroms);

                    chroms.remove(temp.get(0));
                    chroms.remove(temp.get(1));
                    aux.addAll(temp);
                } while (aux.size() < chromosomes.size());
                chromosomes = aux;
            } else {
                chromosomes = reemplazar.apply(mutar.apply(recombinar.apply(seleccionar.apply(chromosomes))), chromosomes);
            }
        }
    }
    
}
