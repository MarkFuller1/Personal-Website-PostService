package my.site.ithoughtilearned.repository;

import my.site.ithoughtilearned.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<Tag, UUID> {
}
