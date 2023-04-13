package conversiones.conversor_monedas;


import proceso_aplicacion.desarrollo;
import proceso_aplicacion.mensaje_final;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane; 


public class  conversor_moneda {
	
	public void obtener_datos() {
		
	
		//ingresar tipo moneda
		Object [] opciones_moneda={"De Dólar a Euro","De Dólar a pesos (MX)","De Euro a Dólar", "De Pesos(MX) a Dólar"}; 
		Object opciones_elegir_moneda = JOptionPane.showInputDialog(null,"Seleccione un opción de conversión", "Menu",JOptionPane.QUESTION_MESSAGE,null,opciones_moneda, opciones_moneda[0]);
		System.out.println(opciones_elegir_moneda);
		String opcionMoneda_elegida= (String)opciones_elegir_moneda;
		
		// por si no elige ninguna opcion de conversion
		if (opciones_elegir_moneda==null) {
			mensaje_final mensaje_final= new mensaje_final();
			mensaje_final.mensaje_final();
			// por si escoge no seguir se sale del programa
			System.exit(0);
		}
		
	
		try {
			//ingresar valor monetario
			String opcion_cantidad_dinero=JOptionPane.showInputDialog("Ingrese la cantidad de dinero que desea convertir \n Opción escogida: "+ opcionMoneda_elegida);
			
			//operción conversión
			conversor_moneda operacion= new conversor_moneda();
			operacion.resultado_conversion(opcionMoneda_elegida, opcion_cantidad_dinero);
		}
		// por si se  ingresa un valor  no permitido
		catch(NumberFormatException | NullPointerException exception) {
			JOptionPane.showMessageDialog(null,"Valor no valido","Error",JOptionPane.ERROR_MESSAGE);
			desarrollo intentar_de_nuevo= new desarrollo();
			intentar_de_nuevo.iniciar();

			
			
		}
		
		
		
		
	}
	
	public void resultado_conversion(String tipoConvercion, String cantidaDinero) {
		
		String datos_api[];//guarda los daots que se envia a  la API
		datos_api= new String[4];
		
		String pasar_De="",pasar_a = "";
	
		switch (tipoConvercion) {
				
			
				
			case "De Dólar a Euro":
				pasar_De="USD";
				pasar_a="EUR";
				break;
				
			case "De Dólar a pesos (MX)":
				pasar_De="USD";
				pasar_a="MXN";
				break;
				
				
			case "De Euro a Dólar":
				pasar_De="EUR";
				pasar_a="USD";
				break;
					
			case "De Pesos(MX) a Dólar":
				pasar_De="MXN";
				pasar_a="USD";
				break;
				
				
			case "Otros valores":
				conversor_moneda operacion= new conversor_moneda();
	
			
			default:
				break;
				//"De Pesos(MX) a Pesos(COP)",
		}
		
		
		
		// obtener la fecha actual 
		LocalDate actualDate =LocalDate.now();
		// pasar de tipo LocalDate a String
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String fecha_formateada = actualDate.format(formatter);
		
		
		// guardar los datos en el array
		datos_api[0]= fecha_formateada; 
		datos_api[1]=cantidaDinero;
		datos_api[2]=pasar_De;
		datos_api[3]=pasar_a;
		
		//pasar datos a la Api
		API_Conversor respuesta = new API_Conversor();
		double valor_convertido=respuesta.get(datos_api[0],datos_api[1],datos_api[2],datos_api[3]);
		
		
		//formato decimales del  resultado
		double valor_formateado=0;
		
		valor_formateado=Math.round((valor_convertido*100.0)/100.0);
		
		
		//mostrar conversión
		JOptionPane.showMessageDialog(null,"El valor de la conversión  "+ tipoConvercion+ " es : $"+ valor_formateado);
		
		//mensaje final
		
		mensaje_final mensaje_final= new mensaje_final();
		mensaje_final.mensaje_final();
		
		
		
	}
	
	
	
	
}
