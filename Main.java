import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
        Create a RANDOMLY sized array of Strings

        For each String in the array:
            Randomly select a length for the random word (you can put lower and upper limits on the length)
            Randomly BUILD that string to represent an ACTUAL word. IE, a word that exists in out lexicon

        After you establish your array of randomly generated “real” words:
        SORT the array by 2 criteria:  from smallest words to largest words and, within these sets, sort
        lexicographically. For example:
            And
            But
            Cat
            Dog
            Melt
            None
            Pole
            Apple
            Bacon
            ...
         */

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int stringArraySize;
        File file;
        Scanner sc;
        StringBuilder stringBuilder;
        String allWords;
        String[] stringArray;

        file = new File("/Users/terrancehuang/IdeaProjects/RandomWords/words.txt");
        sc = new Scanner(file);
        stringBuilder = new StringBuilder();
        // adding text to a string
        while (sc.hasNextLine()) {
            stringBuilder.append(sc.nextLine());
        }
        allWords = stringBuilder.toString();

        // create string array with random length from 20 to 30
        stringArraySize = (int) (Math.random() * 11) + 20;
        stringArray = new String[stringArraySize];

        // select random word from words.txt
        for (int i = 0; i < stringArraySize; i++) {
            int wordIndex = (int) (Math.random() * countWords(allWords));
            stringArray[i] = allWords.split(", ")[wordIndex];
        }

        // original array
        System.out.println(Arrays.toString(stringArray));
        // sorted array
        String[] sortedArray = sortByLetters(sortByLength(stringArray));
        System.out.println(Arrays.toString(sortedArray));
    }

    public static int countWords(String words) {
        int wordCounter = 0;
        for (String ignored : words.split(", ")) { // for each word in words
            wordCounter++;
        }
        return wordCounter;
    }

    public static String[] sortByLength(String[] stringArray) {
        for (int i = 0; i < stringArray.length - 1; i++) {
            for (int j = i + 1; j < stringArray.length; j++) {
                if (stringArray[i].length() > stringArray[j].length()) {
                    String temp = stringArray[i];
                    stringArray[i] = stringArray[j];
                    stringArray[j] = temp;
                }
            }
        }
        return stringArray;
    }

    public static String[] sortByLetters(String[] stringArray) {
        for (int i = 0; i < stringArray.length - 1; i++) {
            for (int j = i + 1; j < stringArray.length; j++) {
                if (stringArray[i].length() == stringArray[j].length()) {
                    if (stringArray[i].compareTo(stringArray[j]) > 0) {
                        String temp = stringArray[i];
                        stringArray[i] = stringArray[j];
                        stringArray[j] = temp;
                    }
                } else {
                    break;
                }
            }
        }
        return stringArray;
    }
}
