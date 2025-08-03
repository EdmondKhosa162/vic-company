package za.co.vic.VIC_Company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import za.co.vic.VIC_Company.model.EmailDTO;
import za.co.vic.VIC_Company.model.User;
import za.co.vic.VIC_Company.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;
	
    @Value("${spring.mail.username}")
    private String fromEmail;

	public User saveUserAndSendEmail(EmailDTO email) {
		if (email == null) {
			throw new IllegalArgumentException("EmailDTO must not be null");
		}

		User user = new User();

		try {
			user.setName(email.getName());
			user.setEmail(email.getEmail());
			user.setServiceType(email.getServiceType());
			user.setSubject("Request A Quote");
			user.setMessage(email.getMessage());
			user.setContactNumber(email.getContactNumber());
			user.setCreated_date(createdDate());
			System.out.println("Sending emails...");

			// Send to internal team (BCC)
			sendInternalEmail(email);

			// Send acknowledgement to user
			sendAcknowledgementEmail(email);
			
			// Save to DB
			System.out.println("Saving to DB...");
			userRepository.save(user);

			return user;

		} catch (Exception e) {
			System.err.println("Error while saving user or sending email: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Failed to process request", e);
		}
	}

	private void sendInternalEmail(EmailDTO email) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		//helper.setFrom("info@viconsultants.co.za");
		helper.setFrom(fromEmail);
		//helper.setTo("edmondkhosa123@yahoo.com"); 
		helper.setBcc(new String[] { "mothipitr@outlook.com", "wise4levy@gmail.com","edmondkhosa123@yahoo.com", "info@viconsultants.co.za" });

		helper.setSubject("New Quote Request - " + email.getServiceType());
		helper.setText(buildHtmlBody(email), true);

		mailSender.send(message);
	}

	private void sendAcknowledgementEmail(EmailDTO email) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		//helper.setFrom("info@viconsultants.co.za");
		helper.setFrom(fromEmail);
		helper.setTo(email.getEmail());
		helper.setSubject("We Received Your Quote Request");

		String ackBody = "<div style='font-family: Arial, sans-serif; padding: 10px;'>" + "<h3>Thank you, "
				+ email.getName() + "!</h3>" + "<p>We acknowledge your <strong>Request A Quote</strong>.</p>"
				+ "<p>One of our consultants will be in touch within <strong>24 hours</strong>.</p>"
				+ "<br><p>Warm regards,<br>Vutlhari Innovation Consultants Team</p>" + "</div>";

		helper.setText(ackBody, true);

		mailSender.send(message);
	}

	private String buildHtmlBody(EmailDTO email) {
		return "<div style='font-family: Arial, sans-serif; padding: 10px;'>" + "<h3>New Quote Request</h3>"
				+ "<p><strong>Name:</strong> " + email.getName() + "</p>" + "<p><strong>Email:</strong> "
				+ email.getEmail() + "</p>" + "<p><strong>Service Type:</strong> " + email.getServiceType() + "</p>"
				+ "<p><strong>Quote Request:</strong><br>" + email.getMessage() + "</p>" + "</div>";
	}
	
	
    private String createdDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mma");
        return LocalDateTime.now().format(formatter);
    }

}
