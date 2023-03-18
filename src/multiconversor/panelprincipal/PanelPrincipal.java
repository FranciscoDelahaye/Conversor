package multiconversor.panelprincipal;
import multiconversor.divisas.ConversorDivisas;
import multiconversor.longitud.ConversorLongitud;
import multiconversor.pesos.ConversorPeso;
import multiconversor.recursos.Funciones;
import multiconversor.temperaturas.ConversorTemperatura;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PanelPrincipal extends Funciones {
	private String [] panelPrincipalOpciones = {"Divisas","Temperatura","Longitud","Peso"};
	private ImageIcon panelPrincipalIcon;
	private String panelPrincipalImageSrc = "src/multiconversor/recursos/imageIcon.png";
	private String panelPrincipalTitle = " - Panel Principal";
	private int indexAux=-2;
	
	public void panelPrincipalPanel() {
		panelPrincipalIcon = new ImageIcon(resizeImage(panelPrincipalIcon,panelPrincipalImageSrc,50,50));

		Object opcionesPanelPrincipal = createOptionDialog(null,
										"           SELECCIONE QUE DESEA CONVERTIR",
										panelPrincipalTitle,
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.PLAIN_MESSAGE,
										panelPrincipalIcon,
										panelPrincipalOpciones,
										panelPrincipalOpciones[0]);
		
		indexAux = (int) opcionesPanelPrincipal;
		
		switch (indexAux) {
			case 0:
				ConversorDivisas conversor_divisas = new ConversorDivisas();
				conversor_divisas.conversorDivisasPanel();
			case 1:
				ConversorTemperatura conversor_temperatura = new ConversorTemperatura();
				conversor_temperatura.conversorTemperaturaPanel();
			case 2:
				ConversorLongitud conversor_longitud = new ConversorLongitud();
				conversor_longitud.conversorLongitudPanel();
			case 3:
				ConversorPeso conversor_peso = new ConversorPeso();
				conversor_peso.conversorPesoPanel();
			case JOptionPane.CLOSED_OPTION:
				createFinalMessage(null);
				System.exit(0);
		}
	}
}