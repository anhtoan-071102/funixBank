/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dictionary.dictionaryapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import org.tartarus.snowball.ext.PorterStemmer;

/**
 *
 * @author ADMIN
 */
public class dataProcess {

    private final String indexPath;
    private final String dictPath;
    private HashMap<String, String> wordIndex = new HashMap<>();

    public dataProcess(String _indexPath, String _dictPath) {
        this.indexPath = _indexPath;
        this.dictPath = _dictPath;
        wordIndex = readIndexFile();
    }

    public final HashMap<String, String> readIndexFile() {
        SwingWorker<HashMap<String, String>, Void> worker = new SwingWorker<HashMap<String, String>, Void>() {
            @Override
            protected HashMap<String, String> doInBackground() throws Exception {
                HashMap<String,String> indexWord = new HashMap<>();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(indexPath));
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] lines = line.split("\t");
                        String word = lines[0];
                        String offSetAndLenght = lines[1] + " " + lines[2];
                        indexWord.put(word, offSetAndLenght);
                    }
                } catch (FileNotFoundException fnfe) {
                    System.out.println("File not found: " + fnfe.getMessage());
                } catch (IOException ioe) {
                    System.out.println("Error reading file: " + ioe.getMessage());
                } catch (Exception e) {
                    System.out.println("Unexpected error: " + e.getMessage());
                }
                return indexWord;
            }
        };
        worker.execute();
        try {
            wordIndex = worker.get();
        } catch (InterruptedException | ExecutionException e ) {
            e.printStackTrace();
        }
        return wordIndex;
    }

    public String getMeaning(String word) {
        StringBuilder meaning = new StringBuilder();
        try {
            RandomAccessFile rf = new RandomAccessFile(this.dictPath, "r");
            String sData = wordIndex.get(word);
            if (sData == null) {
                PorterStemmer stemmer = new PorterStemmer();
                stemmer.setCurrent(word);
                stemmer.stem();
                String stemmedWord = stemmer.getCurrent();
                sData = wordIndex.get(stemmedWord);
            }
            if (sData == null) {
                return null;
            }
            String[] offSetandLenght = sData.split(" ");
            int offSet = decodeData.getDecimalValue(offSetandLenght[0]);
            int len = decodeData.getDecimalValue(offSetandLenght[1]);

            rf.seek(offSet);
            byte[] buffer = new byte[2048];
            int bytesRead;
            while (len > 0 && (bytesRead = rf.read(buffer, 0, Math.min(len, buffer.length))) != -1) {
                meaning.append(new String(buffer,0,bytesRead,StandardCharsets.UTF_8));
                len -= bytesRead;
            }
            rf.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found: " + fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Error reading file: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        return meaning.toString();
    }
}
