package co.com.hoteles.turin.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionesUtil {
	
	private static ValidacionesUtil validacionesUtil;
	
	private ValidacionesUtil() {
		
	}
	
	public static ValidacionesUtil getInstance() {
		
		if(validacionesUtil == null) {
			validacionesUtil = new ValidacionesUtil();
		}
		return validacionesUtil;
		
	}

	public boolean validarCorreo(String correo){
		
		
		boolean retorno = false;
		 String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
			      "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		Pattern pattern = Pattern.compile(emailPattern);
		if (correo != null) {
			   Matcher matcher = pattern.matcher(correo);
			   if (matcher.matches()) {
			     retorno =  true;
		} 
		}
		 
		 return retorno;
	}
	
}
