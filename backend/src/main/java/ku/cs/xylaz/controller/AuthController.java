package ku.cs.xylaz.controller;

import jakarta.validation.Valid;
import ku.cs.xylaz.request.LoginRequest;
import ku.cs.xylaz.request.SignupRequest;
import ku.cs.xylaz.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private SignupService signupService;

    // สำหรับลงทะเบียน
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignupRequest user) {
        // ปริ้นข้อมูลที่ได้รับ
        System.out.println("Received signup request for user: " + user.getUsername());

        if (signupService.isUsernameAvailable(user.getUsername())) {
            signupService.createUser(user);
            return ResponseEntity.ok("Signup successful");
        } else {
            return ResponseEntity.status(400).body("Username not available");
        }
    }


    // สำหรับเข้าสู่ระบบ
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest user) {
        if (user.getUsername().equals("john") && user.getPassword().equals("123456")) {
            return ResponseEntity.ok("Login successful for user: " + user.getUsername());
        } else {
            return ResponseEntity.status(400).body("Invalid credentials");
        }
    }

//    // สำหรับการเปลี่ยนเส้นทางไปหน้า React
//    @GetMapping("/login")
//    public ResponseEntity<Void> redirectToLogin() {
//        return ResponseEntity.status(302)
//                .header("Location", "http://localhost:3000/")
//                .build();
//    }

}
