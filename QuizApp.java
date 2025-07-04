// File: Question.java
import java.util.*;

public class QuizApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Question> questions = new ArrayList<>();
    private static int score = 0;

    public static void main(String[] args) {
        initializeQuestions();
        Collections.shuffle(questions);

        System.out.println("\n===== Welcome to the Java Console Quiz App =====\n");
        for (int i = 0; i < questions.size(); i++) {
            displayQuestion(i + 1, questions.get(i));
        }

        System.out.println("\n✅ Quiz completed!");
        System.out.println("\nYour final score: " + score + " out of " + questions.size());
        System.out.println(score > 7 ? "🎉 Excellent job!" : (score >= 5 ? "🙂 Good effort!" : "📚 Keep practicing!"));

        scanner.close();
    }

    private static void initializeQuestions() {
        questions.add(new Question("What are Java loops?",
            Arrays.asList("Structures to repeat code", "Ways to exit a program", "Variables", "Classes"), 0));
        questions.add(new Question("What is an enhanced for-loop?",
            Arrays.asList("Loop with more features", "Loop using iterators", "Simplified for-each loop", "Custom loop"), 2));
        questions.add(new Question("How do you handle multiple user inputs?",
            Arrays.asList("Using Scanner multiple times", "BufferedReader", "Scanner with loop", "All of the above"), 3));
        questions.add(new Question("How is switch-case different from if-else?",
            Arrays.asList("Faster for discrete values", "Always preferred", "Used for strings only", "Has no break"), 0));
        questions.add(new Question("What are collections in Java?",
            Arrays.asList("Group of interfaces/classes", "Primitive types", "Database", "None"), 0));
        questions.add(new Question("What is ArrayList?",
            Arrays.asList("Resizable array", "Primitive array", "Map", "Fixed set"), 0));
        questions.add(new Question("How to iterate using iterators?",
            Arrays.asList("Using next() & hasNext()", "Using length", "Using size() only", "Using while only"), 0));
        questions.add(new Question("What is a Map?",
            Arrays.asList("Key-value pair", "Set of values", "String", "None"), 0));
        questions.add(new Question("How to sort a list?",
            Arrays.asList("Collections.sort(list)", "list.sort()", "SortUtil", "ListTools"), 0));
        questions.add(new Question("How to shuffle elements?",
            Arrays.asList("Collections.shuffle(list)", "list.random()", "Sort.shuffle()", "List.rotate()"), 0));
    }

    private static void displayQuestion(int number, Question q) {
        System.out.println("Q" + number + ": " + q.getQuestionText());
        List<String> opts = q.getOptions();
        for (int i = 0; i < opts.size(); i++) {
            System.out.println((i + 1) + ". " + opts.get(i));
        }

        int userChoice = getUserAnswer(opts.size());
        if (q.isCorrect(userChoice - 1)) {
            System.out.println("✅ Correct!\n");
            score++;
        } else {
            System.out.println("❌ Incorrect. The correct answer was: " + (q.getOptions().get(((Question) q).correctAnswerIndex)) + "\n");
        }
    }

    private static int getUserAnswer(int optionsSize) {
        int choice = -1;
        while (true) {
            System.out.print("Enter your choice (1-" + optionsSize + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= optionsSize) {
                    return choice;
                } else {
                    System.out.println("⚠️ Invalid option. Please choose between 1 and " + optionsSize + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input. Please enter a number.");
            }
        }
    }
}

class Question {
    private final String questionText;
    private final List<String> options;
    final int correctAnswerIndex;

    public Question(String questionText, List<String> options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}
