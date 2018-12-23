package sample;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class HashMapFromTextFile{
    private File file;
    private HashMap<String, String> hm = new HashMap<>();
    private String []keyArray=new String [500];
    int count=0,key_counter;

    public HashMapFromTextFile(File file) {
        this.file = file;
        try {
            Scanner hmScan = new Scanner(file);
            String key = null, value = null, a = null, b;
            //b to store the current word
            // a to store previous word
            int store = 0;
            key_counter=0;
            String end = "/"; //this marks the end of a key definition in text file
            String start = "=";//this marks the start of a key definition in text file
            //int i=0;
            while (hmScan.hasNext()) {
                b = hmScan.next();
                count++;
                if (b.equals(end)) {
                    store = 0; //when store is 0. value input stops
                    value = value.replaceAll("null", "");//to remove null from value(final definition)
                    value = value.substring(1);

                    hm.put(key, value);
                    value = null;//to reset value after all input. it also prevent overwiting
                }
                if (b.equals(start)) {
                    a= a.replaceAll("_", " ");
                    key = a;

                    keyArray[key_counter]=key;//to store key into array
                    key_counter++;
                    store = 1;      //to start storing definitions
                }
                if (store == 1 && !b.equals(start))//b!= "="
                //to start storing value. also to not store the =
                {
                    value = value + " " + b;
                }
                a = b;//stores the previous string element
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getHashMapDictionary() {
        return hm;
    }public String[] getHashMapKeys() {
        return keyArray;
    }
    public String translateKeyTo(String key,String language,HashMap<String,String> hmEnglish,HashMap<String,String> hmViet,
                                 String []keyArray,String []keyArrayViet){
        //NOTE: this method relies on the positioning of the keys in the two dictionaries
        //this means key 1 in eng dic should be equal to key 1 in viet dic IN TEXT FILE

        if(language.equals("viet")) //translate from eng to viet
            for(int i=0; i<keyArray.length;i++){
                if(keyArray[i]!=null)//we have to check if its null else it will throw a nullPointerException error
                    if(keyArray[i].contains(key))
                        return hmViet.get(keyArrayViet[i]);

            }
        if(language.equals("eng"))  //translate from viet to eng
            for(int i=0; i<keyArray.length;i++){
                if(keyArray[i]!=null)//we have to check if its null else it will throw a nullPointerException error
                    if(keyArrayViet[i].contains(key))
                        return hmEnglish.get(keyArray[i]);
            }
        return "No translation";
    }
    public int getKey_counter() {
        return key_counter;
    }
}





