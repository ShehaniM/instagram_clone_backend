package lk.ijse.instagramclone.controller;

import lk.ijse.instagramclone.dto.CommentDto;
import lk.ijse.instagramclone.entity.Comment;
import lk.ijse.instagramclone.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/instagram/comment")
public class CommentController {
    @Autowired
    CommentRepo commentRepo;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveComment(@RequestBody CommentDto comment) {
        commentRepo.save(new Comment(comment.getPostId(), comment.getUserId(), comment.getComment()));
        return new ResponseEntity(HttpStatus.OK);
    }
}
