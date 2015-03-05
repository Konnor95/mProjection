import com.mprojection.entity.PublicUserInfo;
import com.mprojection.entity.UserType;
import com.mprojection.serializer.JSONSerializer;
import com.mprojection.serializer.ObjectSerializer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UserTest {

    @Test
    public void test() {
        ObjectSerializer serializer = new JSONSerializer();
        PublicUserInfo user = new PublicUserInfo();
        user.setId(1);
        user.setType(UserType.ZOMBIE);
        String s = serialize(serializer, user);
        System.out.println(s);
        PublicUserInfo user2 = deserialize(serializer, s, PublicUserInfo.class);
        System.out.println(user2.isHuman());
        System.out.println(user2.isZombie());
    }

    private String serialize(ObjectSerializer serializer, Object o) {
        OutputStream stream = new ByteArrayOutputStream();
        serializer.serialize(stream, o);
        return stream.toString();
    }

    private <T> T deserialize(ObjectSerializer serializer, String s, Class<T> c) {
        InputStream stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        return serializer.deserialize(stream, c);
    }

}
