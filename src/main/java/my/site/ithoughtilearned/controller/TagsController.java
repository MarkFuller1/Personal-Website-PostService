package my.site.ithoughtilearned.controller;

import lombok.extern.slf4j.Slf4j;
import my.site.ithoughtilearned.service.PostService;
import my.site.ithoughtilearned.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/tags")
@CrossOrigin(originPatterns = "*/**")
public class TagsController {

    @Autowired
    PostService postService;

    @Autowired
    TagService tagService;

    @GetMapping
    public ResponseEntity<List<String>> getAllTags() {
        return ResponseEntity.ok(postService.getAllTags());
    }

    @PostMapping
    public ResponseEntity<String> createNewTag(@RequestBody String tag) {
        return ResponseEntity.ok(tagService.saveTag(tag));
    }
}
