package pl.edu.pw.ee.aisd2023zlab4.performance;

import java.util.UUID;
import pl.edu.pw.ee.aisd2023zlab4.services.MapInterface;


public class RbtMapRandomPerformanceTest extends RbtMapPerformanceTest{
    
    
    @Override
    void putData(MapInterface<String, String> map, int nOfData) {
        String keyAndValue;
        for (int i = 0; i < nOfData; i++) {
            keyAndValue = UUID.randomUUID().toString();
            map.setValue(keyAndValue, keyAndValue);
        }
    }
}
