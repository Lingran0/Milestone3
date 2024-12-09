package main;

import textProcessor.textProcessor;
import statisticCalculator.statisticCalculator;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {

    private static Map<String, String> articleLibrary = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        textProcessor textProcessor;
        statisticCalculator statisticCalculator = new statisticCalculator();

        initializeLibrary();

        try {
            textProcessor = new textProcessor("src/stopWords.txt");

            while (true) {
                printMenu();
                int choice = getValidatedInput(scanner);

                switch (choice) {
                    case 1:
                        removeStopWords(scanner, textProcessor);
                        break;
                    case 2:
                        calculateBasicStats(scanner, textProcessor, statisticCalculator);
                        break;
                    case 3:
                        rankWordByFrequency(scanner, textProcessor, statisticCalculator);
                        break;
                    case 4:
                        calculateVocabularyRichness(scanner, textProcessor, statisticCalculator);
                        break;
                    case 5:
                        findMostRepeatedWords(scanner, textProcessor, statisticCalculator);
                        break;
                    case 6:
                        determineSentiment(scanner, textProcessor, statisticCalculator);
                        break;
                    case 7:
                        selectTopicAndAnalyze(scanner, textProcessor, statisticCalculator);
                        break;
                    case 8:
                        addNewArticles(scanner);
                        break;
                    case 0:
                        System.out.println("End of program\n");
                        return;
                    default:
                        System.out.println("Invalid selection, please try again");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("The stop word file cannot be found: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void printMenu() {
        System.out.println("\nSelect an operation: ");
        System.out.println("1. Remove stop words");
        System.out.println("2. Calculate basic statistics");
        System.out.println("3. Sort by word frequency");
        System.out.println("4. Calculate vocabulary richness");
        System.out.println("5. Find the most repeated words");
        System.out.println("6. Determine sentiment (positive or negative)");
        System.out.println("7. Analyze a topic from the library");
        System.out.println("8. Add a new article set");
        System.out.println("0. Exit");
    }

    private static int getValidatedInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void removeStopWords(Scanner scanner, textProcessor textProcessor) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        scanner.nextLine();
        String filePath = scanner.nextLine();

        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);

        System.out.println("\nContent after deletion of stop words: ");
        System.out.println(filteredContent);
    }

    private static void calculateBasicStats(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        scanner.nextLine();
        String filePath = scanner.nextLine();

        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);

        int wordCount = statisticCalculator.calculateWords(filteredContent);
        int sentenceCount = statisticCalculator.calculateStatements(filteredContent);

        System.out.println("\nBasic statistics: ");
        System.out.println("Total word count: " + wordCount);
        System.out.println("Total sentences: " + sentenceCount);
    }

    private static void rankWordByFrequency(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        scanner.nextLine();
        String filePath = scanner.nextLine();

        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);

        Map<String, Integer> wordFrequency = statisticCalculator.calculateWordFrequency(filteredContent);

        System.out.println("\nWords sorted by word frequency: ");
        wordFrequency.forEach((word, frequency) ->
                System.out.println("Word: " + word + " - Frequency: " + frequency));
    }

    private static void calculateVocabularyRichness(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        scanner.nextLine();
        String filePath = scanner.nextLine();

        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);

        int uniqueWordCount = statisticCalculator.calculateVocabularyRichness(filteredContent);

        System.out.println("Vocabulary richness: " + uniqueWordCount);
    }

    private static void findMostRepeatedWords(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        scanner.nextLine();
        String filePath = scanner.nextLine();

        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);

        Map<String, Integer> mostRepeatedWords = statisticCalculator.mostRepeatedWords(filteredContent);

        System.out.println("The most repeated words: ");
        mostRepeatedWords.forEach((word, frequency) ->
                System.out.println("Word: " + word + " - Frequency: " + frequency));
    }

    private static void determineSentiment(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Please enter the news file path: ");
        scanner.nextLine();
        String filePath = scanner.nextLine();

        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);

        double sentimentScore = statisticCalculator.calculateSentimentScore(filteredContent);

        System.out.println("Sentiment score: " + sentimentScore);
        System.out.println("Sentiment: " + statisticCalculator.determineSentiment(sentimentScore));
    }

    private static void initializeLibrary() {
        articleLibrary.put("pet", "/Users/zhanglingran/Desktop/class_files/Senior_Fall/CS/HW/Lab_milestone1/dir/pet2.txt");
        articleLibrary.put("fashion", "/Users/zhanglingran/Desktop/class_files/Senior_Fall/CS/HW/Lab_milestone1/dir/fashion1.txt");
        articleLibrary.put("sport", "/Users/zhanglingran/Desktop/class_files/Senior_Fall/CS/HW/Lab_milestone1/dir/sport3.txt");
    }

    private static void selectTopicAndAnalyze(Scanner scanner, textProcessor textProcessor, statisticCalculator statisticCalculator) throws FileNotFoundException {
        System.out.println("Available topics:");
        articleLibrary.keySet().forEach(topic -> System.out.println("- " + topic));

        System.out.println("\nPlease enter the topic you want to analyze:");
        scanner.nextLine(); 
        String topic = scanner.nextLine();

        if (!articleLibrary.containsKey(topic)) {
            System.out.println("Invalid topic. Please select a valid topic.");
            return;
        }

        String filePath = articleLibrary.get(topic);

        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Error: The file for the selected topic does not exist. Path: " + filePath);
            return;
        }

        String content = textProcessor.readFile(filePath);
        String filteredContent = textProcessor.removeStopWords(content);

        System.out.println("\nAnalysis for topic: " + topic);
        int wordCount = statisticCalculator.calculateWords(filteredContent);
        int sentenceCount = statisticCalculator.calculateStatements(filteredContent);
        int uniqueWordCount = statisticCalculator.calculateVocabularyRichness(filteredContent);
        Map<String, Integer> mostRepeatedWords = statisticCalculator.mostRepeatedWords(filteredContent);

        System.out.println("Total word count: " + wordCount);
        System.out.println("Total sentences: " + sentenceCount);
        System.out.println("Vocabulary richness: " + uniqueWordCount);
        System.out.println("Most repeated words:");
        mostRepeatedWords.forEach((word, frequency) ->
                System.out.println("Word: " + word + " - Frequency: " + frequency));
    }


    private static void addNewArticles(Scanner scanner) {
        System.out.println("Enter the topic for the new article set:");
        scanner.nextLine();
        String topic = scanner.nextLine();

        System.out.println("Enter the file path for the new article:");
        String filePath = scanner.nextLine();

        if (Files.exists(Paths.get(filePath))) {
            articleLibrary.put(topic, filePath);
            System.out.println("Article added successfully under the topic: " + topic);
        } else {
            System.out.println("Invalid file path. Please try again.");
        }
    }
}
