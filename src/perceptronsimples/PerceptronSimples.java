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
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.SpringLayout;

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
        BufferedReader dataset = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\levig\\Documents\\NetBeansProjects\\PerceptronSimples\\src\\perceptronsimples\\dermatology.data")));
        String linha;
        Integer[][] dermatology = new Integer[366][34];
        Integer[] classes = new Integer[366];
        Double[][] pesos = new Double[6][34];
        String[] dataDermatology;
        Double[] funcAtiv = new Double[6];
        Integer[][] desejado = new Integer[366][6];
        Integer[][] saida = new Integer[366][6];
        Integer[][] erro = new Integer[366][6];
        int acertos = 0;
        int id = 0;
        System.out.println("Digite o percentual de treinamento");
        Scanner treina = new Scanner(System.in);
        int qtde_treino = treina.nextInt();
        System.out.println("Digite o número de rodadas");
        Scanner rod = new Scanner(System.in);
        int rodadas = rod.nextInt();
        System.out.println("Digite a taxa de aprendizagem");
        Scanner tx = new Scanner(System.in);
        double n = tx.nextDouble();
        while ((linha = dataset.readLine()) != null) {
            dataDermatology = linha.split(",");
            dermatology[id][0] = -1;
            for (int j = 1; j < 33; j++) {  
                dermatology[id][j] = Integer.parseInt(dataDermatology[j-1]);
            }
            dermatology[id][33] = (dataDermatology[33].equals("?")) ?  36 : Integer.parseInt(dataDermatology[33]);
            classes[id] = Integer.parseInt(dataDermatology[34]);
            id++;
        }
        // aplico os pesos até acertar a classe
        // faço com seis neurônios  para ver a classe
        // cada um dos 366 eu uso os 6 neurônios
        // 1º caso o desejado é 2. faço t iterações até 
        int c1 = 0, c0 = 0;
        for (int i = 0; i < 366; i++) {
            Arrays.fill(desejado[i], 0);
        }
        for (int j = 0; j < 366; j++) {
            if (null != classes[j]) switch (classes[j]) {
                case 1:
                    desejado[j][0] = 1;
                    break;
                case 2:
                    desejado[j][1] = 1;
                    break;
                case 3:
                    desejado[j][2] = 1;
                    break;
                case 4:
                    desejado[j][3] = 1;
                    break;
                case 5:
                    desejado[j][4] = 1;
                    break;
                case 6:
                    desejado[j][5] = 1;
                    break;
                default:
                    break;
            }
            for (int i = 0; i < 6; i++) {
//                System.out.println("Desejado: " + desejado[j][i]);
            }                
//            System.out.println("________________________");
        }
        Arrays.fill(funcAtiv, 0.0);
        for (int i = 0; i < 6; i++) {
            randomizarPesos(pesos[i]);
        }
        for (int r = 0; r < rodadas; r++) {
            for (int i = 0; i < (int) ((qtde_treino * dermatology.length) / 100); i++) {
                for (int k = 0; k < 6; k++) {
                        funcAtiv[k] = multiplicarPesos(pesos[k], dermatology[i]);
                        saida[i][k] = (funcAtiv[k] > 0) ? 1 : 0;
                        erro[i][k] = desejado[i][k] - saida[i][k];
        //                    System.out.println("Func: " + funcAtiv[k]);
    //                    System.out.println("Desejado: " + desejado[i][k]);
    //                    System.out.println("Saida:    " + saida[i][k]);
                        pesos[k] = atualizarPesos(pesos[k], erro[i][k], dermatology[i], n);
                }
                if(Arrays.equals(saida[i], desejado[i])){
                    acertos++;
                }
    //            System.out.println("------------------------------------------------------");
            }
        }
       
        System.out.println(acertos + " acertos de " + ((int) rodadas * (qtde_treino * dermatology.length) / 100));
        System.err.printf("%.2f",   100 * ((double)acertos / ((rodadas * qtde_treino * dermatology.length) / 100)));
    }
    public static void randomizarPesos(Double[] pesos) {
        for (int j = 0; j < 34; j++) {
            pesos[j] = Math.random();
        }
    }
    public static Double[] atualizarPesos(Double[] pesos, Integer erro, Integer[] derma, double n) {
        Double[] novoPeso = new Double[34];
        for (int i = 0; i < 34; i++) {
            novoPeso[i] = pesos[i] + erro * derma[i] * n;
        }
        return novoPeso;
    }
    public static Double multiplicarPesos(Double[] pesos, Integer[] derma) {
        double result = 0;
        for (int i = 0; i < pesos.length; i++) {
            result += pesos[i] * derma[i];
        }
        return result;
    }
}