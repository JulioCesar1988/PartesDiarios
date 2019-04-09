package controllers;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import models.EdificioBean;
import bd.Conector;
import bd.ParametrosConexion;



public class EdificioController {
	Conector c;
	Connection conexion;
	
	public ArrayList<EdificioBean> getEdificiosPorObra(int obra) throws SQLException {
		ArrayList<EdificioBean> listado = new ArrayList<>();
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.obra_devolver_edificios (?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			System.out.println(obra);
			cs.setInt(2, obra);
			
	
			ResultSet resultado = cs.executeQuery();
			while (resultado.next()) {
				EdificioBean edificio = new EdificioBean();
				edificio.setNombre(resultado.getString(1));
				System.out.println(resultado.getString(1));
				edificio.setObservaciones(resultado.getString(2));
				
				
				
				listado.add(edificio);
				System.out.println("OK3");
				
			}		
			return listado;
		}catch (Exception e) {
		      e.printStackTrace();
		      return listado;
		} finally {
			conexion.close();
		}
	}

	public int insert(EdificioBean edi, int obra_num) throws SQLException {
		
		try {
		
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call edificios_cargar (?, ?, ?, ?)}");
			cs.setInt(1, obra_num);
			cs.setString(2, edi.getNombre());
			cs.setString(3, edi.getObservaciones());
			
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			
			return cs.getInt(4);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		} finally {
			conexion.close();
		}		
	}
	
	/* Creamos un nuevo agregar edificio */
	
	
	public int insertar_edificio_descripcion(EdificioBean edi, int obra_num) throws SQLException {
		
		try {
		
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call edificios_cargar (?, ?, ?, ?)}");
			cs.setInt(1, obra_num);
			cs.setString(2, edi.getNombre());
			cs.setString(3, edi.getObservaciones());
			
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			
			return cs.getInt(4);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		} finally {
			conexion.close();
		}		
	}
	
	

	public String[] getListaDeEdificiosParaModificar(int obra) throws SQLException {
		
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.obra_devolver_edificios (?)}");
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			
			ResultSet resultado = cs.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			while(resultado.next()){
				lista.add(resultado.getString(1));
			}
			String vector [] = pasarAVerctor(lista);
			return vector;
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return null;
		}
		finally{
			conexion.close();
		}
	}
	private String[] pasarAVerctor(ArrayList<String> lista) {
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		return vector;
	}
	public int getEdificioParaModificar(int obra, String nomedificio,EdificioBean edificio) throws SQLException{
		try{
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{ ? = call dbo.edificio_devolver_para_modificar (?,?)}");
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, obra);
			cs.setString(3, nomedificio);
			
			ResultSet resultado = null;
			resultado = cs.executeQuery(); 
			if (resultado.next()) {
				
				edificio.setNombre(resultado.getString(1));
				edificio.setObservaciones(resultado.getString(2));
				
				return 1;
			}else {
				return 0;	
			}
			
		}catch(Exception e){
			System.out.println("Consulta erronea");
			return -1;
		}
		finally{
			conexion.close();
		}
		
	}
public int modificar(EdificioBean edificio, int obra) throws SQLException {
		
		try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			CallableStatement cs = conexion.prepareCall("{call edificios_modificar (?, ?, ?, ?)}");
			
			cs.setInt(1, obra);
			cs.setString(2, edificio.getNombre());
			cs.setString(3, edificio.getObservaciones());
			
			
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			return cs.getInt(4);
		}catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		} finally {
			conexion.close();
		}
	}
	
public int eliminar(String edificio, int obra_num) throws SQLException {
	try {
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		CallableStatement cs = conexion.prepareCall("{call edificios_eliminar (?, ?,?)}");
		cs.setInt(1, obra_num);
		cs.setString(2, edificio);
		cs.registerOutParameter(3, Types.INTEGER);
		cs.execute();
		return cs.getInt(3);
	}catch (Exception e) {
	      e.printStackTrace();
	      return -1;
	} finally {
		conexion.close();
	}
}

public int getIdEdificioPorNombre(String nombre, int id_obra) throws SQLException {
	try {
	
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		CallableStatement cs = conexion.prepareCall("{call edificio_devolverid (?, ?,?)}");
		cs.setString(1, nombre);
		cs.setInt(2, id_obra);
		cs.registerOutParameter(3, Types.INTEGER);
		cs.execute();
		return cs.getInt(3);
	}catch (Exception e) {
	      e.printStackTrace();
	      return -1;
	} finally {
		conexion.close();
	}		

}


// Agregar la informacion tecnica del edificio 

public int setEdificioInformacionTecnica(String titulo , String descripcion ,int num_obra , String edificio) throws SQLException {
	try {
		
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		
		CallableStatement cs = conexion.prepareCall("{call cargar_informacion_obra_edificio (?,?,?,?)}");
		
		cs.setString(1,titulo);
		cs.setString(2,descripcion);
		cs.setInt(3,num_obra);      // en el quer 
		cs.setString(4, edificio);
		//cs.setInt(3,numObra);
		//cs.setInt(4,34);
		//cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		return 1;
	}catch (Exception e) {
	      e.printStackTrace();
	      return -1;
	} finally {
		conexion.close();
	}		
}

public int setEdificioInformacionTecnicaModificar(String titulo , String descripcion ,int num_obra , String edificio) throws SQLException {
	try {
		
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		
		CallableStatement cs = conexion.prepareCall("{call cargar_informacion_tecnica_modificada (?,?,?,?)}");
		
		cs.setString(1,titulo);
		cs.setString(2,descripcion);
		cs.setInt(3,num_obra);     
		cs.setString(4, edificio);
		//cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		return 1;
	}catch (Exception e) {
	      e.printStackTrace();
	      return -1;
	} finally {
		conexion.close();
	}		
}



// Para obtener toda la informacion tecnica de un edificio ... 













// me deberia de retornar titulos y descripciones ...  o solo titulos ...para cargar en un combox
public int getEdificioInformacionTecnica(int Obra , String nombreEdificio ) throws SQLException {
	try {
		
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		
		// Ver como retornar los titulos 
		CallableStatement cs = conexion.prepareCall("{call cargar_informacion_obra_edificio (?,?)}");
		
		//cs.setString(1,titulo);
		//cs.setString(2,descripcion);
		//cs.setInt(3,numObra);
		//cs.setInt(4,34);
		//cs.registerOutParameter(2, Types.INTEGER);
		cs.execute();
		return 1;
	}catch (Exception e) {
	      e.printStackTrace();
	      return -1;
	} finally {
		conexion.close();
	}		
}

// Lista de titulos de una obra determinada y un edificio determinado 
public String[] getListaDeTitulos2( int numObra , String nombreEdificio  ) throws SQLException {
	try{
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		
		CallableStatement cs = conexion.prepareCall("{  call dbo.edificio_devolver_lista_titulos_2 (?,?)}");
		cs.setInt(1,numObra);
		cs.setString(2,nombreEdificio);
		//cs.registerOutParameter(1, Types.OTHER);	
		ResultSet resultado = cs.executeQuery();
		ArrayList<String> lista = new ArrayList<String>();
		while(resultado.next()){
			lista.add(resultado.getString(1));
		}
		
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		
		return vector;
	}catch(Exception e){
		System.out.println("Consulta erronea");
		return null;
	}
	finally{
		conexion.close();
	}
}

public String[] getListaDeTitulos() throws SQLException {
	try{
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		
		CallableStatement cs = conexion.prepareCall("{ ? = call dbo.edificio_devolver_lista_titulos ()}");
		cs.registerOutParameter(1, Types.OTHER);	
		ResultSet resultado = cs.executeQuery();
		ArrayList<String> lista = new ArrayList<String>();
		while(resultado.next()){
			lista.add(resultado.getString(1));
		}
		
		String vector [] = new String [lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vector[i] = lista.get(i);	
		}
		
		return vector;
	}catch(Exception e){
		System.out.println("Consulta erronea");
		return null;
	}
	finally{
		conexion.close();
	}
}


 // Acá vamos a obtener todas las descripciones de un edificio en particular .   

public String getDescripcionTodas(int numObra , String edificio ) throws SQLException {
	String contenido= "                              No existe informacion tecnica                  ";	
	
	try{
		
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		//Envio los 3 parametros para que me de la descripcion que le corresponde . 
		CallableStatement cs = conexion.prepareCall("{ call dbo.edificio_devolver_descripcion (?,?)}");
		
		
		cs.setInt(1,numObra);
		cs.setString(2,edificio);
		//cs.registerOutParameter(1, Types.OTHER);
		ResultSet resultado = cs.executeQuery();
		
		while(resultado.next()){		
			
			//System.out.println("Contenido : ");
			//System.out.println(resultado.getString(1) );
		
			 contenido =  (String)resultado.getString(1);
			
		} 
	
		return contenido;
		
	}catch(Exception e){
		
		System.out.println("Consulta erronea");
		
		return null;
	}
	finally{
		conexion.close();
	}

	
	
}






















// Vamos a devolver  la descripcion , dado su titulo 
public String getDescripcion(String titulo , int numObra , String edificio ) throws SQLException {
	String contenido= "Sin contenido";	
	try{
		
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		//Envio los 3 parametros para que me de la descripcion que le corresponde . 
		CallableStatement cs = conexion.prepareCall("{ call dbo.edificio_devolver_titulo_descripcion (?,?,?)}");
		
		cs.setString(1,titulo);
		cs.setInt(2,numObra);
		cs.setString(3,edificio);
		//cs.registerOutParameter(1, Types.OTHER);
		ResultSet resultado = cs.executeQuery();
		
		while(resultado.next()){		
			
			//System.out.println("Contenido : ");
			//System.out.println(resultado.getString(1) );
		
			 contenido =  (String)resultado.getString(1);
			
		} 
	
		return contenido;
		
	}catch(Exception e){
		
		System.out.println("Consulta erronea");
		
		return null;
	}
	finally{
		conexion.close();
	}

	
	
}


//Vamos a devolver  la descripcion , dado su titulo 
public int eliminar_informacion_tecnica( int numObra , String edificio ,String titulo) throws SQLException {

	try{
		
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
	
		
		CallableStatement cs = conexion.prepareCall("{ call dbo.edificio_descripcion_eliminar (?,?,?)}");
		
		cs.setInt(1,numObra);
		cs.setString(2,edificio);
		cs.setString(3,titulo);
		//cs.registerOutParameter(3, Types.OTHER);
		ResultSet resultado = cs.executeQuery();
		return 1;
		
	}catch(Exception e){
		
		System.out.println("Consulta erronea");
		
		return 0;
	}
	finally{
		conexion.close();
	}

	
	
}




	
}