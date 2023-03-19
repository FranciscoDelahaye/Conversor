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
			
			switch (connection.getResponseCode()) {
				case 200:
					Scanner scanner = new Scanner(url.openStream());
					while(scanner.hasNext()) {
						informacionRequest.append(scanner.nextLine());
					}
				case 400:
					JOptionPane.showMessageDialog(null,"API Error 400 - Llamada incorrecta faltan parametros necesarios.");
				case 401:
					JOptionPane.showMessageDialog(null,"API Error 401 - API Key no valida.");
				case 404:
					JOptionPane.showMessageDialog(null,"API Error 404 - Divisa no encontrada.");
				case 429:
					JOptionPane.showMessageDialog(null,"API Error 429 - Se alcanzo el limite de llamadas a la API.");
				default:
					JOptionPane.showMessageDialog(null,"API Error desconocido.");
				}
			connection.disconnect();
			resultRequest = informacionRequest.substring(informacionRequest.indexOf("result")+9, informacionRequest.length()-1);
			return Double.parseDouble(resultRequest);
		} catch (Exception e) {
			return 0;
		}
	}
}