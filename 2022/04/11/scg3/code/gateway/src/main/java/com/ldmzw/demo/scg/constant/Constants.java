package com.ldmzw.demo.scg.constant;

public class Constants {

    // -----------------------------  PATH ---------------------------------

    public static final String BASE = "";

    public static final String PATH_HEALTH_CHECK = BASE + "/healthCheck";
    public static final String PATH_REFRESH_ROUTE = BASE + "/refreshRoute";
    public static final String PATH_CIRCUIT_BREAKER_FALLBACK = BASE + "/fallback";


    // -----------------------------  ATTRIBUTE ---------------------------------

    public static final String REQUEST_ATTRIBUTE_INSTANCE_ID = "custom-instance-id";
    public static final String REQUEST_ATTRIBUTE_START_TIME = "custom-start-time";


    // -----------------------------  HEADER ---------------------------------

    public static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";


}
