package com.local.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.local.models.LocationStats;

@Service
public class CoronaDataService {

	private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private static final Logger logger = LoggerFactory.getLogger(CoronaDataService.class);

	private List<LocationStats> allStats = new ArrayList<LocationStats>();
	

	/**
	 * @return the allStats
	 */
	public List<LocationStats> getAllStats() {
		return allStats;
	}


	@PostConstruct
	@Scheduled(cron = "* * * * 1 *")
	public void fetchVirusData() throws IOException {

		List<LocationStats> localStats = new ArrayList<LocationStats>();
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpget = new HttpGet(VIRUS_DATA_URL);
			logger.info("Executing request : " + httpget.getRequestLine());

			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};

			String responseBody = httpClient.execute(httpget, responseHandler);
			logger.info("----------------------------------------");
			// logger.info(responseBody);

			StringReader csvReader = new StringReader(responseBody);
			@SuppressWarnings("deprecation")
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
			for (CSVRecord record : records) {
				LocationStats locationStat = new LocationStats();
				locationStat.setState(record.get("Province/State"));
				locationStat.setCountry(record.get("Country/Region"));
				locationStat.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
				locationStat.setPreviousDayTotalCases(Integer.parseInt(record.get(record.size()-2)));
				localStats.add(locationStat);
			}

			this.allStats = localStats;
			logger.info("Location details are : " + allStats);
		}
	}

}
