package herramientas;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import controllers.EmpleadoController;
import models.ObraBean;

public class EnvioCorreoConUsuario
{
	// modificamos la clase para que reciba , nuevos parametros . 
	
    public EnvioCorreoConUsuario(String nivel,String adj, String nomAdj, int numObra, int numPaq, ObraBean obra,String nombre , String apellido)
    {
    	EmpleadoController ec = new EmpleadoController();
    	System.out.println("entro a la clase para enviar el correo ");
    	
    	try
    	
        {
    		
    		
    		ArrayList<String> listaDeCorreos = ec.getCorreosXAccionNivel(nivel);
    		listaDeCorreos.size();
    		System.out.println(listaDeCorreos.get(0));
    		System.out.println("genero la lista para enviar con los correos ");
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.millerbi.net");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "sistemaproto@millerbi.net");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sistemaproto@millerbi.net"));
            
            int sizeTo=listaDeCorreos.size();
            InternetAddress[] addressTo = new InternetAddress[sizeTo];
            for (int i = 0; i < sizeTo; i++)
            {
            addressTo[i] = new InternetAddress(listaDeCorreos.get(i).toString()) ;
            }
            message.setRecipients(
                Message.RecipientType.TO,
                /*new InternetAddress("marceloburriel@gmail.com")*/addressTo);
            message.setSubject("Actualización");
            System.out.println(nivel);
            System.out.println(" Va a entrar a case .");
            switch(nivel){
            
            
            case "aprobar paquete ctrl prod":
            	BodyPart texto = new MimeBodyPart();
            	MimeMultipart multiParte = new MimeMultipart();
            	String html="Se ha producido una actualización en el sistema.<br>" + "Tiene una nueva orden de producción para <b>aprobar</b> en obra número <b>"+numObra+"</b>, paquete número <b>"+numPaq+"</b>."
            			+ "  Aprobado por : " + apellido + nombre;
            	
                texto.setContent(html, "text/html");
                if (adj!="") {
					
				
                BodyPart adjunto = new MimeBodyPart();
                adjunto.setDataHandler(
                    new DataHandler(new FileDataSource(adj)));
                adjunto.setFileName(nomAdj);
                
                
                
                multiParte.addBodyPart(adjunto);
                }
                multiParte.addBodyPart(texto);
                message.setContent(multiParte);
                break;
                
            case "aprobar paquete ing":message.setText(
            		"Se ha producido una actualización en el sistema.<br>" + "Tiene una nueva orden de producción para <b>aprobar</b> en obra número <b>"+numObra+"</b>, paquete número <b>"+numPaq+"</b>."
            		+ "  Aprobado por : " + apellido + nombre,
            		"ISO-8859-1",
            		"html");
            		break;
            		
            case "definir tareas":message.setText(
            		"Se ha producido una actualización en el sistema.<br>" + "Tiene una nueva orden de producción para <b>definir tareas</b> en obra número <b>"+numObra+"</b>, paquete número <b>"+numPaq+"</b>."
            		+ "  Aprobado por : " + apellido+" " + nombre,
            		"ISO-8859-1",
            		"html");
                    System.out.println(" Entro al Case para definir tareas ");
            		break;
            
            case "obra nueva":message.setText(
            		"Se ha producido una actualización en el sistema.<br>" + "Se ha creado la obra <b>"+obra.getNumero()+" - "+obra.getNombre()+"</b>."
            		+ "  Aprobado por : " + apellido + nombre,
            		"ISO-8859-1",
            		"html");
        			break;
        			
            case "obra modificada":message.setText(
            		"Se ha producido una actualización en el sistema.<br>" + "Se ha modificado la obra <b>"+obra.getNumero()+" - "+obra.getNombre()+"</b>."
            		+ "  Aprobado por : " + apellido+" "+ nombre,
            		"ISO-8859-1",
            		"html");
        			break;
            
          // arreglar 		
            case "descripcion_nueva":message.setText(
            		"Se ha producido una actualización en el sistema.<br>" + "Tiene un pedido . <b>Hay una lista con elementos Warehouse .</b> en obra número <b>"+numObra+"</b>, paquete número <b>"+numPaq+"</b>."
            		+ "  Aprobado por : " + apellido+" " + nombre,
            		"ISO-8859-1",
            		"html");
                    System.out.println(" Entro al Case para definir tareas ");
            		break;
            
        }
        
            
            // crear un case mas , con la  nuevo nueva accion . 
            
            
            
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("sistemaproto@millerbi.net", "sistemaproto2013");
            t.sendMessage(message, message.getAllRecipients());
            
            // Guardamos en carpeta de salida
            Store store = session.getStore("imap");
            store.connect("smtp.millerbi.net", "sistemaproto@millerbi.net", "sistemaproto2013");
            Folder folder = store.getFolder("INBOX.Sent");
            folder.open(Folder.READ_WRITE);  
            message.setFlag(Flag.SEEN, true);  
            folder.appendMessages(new Message[] {message});  
            store.close();

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}