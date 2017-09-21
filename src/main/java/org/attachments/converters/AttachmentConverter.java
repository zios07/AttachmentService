package org.attachments.converters;

import org.attachments.models.Attachment;
import org.attachments.vos.AttachmentVO;

public class AttachmentConverter {
	

	public static AttachmentVO entityToVo(Attachment attachment) {
		if(attachment != null) {
			return new AttachmentVO(attachment.getId(), attachment.getName(), attachment.getUploadDate(), attachment.getSize(),
					attachment.getMeaningfulName());
		}
		return null;
	}
	
	public static Attachment voToEntity(AttachmentVO vo) {
		return null;
	}
	
}
