package edu.sjsu.cmpe275.project.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="HACKATHON_TEAMS")
public class HackathonTeams implements Serializable{
	
	public enum SubmissionStatus{
		Yes,
		No
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties(value = {"judgeList", "orgList", "hackathonTeams"})
    private Hackathon hackId;
	
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties(value = {"teamMembers"})
    private Team teamId;
	
	@Column(name="GRADE")
	private Float Grade;
	
	@Column(name="PAYMENTS")
	private boolean Payments;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SUBMISSION_STATUS")
	private SubmissionStatus Submission_status;
	
	@Column(name = "SUBMISSION_URL")
	private String submissionUrl;
	
	@Column(name="DISCOUNT")
	private String Discount;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hackathon getHackId() {
		return hackId;
	}

	public void setHackId(Hackathon hackId) {
		this.hackId = hackId;
	}

	public Team getTeamId() {
		return teamId;
	}

	public void setTeamId(Team teamId) {
		this.teamId = teamId;
	}

	public Float getGrade() {
		return Grade;
	}

	public void setGrade(Float grade) {
		Grade = grade;
	}

	public boolean getPayments() {
		return Payments;
	}

	public void setPayments(boolean payments) {
		Payments = payments;
	}

	public SubmissionStatus getSubmission_status() {
		return Submission_status;
	}

	public void setSubmission_status(SubmissionStatus submission_status) {
		Submission_status = submission_status;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}

	public String getSubmissionUrl() {
		return submissionUrl;
	}

	public void setSubmissionUrl(String submissionUrl) {
		this.submissionUrl = submissionUrl;
	}
	
	
	
	
}
