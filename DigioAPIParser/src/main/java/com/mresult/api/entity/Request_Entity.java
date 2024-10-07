package com.mresult.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "request_data")
	public class Request_Entity {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "aadhar_number", nullable = false)
	    private String aadharNumber;

	    @Column(name = "customer_identifier", nullable = false)
	    private String customerIdentifier;

	    @Column(name = "template_name")
	    private String templateName;

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getAadharNumber() {
	        return aadharNumber;
	    }

	    public void setAadharNumber(String aadharNumber) {
	        this.aadharNumber = aadharNumber;
	    }

	    public String getCustomerIdentifier() {
	        return customerIdentifier;
	    }

	    public void setCustomerIdentifier(String customerIdentifier) {
	        this.customerIdentifier = customerIdentifier;
	    }

	    public String getTemplateName() {
	        return templateName;
	    }

	    public void setTemplateName(String templateName) {
	        this.templateName = templateName;
	    }
	}
