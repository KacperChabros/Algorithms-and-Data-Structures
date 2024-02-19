package pl.edu.pw.ee.aisd2023zlab3.performance;

import pl.edu.pw.ee.aisd2023zlab3.HashOpenAddressing;
import static pl.edu.pw.ee.aisd2023zlab3.dataUtils.WordsGenerator.prepareRandomWords;

public abstract class RandWordsPerformanceTest extends WordsPerformanceTest {

    public RandWordsPerformanceTest(Class<? extends HashOpenAddressing> hashClass) {
        super(hashClass);
    }

    @Override
    String[] prepareWords() {
        return prepareRandomWords();
    }

}
