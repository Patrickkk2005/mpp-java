package testing;

import org.junit.Test;
import eu.ase.poly.Auto;
import org.junit.Assert;

public class JUnitEva {
    @Test
    public void testAutoSetDoorsNoLt0() throws Exception{
        Auto auto=new Auto();
        try{
            auto.setDoorsNo(-5);
            Assert.fail("setDoorsNo accepts neg val!");
        } catch (Exception e){
            //OK!
        }
    }

    @Test
    public void testSetDoorsNo(){
        Auto auto= new Auto();
        try{
            auto.setDoorsNo(4);
            Assert.assertEquals(4,auto.getDoorsNo());
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
