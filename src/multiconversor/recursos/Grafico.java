package multiconversor.recursos;

import java.awt.Component;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Grafico {
	private static final String appName = "Multi Conversor";
	private String [] intentarNuevamente = {"Si, volver a intentar","No, volver al menu principal","Salir"};
	private String titleFinal = appName+" Challenge BackEnd Alura";
	private String messageFinal = "Gracias por utilizar Multi Conversor\n\nDesarrollado por Francisco Delahaye";
	
	public Object createOptionDialog(Component component, Object message, String title, int optionType, int messageType, Icon icon, Object[] options, Object initialValue) {
		return(JOptionPane.showOptionDialog(component,message,appName+title,optionType,messageType,icon,options,initialValue));
	}
	
	public int createIntentarNuevoDialog(Component component, Object message, String title, Icon icon) {
		return(JOptionPane.showOptionDialog(component,message+"\n\n¿Quiere volver a intentarlo?",appName+title,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,icon,intentarNuevamente,intentarNuevamente[0]));
	}
	
	public Object createInputOption(Component component, Object message, String title, int messageType, Icon icon, Object[] options, Object initialValue) {
		return(JOptionPane.showInputDialog(component,message,appName+title,messageType,icon,options,initialValue));
	}
	
	public String createInputTextArea(Component component, Object message, String title, int messageType) {
		return(JOptionPane.showInputDialog(component,message,appName+title,messageType));
	}
	
	public void createShowMessage(Component component, Object message, String title, int messageType) {
		JOptionPane.showMessageDialog(component,message,appName+title,messageType);
	}
	
	public void createFinalMessage(Component component) {
		JOptionPane.showMessageDialog(component,messageFinal,titleFinal,JOptionPane.PLAIN_MESSAGE);
	}
	
	public Image resizeImage(ImageIcon icon,String imageSrc, int ancho, int alto) {
		icon = new ImageIcon(imageSrc);
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(ancho, alto,  java.awt.Image.SCALE_SMOOTH);
		return(newimg);
	}
	
	public double validarInputStringToDouble(String input) {
		if(input.isEmpty() || input.equalsIgnoreCase("0"))
			return 0;
		try {
			return (Double.parseDouble(input));
		} catch (Exception e) {
			return 0;
		}
	}
}