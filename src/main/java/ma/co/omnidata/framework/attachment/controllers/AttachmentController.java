package ma.co.omnidata.framework.attachment.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.co.omnidata.framework.attachment.services.impl.AttachmentService;
import ma.co.omnidata.framework.services.attachment.domain.dto.AttachmentDto;

@RestController
public class AttachmentController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4431842080646836475L;

	@Autowired
	AttachmentService attachmentService;

	@RequestMapping(value = "attachments", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void upload(@RequestPart MultipartFile file, @RequestParam Long attachableId, @RequestParam String className, @RequestParam String appName) throws Exception {
		attachmentService.uploadFile(file, attachableId, className, appName);
	}
	
	//GET request doesn't accept request body -> send details as RequestParams
	@RequestMapping(value = "attachments")
	public List<AttachmentDto> getAttachments(@RequestParam Long attachableId, @RequestParam String className, @RequestParam String appName) {
		return attachmentService.getAttachments(attachableId, className, appName);
	}
	
	@RequestMapping(value = "attachments/{id}")
	public byte[] getAttachmentById(@PathVariable String id) {
		return attachmentService.getAttachmentContentById(id);
	}

}
