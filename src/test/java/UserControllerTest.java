import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.entity.UserType;
import com.mprojection.serializer.JSONSerializer;
import com.mprojection.serializer.ObjectSerializer;
import com.mprojection.weather.Weather;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml", "classpath:servletConfig.xml"})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectSerializer serializer;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        serializer = new JSONSerializer();
    }

//    @Test
//    public void updateAbilities() throws Exception {
//        String lat = "50.0260317";
//        String lng = "36.2249179";
//        String measureUnit = "1";
//        String timeZone = "GMT+02:00";
//        MvcResult result = mockMvc.perform(post("/api/user/updateAbilities")
//                .param("lat", lat)
//                .param("lng", lng)
//                .param("measureUnit", measureUnit)
//                .param("timeZone", timeZone))
//                .andExpect(status().isOk())
//                .andReturn();
//        Weather weather = deserialize(result, Weather.class);
//        System.out.println(weather);
//    }

    @Test
    public void add() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/user/").
                param("firstName", "Vladimir").
                param("lastName", "Gritsenko").
                param("gender", "0").
                param("login", "Vladimir Gritsenko")
                .param("facebookToken", "1102682479758112")
                .param("appleToken", "simulator")
                .param("lang", "ru")
                .param("type", "SOLDIER"))
                .andReturn();
        FullUserInfo user = deserialize(result, FullUserInfo.class);
        System.out.println(user);
    }

    private String serialize(Object o) {
        OutputStream stream = new ByteArrayOutputStream();
        serializer.serialize(stream, o);
        return stream.toString();
    }

    private <T> T deserialize(MvcResult result, Class<T> c) {
        byte[] bytes = result.getResponse().getContentAsByteArray();
        InputStream res = new ByteArrayInputStream(bytes);
        return serializer.deserialize(res, c);
    }

}
