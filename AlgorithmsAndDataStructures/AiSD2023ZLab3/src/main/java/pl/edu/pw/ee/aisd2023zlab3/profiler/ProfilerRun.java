package pl.edu.pw.ee.aisd2023zlab3.profiler;

import pl.edu.pw.ee.aisd2023zlab3.HashDoubleHashing;
import static pl.edu.pw.ee.aisd2023zlab3.dataUtils.WordsGenerator.prepareRandomWords;
import pl.edu.pw.ee.aisd2023zlab3.services.HashTable;

public class ProfilerRun {

    public static void main(String[] args) {
        int size = 524_413; //{4095, 8191, 16_383, 32_771, 65_927, 131_357, 263_293, 524_413};

        String[] words = prepareRandomWords(); // prepareWordsFromFile();


        HashTable<String> hash = new HashDoubleHashing<>(size);
        //HashTable<String> hash = new HashLinearProbing<>(size); // new HashDoubleHashing<>(size);

        putWordsIntoHash(hash, words);
    }

    private static void putWordsIntoHash(HashTable<String> hash, String[] words) {
        int n = words.length;

        for (int i = 0; i < n; i++) {
            hash.put(words[i]);
        }
    }

}
