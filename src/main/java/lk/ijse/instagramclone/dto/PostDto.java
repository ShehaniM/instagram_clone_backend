package lk.ijse.instagramclone.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PostDto {
    private Integer userId;
    private MultipartFile post;
    private String postDate;
    private List<CommentDto> commentDtos;

    public PostDto() {
    }

    public PostDto(Integer userId, MultipartFile post, String postDate) {
        this.userId = userId;
        this.post = post;
        this.postDate = postDate;
    }

    public PostDto(Integer userId, MultipartFile post, String postDate, List<CommentDto> commentDtos) {
        this.userId = userId;
        this.post = post;
        this.postDate = postDate;
        this.commentDtos = commentDtos;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public MultipartFile getPost() {
        return post;
    }

    public void setPost(MultipartFile post) {
        this.post = post;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public List<CommentDto> getCommentDtos() {
        return commentDtos;
    }

    public void setCommentDtos(List<CommentDto> commentDtos) {
        this.commentDtos = commentDtos;
    }
}
