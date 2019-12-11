package com.bringoz.driverscore.utils;

public class MapBounds {

	public static final double TA_MAX_NORTH_LATITUDE = 32.04641454585623;
	public static final double TA_MAX_SOUTH_LATITUDE = 32.02356766431649;
	public static final double TA_MAX_WEST_LONGITUDE = 34.74912125617269;
	public static final double TA_MAX_EAST_LONGITUDE = 34.77613199676856;

	private double maxNorthLatitude;
	private double maxSouthLatitude;
	private double maxWestLongitude;
	private double maxEastLongitude;

	public MapBounds(double maxNorthLatitude, double maxSouthLatitude, double maxWestLongitude,
			double maxEastLongitude) {
		super();
		this.maxNorthLatitude = maxNorthLatitude;
		this.maxSouthLatitude = maxSouthLatitude;
		this.maxWestLongitude = maxWestLongitude;
		this.maxEastLongitude = maxEastLongitude;
	}

	public boolean ifInMapBounds(double latitude, double longitude) {

		if ((latitude > maxSouthLatitude && latitude < maxNorthLatitude)
				&& (longitude < maxEastLongitude && longitude > maxWestLongitude)) {
			return true;
		} else {
			return false;
		}
	}

}
