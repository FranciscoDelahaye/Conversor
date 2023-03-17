package multiconversor.recursos;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class API_Request {
	private String urlAPI = "https://api.apilayer.com/exchangerates_data/convert?";
	private String API_KEY = "v44JaN75uIbmSoVF3FxoZoyn9kW25RG1";
	private String requestMethod = "GET";
	private StringBuilder informacionRequest = new StringBuilder();
	private String resultRequest = "";
	
	public double getRequest(double cantidad, String convertFrom, String convertTo) {
		try {
			URL url = new URL(urlAPI+"to="+convertTo+"&from="+convertFrom+"&amount="+cantidad+"&apikey="+API_KEY);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection() ;
			connection.setRequestProperty("apikey", API_KEY);
			connection.setRequestMethod(requestMethod);
			
			if(connection.getResponseCode()==200) {
				Scanner scanner = new Scanner(url.openStream());
				while(scanner.hasNext()) {
					informacionRequest.append(scanner.nextLine());
				}
			}
			connection.disconnect();
			resultRequest = informacionRequest.substring(informacionRequest.indexOf("result")+9, informacionRequest.length()-1);
			return Double.parseDouble(resultRequest);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error de API_Request.");
			return 0;
		}
	}
}