import java.util.Arrays;
public class Population {
    private Chromosome[] chromosomes;

    public Population(int length) {
        chromosomes = new Chromosome[length];
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public Population initializePopulation() {
        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i] = new Chromosome(GeneticAlgorithm.PASSWORD.length()).initializeChromosome();
        }
        sortChromosomesByFitness();
        return this;
    }

    public void sortChromosomesByFitness() {
        Arrays.sort(chromosomes); //ascending order, the fittest first, the smallest fitness first!
    }
}
