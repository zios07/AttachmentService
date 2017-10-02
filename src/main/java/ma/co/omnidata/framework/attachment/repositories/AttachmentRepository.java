package ma.co.omnidata.framework.attachment.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ma.co.omnidata.framework.services.attachment.domain.Attachment;

@Repository
public interface AttachmentRepository extends MongoRepository<Attachment, String>{

	List<Attachment> findByEntityIdAndClassNameAndAppName(Long attachableId, String className, String appName);

}
