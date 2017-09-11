package org.attachments.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.attachments.models.Attachment;
import org.attachments.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	@Autowired
	AttachmentService attachmentService;
	
	@RequestMapping(value = "/attachments", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void uploadFile(@RequestPart(required = false) MultipartFile file, @RequestPart String meanfulName) throws Exception {
		attachmentService.uploadFile(file, meanfulName);
	}

	@RequestMapping(value = "/attachments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Attachment> getAttachments() {
		return attachmentService.getAttachments();
	}

	@RequestMapping(value = "/attachments/{id}", method = RequestMethod.GET)
	public void deleteAttachment(@PathVariable String id) {
		attachmentService.deleteAttachment(id);
	}

	@RequestMapping(value = "/download/{filename:.+}", method = RequestMethod.GET)
	public void downloadAttachment(@PathVariable String filename, HttpServletResponse response) throws IOException {
		attachmentService.downloadAttachment(filename, response);
	}

}
