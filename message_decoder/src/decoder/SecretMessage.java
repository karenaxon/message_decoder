package decoder;
/**
 * Karen Axon
 * This is free and unencumbered software released into the public domain.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program creates a Linked List. After the secret message has
 * been decoded, it displays the message in plain text to standard output.
 * @author Karen Axon
 * @version 2.0
 */

/**
 * The SecretMessage class takes user input for the name of the file to be
 * decoded. It validates the name of the file and passes it on to
 * the MessageDecoder class. After the message has been decoded, it prints
 * it in plain text to standard output.
 */
public class SecretMessage {

    /**
     * Checks to see that the user-specified file name refers to a valid
     * file on the disk and not a directory. Displays an error message to the
     * user if that is not the case.
     * @param fName file name string to check
     * @return true if file exists on disk and is not a directory
     */
    private static boolean isValidFile(String fName) {
        File path = new File(fName);
        boolean isValid = path.exists() && !path.isDirectory();
        if (!isValid) {
            System.out.println("The file " + path + " doesn't exist.");
        }
        return isValid;
    }

    /**
     * The getFileName takes input from the user for the name of the file.
     * @param keyboard Scanner for user input
     * @return file The name of the file
     */
    public static String getFileName(Scanner keyboard) {
        String file;

        // Get user input for file name
        System.out.print("Enter the name of the file: ");
        file = keyboard.nextLine(); // captures user input
        return file;
    }

    /**
     * The displayGoodbye method displays a goodbye message to standard output.
     */
    public static void displayGoodbye()
    {
        // Display message
        System.out.println("\nThank you for using the message decoder.\n");
    }

    /**
     * The displayHello method displays a goodbye message to standard output.
     */
    public static void displayHello()
    {
        // Display message
        System.out.println("\nThis program reads an encoded message from a " +
                "file supplied by the user\nand displays the contents of the" +
                " message before it was encoded.\n");
    }

    /**
     * The Main method displays messages to standard output, it asks the user
     * for input for the file name, it passes the name of the file on for the
     * secret message to be decoded and then displays the decoded message in
     * plain text to standard output. It also asks the user if another
     * message should be decoded.
     * @param args  A string array containing the command line arguments.
     * @throws FileNotFoundException If the file isn't found
     */
    public static void main(String [] args) throws FileNotFoundException {
        String fileName;
        char decodeAgain = 'y'; // To get the game started

        // Create a Scanner object to read input
        Scanner keyboard = new Scanner(System.in);

        displayHello();

        do {
            decodeAgain = 'n';

            // Get the file name from user
            fileName = getFileName(keyboard);

            // Verify the file exists
            while (!isValidFile(fileName)) {
                fileName = getFileName(keyboard);
            }

            MessageDecoder secretList = new MessageDecoder();

            // Pass the file to be decoded
            secretList.setFile(fileName);

            // Decode message
            secretList.decodeFile(secretList);

            // Print the decoded message
            System.out.println(secretList.toString());

            // Determine if user wants to play again
            System.out.print("\nWould you like to decode another message? " +
                    "Enter 'y' or 'n': ");
            decodeAgain = keyboard.next().charAt(0);
            keyboard.nextLine();

        } while (decodeAgain == 'y' || decodeAgain == 'Y');

        displayGoodbye();

        // Close Scanner
        keyboard.close();
    }
}
