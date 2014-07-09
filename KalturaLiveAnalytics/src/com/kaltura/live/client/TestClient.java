package com.kaltura.live.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.kaltura.live.webservice.LiveAnalytics;
import com.kaltura.live.webservice.model.AnalyticsException;
import com.kaltura.live.webservice.model.EntryReferrerLiveStats;
import com.kaltura.live.webservice.model.LiveReportInputFilter;
import com.kaltura.live.webservice.model.LiveReportType;
import com.kaltura.live.webservice.model.LiveStats;
import com.kaltura.live.webservice.model.LiveStatsListResponse;

public class TestClient{
	
	public static void main(String[] args) throws Exception {
	   
		
		URL url = new URL("http://pa-erans:9090/KalturaLiveAnalytics/KalturaLiveAnalytics?wsdl");
        QName qname = new QName("http://webservice.live.kaltura.com/", "LiveAnalyticsImplService");

        Service service = Service.create(url, qname);
        LiveAnalytics hello = service.getPort(LiveAnalytics.class);
        
//        testGeoTimeLine(hello);
        testSyndicationTotal(hello);
//        testTimeLine(hello);
//        testEntryTotalDead(hello);
//        testEntryTotalLive(hello);
//        testDeadPartner(hello);
//        testLivePartner(hello);
        
    }
	
	private static void testGeoTimeLine(LiveAnalytics hello) throws AnalyticsException {
		LiveReportType reportType = LiveReportType.ENTRY_GEO_TIME_LINE;
		LiveReportInputFilter filter = new LiveReportInputFilter();
		filter.setEntryIds("1_lcn2avg8");
		filter.setEventTime(1387121900);
		
		LiveStatsListResponse z = hello.getReport(reportType, filter);
        printResult(reportType, z);
	}
	
	private static void testTimeLine(LiveAnalytics hello) throws AnalyticsException {
		LiveReportType reportType = LiveReportType.ENTRY_TIME_LINE;
		LiveReportInputFilter filter = new LiveReportInputFilter();
		filter.setEntryIds("1_lcn2avg8");
		filter.setFromTime(1387100000);
		filter.setToTime(1387200000);
		
		LiveStatsListResponse z = hello.getReport(reportType, filter);
        printResult(reportType, z);
	}
	
	private static void testSyndicationTotal(LiveAnalytics hello) throws AnalyticsException {
		LiveReportType reportType = LiveReportType.ENTRY_SYNDICATION_TOTAL;
		LiveReportInputFilter filter = new LiveReportInputFilter();
		filter.setEntryIds("1_lcn2avg8");
		filter.setHoursBefore(1);
		
		LiveStatsListResponse z = hello.getReport(reportType, filter);
		System.out.println("reportType: " + reportType);
		System.out.println("Total count : " + z.getTotalCount());
        
        int id = 1;
        for (LiveStats liveStats : z.getEvents()) {
			System.out.println("\tid:[" + id++ + "]" + ((EntryReferrerLiveStats)liveStats).getReferrer());
		}
	}
	
	private static void testEntryTotalDead(LiveAnalytics hello) throws AnalyticsException {
		LiveReportType reportType = LiveReportType.ENTRY_TOTAL;
		LiveReportInputFilter filter = new LiveReportInputFilter();
		filter.setEntryIds("1_lcn2avg8");
		filter.setHoursBefore(1);
		filter.setLive(false);
		
		LiveStatsListResponse z = hello.getReport(reportType, filter);
        printResult(reportType, z);
	}
	
	private static void testEntryTotalLive(LiveAnalytics hello) throws AnalyticsException {
		LiveReportType reportType = LiveReportType.ENTRY_TOTAL;
		LiveReportInputFilter filter = new LiveReportInputFilter();
		filter.setEntryIds("1_lcn2avg8");
		filter.setLive(true);
		
		LiveStatsListResponse z = hello.getReport(reportType, filter);
        printResult(reportType, z);
	}
	
	public static void testDeadPartner(LiveAnalytics hello) throws AnalyticsException {
		LiveReportType reportType = LiveReportType.PARTNER_TOTAL;
		LiveReportInputFilter filter = new LiveReportInputFilter();
		filter.setPartnerId(662652);
		filter.setLive(false);
		filter.setHoursBefore(1);
		
		LiveStatsListResponse z = hello.getReport(reportType, filter);
        printResult(reportType, z);
		
	}
	
	public static void testLivePartner(LiveAnalytics hello) throws AnalyticsException {
		LiveReportType reportType = LiveReportType.PARTNER_TOTAL;
		LiveReportInputFilter filter = new LiveReportInputFilter();
		filter.setEntryIds("1_lcn2avg8");
		filter.setLive(true);
		
		LiveStatsListResponse z = hello.getReport(reportType, filter);
        printResult(reportType, z);
		
	}

	private static void printResult(LiveReportType reportType, LiveStatsListResponse z) {
		System.out.println("reportType: " + reportType);
		System.out.println("Total count : " + z.getTotalCount());
        
        int id = 1;
        for (LiveStats liveStats : z.getEvents()) {
			System.out.println("\tid:[" + id++ + "]" + liveStats.getBufferTime());
		}
	}
	
	

}
