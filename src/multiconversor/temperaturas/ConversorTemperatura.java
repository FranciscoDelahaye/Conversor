package multiconversor.temperaturas;

import javax.swing.JOptionPane;

import multiconversor.panelprincipal.PanelPrincipal;
import multiconversor.recursos.Grafico;
import multiconversor.recursos.NuevoIntento;

public class ConversorTemperatura extends Grafico implements NuevoIntento {
	private String [] temperaturaNombre = {"Celsius (°C)","Fahrenheit (°F)","Kelvin (°K)"};
	private double [] temperaturaAuxiliares = {0, 0, 0};
	private int indexAux=-1;
	private String temperaturaTitle = " - Temperatura";
		
	public void conversorTemperaturaPanel() {
		double cantidadElegida=0;
		Object inputTemperaturaOpcion = createInputOption(null,
											"Seleccione desde que temperatura quiere convertir",
											temperaturaTitle,
											JOptionPane.PLAIN_MESSAGE,
											null,
											temperaturaNombre,
											temperaturaNombre[0]);
		if(inputTemperaturaOpcion==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));

		String inputTemperaturaValor = createInputTextArea(null,
											"Ingrese el valor en "+inputTemperaturaOpcion+" a convertir.",
											temperaturaTitle,
											JOptionPane.PLAIN_MESSAGE);		

		if(inputTemperaturaValor==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		cantidadElegida=validarInputStringToDouble(inputTemperaturaValor);
		if(cantidadElegida==0)			nuevoIntentoEjecutar(nuevoIntentoDialog("Solo se permiten valores numericos distinto a 0."));

		for(int i=0; i<temperaturaNombre.length; i++) {
			if(temperaturaNombre[i]==inputTemperaturaOpcion) {
				indexAux=i;
				break;
			}
		}

		switch (indexAux) {
		case 0:
			if(deCelsius(cantidadElegida)) {
				createShowMessage(null,
						"El valor de "+temperaturaAuxiliares[0]+"°C equivale a "+String.format("%.2f",temperaturaAuxiliares[1])+"°F y a "+String.format("%.2f",temperaturaAuxiliares[2])+"°K.",
						temperaturaTitle,
						JOptionPane.PLAIN_MESSAGE);
				nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo realizado."));
			}
		case 1:
			if(deFahrenheit(cantidadElegida)) {
				createShowMessage(null,
						"El valor de "+temperaturaAuxiliares[1]+"°F equivale a "+String.format("%.2f",temperaturaAuxiliares[0])+"°C y a "+String.format("%.2f",temperaturaAuxiliares[2])+"°K.",
						temperaturaTitle,
						JOptionPane.PLAIN_MESSAGE);
				nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo realizado."));
			}
		case 2:
			if(deKelvin(cantidadElegida)) {
				createShowMessage(null,
						"El valor de "+temperaturaAuxiliares[2]+"°K equivale a "+String.format("%.2f",temperaturaAuxiliares[0])+"°C y a "+String.format("%.2f",temperaturaAuxiliares[1])+"°F",
						temperaturaTitle,
						JOptionPane.PLAIN_MESSAGE);
				nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo realizado."));
			}
		}
	}
	
	private boolean deCelsius(double celsiusValue) {
		try {
			temperaturaAuxiliares[0] = celsiusValue;
			temperaturaAuxiliares[1] = (1.8*celsiusValue)+32;
			temperaturaAuxiliares[2] = celsiusValue+273;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean deFahrenheit(double fahrenheitValue) {
		try {
			temperaturaAuxiliares[0] = (fahrenheitValue-32)/1.8;
			temperaturaAuxiliares[1] = fahrenheitValue;
			temperaturaAuxiliares[2] = 5/9*(fahrenheitValue-32)+273.15;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean deKelvin(double kelvinValue) {
		try {
			temperaturaAuxiliares[0] = kelvinValue-273.15;
			temperaturaAuxiliares[1] = 1.8*(kelvinValue-2733.15)+32;
			temperaturaAuxiliares[2] = kelvinValue;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void nuevoIntentoEjecutar(int opcion) {
		if(opcion==0) {
			ConversorTemperatura conversor_temperatura = new ConversorTemperatura();
			conversor_temperatura.conversorTemperaturaPanel();
		}
		else if (opcion==1) {
			PanelPrincipal volverPanelPrincipal = new PanelPrincipal();
			volverPanelPrincipal.panelPrincipalPanel();
		}
		else if (opcion==2) {
			createFinalMessage(null);
			System.exit(0);
		}
	}
	
	@Override
	public int nuevoIntentoDialog(String message) {
		try {
			return(createIntentarNuevoDialog(null,
					message,
					temperaturaTitle,
					null));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al crear panel nuevo intento.");
			createFinalMessage(null);
			System.exit(0);
			return 0;
		}
	}
}