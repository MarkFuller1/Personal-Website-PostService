package my.site.ithoughtilearned.service;

import lombok.extern.slf4j.Slf4j;
import my.site.ithoughtilearned.model.GeoData;
import my.site.ithoughtilearned.model.Visitors;
import my.site.ithoughtilearned.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VisitorService {

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    GeoService geoService;

    public Visitors save(Visitors v) {
        GeoData gd = geoService.getGeoData(v.ip);

        log.info(gd.toString());

        geoService.save(gd);

        return visitorRepository.save(v);
    }
}
