package pl.edu.pw.ee.aisd2023zlab4.performance;

import pl.edu.pw.ee.aisd2023zlab4.services.MapInterface;


public class RbtMapDescendingPerformanceTest extends RbtMapPerformanceTest{

    @Override
    void putData(MapInterface<String, String> map, int nOfData) {
        String keyAndValue;
        for(int i=nOfData; i>=0; i--)
        {
                keyAndValue = String.valueOf(prefix + descending);
                map.setValue(keyAndValue, keyAndValue);
                descending--;
        }
    }
    
}
