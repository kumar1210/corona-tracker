package com.local.models;

public class LocationStats {

	private String state;
	private String country;
	private int latestTotalCases;
	private int previousDayTotalCases;

	/**
	 * @return the previousDayTotalCases
	 */
	public int getPreviousDayTotalCases() {
		return previousDayTotalCases;
	}

	/**
	 * @param previousDayTotalCases the previousDayTotalCases to set
	 */
	public void setPreviousDayTotalCases(int previousDayTotalCases) {
		this.previousDayTotalCases = previousDayTotalCases;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the latestTotalCases
	 */
	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	/**
	 * @param latestTotalCases the latestTotalCases to set
	 */
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestTotalCases=" + latestTotalCases
				+ ", previousDayTotalCases=" + previousDayTotalCases + "]";
	}

	
}
