package my.site.ithoughtilearned.repository;

import my.site.ithoughtilearned.model.Visitors;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface VisitorRepository extends CrudRepository<Visitors, UUID> {

}
