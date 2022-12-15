package com.masai.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Package {

private Integer packageId;

@NotNull(message="Package name is mandatory")
@NotBlank @NotEmpty
@Size(min = 5, max = 15, message = "Package name should be of 5-7 charecters")
private String packageName;

@NotNull(message="Package type should be mandatory")
@NotBlank @NotEmpty
@Size(min = 5, max = 10, message = "Package Type should be of 5-10 charecters")
private String packageType;

@NotNull(message=" is mandatory")
@NotBlank @NotEmpty
@Size(min = 5, max = 10, message = "Package Description should be of 5-50 charecters")
private String packageDescription;

@NotNull
@NotBlank @NotEmpty
private Integer packageCost;

public Package(Integer packageId,
		@NotNull(message = "Package name is mandatory") @NotBlank @NotEmpty @Size(min = 5, max = 15, message = "Package name should be of 5-7 charecters") String packageName,
		@NotNull(message = "Package type should be mandatory") @NotBlank @NotEmpty @Size(min = 5, max = 10, message = "Package Type should be of 5-10 charecters") String packageType,
		@NotNull(message = " is mandatory") @NotBlank @NotEmpty @Size(min = 5, max = 10, message = "Package Description should be of 5-50 charecters") String packageDescription,
		@NotNull @NotBlank @NotEmpty Integer packageCost) {
	super();
	this.packageId = packageId;
	this.packageName = packageName;
	this.packageType = packageType;
	this.packageDescription = packageDescription;
	this.packageCost = packageCost;
}

public Package() {
	// TODO Auto-generated constructor stub
}

@Override
public String toString() {
	return "Package [packageId=" + packageId + ", packageName=" + packageName + ", packageType=" + packageType
			+ ", packageDescription=" + packageDescription + ", packageCost=" + packageCost + "]";
}

public Integer getPackageId() {
	return packageId;
}

public void setPackageId(Integer packageId) {
	this.packageId = packageId;
}

public String getPackageName() {
	return packageName;
}

public void setPackageName(String packageName) {
	this.packageName = packageName;
}

public String getPackageType() {
	return packageType;
}

public void setPackageType(String packageType) {
	this.packageType = packageType;
}

public String getPackageDescription() {
	return packageDescription;
}

public void setPackageDescription(String packageDescription) {
	this.packageDescription = packageDescription;
}

public Integer getPackageCost() {
	return packageCost;
}

public void setPackageCost(Integer packageCost) {
	this.packageCost = packageCost;
}


}
