package ma.co.omnidata.framework.attachment.services.impl;

import static ma.co.omnidata.framework.services.attachment.domain.converter.AttachmentConverter.entitiesToDtos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ma.co.omnidata.framework.attachment.repositories.AttachmentRepository;
import ma.co.omnidata.framework.attachment.services.IAttachmentService;
import ma.co.omnidata.framework.services.attachment.domain.Attachment;
import ma.co.omnidata.framework.services.attachment.domain.dto.AttachmentDto;

@Service
public class AttachmentService implements IAttachmentService {

	@Autowired
	AttachmentRepository repo;

	public List<AttachmentDto> getAttachments(Long attachableId, String className, String appName) {
		return entitiesToDtos(repo.findByEntityIdAndClassNameAndAppName(attachableId, className, appName));
	}

	public void uploadFile(MultipartFile file, Long attachableId, String className, String appName) throws Exception {

		if (file != null) {

			// Retrieving file extension and file name
			String fullFileName = file.getOriginalFilename();
			String fileExtension = fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
			String fileName = fullFileName.substring(0, fullFileName.lastIndexOf("."));

			Attachment attachment = new Attachment();

			attachment.setName("");
			attachment.setMime(file.getContentType());
			attachment.setAppName(appName);
			attachment.setClassName(className);
			attachment.setEntityId(attachableId);
			attachment.setFileContent(file.getBytes());
			attachment.setFileName(fileName);
			attachment.setFileExtension(fileExtension);
			attachment.setSize(file.getSize());

			repo.save(attachment);
		}

	}

	public byte[] getAttachmentContentById(String id) {
		Attachment attachment = repo.findOne(id);
		if (attachment != null) {
			return attachment.getFileContent();
		}
		return null;
	}

	// public void downloadAttachment(String filename, HttpServletResponse response)
	// throws IOException {

	// try {
	// Attachment attachment = repo.findByName(filename);
	//
	// if (attachment != null) {
	// InputStream input = new ByteArrayInputStream(attachment.getFile());
	//
	// IOUtils.copy(input, response.getOutputStream());
	// response.flushBuffer();
	// } else {
	// System.out.println("Attachment is null !!");
	// }
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }

	// }

}
