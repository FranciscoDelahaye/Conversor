package multiconversor.pesos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import multiconversor.panelprincipal.PanelPrincipal;
import multiconversor.recursos.Funciones;
import multiconversor.recursos.InterfaceNuevoIntento;
import multiconversor.recursos.Lista;

public class ConversorPeso extends Funciones implements InterfaceNuevoIntento {
	private List<Lista> pesoList = new ArrayList<>();
	private String [] pesoArrayNombre;
	private double [] pesoElegidoAuxiliares;
	private String pesoTitle = " - Peso";
	
	public void conversorPesoPanel() {
		pesoList.add(0, new Lista("Tonelada", "t", 0.000001));
		pesoList.add(1, new Lista("Kilogramo", "kg", 0.001));
		pesoList.add(2, new Lista("Gramo", "g", 1));
		pesoList.add(3, new Lista("Miligramo", "mg", 1000));
		pesoList.add(4, new Lista("Libra", "lb", 0.0022046244201837776));
		pesoList.add(5, new Lista("Onza", "oz", 0.03527461286112385));
		pesoArrayNombre = new String[pesoList.size()];
		pesoElegidoAuxiliares = new double[pesoList.size()];		
		double cantidadElegida=0;
		int gramoIndex=-1, opcionElegidaIndex=-1;
		
		for(int i=0; i<pesoList.size(); i++) {
			pesoArrayNombre[i] = pesoList.get(i).getName();
		}
		
		Object inputPesoOpcion = createInputOption(null,
				"Seleccione escala de peso/masa a usar para convertir",
				pesoTitle,
				JOptionPane.PLAIN_MESSAGE,
				null,
				pesoArrayNombre,
				pesoArrayNombre[0]);
		if(inputPesoOpcion==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		
		String inputPesoValor = createInputTextArea(null,
				"Introduzca el numero de "+inputPesoOpcion+" a convertir.",
				pesoTitle,
				JOptionPane.PLAIN_MESSAGE);		

		if(inputPesoValor==null)		nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		cantidadElegida=validarInputStringToDouble(inputPesoValor);
		if(cantidadElegida<=0)			nuevoIntentoEjecutar(nuevoIntentoDialog("Solo se permiten valores numericos mayor a 0."));
		
		for(int i=0; i<pesoList.size(); i++) {
			if(pesoList.get(i).getSymbol() == "g") 					gramoIndex = i;
			if(pesoList.get(i).getName() == inputPesoOpcion) 		opcionElegidaIndex = i;
		}
		
		try {
			StringBuilder resultString = new StringBuilder();
			pesoElegidoAuxiliares[gramoIndex] = cantidadElegida / pesoList.get(opcionElegidaIndex).getEquivalencia();
			resultString.append("Resultados de convertir "+cantidadElegida+" ("+pesoList.get(opcionElegidaIndex).getName()+")\n\n");
			
			for(int i=0; i<pesoList.size(); i++) {
				pesoElegidoAuxiliares[i] = pesoElegidoAuxiliares[gramoIndex] * pesoList.get(i).getEquivalencia();
				if(i != opcionElegidaIndex) {
					resultString.append(String.format("%.5f",pesoElegidoAuxiliares[i])+" "+pesoList.get(i).getName()+" ("+pesoList.get(i).getSymbol()+")\n");
				}				
			}
			createShowMessage(null,
					resultString, 
					pesoTitle,
					JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			nuevoIntentoEjecutar(nuevoIntentoDialog("Ocurrio un error."));
		}	
		nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo Realizado."));
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