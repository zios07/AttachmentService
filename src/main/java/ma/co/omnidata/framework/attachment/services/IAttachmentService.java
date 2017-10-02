package ma.co.omnidata.framework.attachment.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ma.co.omnidata.framework.services.attachment.domain.dto.AttachmentDto;

public interface IAttachmentService {
	
	List<AttachmentDto> getAttachments(Long attachableId, String className, String appName);
	
	void uploadFile(MultipartFile file, Long attachableId, String className, String appName) throws Exception;
	
	byte[] getAttachmentContentById(String id);
	
	
}
