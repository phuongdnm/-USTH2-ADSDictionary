package sample;

import java.io.*;
import java.util.*;
import java.io.File;
import java.util.HashMap;
import java.util.stream.Stream;

public class Dictionaryy {
    HashMap<String, String> hm = new HashMap<>();
    HashMap<String, String> hmViet = new HashMap<>();
    String[] keyArray;
    String[] keyArrayViet;
    HashMap<String, String> hmlink = new HashMap<>();
    String[] link_key;
    /**
     * you may need to change the path
     * This part is for my system
     **/

    File file_english = new File("src/sample/Keyword/dictionary.txt");
    File file_viet = new File("src/sample/Keyword/dictionaryViet.txt");
    //english hashmap dictionary creation
    HashMapFromTextFile dictionary = new HashMapFromTextFile(file_english);
    //vietnamese hashmap dictionary creation
    HashMapFromTextFile dictionaryViet = new HashMapFromTextFile(file_viet);
    HashMapFromTextFile keyLink = new HashMapFromTextFile(new File("src/sample/Keyword/keyText-1.txt"));
    int words_found = 0;

    public Dictionaryy() {
        hmlink = keyLink.getHashMapDictionary();
        link_key = keyLink.getHashMapKeys();

        hm = dictionary.getHashMapDictionary();
        keyArray = dictionary.getHashMapKeys();

        hmViet = dictionaryViet.getHashMapDictionary();
        keyArrayViet = dictionaryViet.getHashMapKeys();
    }

    public HashMap<String, String> getHm() {
        return hm;
    }

    public HashMap<String, String> getHmViet() {
        return hmViet;
    }

    public String[] getKeyArray() {
        return keyArray;
    }

    public String[] getKeyArrayViet() {
        return keyArrayViet;
    }

    public String Translate(String word) {
        String translation = "No translation";
        for (int i = 0; i < keyArray.length; i++)      //this is to show related words
        {
            //for english words
            if (keyArray[i] != null) { //we have to check if its null else it will throw a nullPointerException error
                if (keyArray[i].equals(word)) { //If we find the word immediately then show  no suggestions
                    translation = dictionary.translateKeyTo(keyArray[i], "viet", hm, hmViet, keyArray, keyArrayViet);
                    break;
                }
            }
            //for vietnamese words
            if (keyArrayViet[i] != null) { //we have to check if its null else it will throw a nullPointerException error
                if (keyArrayViet[i].equals(word)) { //If we find the word immediately then show  no suggestions
                    translation = dictionary.translateKeyTo(keyArrayViet[i], "eng", hm, hmViet, keyArray, keyArrayViet);
                    break;
                }
            }
        }
        return translation;
    }

    public String Search(String word) {
        String definition = "Definition Not available";
        words_found = 0;
        //to check if no word are found
        for (int i = 0; i < keyArray.length; i++)      //this is to show related words
        {
            //for english words
            if (keyArray[i] != null) { //we have to check if its null else it will throw a nullPointerException error
                if (keyArray[i].equals(word)) { //If we find the word immediately then show  no suggestions
                    definition = hm.get(keyArray[i]);
                    words_found++;
                }
                if (keyArray[i].contains(word) && word.charAt(0) == keyArray[i].charAt(0)) {;
                    words_found++;
                }
            }
            //for vietnamese words
            if (keyArrayViet[i] != null) { //we have to check if its null else it will throw a nullPointerException error
                if (keyArrayViet[i].equals(word)) { //If we find the word immediately then show  no suggestions
                    definition = hmViet.get(keyArrayViet[i]);
                    words_found++;
                }
                if (keyArrayViet[i].contains(word) && word.charAt(0) == keyArrayViet[i].charAt(0)) {
                    words_found++;
                    System.out.println("Searched!");
                }
            }
        }
        return definition;
    }

    public String get_key_link(String word) {
        String link = null;
        words_found = 0;
        //to check if no word are found
        for (int i = 0; i < link_key.length; i++)      //this is to show related words
        {
            if (link_key[i] != null) { //we have to check if its null else it will throw a nullPointerException error
                if (link_key[i].equals(word)) { //If we find the word immediately then show  no suggestions
                    link = hmlink.get(link_key[i]);
                    words_found++;
                }
            }
        }
        if(link.equals("\"\""))
            link=null;
        return link;
    }
}




