package pl.edu.pw.ee.aisd2023zlab4.performance;

import pl.edu.pw.ee.aisd2023zlab4.services.MapInterface;


public class RbtMapAscendingPerformanceTest extends RbtMapPerformanceTest{

    @Override
    void putData(MapInterface<String, String> map, int nOfData) {
        String keyAndValue;
        for(int i=0; i<nOfData; i++)
        {
                keyAndValue = String.valueOf(prefix + ascending);
                map.setValue(keyAndValue, keyAndValue);
                ascending++;
        }
    }
    
}
