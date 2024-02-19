package pl.edu.pw.ee.aisd2023zlab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab2.services.HashTable;

public class PerformanceTest {

    private static final int N_VARIANTS = 10;
    private static final int INIT_SIZE = 4096;
    private static final String DEFAULT_PATH_TO_FILE_WITH_WORDS = "src/main/resources/words.txt";
    private static final int N_OF_REPETITIONS = 30;
    private static final int NUM_OF_AVG_VALUES = 10;

    @Test
    public void runPerformanceTestOnNVariantsOfSize() {
        int[] hashSizes = getAllHashSizes();
        List<String> words = getAllWordsFromDefaultTxtFile();
        HashTable hash;
        long averageTime;

        System.out.println("Average time for");

        for (int i = 0; i < N_VARIANTS; i++) {
            hash = prepareHashWithSizeAndFillIt(hashSizes[i], words);
            averageTime = measureAverageTimeOfGettingWords(words, hash);

            System.out.println(String.format("\t size = %7d | avg_time = %9d", hashSizes[i], averageTime));
        }

        assert true;
    }

    private int[] getAllHashSizes() {
        int n = N_VARIANTS;
        int[] hashSizes = new int[]{4099, 8089, 16273, 32633, 65293, 130973, 261887, 526619, 1048391, 2096909};
//        int[] hashSizes = new int[n];
//        int initSize = INIT_SIZE;
//        int multiplier;
//
//        for (int i = 0; i < n; i++) {
//            multiplier = (int) Math.pow(2, i);
//            hashSizes[i] = initSize * multiplier;
//        }
        return hashSizes;
    }

    private List<String> getAllWordsFromDefaultTxtFile() {
        List<String> words = new ArrayList<>();
        try (FileReader fileReader = new FileReader(DEFAULT_PATH_TO_FILE_WITH_WORDS);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String singleWord;

            while ((singleWord = bufferedReader.readLine()) != null) {
                words.add(singleWord);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }

    private HashTable prepareHashWithSizeAndFillIt(int size, List<String> words) {
        HashTable hash = new HashListChainingModularHashing(size);
        for (String word : words) {
            hash.add(word);
        }
        return hash;
    }

    private long measureAverageTimeOfGettingWords(List<String> words, HashTable hash) {
        long[] results = new long[N_OF_REPETITIONS];

        for (int i = 0; i < N_OF_REPETITIONS; i++) {
            results[i] = measureTimeOfGettingWords(words, hash);
        }

        long avgMeasuredTime = countAverageFromCenter10Values(results);

        return avgMeasuredTime;
    }

    private long measureTimeOfGettingWords(List<String> words, HashTable hash) {
        int n = words.size();
        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            hash.get(words.get(i));
        }

        long totalTime = System.nanoTime() - startTime;

        return totalTime;
    }

    private long countAverageFromCenter10Values(long[] results) {
        int startId = (N_OF_REPETITIONS - NUM_OF_AVG_VALUES) / 2;
        int endId = startId + NUM_OF_AVG_VALUES;

        long avgValue = 0;

        for (int i = startId; i < endId; i++) {
            avgValue += results[i];
        }

        avgValue /= NUM_OF_AVG_VALUES;

        return avgValue;
    }

}
