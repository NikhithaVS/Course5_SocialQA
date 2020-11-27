package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.User;
import com.upgrad.quora.api.model.UserAuth;
import com.upgrad.quora.api.service.UserAuthService;
import com.upgrad.quora.api.service.UserService;
import com.upgrad.quora.service.business.JwtTokenProvider;
import com.upgrad.quora.service.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.*;

import static java.time.ZonedDateTime.now;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthService userAuthService;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
    }

    @RequestMapping(value = "/user/signin", method = RequestMethod.POST)
    public HashMap<String, String> signin(@RequestHeader("authorization") String authHeader, HttpServletResponse response) throws AuthenticationFailedException {

        String[] userNamePassword = authHeader.split(" ")[1].split(":");
        String userNameDecoded = new String(Base64.getDecoder().decode(userNamePassword[0]));
        String password = new String(Base64.getDecoder().decode(userNamePassword[1]));

        User u = userService.getUserByUserName(userNameDecoded);
        if (u == null) {
            throw new AuthenticationFailedException("ATH-001", "This username does not exist");
        }

        if(!u.getPassword().equals(password)) {
            throw new AuthenticationFailedException("ATH-002", "Password failed");
        }

        String accessToken = new JwtTokenProvider(password).generateToken(u.getUuid(),
                now(), now().plusDays(1));

        Date loginAt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(loginAt);
        c.add(Calendar.DATE, 5);


        UserAuth authObj = new UserAuth(u.getId(), accessToken, c.getTime() , loginAt);
        userAuthService.save(authObj);

        response.setHeader("access_token", accessToken);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", u.getUuid());
        map.put("message", "SIGNED IN SUCCESSFULLY");
        return map;
    }
}
