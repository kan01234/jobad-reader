package com.dotterbear.jobad.reader.data.model;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JobAd")
public class JobAd {

	@Id
	private String id;

	private String companyName;

	private String companyProfile;

	private String title;

	private String details;

	private String industry;

	private String careerLevel;

	private String qualification;

	private String location;

	private String employmentType;

	private String others;

	private String url;

	private String extRefId;

	private Integer yearsOfExp;

	private Integer salary;

	private Set<String> benefits;

	private Date postedDate;

	private Date ts;

	private WebSiteEnum fromWebSite;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public JobAd setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public JobAd setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public JobAd setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDetails() {
		return details;
	}

	public JobAd setDetails(String details) {
		this.details = details;
		return this;
	}

	public String getIndustry() {
		return industry;
	}

	public JobAd setIndustry(String industry) {
		this.industry = industry;
		return this;
	}

	public String getCareerLevel() {
		return careerLevel;
	}

	public JobAd setCareerLevel(String careerLevel) {
		this.careerLevel = careerLevel;
		return this;
	}

	public String getQualification() {
		return qualification;
	}

	public JobAd setQualification(String qualification) {
		this.qualification = qualification;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public JobAd setLocation(String location) {
		this.location = location;
		return this;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public JobAd setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
		return this;
	}

	public String getOthers() {
		return others;
	}

	public JobAd setOthers(String others) {
		this.others = others;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public JobAd setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getExtRefId() {
		return extRefId;
	}

	public JobAd setExtRefId(String extRefId) {
		this.extRefId = extRefId;
		return this;
	}

	public Integer getYearsOfExp() {
		return yearsOfExp;
	}

	public JobAd setYearsOfExp(Integer yearOfExp) {
		this.yearsOfExp = yearOfExp;
		return this;
	}

	public Integer getSalary() {
		return salary;
	}

	public JobAd setSalary(Integer salary) {
		this.salary = salary;
		return this;
	}

	public Set<String> getBenefits() {
		return benefits;
	}

	public JobAd setBenefits(Set<String> benefits) {
		this.benefits = benefits;
		return this;
	}

	public Date getTs() {
		return ts;
	}

	public JobAd setTs(Date ts) {
		this.ts = ts;
		return this;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public JobAd setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
		return this;
	}

	public WebSiteEnum getFromWebSite() {
		return fromWebSite;
	}

	public JobAd setFromWebSite(WebSiteEnum fromWebSite) {
		this.fromWebSite = fromWebSite;
		return this;
	}

	@Override
	public String toString() {
		return "JobAd [id=" + id + ", companyName=" + companyName + ", companyProfile=" + companyProfile + ", title="
				+ title + ", details=" + details + ", industry=" + industry + ", careerLevel=" + careerLevel
				+ ", qualification=" + qualification + ", location=" + location + ", employmentType=" + employmentType
				+ ", others=" + others + ", url=" + url + ", extRefId=" + extRefId + ", yearsOfExp=" + yearsOfExp
				+ ", salary=" + salary + ", benefits=" + benefits + ", postedDate=" + postedDate + ", ts=" + ts
				+ ", fromWebSite=" + fromWebSite + "]";
	}

}