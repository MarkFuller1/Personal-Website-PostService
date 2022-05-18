package my.site.ithoughtilearned.controller;

import lombok.extern.slf4j.Slf4j;
import my.site.ithoughtilearned.model.dto.PostDto;
import my.site.ithoughtilearned.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@CrossOrigin(originPatterns = "*/**")
public class PostsController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getLastFewPosts() {
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