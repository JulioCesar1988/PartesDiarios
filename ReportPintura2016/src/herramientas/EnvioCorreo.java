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



public class EnvioCorreo
{

    public EnvioCorreo(String nivel,String adj, String nomAdj, int numObra, int numPaq, ObraBean obra)
    {
    	EmpleadoController ec=new EmpleadoController();
    	
    	try
        {
    		ArrayList<String> listaDeCorreos = ec.getCorreosXAccionNivel(nivel);
    		listaDeCorreos.size();
    		System.out.println(listaDeCorreos.get(0));
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
            switch(nivel){
            case "aprobar paquete ctrl prod":
            	BodyPart texto = new MimeBodyPart();
            	MimeMultipart multiParte = new MimeMultipart();
            	String html="Se ha producido una actualización en el sistema.<br>" + "Tiene una nueva orden de producción para <b>aprobar</b> en obra número <b>"+numObra+"</b>, paquete número <b>"+numPaq+"</b>.";
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
            		"Se ha producido una actualización en el sistema.<br>" + "Tiene una nueva orden de producción para <b>aprobar</b> en obra número <b>"+numObra+"</b>, paquete número <b>"+numPaq+"</b>.",
            		"ISO-8859-1",
            		"html");
            		break;
            case "definir tareas":message.setText(
            		"Se ha producido una actualización en el sistema.<br>" + "Tiene una nueva orden de producción para <b>definir tareas</b> en obra número <b>"+numObra+"</b>, paquete número <b>"+numPaq+"</b>.",
            		"ISO-8859-1",
            		"html");
            		break;
            
            case "obra nueva":message.setText(
            		"Se ha producido una actualización en el sistema.<br>" + "Se ha creado la obra <b>"+obra.getNumero()+" - "+obra.getNombre()+"</b>.",
            		"ISO-8859-1",
            		"html");
        			break;
            case "obra modificada":message.setText(
            		"Se ha producido una actualización en el sistema.<br>" + "Se ha modificado la obra <b>"+obra.getNumero()+" - "+obra.getNombre()+"</b>.",
            		"ISO-8859-1",
            		"html");
        			break;
            }
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