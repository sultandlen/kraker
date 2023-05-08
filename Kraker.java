public class Kraker {
    public static void main(String[] args) {
        long[][] data = new long[3][2];
        for (int i = 0; i < 3; i++) {
            Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
            System.out.println("Generation # 0 " + "\nFittest chromosome fitness: " + population.getChromosomes()[0].getFitness());
            //printPopulation(population); //print first generation
            System.out.println("Fittest chromosome: " + population.getChromosomes()[0].toString());
            int generationNumber = 0;
            long start = System.currentTimeMillis();
            while (population.getChromosomes()[0].getFitness() != 0) {
                generationNumber++;
                //System.out.println("\n--------------------------------------");
                population = geneticAlgorithm.evolve(population);
                population.sortChromosomesByFitness();
                System.out.println("Generation # " + generationNumber + "\nFittest chromosome fitness: " + population.getChromosomes()[0].getFitness());
                System.out.println("Fittest chromosome: " + population.getChromosomes()[0].toString());
            }
            //printPopulation(population); //print last generation
            long end = System.currentTimeMillis();
            data[i][0] = (end - start);
            data[i][1] = generationNumber;
        }
        printTable(data);
    }

    public static void printTable(long[][] data) {
        System.out.println("\n--------------------------------------");
        System.out.println("             | Time(ms) | Generation |");
        System.out.println("-------------|----------|------------|");
        int totalGeneration = 0;
        for (int i = 0; i < data.length; i++) {
            System.out.print("Iteration # " + (i + 1));
            System.out.printf("|%10d|%12d|\n", data[i][0], data[i][1]);
            totalGeneration += data[i][1];
        }
        System.out.println("Generation average: " + totalGeneration / 3);
    }

    public static void printPopulation(Population population) {
        for (int i = 0; i < population.getChromosomes().length; i++) {
            System.out.println("Chromosome # " + i + " : " + '"' + population.getChromosomes()[i].toString() + '"'
                    + " | Fitness: " + population.getChromosomes()[i].getFitness());
        }

    }
}
