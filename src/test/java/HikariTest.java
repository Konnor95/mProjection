import com.mprojection.db.connection.HikariCPManager;
import org.junit.Test;

/**
 * Created by Дмитрий on 2/20/2015.
 */
public class HikariTest {

    @Test
    public void test() {
        HikariCPManager manager = new HikariCPManager();
        manager.getConnection();
        manager.shutdown();
    }
}
