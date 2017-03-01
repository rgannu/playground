package com.utopian.samples;

/**
 * Created by garamasu on 2015-11-05.
 */
public class EUDReport {
    private long receiveTs;
    private String homeNetworkId;
    private long ts;
    private String mac;
    private double linkSpeedMbps;
    private String connectedBssid;

    private static class CustomInfo {
        private String sessionId;
        private String location;
        private String endUserDeviceId;
    }
}
