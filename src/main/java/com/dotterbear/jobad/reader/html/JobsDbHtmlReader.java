package com.dotterbear.jobad.reader.html;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.springframework.format.datetime.DateFormatter;

import com.dotterbear.jobad.reader.data.model.JobAd;
import com.dotterbear.jobad.reader.data.model.WebSiteEnum;
import com.dotterbear.jobad.reader.html.utils.DocumentWrapper;

public class JobsDbHtmlReader implements HtmlReader {

	private DocumentWrapper documentWrapper;

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);

	private static final String COMPANY_NAME = "jobad-header-company";
	private static final String COMPANY_PROFILE = "primary-profile-detail";
	private static final String TITLE = "general-pos";
	private static final String DETAILS = "jobad-primary-details";
	private static final String CAREER_LEVEL = "meta-lv";
	private static final String YEARS_OF_EXP = "primary-meta-exp";
	private static final String QUALIFICATION = "primary-meta-edu";
	private static final String INDUSTRY = "meta-industry";
	private static final String LOCATION = "meta-location";
	private static final String SALARY = "meta-salary";
	private static final String EMPLOYMENT_TYPE = "meta-employmenttype";
	private static final String OTHERS = "meta-others";
	private static final String BENEFIT = "meta-benefit";
	private static final String JOB_AD_BODY = "jobad-body";
	private static final String PRIMARY_META_BOX = "jobad-primary-meta";
	private static final String PRIMARY_META_LV = "primary-meta-lv";
	private static final String PRIMARY_GENERAL_BOX = "primary-general-box";
	private static final String POSTED_DATE = "data-timestamp";
	private static final String JOBSDB_REF = "ref-jobsdb";

	public JobsDbHtmlReader() {
		super();
		documentWrapper = new DocumentWrapper(document -> {
			String body = document.body().text();
			return !body.isEmpty() && !body.contains("requested may have been removed or is no longer available");
		});
	}

	@Override
	public JobAd convertDocumentToJobAd(String url) {
		try {
			documentWrapper.fetchDocument(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
		return buildJobAdModel(url);
	}

	private JobAd buildJobAdModel(String url) {
		Date postedDate = null;
		try {
			String dateStr = documentWrapper.getElementTextByClassNames(JOB_AD_BODY, PRIMARY_GENERAL_BOX, POSTED_DATE);
			postedDate = dateFormatter.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JobAd jobAd = new JobAd()
				.setFromWebSite(WebSiteEnum.JOBSDB)
				.setCompanyName(documentWrapper.getElementTextByClassNames(JOB_AD_BODY, COMPANY_NAME))
				.setCompanyProfile(documentWrapper.getElementHtmlBySelector(JOB_AD_BODY, COMPANY_PROFILE))
				.setTitle(documentWrapper.getElementTextByClassNames(JOB_AD_BODY, TITLE))
				.setDetails(documentWrapper.getElementHtmlBySelector(JOB_AD_BODY, DETAILS))
				.setCareerLevel(documentWrapper.getElementTextByClassNames(JOB_AD_BODY, PRIMARY_META_BOX, CAREER_LEVEL, PRIMARY_META_LV))
				.setYearsOfExp(buildYearsOfExp(documentWrapper.getElementTextByClassNames(JOB_AD_BODY, PRIMARY_META_BOX, YEARS_OF_EXP)))
				.setQualification(documentWrapper.getElementTextByClassNames(JOB_AD_BODY, PRIMARY_META_BOX, QUALIFICATION))
				.setIndustry(documentWrapper.getElementTextBySelector(documentWrapper.concatClassNamesSelector(JOB_AD_BODY, PRIMARY_META_BOX, INDUSTRY) + " a"))
				.setLocation(documentWrapper.getElementTextBySelector(documentWrapper.concatClassNamesSelector(JOB_AD_BODY, PRIMARY_META_BOX, LOCATION) + " a"))
				// TODO review how to store salary
				//.setSalary(salary)
				.setEmploymentType(documentWrapper.getElementTextByClassNames(JOB_AD_BODY, PRIMARY_META_BOX, EMPLOYMENT_TYPE, PRIMARY_META_LV))
				.setOthers(documentWrapper.getElementTextByClassNames(JOB_AD_BODY, PRIMARY_META_BOX, OTHERS, PRIMARY_META_LV))
				.setBenefits(documentWrapper.getDocument()
						.select(documentWrapper.concatClassNamesSelector(JOB_AD_BODY, PRIMARY_META_BOX, BENEFIT) + " span")
						.stream()
						.map(element -> element.text())
						.collect(Collectors.toSet()))
				.setPostedDate(postedDate)
				.setExtRefId(
						Optional.ofNullable(documentWrapper.getElementTextByClassNames(JOB_AD_BODY, PRIMARY_GENERAL_BOX, JOBSDB_REF))
						.map(txt -> txt.replace("jobsDB Ref.", "").trim())
						.orElse(null))
				.setUrl(url);
		return jobAd;
	}

	private Integer buildYearsOfExp(String str) {
		if (str == null)
			return null;
		Pattern pattern = Pattern.compile("(\\d+).*");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find())
			return Integer.parseInt(matcher.group(1));
		return null;
	}

}