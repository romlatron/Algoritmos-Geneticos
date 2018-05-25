/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos;

import selection.SelectionElite;
import replacement.ReplaceKMutated;
import mutation.*;
import crossover.*;
;
import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import replacement.Replacement;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import replacement.Replacement;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import selection.Selection;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import replacement.Replacement;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import replacement.Replacement;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import replacement.Replacement;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import replacement.Replacement;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import selection.Selection;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import replacement.Replacement;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import replacement.Replacement;import stop_condition.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author v1nkey
 */
public class AlgoritmosGeneticos {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        ParseConfig pc = ParseConfig.getInstance("config.properties");
        List<Chromosome> chromosomes = pc.generatePopulation();
        
        // Define methods
        Crossover crossover = pc.selectCrossover();
        Mutation mutation = pc.selectMutation();
        StopCondition stopCondition = pc.selectStopCondition();
        
        
        Replacement reemplazar = new ReplaceKMutated(new SelectionElite(0)); // 10 is param, 0 is not important since it gets overwritten in replacement.
        Selection seleccionar = new SelectionElite((int) (10)); // N is the total number of chromosomes. gap is a parameter between 0 and 1.
        Crossover recombinar = new OnePointCrossover(0.8);

        // Iterate to stop condition
        Boolean rmt = false;
        while (!stopCondition.stop(chromosomes)) {

            // If the replacement strategy is mutate all, do some extra logic
            if (rmt) {
                seleccionar.setTake(2);
                List<Chromosome> aux = new ArrayList<>();
                List<Chromosome> chroms = new ArrayList<>(chromosomes);
                
                do {
                    List<Chromosome> temp = reemplazar.apply(mutation.apply(recombinar.apply(seleccionar.apply(chromosomes))), chroms);

                    chroms.remove(temp.get(0));
                    chroms.remove(temp.get(1));
                    aux.addAll(temp);
                } while (aux.size() < chromosomes.size());
                chromosomes = aux;
            } else {
                chromosomes = reemplazar.apply(mutation.apply(recombinar.apply(seleccionar.apply(chromosomes))), chromosomes);
            }
        }
    }
    
}
