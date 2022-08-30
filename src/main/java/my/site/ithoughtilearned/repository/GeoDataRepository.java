package my.site.ithoughtilearned.repository;

import my.site.ithoughtilearned.model.GeoData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GeoDataRepository extends CrudRepository<GeoData, UUID> {
}
