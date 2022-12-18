package com.masai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 20, message = "Name shuld contain minimum 3 and maximum 20 character")
	private String name;

	@Email(message = "Please provide a valid email")
	private String email;

	@NotNull
	@Pattern(regexp = "^[789][0-9]{9}", message = "Mobile number should be of 10 digits")
	private String mobile;

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 30, message = "Address shuld contain minimum 3 and maximum 30 character")
	private String address;

	@Pattern(regexp = "^(^[a-zA-Z0-9]{4,12}$)", message = "password must contain atleast 1 uppercase, 1 lowercase, and 1 digit ")
	private String password;

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 30, message = "Address shuld contain minimum 3 and maximum 30 character")
	private String companyName;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "busId")
	private List<Bus> buses;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "hotelId")
	private List<Hotel> hotels;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "packageId")
	private List<Package> packages;

}
