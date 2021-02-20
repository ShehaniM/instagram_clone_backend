package lk.ijse.instagramclone.controller;

import lk.ijse.instagramclone.dto.CommentDto;
import lk.ijse.instagramclone.dto.PostDto;
import lk.ijse.instagramclone.dto.PostDto2;
import lk.ijse.instagramclone.entity.Comment;
import lk.ijse.instagramclone.entity.Post;
import lk.ijse.instagramclone.repository.CommentRepo;
import lk.ijse.instagramclone.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author : Yasindu Sathsara <yasindusathsara@gmail.lk>
 * @since : 12/19/20
 **/

@RestController
@RequestMapping(path = "/api/instagram/post")
public class PostController {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity savePost(@ModelAttribute PostDto post) {
        postRepo.save(new Post(post.getUserId(), post.getPost().getOriginalFilename(), post.getPostDate()));
        uploadData(post.getPost());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDto2> getAllPost() {
        ArrayList<PostDto2> all = new ArrayList<>();
        ArrayList<CommentDto> allComment = new ArrayList<>();
        for (Post p : postRepo.findAll()) {
            for (Comment c : commentRepo.findAllByPostId(p.getId())) {
                allComment.add(new CommentDto(c.getPostId(), c.getUserId(), c.getComment()));
            }
            all.add(new PostDto2(p.getUserId(), getUploadedData(p.getPost()), p.getPostDate(), allComment));
        }
        return all;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDto2> getAllPostsByUser(@PathVariable("id") Integer id) {
        ArrayList<PostDto2> all = new ArrayList<>();
        ArrayList<CommentDto> allComment = new ArrayList<>();
        for (Post p : postRepo.findAllPostByUser(id)) {
            for (Comment c : commentRepo.findAllByPostId(p.getId())) {
                allComment.add(new CommentDto(c.getPostId(), c.getUserId(), c.getComment()));
            }
            all.add(new PostDto2(p.getUserId(), getUploadedData(p.getPost()), p.getPostDate(), allComment));
        }
        return all;
    }

    public String uploadData(MultipartFile file) {

        File uploadedFile = new File(file.getOriginalFilename());

        try (OutputStream os = new FileOutputStream(uploadedFile)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String bucketName = "demo-ijse";
        String objectKey = uploadedFile.getName();


        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        PutObjectResponse response = s3.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .build(),
                RequestBody.fromFile(uploadedFile)
        );

        System.out.println(response.eTag());

        return "DONE";
    }

    public String getUploadedData(String key) {
        String bucketName = "demo-ijse";
        String objectKey = key;
        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        ResponseBytes<GetObjectResponse> responseBytes = s3.getObject(GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build(), ResponseTransformer.toBytes());
        String encodedString = Base64.getEncoder().encodeToString(responseBytes.asByteArray());
        return encodedString;
    }
}
