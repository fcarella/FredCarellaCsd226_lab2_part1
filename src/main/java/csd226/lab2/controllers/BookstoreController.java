package csd226.lab2.controllers;

import csd226.lab2.data.Content;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookstoreController {

    @GetMapping("/staffcontent")
    public ResponseEntity<String> getStaffContent(){ // map a URL to a method
        return ResponseEntity.ok("getStaffContent() : Staff");
    }
    @GetMapping("/publiccontent")
    public ResponseEntity<String> getPublicContent(){ // map a URL to a method
        return ResponseEntity.ok("getPublicContent() : Home");
    }
    @GetMapping("/about")
    public ResponseEntity<String> getAbout(){ // map a URL to a method
        return ResponseEntity.ok("getAbout() : About");
    }
    @GetMapping("/protectedPage")
    public ResponseEntity<String> getProtectedContent(){ // map a URL to a method
        return ResponseEntity.ok("Protected Content");
    }
    @GetMapping("/publiccontent2")
    public Content getPublicContent2(){ // map a URL to a method
        return new Content("some content");
    }

//    @PostMapping("/signup")
//    public ResponseEntity<String> createAccount(@RequestBody Account signUpFormData){
//        return ResponseEntity.ok("createAccount() : "+signUpFormData.getEmail());
//    }
}
