package multiconversor.divisas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import multiconversor.panelprincipal.PanelPrincipal;
import multiconversor.recursos.API_Request;
import multiconversor.recursos.Funciones;
import multiconversor.recursos.InterfaceNuevoIntento;
import multiconversor.recursos.Lista;

public class ConversorDivisas extends Funciones implements InterfaceNuevoIntento {
	private List<Lista> symbolList = new ArrayList<>();
	private String [] symbolArraySymbol;
	private String divisasTitle = " - Divisas";
	
	public void conversorDivisasPanel() {
		symbolList.add(0, new Lista("Peso Argentino", "ARS"));
		symbolList.add(1, new Lista("Dolar Estadounidense", "USD"));
		symbolList.add(2, new Lista("Euro", "EUR"));
		symbolList.add(3, new Lista("Libra Esterlina", "GBP"));
		symbolList.add(4, new Lista("Yen Japones", "JPY"));
		symbolList.add(5, new Lista("Won sul-coreano", "KRW"));
		symbolArraySymbol = new String[symbolList.size()];
		double cantidadElegida=0;
		
		for(int i=0; i<symbolList.size(); i++) {
			symbolArraySymbol[i] = symbolList.get(i).getSymbol();
		}
		
		Object inputDivisaDesde = createInputOption(null,
								"Seleccione desde que divisa quiere convertir",
								divisasTitle,
								JOptionPane.PLAIN_MESSAGE,
								null,
								symbolArraySymbol,
								symbolArraySymbol[0]);
		if(inputDivisaDesde==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));

		Object inputDivisaPara = createInputOption(null,
								"Seleccionado "+inputDivisaDesde+"\n\nSeleccione a que divisa quiere convertir",
								divisasTitle,
								JOptionPane.PLAIN_MESSAGE,
								null,
								symbolArraySymbol,
								symbolArraySymbol[1]);
		if(inputDivisaPara==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		
		if(inputDivisaDesde==inputDivisaPara)	nuevoIntentoEjecutar(nuevoIntentoDialog("Debe seleccionar 2 divisas distintas para realizar la conversion."));

		String inputCantidad = createInputTextArea(null,
								"De "+inputDivisaDesde+" a "+inputDivisaPara+"\n\nIngrese la cantidad de dinero que desea convertir: ",
								divisasTitle,
								JOptionPane.PLAIN_MESSAGE);
		
		if(inputCantidad==null)			nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		cantidadElegida=validarInputStringToDouble(inputCantidad);
		if(cantidadElegida<=0)			nuevoIntentoEjecutar(nuevoIntentoDialog("Solo se permiten valores numericos mayor a 0."));
		
		API_Request request = new API_Request();
		double result = request.getRequest(cantidadElegida, (String)inputDivisaDesde, (String)inputDivisaPara);

		if(result!=0) {
			createShowMessage(null,
					cantidadElegida+" "+(String)inputDivisaDesde+"("+inputDivisaDesde+")"+" equivalen a \n"+String.format("%.2f",result)+" "+(String)inputDivisaPara+"("+inputDivisaPara+")",
					divisasTitle,
					JOptionPane.PLAIN_MESSAGE);
		}
		else {
			nuevoIntentoEjecutar(nuevoIntentoDialog("Ocurrio un problema para calcular la conversion de divisas.\n\nVerifique su conexion a internet e intentelo de nuevo."));
		}
		nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo realizado."));
	}

	@Override
	public void nuevoIntentoEjecutar(int opcion) {
		if(opcion==0) {
			ConversorDivisas conversor_divisas = new ConversorDivisas();
			conversor_divisas.conversorDivisasPanel();
		}
		else if (opcion==1) {
			PanelPrincipal volverPanelPrincipal = new PanelPrincipal();
			volverPanelPrincipal.panelPrincipalPanel();
		}
		else if (opcion==2){
			createFinalMessage(null);
			System.exit(0);
		}
	}

	@Override
	public int nuevoIntentoDialog(String message) {
		try {
			return(createIntentarNuevoDialog(null,
					message,
					divisasTitle,
					null));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al crear panel nuevo intento.");
			createFinalMessage(null);
			System.exit(0);
			return 0;
		}
	}
}