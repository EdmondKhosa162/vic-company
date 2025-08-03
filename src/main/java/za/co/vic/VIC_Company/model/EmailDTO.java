package za.co.vic.VIC_Company.model;

import lombok.Data;

@Data
public class EmailDTO {
	private String name;
	private String email;
	private String subject;
	private String serviceType;
	private String message;
	private String contactNumber;
}
