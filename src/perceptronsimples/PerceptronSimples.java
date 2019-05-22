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
import java.util.Random;

/**
 *
 * @author levigleik
 */
public class PerceptronSimples {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader dataset = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\1721057\\Documents\\NetBeansProjects\\PerceptronSimples\\src\\perceptronsimples\\dermatology.data")));
        String linha;
        Integer[][] dermatology = new Integer[366][36];
        Double[][] pesos = new Double[366][35];
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
        // aplico os pesos até acertar a classe
        // faço com seis neurônios  para ver a classe
        // cada um dos 366 eu uso os 6 neurônios
        // 1º caso o desejado é 2. faço t iterações até 
        int c1 = 0, c0 = 0;
        for (int i = 0; i < 366; i++) {
            pesos[i][0] = -1.0;
            for (int j = 1; j < 35; j++) {
                pesos[i][j] = (Math.random() < 0.5) ? 0.0 : 1.0;
                if (pesos[i][j] == 0.0) {
                    c0++;
                }else {
                    c1++;
                }
            }
        }
        for (int i = 0; i < 366; i++) {
            for (int j = 0; j < 35; j++) {
                System.out.println("Pesos[" + i + "][" + j + "]: " + pesos[i][j]);
            }
        }
        System.out.println("1: " + c1 + "\n0: " + c0);
    }
    
}
