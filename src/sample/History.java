package sample;

import java.io.*;
import java.util.Scanner;

public class History {
    File file = new File("src/sample/Keyword/history.txt");

    public String[] getBookmark() {
        String[] value_arr = new String[200];
        String value = null;
        try {
            Scanner themeScan = new Scanner(file);
            String key = null, a = null, b = null;
            String end = "//";
            String start = "bookmark=";
            int store = 0, i = 0;

            while (themeScan.hasNext()) {
                b = themeScan.next().toLowerCase();

                if (b.equals(end)) {
                    store = 0; //when store is 0. value input stops
                    value = value.replaceAll("null", "");//to remove null from value(final definition)
                    value = value.replaceAll("bookmark=", "");//to remove null from value(final definition)
                    value = value.replaceAll("_", " ");

                    value_arr[i] = value;
                    value = null;
                    i++;
                }
                if (b.contains(start)) {
                    store = 1;      //to start storing theme data
                }
                if (store == 1)
                //to start storing theme data.
                {
                    value = value + " " + b;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // theme=value;
        return value_arr;
    }

    @SuppressWarnings("Duplicates")
    public void setBookmark(String bookmark) {
        BufferedReader br = null;
        FileReader fr = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        String old_content = "";
        String new_content = "";
        String replacement = null;
        bookmark = bookmark.replaceAll(" ", "_");
        int i = 0;
        try {
            //open the current file for reading
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                //copy the file to a string
                old_content = old_content + line + System.lineSeparator();//to store the old content
                line = br.readLine();
            }
            old_content = old_content.replaceAll("  ", "");
            //Replacing oldString with newString in the oldContent
            //string to array
            String[] old_content_array = old_content.split(" ");
            for (int j = 0; j < old_content_array.length; j++) {
                //if(old_content_array!=null)
                if (old_content_array[j].contains("bookmarkstart")) {
                    replacement = "bookmark=" + bookmark + " //";
                    old_content_array[j] = replacement;
                } else if (old_content_array[j].contains("bookmark=") && old_content_array[j + 1].contains("//")) {
                    try {
                        if (old_content_array[j + 2] == null && old_content_array[j + 3] == null && old_content_array[j + 4] == null)
                            System.out.println("ready to store");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(old_content_array[j] + "before");
                        replacement = old_content_array[j] + " // bookmark=" + bookmark;
                        old_content_array[j] = replacement;
                    }
                }
                System.err.println(old_content_array[j]);
                //array to string
                String str = String.join(" ", old_content_array);
                new_content = str;
            }
            //writing the modified data to the new file
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            if (new_content != null)
                bw.write(new_content);
            bw.close();
            new_content = null;
            old_content_array = null;
            old_content = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void deleteBookmark(String item) {
        BufferedReader br = null;
        FileReader fr = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        String old_content = "";
        String new_content = "";
        String replacement = null;
        if (item.charAt(0) == ' ') {
            StringBuilder sb = new StringBuilder(item);
            sb.deleteCharAt(0);
            item = sb.toString();
        }

        item = item.replaceAll(" ", "_");
        System.err.println(item);
        int i = 0;
        try {
            //open the current file for reading
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                //copy the file to a string
                old_content = old_content + line + System.lineSeparator();//to store the old content
                line = br.readLine();
            }
            //Replacing oldString with newString in the oldContent
            //string to array
            String[] old_content_array = old_content.split(" ");

            for (int j = 0; j < old_content_array.length; j++) {

                if (old_content_array[j] != null)
                    if (old_content_array[j].contains(item)) {
                        System.err.println("It contains Item");
                        old_content_array[j] = "null";
                        old_content_array[j + 1] = "null";
                    }
            }
            //array to string
            String str = String.join(" ", old_content_array);
            str = str.replaceAll("null", "");

            for (int j = 0; j < old_content_array.length; j++) {
                if (old_content_array[j].equals("bookmark") && old_content_array[j + 1] == null) {
                    old_content_array[j] = old_content_array[j] + " bookmarkstart";
                    str = String.join(" ", old_content_array);
                    str = str.replaceAll("null", "");
                }
            }
            new_content = str;
            //writing the modified data to the new file
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            if (new_content != null)
                bw.write(new_content);
            bw.close();
            new_content = null;
            old_content_array = null;
            old_content = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
