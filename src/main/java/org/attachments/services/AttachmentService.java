package org.attachments.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.attachments.converters.AttachmentConverter.entityToVo;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.attachments.models.Attachment;
import org.attachments.repositories.FileRepository;
import org.attachments.vos.AttachmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentService {
	
	@Autowired
	FileRepository repo;
	
	public List<AttachmentVO> getAttachments() {
		List<AttachmentVO> list = new ArrayList<>();
		for (Attachment attachment : repo.findAll()) {
			list.add(entityToVo(attachment));
		}
		return list;
	}
	
	public void uploadFile(MultipartFile file, Attachment attachment) throws Exception {

		if (file != null) {

			if (repo.findByName(file.getOriginalFilename()) != null) {
				throw new Exception("Fichier existe déja !");
			} else {

				attachment.setFile(file.getBytes());
				attachment.setName(file.getOriginalFilename());
				attachment.setSize(file.getSize());
				repo.save(attachment);
			}

		} else {
			System.out.println("File is null !!! :/");
		}

	}

	public void deleteAttachment(String id) {
		repo.delete(id);
	}
	
	public void downloadAttachment(String filename, HttpServletResponse response) throws IOException {

		try {
			Attachment attachment = repo.findByName(filename);

			if (attachment != null) {
				InputStream input = new ByteArrayInputStream(attachment.getFile());

				IOUtils.copy(input, response.getOutputStream());
				response.flushBuffer();
			} else {
				System.out.println("Attachment is null !!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
}
