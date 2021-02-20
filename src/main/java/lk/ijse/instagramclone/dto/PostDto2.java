package lk.ijse.instagramclone.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PostDto2 {
    private Integer userId;
    private String post;
    private String postDate;
    private List<CommentDto> commentDtos;

    public PostDto2(Integer userId, String post, String postDate, List<CommentDto> commentDtos) {
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
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
