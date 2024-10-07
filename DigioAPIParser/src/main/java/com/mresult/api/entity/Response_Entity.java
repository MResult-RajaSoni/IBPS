package com.mresult.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "response_data")
public class Response_Entity {
	
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long number;

		private String id;
	    private String status;
	    private String customerIdentifier;
	    private String referenceId;
	    private String transactionId;
	    private String customerName;
	    private int expireInDays;
	    private boolean reminderRegistered;
	    private String workflowName;
	    private boolean autoApproved;
	    private String templateId;
//	    private String aadhar_number;

	    @Embedded
	    private AccessToken accessToken;
	    
	    @Embeddable
	    public static class AccessToken {
	        private String entityId;
	        private String accessTokenId;
	        private LocalDateTime validTill;
	        private LocalDateTime createdAt;
			public String getEntityId() {
				return entityId;
			}
			public void setEntityId(String entityId) {
				this.entityId = entityId;
			}
			public String getAccessTokenId() {
				return accessTokenId;
			}
			public void setAccessTokenId(String accessTokenId) {
				this.accessTokenId = accessTokenId;
			}
			public LocalDateTime getValidTill() {
				return validTill;
			}
			public void setValidTill(LocalDateTime validTill) {
				this.validTill = validTill;
			}
			public LocalDateTime getCreatedAt() {
				return createdAt;
			}
			public void setCreatedAt(LocalDateTime createdAt) {
				this.createdAt = createdAt;
			}
	    }
	    
	    @Embedded
	    private Request_details requestDetails;	

	    @Embeddable
	    public static class Request_details {
	        
	    	private String Aadhar_number;
		    
	    	public String getAadhar_number() {
				return Aadhar_number;
			}
	
			public void setAadhar_number(String aadhar_number) {
				this.Aadhar_number = aadhar_number;
			}
	    }
	    
	    
		public Long getNumber() {
			return number;
		}

		public void setNumber(Long number) {
			this.number = number;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}


		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCustomerIdentifier() {
			return customerIdentifier;
		}

		public void setCustomerIdentifier(String customerIdentifier) {
			this.customerIdentifier = customerIdentifier;
		}

		public String getReferenceId() {
			return referenceId;
		}

		public void setReferenceId(String referenceId) {
			this.referenceId = referenceId;
		}

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public int getExpireInDays() {
			return expireInDays;
		}

		public void setExpireInDays(int expireInDays) {
			this.expireInDays = expireInDays;
		}

		public boolean isReminderRegistered() {
			return reminderRegistered;
		}

		public void setReminderRegistered(boolean reminderRegistered) {
			this.reminderRegistered = reminderRegistered;
		}

		public String getWorkflowName() {
			return workflowName;
		}

		public void setWorkflowName(String workflowName) {
			this.workflowName = workflowName;
		}

		public boolean isAutoApproved() {
			return autoApproved;
		}

		public void setAutoApproved(boolean autoApproved) {
			this.autoApproved = autoApproved;
		}

		public String getTemplateId() {
			return templateId;
		}

		public void setTemplateId(String templateId) {
			this.templateId = templateId;
		}

		public AccessToken getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(AccessToken accessToken) {
			this.accessToken = accessToken;
		}

		public Request_details getRequestDetails() {
			return requestDetails;
		}

		public void setRequestDetails(Request_details requestDetails) {
			this.requestDetails = requestDetails;
		}
		
		
	    
}
