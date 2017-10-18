package ma.co.omnidata.framework.attachment.services;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import ma.co.omnidata.framework.attachment.services.exceptions.NotFoundException;
import ma.co.omnidata.framework.services.attachment.domain.dto.AttachmentDto;

public interface IAttachmentService {

	List<AttachmentDto> getAttachments(Long attachableId, String className, String appName) throws Exception;

	ResponseEntity<String> uploadFile(MultipartFile file, Long attachableId, String className, String appName, String name)
			throws IOException;

	ResponseEntity<byte[]> getAttachment(String id) throws NotFoundException, DataAccessResourceFailureException;

	ResponseEntity<String> deleteAttachment(String id);

}
