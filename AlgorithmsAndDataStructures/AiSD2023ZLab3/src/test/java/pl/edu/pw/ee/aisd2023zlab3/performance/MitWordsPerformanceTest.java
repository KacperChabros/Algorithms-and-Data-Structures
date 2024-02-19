package pl.edu.pw.ee.aisd2023zlab3.performance;

import pl.edu.pw.ee.aisd2023zlab3.HashOpenAddressing;
import static pl.edu.pw.ee.aisd2023zlab3.dataUtils.WordsGenerator.prepareWordsFromFile;

public abstract class MitWordsPerformanceTest extends WordsPerformanceTest {

    public MitWordsPerformanceTest(Class<? extends HashOpenAddressing> hashClass) {
        super(hashClass);
    }

    @Override
    String[] prepareWords() {
        return prepareWordsFromFile();
    }

}
