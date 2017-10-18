package ma.co.omnidata.framework.attachment.services.impl;

import static ma.co.omnidata.framework.services.attachment.domain.converter.AttachmentConverter.entitiesToDtos;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ma.co.omnidata.framework.attachment.repositories.AttachmentRepository;
import ma.co.omnidata.framework.attachment.services.IAttachmentService;
import ma.co.omnidata.framework.attachment.services.exceptions.NotFoundException;
import ma.co.omnidata.framework.attachment.utils.ErrorCodeConstants;
import ma.co.omnidata.framework.services.attachment.domain.Attachment;
import ma.co.omnidata.framework.services.attachment.domain.dto.AttachmentDto;

@Service
public class AttachmentService implements IAttachmentService {

	@Autowired
	AttachmentRepository repo;

	@Override
	public List<AttachmentDto> getAttachments(Long attachableId, String className, String appName) throws Exception {
		return entitiesToDtos(repo.findByEntityIdAndClassNameAndAppName(attachableId, className, appName));
	}

	@Override
	public ResponseEntity<String> uploadFile(MultipartFile file, Long attachableId, String className, String appName, String name) throws IOException
			 {

		if (file != null) {
			String fullFileName = file.getOriginalFilename();
			String fileExtension = fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
			String fileName = fullFileName.substring(0, fullFileName.lastIndexOf("."));

			Attachment attachment = new Attachment(name, file.getContentType(), file.getSize(), fileExtension, fileName,
					attachableId, className, file.getBytes(), appName);
			repo.save(attachment);
			return new ResponseEntity<String>("Attachment uploaded successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Attachment file cannot be empty", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<byte[]> getAttachment(String id) throws NotFoundException, DataAccessResourceFailureException {
		Attachment attachment = repo.findOne(id);
		if (attachment == null) {
			throw new NotFoundException(ErrorCodeConstants.ATTACHMENT_NOT_FOUND, "Attachment not found");
		}
		byte[] file = attachment.getFileContent();
		String mimeType = attachment.getMime();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(mimeType));

		String disposition = "attachment";

//		 If the attachment is a PDF document, it will open it in the browser
		if ("application/pdf".equalsIgnoreCase(mimeType)) {
			disposition = "inline";
		}
		headers.add("content-disposition",
				disposition + "; filename=\"" + attachment.getFileName() + "." + attachment.getFileExtension()+"\"");
		return new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteAttachment(String id) {
		repo.delete(id);
		return new ResponseEntity<String>("Attachment deleted", HttpStatus.OK);
	}

}
