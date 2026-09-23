/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author stabeeb
 */

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Record {

    private static Record instance = null;   // singleton instance
    // Name of the associated file
    private String filename;

    // private constructor (prevents outside instantiating)
    private Record(String n) {
        filename = n;
    }

    // public method to get the sole instance
    public static Record getInstance() {
        if (instance == null) {
            instance = new Record("record.txt");
        }
        return instance;
    }

    // Effects: Reads and prints the contents of the associated
    // file to the standard output.
    public void read() {
        try {
            FileReader fr = new FileReader(filename);
            Scanner scanner = new Scanner(fr);

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Effects: Appends the specified message, msg, to the
    // associated file.
    public void write(String msg) {
        try {
            FileWriter fw = new FileWriter(filename, true); // true = append
            fw.write(msg);
            fw.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Get singleton instance (DO NOT invoke constructor)
        Record r = Record.getInstance();

        // Do not modify the code below
        r.write("Hello-1\n");
        r.write("Hello-2\n");

        System.out.println("currently the file record.txt contains the following lines:");
        r.read();
    }
}