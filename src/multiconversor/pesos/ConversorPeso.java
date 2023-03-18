package multiconversor.pesos;

import javax.swing.JOptionPane;

import multiconversor.panelprincipal.PanelPrincipal;
import multiconversor.recursos.Funciones;
import multiconversor.recursos.InterfaceNuevoIntento;

public class ConversorPeso extends Funciones implements InterfaceNuevoIntento {
	private String [] pesoNombre = {"Tonelada","Kilogramo","Gramo","Miligramo","Libra","Onza"};
	private String [] pesoSimbolo = {"t","kg","g","mg","lb","oz"};
	private double [] pesoGramoAuxiliares = {0.000001, 0.001, 1, 1000, 0.0022046244201837776, 0.03527461286112385};
	private double [] pesoElegidaAuxiliares = {0,0,0,0,0,0};
	private double pesoEnGramos=0.0;
	private int indexAux=-1;
	private String pesoTitle = " - Peso";
	
	public void conversorPesoPanel() {
		double cantidadElegida=0;
		Object inputPesoOpcion = createInputOption(null,
				"Seleccione escala de peso/masa a usar para convertir",
				pesoTitle,
				JOptionPane.PLAIN_MESSAGE,
				null,
				pesoNombre,
				pesoNombre[0]);
		if(inputPesoOpcion==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		
		String inputPesoValor = createInputTextArea(null,
				"Introduzca el numero de "+inputPesoOpcion+" a convertir.",
				pesoTitle,
				JOptionPane.PLAIN_MESSAGE);		

		if(inputPesoValor==null)		nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		cantidadElegida=validarInputStringToDouble(inputPesoValor);
		if(cantidadElegida<=0)			nuevoIntentoEjecutar(nuevoIntentoDialog("Solo se permiten valores numericos mayor a 0."));
		
		for(int i=0; i<pesoNombre.length; i++) {
			if(pesoNombre[i]==inputPesoOpcion) {
				indexAux=i;
				break;
			}
		}
		
		if(indexAux!=-1) {
			pesoEnGramos = aGramo(cantidadElegida,indexAux);
			calculoDeGramoATodo(cantidadElegida, pesoEnGramos, pesoSimbolo[indexAux], pesoSimbolo);
			nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo Realizado."));
		}
	}
	
	private double aGramo(double valor, int index) {
		return (valor/pesoGramoAuxiliares[index]);
	}
	
	private void calculoDeGramoATodo(double valor, double valorEnGramos, String pesoSimbolo, String []pesoSimboloArr) {
		for(int i=0; i<pesoNombre.length; i++) {
			pesoElegidaAuxiliares[i] = valorEnGramos * pesoGramoAuxiliares[i];
		}
		createShowMessage(null,
				"Resultados de convertir "+valor+" ("+pesoSimbolo+")\n\n"
				+String.format("%.5f",pesoElegidaAuxiliares[0])+" "+pesoNombre[0]+" ("+pesoSimboloArr[0]+")\n"
				+String.format("%.5f",pesoElegidaAuxiliares[1])+" "+pesoNombre[1]+" ("+pesoSimboloArr[1]+")\n"
				+String.format("%.5f",pesoElegidaAuxiliares[2])+" "+pesoNombre[2]+" ("+pesoSimboloArr[2]+")\n"
				+String.format("%.5f",pesoElegidaAuxiliares[3])+" "+pesoNombre[3]+" ("+pesoSimboloArr[3]+")\n"
				+String.format("%.5f",pesoElegidaAuxiliares[4])+" "+pesoNombre[4]+" ("+pesoSimboloArr[4]+")\n"
				+String.format("%.5f",pesoElegidaAuxiliares[5])+" "+pesoNombre[5]+" ("+pesoSimboloArr[5]+")\n",
				pesoTitle,
				JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void nuevoIntentoEjecutar(int opcion) {
		if(opcion==0) {
			ConversorPeso conversor_peso = new ConversorPeso();
			conversor_peso.conversorPesoPanel();
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
					pesoTitle,
					null));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al crear panel nuevo intento.");
			createFinalMessage(null);
			System.exit(0);
			return 0;
		}
	}
}