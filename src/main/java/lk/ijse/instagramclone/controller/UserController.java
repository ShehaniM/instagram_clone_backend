package lk.ijse.instagramclone.controller;

import lk.ijse.instagramclone.dto.UserDto;
import lk.ijse.instagramclone.entity.User;
import lk.ijse.instagramclone.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/instagram")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register_user(@RequestBody UserDto user) {
        if (userRepo.existsUserByEmail(user.getEmail())) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else {
            userRepo.save(new User(user.getEmail(), user.getPassword()));
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
