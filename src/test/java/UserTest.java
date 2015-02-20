import com.mprojection.entity.User;
import com.mprojection.entity.UserType;
import com.mprojection.serializer.JSONSerializer;
import com.mprojection.serializer.StreamSerializer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by Дмитрий on 2/19/2015.
 */
public class UserTest {

    @Test
    public void test() {
        StreamSerializer serializer = new JSONSerializer();
        User user = new User();
        user.setId(1);
        user.setType(UserType.ZOMBIE);
        String s = serialize(serializer, user);
        System.out.println(s);
        User user2 = deserialize(serializer, s, User.class);
        System.out.println(user2.isHuman());
        System.out.println(user2.isZombie());
    }

    private String serialize(StreamSerializer serializer, Object o) {
        OutputStream stream = new ByteArrayOutputStream();
        serializer.serialize(stream, o);
        return stream.toString();
    }

    private <T> T deserialize(StreamSerializer serializer, String s, Class<T> c) {
        InputStream stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        return serializer.deserialize(stream, c);
    }

}
