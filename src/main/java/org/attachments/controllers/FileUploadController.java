package org.attachments.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.attachments.models.Attachment;
import org.attachments.services.AttachmentService;
import org.attachments.vos.AttachmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value="/api/v1")
public class FileUploadController {

	@Autowired
	AttachmentService attachmentService;
	
	@RequestMapping(value = "/attachments", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadFile(@RequestPart(required = false) MultipartFile file, @RequestPart Attachment attachment) throws Exception {
		attachmentService.uploadFile(file, attachment); 
	}

	@RequestMapping(value = "/attachments")
	public List<AttachmentVO> getAttachments() {
		return attachmentService.getAttachments();
	}

	@RequestMapping(value = "/attachments", method = RequestMethod.POST)
	public void deleteAttachment(@RequestBody String id) {
		attachmentService.deleteAttachment(id);
	}
	
	@RequestMapping(value = "/attachments/{id}")
	public AttachmentVO getAttachmentById(@PathVariable String id) {
		return attachmentService.getAttachmentById(id);
	}

	@RequestMapping(value = "/download/{filename:.+}")
	public void downloadAttachment(@PathVariable String filename, HttpServletResponse response) throws IOException {
		attachmentService.downloadAttachment(filename, response);
	}
	
}
