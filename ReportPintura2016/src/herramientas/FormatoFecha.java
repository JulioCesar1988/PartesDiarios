package herramientas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class FormatoFecha  extends MaskFormatter {
    /** Se construye con el patrón deseado */
    public FormatoFecha() throws ParseException
    {
        // Las # son cifras y representa "dd/mm/yy hh:mm:ss"
        super ("##:##");
    }

    /** Una clase adecuada para convertir Date a String y viceversa de forma cómoda. Puedes ver cómo se hace el patrón "dd/MM/yy kk:mm:ss" en la API.
        El patrón que pongamos aquí debe cuadrar correctamente con la máscara que hemos puesto en el constructor */
    @SuppressWarnings("unused")
	private SimpleDateFormat formato = new SimpleDateFormat("hh:mm");

    /*
    // Convierte el texto del editor en un Date
    public Object stringToValue(String text) throws ParseException
    {
        return formato.parseObject(text);
    }

    // Redibe un Date o null y debe convertirlo a texto que cumpla el patrón indicado anteriormente 
    public String valueToString(Object value) throws ParseException
    {
        if (value instanceof Date)
            return formato.format((Date)value);
        return formato.format(new Date());
    }
    */
}
