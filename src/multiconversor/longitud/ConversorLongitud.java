package multiconversor.longitud;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import multiconversor.panelprincipal.PanelPrincipal;
import multiconversor.recursos.Funciones;
import multiconversor.recursos.InterfaceNuevoIntento;
import multiconversor.recursos.Lista;

public class ConversorLongitud extends Funciones implements InterfaceNuevoIntento{
	private List<Lista> longitudList = new ArrayList<>();
	private String [] longitudArrayNombre;
	private double [] longitudAuxiliares;
	private String longitudTitle = " - Longitud";
	
	public void conversorLongitudPanel() {
		longitudList.add(0, new Lista("Kilometros", "km", 0.00001));			//Las equivalencias corresponden a 1 centimetro
		longitudList.add(1, new Lista("Metros", "m", 0.01));
		longitudList.add(2, new Lista("Decimetros", "dc", 0.1));
		longitudList.add(3, new Lista("Centimetros", "cm", 1));
		longitudList.add(4, new Lista("Milimetros", "mm", 10));
		longitudList.add(5, new Lista("Millas", "mi", 0.000006213711922373));
		longitudList.add(6, new Lista("Pies", "ft", 0.03280839895013));
		longitudList.add(7, new Lista("Pulgadas", "in", 0.3937007874016));
		longitudArrayNombre = new String[longitudList.size()];
		longitudAuxiliares = new double[longitudList.size()];
		double cantidadElegida=0;
		int centimetroIndex=-1, opcionElegidaIndex=-1;
		
		for(int i=0; i<longitudList.size(); i++) {
			longitudArrayNombre[i] = longitudList.get(i).getName();
		}
		
		Object inputLongitudOpcion = createInputOption(null,
										"Seleccione escala de longitud a usar para convertir",
										longitudTitle,
										JOptionPane.PLAIN_MESSAGE,
										null,
										longitudArrayNombre,
										longitudArrayNombre[0]);
		if(inputLongitudOpcion==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		
		String inputLongitudValor = createInputTextArea(null,
										"Introduzca el numero de "+inputLongitudOpcion+" a convertir.",
										longitudTitle,
										JOptionPane.PLAIN_MESSAGE);
		
		if(inputLongitudValor==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		cantidadElegida=validarInputStringToDouble(inputLongitudValor);
		if(cantidadElegida<=0)			nuevoIntentoEjecutar(nuevoIntentoDialog("Solo se permiten valores numericos mayor a 0."));
		
		for(int i=0; i<longitudList.size(); i++) {
			if(longitudList.get(i).getSymbol() == "cm") 				centimetroIndex = i;
			if(longitudList.get(i).getName() == inputLongitudOpcion) 	opcionElegidaIndex = i;
		}
		
		try {
			StringBuilder resultString = new StringBuilder();
			longitudAuxiliares[centimetroIndex] = cantidadElegida / longitudList.get(opcionElegidaIndex).getEquivalencia();
			resultString.append("Resultados de convertir "+cantidadElegida+" ("+longitudList.get(opcionElegidaIndex).getName()+")\n\n");
			
			for(int i=0; i<longitudList.size(); i++) {
				longitudAuxiliares[i] = longitudAuxiliares[centimetroIndex] * longitudList.get(i).getEquivalencia();
				if(i != opcionElegidaIndex) {
					resultString.append(String.format("%.5f",longitudAuxiliares[i])+" "+longitudList.get(i).getName()+" ("+longitudList.get(i).getSymbol()+")\n");
				}				
			}
			createShowMessage(null,
					resultString, 
					longitudTitle,
					JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			nuevoIntentoEjecutar(nuevoIntentoDialog("Ocurrio un error."));
		}	
		nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo Realizado."));
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