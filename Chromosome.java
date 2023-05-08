import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
    private char[] genes;
    private int fitness = Integer.MAX_VALUE;
    private boolean isFitnessChanged = true;

    public Chromosome(int length) {
        genes = new char[length];
        fitness = 97 * length; //the fittest chromosome should have 0 fitness
    }

    public Chromosome initializeChromosome() {
        for (int i = 0; i < genes.length; i++) {
            Random random = new Random();
            genes[i] = (char) (random.nextInt(94) + 32);
        }
        return this;
    }

    public int calculateFitness() {
        int chromosomeFitness = 0;
        for (int i = 0; i < genes.length; i++) {
            chromosomeFitness += Math.abs((int) genes[i] - (int) GeneticAlgorithm.PASSWORD.charAt(i));
        }
        return chromosomeFitness;
    }

    public int getFitness() {
        if (isFitnessChanged) {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    public char[] getGenes() {
        isFitnessChanged = true;
        return genes;
    }

    @Override
    public String toString() {
        return String.valueOf(genes);
    }

    @Override
    public int compareTo(Chromosome chromosome) {
        return this.getFitness() - chromosome.getFitness();
    }
}
