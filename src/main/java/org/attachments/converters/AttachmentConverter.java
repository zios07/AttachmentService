package org.attachments.converters;

import org.attachments.models.Attachment;
import org.attachments.vos.AttachmentVO;

public class AttachmentConverter {
	

	public static AttachmentVO entityToVo(Attachment attachment) {
		return new AttachmentVO(attachment.getName(), attachment.getUploadDate(), attachment.getSize(),
				attachment.getMeaningfulName());
	}
	
	public static Attachment voToEntity(AttachmentVO vo) {
		return null;
	}
	
	

}
