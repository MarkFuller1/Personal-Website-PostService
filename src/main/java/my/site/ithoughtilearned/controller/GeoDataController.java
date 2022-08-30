package my.site.ithoughtilearned.controller;

import my.site.ithoughtilearned.model.GeoData;
import my.site.ithoughtilearned.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/geoData")
public class GeoDataController {

    @Autowired
    GeoService geoService;

    @GetMapping
    public Map<String, List<GeoData>> getAllGeoData() {
        return geoService.getAll().stream().collect(Collectors.groupingBy(geo -> geo.ip + "|" + geo.lat + "|" + geo.lon));
    }
}
