package com.eriknakamura.skinsense;

public class SymptomAnalyzer {
    public int[] regularskin = {0, 0, 0, 0, 0, 0, 0, 0};
    public int[] ringworm = {1, 0, 0, 1, 0, 1, 0, 0};
    public int[] eczema = {1, 1, 1, 1, 0, 1, 0, 0};
    public int[] lyme = {1, 0, 0, 0, 1, 0, 1, 0};
    public int[] pyoderma = {0, 0, 1, 1, 0, 0, 1, 1};
    public int[] psoriasis = {1, 1, 1, 1, 1, 0, 0, 1};
    public int[] mrsa = {0, 0, 1, 1, 0, 0, 1, 0};
    public double correct;
    public double total = 8.0;
    public double[] probabilities = new double[7];
    public int[][] diseases = {ringworm, eczema, lyme, pyoderma, psoriasis, mrsa, regularskin};
    public double[] probabilitiesflipped = new double[7];

    public double[] probability(int[] a) {
        for (int j = 0; j <= 6; j++) {
            correct = 0.0;
            for (int i = 0; i <= 7; i++) {
                if (diseases[j][i] == a[i]) {
                    correct++;
                }
            }
            probabilities[j] = correct / total;
            System.out.println(probabilities[j]);
        }
        for(int i = 0; i < probabilities.length; i++){
            probabilitiesflipped[i] = probabilities[6-i];
        }
        return (probabilitiesflipped);

    }
}