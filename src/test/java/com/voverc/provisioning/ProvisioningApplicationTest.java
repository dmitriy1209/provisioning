package com.voverc.provisioning;

import static org.hamcrest.Matchers.containsString;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProvisioningApplicationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testDeskPhoneWithoutOverrideFragment() throws Exception {
        String macAddress = "aa-bb-cc-dd-ee-ff";
        String expectedResult = "username=john\npassword=doe\ndomain=sip.voverc.com\nport=5060\ncodecs=G711,G729,OPUS\ntimeout=null";
        test(macAddress, expectedResult);
    }
    
    @Test
    public void testDeskPhoneWithOverrideFragment() throws Exception {
        String macAddress = "a1-b2-c3-d4-e5-f6";
        String expectedResult = "username=walter\npassword=white\ndomain=sip.anotherdomain.com\nport=5161\ncodecs=G711,G729,OPUS\ntimeout=10";
    test(macAddress, expectedResult);
    }
    
    @Test
    public void testConferencePhoneWithoutOverrideFragment() throws Exception {
        String macAddress = "f1-e2-d3-c4-b5-a6";
        String expectedResult = "{\"username\":\"sofia\",\"password\":\"red\",\"domain\":\"sip.voverc.com\",\"port\":\"5060\",\"codecs\":\"G711,G729,OPUS\",\"timeout\":null}";
        test(macAddress, expectedResult);
    }
    
    @Test
    public void testConferencePhoneWithOverrideFragment() throws Exception {
        String macAddress = "1a-2b-3c-4d-5e-6f";
        String expectedResult = "{\"username\":\"eric\",\"password\":\"blue\",\"domain\":\"sip.anotherdomain.com\",\"port\":\"5161\",\"codecs\":\"G711,G729,OPUS\",\"timeout\":\"10\"}";
        test(macAddress, expectedResult);
    }
    
    @Test
    public void testIncorrectRequest() throws Exception {
        this.mockMvc.perform(get("/api/v1/provisioning/incorrect_mac_address"))
                .andDo(print())
                .andExpect(status().is(400));
    }
    
    private void test(String macAddress, String expectedResult) throws Exception {
        this.mockMvc.perform(get("/api/v1/provisioning/" + macAddress))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)));
    }

}
