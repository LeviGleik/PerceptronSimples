/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptronsimples;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author levig
 */
public class PerceptronSimples {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader dataset = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\levig\\Documents\\NetBeansProjects\\PerceptronSimples\\src\\perceptronsimples\\dermatology.data")));
        String linha;
        Integer[][] dermatology = new Integer[366][36];
        String[] dataDermatology;
        int id = 0;
        while ((linha = dataset.readLine()) != null) {
            dataDermatology = linha.split(",");
            dermatology[id][0] = -1;
            for (int j = 0; j < 33; j++) {  
                dermatology[id][j] = Integer.parseInt(dataDermatology[j]);
            }
            dermatology[id][33] = (dataDermatology[33].equals("?")) ?  36 : Integer.parseInt(dataDermatology[33]);
            id++;
        }
        
    }
    
}
