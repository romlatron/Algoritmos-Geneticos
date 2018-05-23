/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import algoritmosgeneticos.item.Armor;
import algoritmosgeneticos.item.Boot;
import algoritmosgeneticos.item.Glove;
import algoritmosgeneticos.item.Helmet;
import algoritmosgeneticos.item.Item;
import algoritmosgeneticos.item.Weapon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author rama
 */
public class FileReader {
    public FileReader() {}
    
    public List<Item> readItems(String filename, String itemType) {
        File file = new File("./testdata/" + filename);
        List<Item> items = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file).useLocale(Locale.US);
            sc.nextLine();

            while(sc.hasNextInt()){
                switch(itemType){
                case "BOOTS":
                    items.add(new Boot(sc.nextInt(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(),sc.nextDouble()));
                    break;
                case "HELMET":
                    items.add(new Helmet(sc.nextInt(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(),sc.nextDouble()));
                    break;
                case "WEAPON":
                    items.add(new Weapon(sc.nextInt(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(),sc.nextDouble()));
                    break;
                case "GLOVES":
                    items.add(new Glove(sc.nextInt(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(),sc.nextDouble()));
                    break;
                case "ARMOR":
                    items.add(new Armor(sc.nextInt(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble(),sc.nextDouble()));
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }
}
