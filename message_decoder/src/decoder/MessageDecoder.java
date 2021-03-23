package decoder;
/**
 * Karen Axon
 * This is free and unencumbered software released into the public domain.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 This program creates a Linked List and adds elements to it from a file with
 each line containing: a letter/character/space + a space + a number (position
 of the letter/character/space in the secret message). It converts a scrambled
 message file into plain text.
 * @author Karen Axon
 * @version 2.0
 */

/**
 * The MessageDecoder class implements a linked list. It converts a scrambled
 * message file into plain text.
 */
public class MessageDecoder {

    /**
     * The Node class stores a list element and a reference to the next node.
     */
    private class Node {
        String value;
        Node next;
        Integer index;

        /**
         Constructor.
         @param val The element to store in the node.
         @param i The reference to the index of the node.
         */
        Node(String val, Integer i) {
            value = val;
            next = null;
            index = i;
        }

        /**
         Constructor.
         @param val The element to store in the node.
         */
        Node(String val) {
            val = value;
        }
    }

    private Node first;             // list head
    private Node last;              // last element in list
    private String fileName;
    private String data;            // A line from the file
    private char letter;            // The letter from the line
    private String letter2;         // The letter converted to a String
    private int num;                // The number from the line

    /**
     Constructor.
     */
    public MessageDecoder() {
        first = null;
        last = null;
    }

    /**
     The isEmpty method checks to see if the list is empty.
     @return true if list is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return first == null;
    }


    /**
     The size method returns the length of the list.
     @return The number of elements in the list.
     */
    public int size() {
        int count = 0;

        Node current = first;
        while (current != null) {
            // There is an element at p
            count ++;
            current = current.next;
        }
        return count;
    }

    /**
     * The setFile name takes in the name of a file.
     * @param inputName The file name passed in.
     */
    public void setFile(String inputName) {
        fileName = inputName;
    }

    /**
     * The add method adds an element to the Linked List.
     * @param e The value to be added
     */
    public void add(String e) {
        if (isEmpty()) {
            first = new Node(e);
            last = first;
        } else {
            // Add to end of existing list
            last.next = new Node(e);
            last = last.next;
        }
    }

    /**
     * The add method adds an element to the Linked List.
     * @param index The position at which to add the element.
     * @param e The value to be added
     */
    public void add(int index, String e) {
        if (index < 0) {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        Node cur = first;
        Node prev = first;
        Node newNode = new Node(e, index);

        // Add a node at the beginning of the list
        if (isEmpty() || index == 0) {
            first = newNode;
        } else {

            // Add a node in front of existing nodes
            if (index < cur.index && !isEmpty()) {
                newNode.next = cur;
                first = newNode;
                return;
            }

            while(cur.next != null) {
                if(index > cur.index) {
                    // Traverse the list
                    prev = cur;
                    cur = cur.next;
                } else {
                    // Slice a node in between nodes
                    prev.next = newNode;
                    newNode.next = cur;
                    return;
                }
            }

            // If index is smaller than the last node's, slide the new
            // node prior to last node
            if(index < cur.index){
                // Slice a node in between nodes
                prev.next = newNode;
                newNode.next = cur;
                return;
            }
            // Insert a node at the end of the list
            cur.next = newNode;
        }
    }

    /**
     * The decodeFile method adds elements from a file to a link list.
     * @param list The link list to hold the elements from the file
     * @throws FileNotFoundException If the file isn't found
     */
    public void decodeFile(MessageDecoder list) throws FileNotFoundException {
        // Open the file
        File file = new File(fileName);
        Scanner inputFile = new Scanner(file);

        //Read the elements from the file and pass them to the Linked List.
        while (inputFile.hasNext()) {
            data = inputFile.nextLine();

            // Get the letter from the line
            letter = data.charAt(0);
            letter2 = Character.toString(letter);

            // Get the number from the line
            num = Integer.parseInt(data.substring(2));

            // Add the letter and number to the linked list
            list.add(num, letter2);
        }
        // Close the file
        inputFile.close();
    }

    /**
     The toString method computes the string representation of the list.
     @return The string form of the list.
     */
    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();

        // Use p to walk down the linked list
        Node p = first;
        while (p != null)
        {
            strBuilder.append(p.value);
            p = p.next;
        }
        return strBuilder.toString();
    }
}

