import java.util.Random;

public class GeneticAlgorithm {
    public static final String PASSWORD = "ChatGPT and GPT-4";
    public static final int POPULATION_SIZE = 150;
    public static final int NUM_OF_ELITE_CHROMOSOMES = 2;
    public static final int TOURNAMENT_SELECTION_SIZE = 5;
    private static final double MUTATION_RATE = 0.1;

    public Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population) {
        Population crossoveredPopulation = new Population(population.getChromosomes().length);

        for (int i = 0; i < population.getChromosomes().length; i++) {
            if (i < NUM_OF_ELITE_CHROMOSOMES) {
                crossoveredPopulation.getChromosomes()[i] = population.getChromosomes()[i];
                continue;
            }
            Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
            Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
            crossoveredPopulation.getChromosomes()[i] = crossoverChromosome(chromosome1, chromosome2);
        }
        return crossoveredPopulation;
    }

    private Population mutatePopulation(Population population) {
        Population mutatedPopulation = new Population(population.getChromosomes().length);
        for (int i = 0; i < population.getChromosomes().length; i++) {
            if (i < NUM_OF_ELITE_CHROMOSOMES) {
                mutatedPopulation.getChromosomes()[i] = population.getChromosomes()[i];
                continue;
            }
            mutatedPopulation.getChromosomes()[i] = mutateChromosome(population.getChromosomes()[i]);
        }
        return mutatedPopulation;
    }

    private Chromosome crossoverChromosome(Chromosome chromosome1, Chromosome chromosome2) {
        Chromosome crossoveredChromosome = new Chromosome(PASSWORD.length());
        for (int i = 0; i < chromosome1.getGenes().length; i++) {
            if (Math.random() < 0.5) {
                crossoveredChromosome.getGenes()[i] = chromosome1.getGenes()[i];
            } else {
                crossoveredChromosome.getGenes()[i] = chromosome2.getGenes()[i];
            }
        }
        return crossoveredChromosome;
    }

    private Chromosome mutateChromosome(Chromosome chromosome) {
        Chromosome mutatedChromosome = new Chromosome(PASSWORD.length());
        Random random = new Random();
        for (int i = 0; i < chromosome.getGenes().length; i++) {
            if (Math.random() < MUTATION_RATE) {
                mutatedChromosome.getGenes()[i] = (char) (random.nextInt(94) + 32);
            } else {
                mutatedChromosome.getGenes()[i] = chromosome.getGenes()[i];
            }
        }
        return mutatedChromosome;
    }

    private Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
        for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
            tournamentPopulation.getChromosomes()[i] =
                    population.getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
        }
        tournamentPopulation.sortChromosomesByFitness();
        return tournamentPopulation;
    }
}
