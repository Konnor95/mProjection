import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * Created by Дмитрий on 2/16/2015.
 */
public class HEXTest {

    @Test
    public void test(){
        System.out.println(DigestUtils.sha256Hex("210194dima"));
    }
}
