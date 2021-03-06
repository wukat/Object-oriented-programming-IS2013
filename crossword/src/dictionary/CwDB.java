package dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Implementation of crossword database
 *
 * @author wukat
 *
 */
public class CwDB {

    protected LinkedList<Entry> dict = new LinkedList<>(); // dictionary - list of entries 

    /**
     * Constructor
     *
     * @param filename - name of file with date
     * @throws IOException
     */
    public CwDB(String filename) throws IOException {
        createDB(filename);
    }

    /**
     * Adds new record
     *
     * @param word
     * @param clue
     */
    public void add(String word, String clue) {
        dict.add(new Entry(word, clue));
    }

    /**
     * Getter
     *
     * @param word - parameter to identify entry
     * @return entry in dictionary with field word, null if no entry have this word
     */
    public Entry get(String word) {
        java.util.ListIterator<Entry> iter = dict.listIterator(0);
        while (iter.hasNext()) {
            Entry temp = iter.next();
            if (temp.getWord().equals(word)) {
                return temp;
            }
        }
        return null;
    }

    /**
     * Removes specified entry from dictionary
     *
     * @param word - parameter to identify entry
     */
    public void remove(String word) {
        java.util.ListIterator<Entry> iter = dict.listIterator(0);
        while (iter.hasNext()) {
            Entry temp = iter.next();
            if (temp.getWord().equals(word)) {
                iter.remove();
            }
        }
    }

    /**
     * Saves database in file
     *
     * @param filename - name of output file
     * @throws IOException
     */
    public void saveDB(String filename) throws IOException {
        try (FileWriter outputDB = new FileWriter(filename)) {
            java.util.ListIterator<Entry> iter = dict.listIterator(0);
            while (iter.hasNext()) {
                Entry temp = iter.next();
                outputDB.write(temp.toString());
            }
        }
    }

    /**
     * Getter
     *
     * @return size of dictionary (number of elements)
     */
    public int getSize() {
        return dict.size();
    }

    /**
     * Creates database from file
     *
     * @param filename - file name
     * @throws IOException
     */
    protected final void createDB(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line1;
            String line2;
            while (((line1 = br.readLine()) != null)
                    && ((line2 = br.readLine()) != null)) {
                add(line1.toUpperCase(), line2);
            }
        }
    }
}
