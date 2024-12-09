# Milestone3

## Author
LINGRAN ZHANG  

CPSC2231L 01 Programming Workshop Lab  

2024Fall  

---

## Overview

This project is a robust **Java-based text analysis toolkit** designed to simplify processing and analyzing textual data. It provides a user-friendly, menu-driven interface to clean data, extract meaningful insights, and perform advanced text analysis, including sentiment evaluation.

Whether you're processing large text files for research or preparing datasets for machine learning, this toolkit streamlines essential tasks like removing stop words, calculating statistics, analyzing vocabulary richness, and understanding sentiment.

---

## Why This Project Is Useful

This project serves as a versatile and powerful tool for anyone working with textual data. Key benefits include:

- **Text Cleaning**: Helps clean text by removing stop words, making it ready for advanced analysis.
- **Detailed Statistics**: Quickly calculates important metrics like word count, sentence count, and vocabulary richness.
- **Actionable Insights**: Identifies commonly used words and their frequencies, providing deeper insight into text content.
- **Sentiment Analysis**: Understands the emotional tone of text (positive, negative, neutral).
- **Efficiency**: Automates repetitive and time-consuming text preprocessing tasks.
- **Educational Value**: Offers a great way to practice object-oriented programming and text processing techniques in Java.

---

## UML Diagram
![UML Diagram](https://github.com/Lingran0/Milestone3/blob/main/UML%20Diagram.png?raw=true)

---

## Features

This project provides the following functionalities:

1. **Remove Stop Words**: Filters out common stop words to focus on meaningful text.
2. **Calculate Basic Statistics**:
   - Total word count.
   - Total sentence count.
3. **Sort Words by Frequency**: Lists words sorted by their frequency of occurrence.
4. **Calculate Vocabulary Richness**: Measures the uniqueness of the text by counting unique words.
5. **Find Most Repeated Words**: Identifies the top three most frequently occurring words.
6. **Determine Sentiment**:
   - Calculates a sentiment score based on positive and negative word counts.
   - Outputs a sentiment classification (positive, negative, or neutral).
7. **Topic Selection**:  
   - Browse and select topics from the existing library.  
   - View detailed analysis results for selected topics/news.  
8. **Content Management**:  
   - Add new articles or text files to the library.  
   - Expand the collection with user-provided content.  
9. **Text-Based Interface**:  
   - User-friendly command-line interface.  
   - Interactive text-based navigation and selection.  

---

## Project Structure

The project is organized into the following components:

1. **`main.main`**:  
   The entry point of the program.  
   - Provides a menu-driven interface for user interaction.
   - Handles inputs, error messages, and controls the flow of the application.

2. **`textProcessor.textProcessor`**:  
   Responsible for text-related preprocessing tasks.  
   - Reads text files from the system.
   - Removes stop words using a predefined stop word file.

3. **`statisticCalculator.statisticCalculator`**:  
   Performs all statistical computations, word frequency ranking, and sentiment analysis.  
   - Calculates word and sentence counts.
   - Generates word frequency maps.
   - Evaluates vocabulary richness and sentiment scores.

---

## Usage Instructions
1. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse, BlueJ).
2. Run the `main` class located in the `src/main` folder.
3.  Place your text files in the appropriate directory.
4. Modify the file paths in the code as needed for your environment.
5. Run the program and follow the on-screen instructions to process your files.


## Code Analysis and Refactoring  

### Main Class  
**Reason for Refactoring**: Handles multiple responsibilities (UI, file operations, coordination)  

**Reduced Coupling**  
- Introduce UserInterface interface for user interactions  
- Create FileOperationHandler for file operations  
- Move menu operations to MenuHandler class  
- Reduces direct dependencies on Scanner and System.out  

**Improved Cohesion**  
- Create ArticleManager for article library operations  
- Move file processing to TextAnalysisController  
- Main class focuses only on coordination  

### TextProcessor Class  
**Reason for Refactoring**: Combines file operations with text processing  

**Reduced Coupling**  
- Create FileReader interface for file operations  
- Inject file reading dependency through constructor  
- Remove direct file handling from processing methods  

**Improved Cohesion**  
- Split into TextCleaner and TextReader classes  
- TextCleaner focuses on text processing  
- Separate FileHandler for file operations  

### StatisticCalculator Class  
**Reason for Refactoring**: Combines sentiment analysis with general statistics  

**Reduced Coupling**  
- Create SentimentAnalyzer interface  
- Separate LexiconLoader class  
- Use dependency injection for components  

**Improved Cohesion**  
- Split into BasicStatistics and SentimentAnalysis  
- Create WordFrequencyAnalyzer class  
- Dedicated LexiconManager for lexicon handling  
