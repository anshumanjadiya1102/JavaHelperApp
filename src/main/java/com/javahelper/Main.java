package com.javahelper;

import com.javahelper.qa.QAEngine;
import com.javahelper.utils.ConsoleUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            QAEngine engine = new QAEngine("src/main/resources/knowledge.json");
            List<String> history = new ArrayList<>();

            System.out.println("=== Java Helper CLI ===");

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Ask a Java Question");
                System.out.println("2. Show Q&A History");
                System.out.println("3. Export History CSV/JSON");
                System.out.println("0. Exit");

                String choice = ConsoleUtils.prompt("Choose option");
                switch (choice) {
                    case "1":
                        String question = ConsoleUtils.prompt("Enter your question");
                        if (question.equalsIgnoreCase("exit")) continue;
                        String answer = engine.getAnswer(question);
                        System.out.println("Answer: " + answer);
                        history.add("Q: " + question + " | A: " + answer);
                        break;

                    case "2":
                        if (history.isEmpty()) System.out.println("No history yet.");
                        else history.forEach(System.out::println);
                        break;

                    case "3":
                        exportHistory(history);
                        break;

                    case "0":
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exportHistory(List<String> history) {
        try {
            // CSV
            try (CSVPrinter printer = new CSVPrinter(new FileWriter("history.csv"), CSVFormat.DEFAULT.withHeader("Q&A"))) {
                for (String record : history) printer.printRecord(record);
            }

            // JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter("history.json")) {
                gson.toJson(history, writer);
            }

            System.out.println("Exported history.csv and history.json");

        } catch (IOException e) {
            System.err.println("Export failed: " + e.getMessage());
        }
    }
}

