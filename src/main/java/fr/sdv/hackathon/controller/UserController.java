package fr.sdv.hackathon.controller;

import fr.sdv.hackathon.model.User;
import fr.sdv.hackathon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public boolean signup(@RequestParam String email, @RequestParam String password) {
        return userService.add(email, password);
    }

    @GetMapping("/user/owner")
    public User getOwner() {
        return userService.getConnected();
    }

    @GetMapping("/user/random")
    public User random() {
        return userService.getRandom();
    }

    @PutMapping("/user")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

}
