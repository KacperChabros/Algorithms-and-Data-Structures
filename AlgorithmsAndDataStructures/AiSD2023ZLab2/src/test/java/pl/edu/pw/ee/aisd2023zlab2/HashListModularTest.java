package pl.edu.pw.ee.aisd2023zlab2;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.edu.pw.ee.aisd2023zlab2.utils.GeneralHashListChainingTest;

public class HashListModularTest extends  GeneralHashListChainingTest{

    @Before
    public void setup() {
        hashString = new HashListMultiplicative<>();
    }
}
