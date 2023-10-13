package com.example.laboratoire5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class CSVFile {


    public CSVFile(){}
    public void ajouter (List<String> data) throws IOException {
        List<String> dataCSV = Files.readAllLines(Paths.get("CSVData.csv"));

        FileWriter file = new FileWriter("CSVData.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(file);


        for (String s : data) {
        bufferedWriter.write(s);

        if (!s.trim().equals("H") && !s.trim().equals("F") && !s.trim().equals("A"))
            bufferedWriter.write(", ");
        }

        for (String s : dataCSV) {

            bufferedWriter.write("\n");
            bufferedWriter.write(s);

        }
        bufferedWriter.close();
        System.out.println(Files.readAllLines(Paths.get("CSVData.csv")));

    }
    public boolean verifierUsername(String data) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("CSVData.csv"));
        if (allLines.size() == 1)
            return false;


        for (String s : allLines) {
            String[] username = s.split(",");
            if (Objects.equals(data, username[2]))
                return true;
        }
        return false;
    }
    public boolean verifierUtilisateur(String[] data) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("CSVData.csv"));

        for (String s : allLines) {
            String[] donnees = s.split(",");
            if (Objects.equals(data[0], donnees[2].trim()) && Objects.equals(data[1], donnees[3].trim()))
               return true;
        }
        return false;
    }
}
