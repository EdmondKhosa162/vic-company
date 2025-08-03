package za.co.vic.VIC_Company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.vic.VIC_Company.model.EmailDTO;
import za.co.vic.VIC_Company.model.User;
import za.co.vic.VIC_Company.repository.UserRepository;
import za.co.vic.VIC_Company.response.ResponseHandler;
import za.co.vic.VIC_Company.service.EmailService;

@RestController
@RequestMapping("/api/vic")
public class VIC_Controller {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;

	@GetMapping("/")
	public String index() {
	    return "index";
	}


	@PostMapping("/send-email")
	public ResponseEntity<Object> sendEmail(@RequestBody EmailDTO email) {
	    try {
	        User user = emailService.saveUserAndSendEmail(email);
	        System.out.println("User saved and Email sent successfully.");
	        return ResponseHandler.responseBuilder("User saved and Email sent successfully.", HttpStatus.CREATED, user);
	    } catch (Exception e) {
	        return ResponseHandler.responseBuilder("Error occurred sending email", HttpStatus.BAD_REQUEST, null);
	    }
	}


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllCompanies() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete-all-users")
    public ResponseEntity<Object> deleteAllUsers() {
        try {
            userRepository.deleteAll(); // Deletes all rows in the user table
            return ResponseHandler.responseBuilder("All users deleted successfully.", HttpStatus.OK, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.responseBuilder("Failed to delete users.", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
