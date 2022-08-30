package my.site.ithoughtilearned.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.site.ithoughtilearned.model.GeoData;
import my.site.ithoughtilearned.repository.GeoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class GeoService {

    @Autowired
    GeoDataRepository geoDataRepository;

    public GeoData save(GeoData gd) {
        return geoDataRepository.save(gd);
    }

    public List<GeoData> getAll() {
        return StreamSupport.stream(geoDataRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class GeoResponseList extends ArrayList<GeoResponse> {
        List<GeoResponse> response;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class GeoResponse {
        String status, country, countryCode, region, regionName, city, zip, lat, lon, timezone, isp, org, as, query;

    }

    public GeoData getGeoData(String ip) {
        GeoResponseList response = new GeoResponseList();
        try {
            response = new RestTemplate().postForObject("http://ip-api.com/batch", List.of(ip), GeoService.GeoResponseList.class);
        } catch (RestClientException e) {
            log.error(e.toString());
        }
        String res_String = "";
        try {
            res_String = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert payload to string" + res_String.toString());
        }
        return new GeoData(UUID.randomUUID(), ip, response.get(0).lat, response.get(0).lon, res_String);
    }
}
