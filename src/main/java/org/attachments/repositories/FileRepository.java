package org.attachments.repositories;

import org.attachments.models.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<Attachment, String> {
	Attachment findByName(String name);
}
