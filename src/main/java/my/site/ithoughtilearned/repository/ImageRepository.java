package my.site.ithoughtilearned.repository;

import my.site.ithoughtilearned.model.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;


public interface ImageRepository extends CrudRepository<Image, UUID> {
    Optional<Image> findByName(String name);
}
