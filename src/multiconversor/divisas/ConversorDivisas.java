package multiconversor.divisas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import multiconversor.panelprincipal.PanelPrincipal;
import multiconversor.recursos.API_Request;
import multiconversor.recursos.Grafico;
import multiconversor.recursos.NuevoIntento;

public class ConversorDivisas extends Grafico implements NuevoIntento {
	private List<simbolo> symbolList = new ArrayList<>();
	private String symbolListName[];
	private String symbolListSymbol[];
	private String divisasTitle = " - Divisas";
	
	public void initSimbolo() {
		try {			
			symbolList.add(0, new simbolo("Peso Argentino", "ARS"));
			symbolList.add(1, new simbolo("Dolar Estadounidense", "USD"));
			symbolList.add(2, new simbolo("Euro", "EUR"));
			symbolList.add(3, new simbolo("Libra Esterlina", "GBP"));
			symbolList.add(4, new simbolo("Yen Japones", "JPY"));
			symbolList.add(5, new simbolo("Won sul-coreano", "KRW"));
			
			symbolListName = new String[symbolList.size()];
			symbolListSymbol = new String[symbolList.size()];
			
			for(int i=0; i<symbolList.size(); i++) {
				symbolListName[i] = symbolList.get(i).getName();
				symbolListSymbol[i] = symbolList.get(i).getSymbol();
			}
		} catch (Exception e) {
			createShowMessage(null,
						"Ocurrio un error al iniciar simbolos.",
						divisasTitle,
						JOptionPane.PLAIN_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void conversorDivisasPanel() {
		String divisaDesdeName="", divisaParaName="";
		double cantidadElegida=0;
		
		Object inputDivisaDesde = createInputOption(null,
								"Seleccione desde que divisa quiere convertir",
								divisasTitle,
								JOptionPane.PLAIN_MESSAGE,
								null,
								symbolListSymbol,
								symbolListSymbol[0]);
		if(inputDivisaDesde==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));

		Object inputDivisaPara = createInputOption(null,
								"Seleccionado "+inputDivisaDesde+"\n\nSeleccione a que divisa quiere convertir",
								divisasTitle,
								JOptionPane.PLAIN_MESSAGE,
								null,
								symbolListSymbol,
								symbolListSymbol[0]);
		if(inputDivisaPara==null)	nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));

		String inputCantidad = createInputTextArea(null,
								"De "+inputDivisaDesde+" a "+inputDivisaPara+"\n\nIngrese la cantidad de dinero que desea convertir: ",
								divisasTitle,
								JOptionPane.PLAIN_MESSAGE);
		
		if(inputCantidad==null)			nuevoIntentoEjecutar(nuevoIntentoDialog("Se cancelo operacion."));
		cantidadElegida=validarInputStringToDouble(inputCantidad);
		if(cantidadElegida<=0)			nuevoIntentoEjecutar(nuevoIntentoDialog("Solo se permiten valores numericos mayor a 0."));
		
		for(int i=0; i<symbolList.size(); i++) {
			if(symbolListSymbol[i] == inputDivisaDesde) {
				divisaDesdeName = symbolListName[i];
			}
			if(symbolListSymbol[i] == inputDivisaPara) {
				divisaParaName = symbolListName[i];
			}
		}
		
		API_Request request = new API_Request();
		double result = request.getRequest(cantidadElegida, (String)inputDivisaDesde, (String)inputDivisaPara);

		if(result!=0) {
			createShowMessage(null,
					cantidadElegida+" "+divisaDesdeName+"("+inputDivisaDesde+")"+" equivalen a \n"+String.format("%.2f",result)+" "+divisaParaName+"("+inputDivisaPara+")",
					divisasTitle,
					JOptionPane.PLAIN_MESSAGE);
			nuevoIntentoEjecutar(nuevoIntentoDialog("Calculo realizado."));
		}
		else {
			nuevoIntentoEjecutar(nuevoIntentoDialog("Ocurrio un problema para calcular la conversion de divisas.\n\nVerifique su conexion a internet e intentelo de nuevo."));
		}
	}

	@Override
	public void nuevoIntentoEjecutar(int opcion) {
		if(opcion==0) {
			ConversorDivisas conversor_divisas = new ConversorDivisas();
			conversor_divisas.initSimbolo();
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