package za.co.vic.VIC_Company.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "user")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String name;
	private String email;
	private String subject;
	private String serviceType;
	private String message;
	private String contactNumber;
	private String created_date;
}
