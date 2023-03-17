package multiconversor.longitud;

import javax.swing.JOptionPane;

import multiconversor.panelprincipal.PanelPrincipal;
import multiconversor.recursos.Grafico;
import multiconversor.recursos.NuevoIntento;

public class ConversorLongitud extends Grafico implements NuevoIntento{
	private String [] longitudNombre = {"Kilometros","Metros","Decimetros","Centimetros","Milimetros","Millas","Pies","Pulgadas"};
	private String [] longitudSimbolo = {"km","m","dc","cm","mm","mi","ft","in"};
	private double [] longitudCentimetroAuxiliares = {0.00001, 0.01, 0.1, 1, 10, 0.000006213711922373, 0.03280839895013, 0.3937007874016};
	private double [] longitudElegidaAuxiliares = {0,0,0,0,0,0,0,0};
	private double medidaEnCentimetros=0.0;
	private int indexAux=-1;
	private String longitudTitle = " - Longitud";
	
	public void conversorLongitudPanel() {
		double cantidadElegida=0;
		Object inputLongitudOpcion = createInputOption(null,
										"Seleccione escala de longitud a usar para convertir",
										longitudTitle,
										JOptionPane.PLAIN_MESSAGE,
										null,
										longitudNombre,
										longitudNombre[0]);
		if(inputLongitudOpcion==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));

		String inputLongitudValor = createInputTextArea(null,
										"Introduzca el numero de "+inputLongitudOpcion+" a convertir.",
										longitudTitle,
										JOptionPane.PLAIN_MESSAGE);		
		
		if(inputLongitudValor==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		cantidadElegida=validarInputStringToDouble(inputLongitudValor);
		if(cantidadElegida<=0)			nuevoIntentoEjecutar(nuevoIntentoDialog("Solo se permiten valores numericos mayor a 0."));
		
		for(int i=0; i<longitudNombre.length; i++) {
			if(longitudNombre[i]==inputLongitudOpcion) {
				indexAux=i;
				break;
			}
		}
		
		if(indexAux!=-1) {
			medidaEnCentimetros = aCentimetro(cantidadElegida,indexAux);
			calculoDeCentimetroATodo(cantidadElegida, medidaEnCentimetros, longitudSimbolo[indexAux], longitudSimbolo);
			nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo Realizado."));
		}
	}
	
	private double aCentimetro(double valor, int index) {
		return (valor/longitudCentimetroAuxiliares[index]);
	}
	
	private void calculoDeCentimetroATodo(double valor, double valorEnCentimetros, String longitudSimbolo, String []longitudSimboloArr) {
		for(int i=0; i<longitudNombre.length; i++) {
			longitudElegidaAuxiliares[i] = valorEnCentimetros * longitudCentimetroAuxiliares[i];
		}
		createShowMessage(null,
				"Resultados de convertir "+valor+" ("+longitudSimbolo+")\n\n"
				+String.format("%.5f",longitudElegidaAuxiliares[0])+" "+longitudNombre[0]+" ("+longitudSimboloArr[0]+")\n"
				+String.format("%.5f",longitudElegidaAuxiliares[1])+" "+longitudNombre[1]+" ("+longitudSimboloArr[1]+")\n"
				+String.format("%.5f",longitudElegidaAuxiliares[2])+" "+longitudNombre[2]+" ("+longitudSimboloArr[2]+")\n"
				+String.format("%.5f",longitudElegidaAuxiliares[3])+" "+longitudNombre[3]+" ("+longitudSimboloArr[3]+")\n"
				+String.format("%.5f",longitudElegidaAuxiliares[4])+" "+longitudNombre[4]+" ("+longitudSimboloArr[4]+")\n"
				+String.format("%.5f",longitudElegidaAuxiliares[5])+" "+longitudNombre[5]+" ("+longitudSimboloArr[5]+")\n"
				+String.format("%.5f",longitudElegidaAuxiliares[6])+" "+longitudNombre[6]+" ("+longitudSimboloArr[6]+")\n"
				+String.format("%.5f",longitudElegidaAuxiliares[7])+" "+longitudNombre[7]+" ("+longitudSimboloArr[7]+")", 
				longitudTitle,
				JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void nuevoIntentoEjecutar(int opcion) {
		if(opcion==0) {
			ConversorLongitud conversor_longitud = new ConversorLongitud();
			conversor_longitud.conversorLongitudPanel();
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
					longitudTitle,
					null));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al crear panel nuevo intento.");
			createFinalMessage(null);
			System.exit(0);
			return 0;
		}
	}
}