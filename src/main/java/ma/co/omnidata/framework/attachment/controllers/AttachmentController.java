package ma.co.omnidata.framework.attachment.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.co.omnidata.framework.attachment.services.IAttachmentService;
import ma.co.omnidata.framework.attachment.services.exceptions.NotFoundException;
import ma.co.omnidata.framework.services.attachment.domain.dto.AttachmentDto;

@RestController
public class AttachmentController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4431842080646836475L;

	@Autowired
	IAttachmentService attachmentService;

	@RequestMapping(value = "attachments", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> upload(@RequestPart MultipartFile file, @RequestParam Long attachableId,
			@RequestParam String className, @RequestParam String appName, @RequestParam String name) throws IOException {
		return attachmentService.uploadFile(file, attachableId, className, appName, name);
	}

	@RequestMapping(value = "attachments")
	public List<AttachmentDto> getAttachments(@RequestParam Long attachableId, @RequestParam String className,
			@RequestParam String appName) throws Exception {
		return attachmentService.getAttachments(attachableId, className, appName);
	}

	@RequestMapping(value = "attachments/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getAttachmentById(@PathVariable String id)
			throws NotFoundException, DataAccessResourceFailureException {
		return attachmentService.getAttachment(id);
	}
	
	@RequestMapping(value = "attachments/{id}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteAttachment(@PathVariable String id){
		return attachmentService.deleteAttachment(id);
	}

}
