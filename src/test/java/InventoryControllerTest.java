import com.mprojection.serializer.JSONSerializer;
import com.mprojection.serializer.ObjectSerializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml", "classpath:servletConfig.xml"})
@WebAppConfiguration
public class InventoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectSerializer serializer;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        serializer = new JSONSerializer();
    }

    @Test
    public void add() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/inventory/").
                param("itemType", "Lighter").
                param("itemDescription", "Lighter can be used to fire zombie").
                param("barCode", "4820063390360").
                param("userId", "5"))
                .andReturn();
        System.out.println(result);
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
