package csd226.lab2.controllers;

import csd226.lab2.data.Account;
import csd226.lab2.security.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import csd226.lab2.data.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

//    @PostMapping("/auth")
    @PostMapping(value = "/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            Account account = new Account();
            account.setId(1);
            account.setEmail(authentication.getPrincipal().toString());

            String accessToken = jwtUtil.generateAccessToken(account);

            AuthResponse response = new AuthResponse(account.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);
        } catch( Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/login")
    public ResponseEntity<String> getLogin(){ // map a URL to a method
        String s="<script src=\"https://unpkg.com/htmx.org@1.9.10\" integrity=\"sha384-D1Kt99CQMDuVetoL1lrYwg5t+9QdHe7NLX/SoJYkXDFfX37iInKRy5xLSi8nO7UC\" crossorigin=\"anonymous\"></script>"+
                "<div hx-get=\"/protectedPage\" hx-target=\"#protectedContent\">" +
                "    Get protected content" +
                "</div>" +
                "<div id=\"protectedContent\"></div>" +
                "<div hx-get=\"/publiccontent\" hx-target=\"#content\">" +
                "    Get content" +
                "</div>" +
                "<div id=\"content\"></div>" +
                "<form hx-post=\"/signup\" hx-target=\"this\" hx-swap=\"outerHTML\">" +
                "    <div>" +
                "        <label>Username</label>" +
                "        <input type=\"text\" name=\"username\" value=\"user\">" +
                "    </div>" +
                "    <div class=\"form-group\">" +
                "        <label>Password</label>" +
                "        <input type=\"password\" name=\"password\" value=\"password\">" +
                "    </div>" +
                "    <button class=\"btn\">Submit</button>" +
                "    <button class=\"btn\" hx-get=\"/signup\">Cancel</button>" +
                "</form>";
        return ResponseEntity.ok(s);
    }
    @GetMapping("/signin")
    public ResponseEntity<String> getSignin(){ // map a URL to a method
        String s="<form hx-post=\"/signup\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
                "    <div>\n" +
                "        <label>First Name</label>\n" +
                "        <input type=\"text\" name=\"firstname\" value=\"Joe\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label>Last Name</label>\n" +
                "        <input type=\"text\" name=\"lastname\" value=\"Blow\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label>Email Address</label>\n" +
                "        <input type=\"email\" name=\"email\" value=\"joe@blow.com\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label>Password</label>\n" +
                "        <input type=\"password\" name=\"password\" value=\"xxxxx\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label>Confirm Password</label>\n" +
                "        <input type=\"password\" name=\"confirmPassword\" value=\"xxxxx\">\n" +
                "    </div>\n" +
                "    <button class=\"btn\">Submit</button>\n" +
                "    <button class=\"btn\" hx-get=\"/signin\">Cancel</button>\n" +
                "</form>";
        return ResponseEntity.ok(s);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createAccount(@RequestBody Account signUpFormData){
        return ResponseEntity.ok("createAccount() : "+signUpFormData.getUsername());
    }


}
