package org.example;

import java.util.List;

public interface WordsDao {
    List<String> getWords();
    boolean addWord(String word);
}
