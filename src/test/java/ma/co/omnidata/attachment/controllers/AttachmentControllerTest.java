package ma.co.omnidata.attachment.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import ma.co.omnidata.framework.attachment.controllers.AttachmentController;
import ma.co.omnidata.framework.services.attachment.domain.Attachment;

@SpringBootTest()
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class AttachmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private AttachmentController controller;

	private Attachment mockAttachment;
	
	@Value("${spring.application.name}")
	String appName;
	
	private final Gson gson = new Gson();
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		mockAttachment = new Attachment("x","application/pdf",90232,"pdf","courses",9782254,"candidat",appName);
	}
	
	@Test
	public void verifyGetAllAttachments() throws Exception {
		RequestBuilder builder = MockMvcRequestBuilders.get("/attachments?attachableId=9782254&className=candidat&appName=attachment-service-user")
														.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		JSONAssert.assertEquals(gson.toJson(mockAttachment), result.getResponse().getContentAsString(), false);
//		System.out.println(result.getResponse().getContentAsString());
	}

}
