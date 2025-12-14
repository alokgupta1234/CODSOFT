import java.util.Scanner;

public class RuleBasedChatBot {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("ChatBot: Hello! I am a simple rule-based chatbot.");
        System.out.println("ChatBot: Type 'bye' to end the conversation.\n");

        while (true) {

            System.out.print("You: ");
            userInput = scanner.nextLine().toLowerCase().trim();

            if (userInput.contains("hello") || userInput.contains("hi")) {
                System.out.println("ChatBot: Hello! How may I help you?");
            }
            else if (userInput.contains("how are you")) {
                System.out.println("ChatBot: I am doing well. Thank you for asking.");
            }
            else if (userInput.contains("your name")) {
                System.out.println("ChatBot: I am a rule-based chatbot developed in Java.");
            }
            else if (userInput.contains("help")) {
                System.out.println("ChatBot: I can answer basic questions and greetings.");
            }
            else if (userInput.contains("bye")) {
                System.out.println("ChatBot: Goodbye! Have a nice day.");
                break;
            }
            else {
                System.out.println("ChatBot: Sorry, I did not understand that.");
            }
        }

        scanner.close();
    }
}
