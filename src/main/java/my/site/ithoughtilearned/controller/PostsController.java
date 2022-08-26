package my.site.ithoughtilearned.controller;

import lombok.extern.slf4j.Slf4j;
import my.site.ithoughtilearned.model.Visitors;
import my.site.ithoughtilearned.model.dto.PostDto;
import my.site.ithoughtilearned.repository.VisitorRepository;
import my.site.ithoughtilearned.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/posts")
@CrossOrigin(originPatterns = "*/**")
public class PostsController {

    @Autowired
    PostService postService;

    @Autowired
    VisitorRepository visitorRepository;

    @GetMapping
    public ResponseEntity<List<PostDto>> getLastFewPosts(HttpServletRequest response) {
        log.info("saving visitor");
        visitorRepository.save(new Visitors(UUID.randomUUID(), response.getRemoteAddr(), LocalDateTime.now().toString()));
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping()
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto post) {
        log.info("Creating new post:", post.toString());
        return ResponseEntity.ok(postService.savePost(post));
    }

    @GetMapping("/tag/{query}")
    public ResponseEntity<List<PostDto>> getLastFewPosts(@PathVariable String query) {
        return ResponseEntity.ok(postService.searchPostsByTag(query));
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<PostDto>> searchForPosts(@PathVariable String query) {
        return ResponseEntity.ok(postService.searchPosts(query));
    }

}