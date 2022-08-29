package my.site.ithoughtilearned.controller;

import my.site.ithoughtilearned.model.Visitors;
import my.site.ithoughtilearned.repository.VisitorRepository;
import my.site.ithoughtilearned.service.VisitorCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    VisitorRepository visitorRepository;

    @GetMapping
    public ResponseEntity<Map<String, List<String>>> getVisitors(){
        return ResponseEntity.ok(StreamSupport.stream(visitorRepository.findAll().spliterator(), false).collect(Collectors.groupingBy(Visitors::getIp, new VisitorCollector())));
    }
}
