package org.demo.spinncast.applicationHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationHepler {
	
	private static String vendorCode;
	private static String exciseCode;
	private static String regCertNo;
	private static String plaNo;
	private static String rangeAddress;
	private static String division;
	private static String tariffHeading;
	private static String excemption;

	private static String nameofexcisable;
	private static String serviceTax;
	private static String incomeTaxPan;
	private static String searchCustomer;

	public static String getNameofexcisable() {
		return nameofexcisable;
	}

	public static String getServiceTax() {
		return serviceTax;
	}

	public static String getIncomeTaxPan() {
		return incomeTaxPan;
	}

	public static String getSearchCustomer() {
		return searchCustomer;
	}

	public static String getVendorCode() {
		return vendorCode;
	}

	public static String getExciseCode() {
		return exciseCode;
	}

	public static String getRegCertNo() {
		return regCertNo;
	}

	public static String getPlaNo() {
		return plaNo;
	}

	public static String getRangeAddress() {
		return rangeAddress;
	}

	public static String getDivision() {
		return division;
	}

	public static String getTariffHeading() {
		return tariffHeading;
	}

	public static String getExcemption() {
		return excemption;
	}

	public static void readProperties() {
		Properties prop = new Properties();
		InputStream input = null;
		// InputStream configStream =
		// ctx.getResourceAsStream("/WEB-INF/config.properties");
		try {
			input = new FileInputStream("config.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			vendorCode = prop.getProperty("vendorCode");
			exciseCode = prop.getProperty("exciseCode");
			regCertNo = prop.getProperty("regCertNo");
			plaNo = prop.getProperty("PLANO");
			rangeAddress = prop.getProperty("rangeAddress");
			division = prop.getProperty("division");
			nameofexcisable = prop.getProperty("nameofexcisablecommdity");
			tariffHeading = prop.getProperty("tariffheadingnumber");
			excemption = prop.getProperty("excemptionnotiff");
			serviceTax = prop.getProperty("servicetax");
			incomeTaxPan = prop.getProperty("incometaxpan");
			System.out.println("inc" + incomeTaxPan);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
