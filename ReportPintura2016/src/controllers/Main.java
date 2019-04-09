package controllers;


import java.sql.*;


public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		int num = 55555;
		ObrasController oController = new ObrasController();
		//System.out.println(oController.getIdObraPorId(num));
		

/*try{
		Conector c;
		Connection conexion = null;

		String tipo = "Chapa";
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		System.out.println("hola");
		CallableStatement cs = conexion.prepareCall("{ ? = call dbo.devolver_tipo_material (?)}");
		
		cs.registerOutParameter(1, Types.OTHER);
		cs.setString(2, tipo);
		System.out.println("HOOLA1/2");
		
		ResultSet resultado = cs.executeQuery();
		System.out.println("hola2");
		//ResultSet resultado = cs.getResultSet();
		ArrayList<String> lista = new ArrayList<String>();
		while(resultado.next()){
			lista.add(resultado.getString(1));
		}
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
	
	}catch(Exception e){
		System.out.println("Consulta erronea");
	}

		
}		*/
		
		
		
		
		
	/*	CamionBean camion = new CamionBean();
		camion.setPatente("ale123");
		camion.setCompania("DHL");
		
		
		try {
			String queryString = "INSERT INTO Camiones"
					+ "(patente, compania) "
					+ "VALUES(?,?);";
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			PreparedStatement query = (PreparedStatement) conexion.prepareStatement(queryString);
			query.setString(1, camion.getPatente());
			query.setString(2, camion.getCompania());
		
		
			 query.executeUpdate();
			 System.out.println("se inserto");
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("No se inserto");

		} finally {

			conexion.close();
			
		}
		
		
		
		
		
		// TODO Auto-generated method stub
		//PiezaBean pieza = new PiezaBean();
		//PiezasController pController = new PiezasController();
	
		/*
		PiezasController pController = new PiezasController();
		ArrayList<PiezaBean> piezas = pController.crearListadoMock();
		for (int i = 0; i < piezas.size(); i++) {
			piezas.get(i).imprimirDatos();
		}
		
		
		 
		
		
		
		
		
		/*
		ObraBean obra = new ObraBean();
		ObrasController oController = new ObrasController();
		
		obra.setNombre("cimbas2");
		obra.setCalle("la concha de la lora");
		obra.setLocalidad("Muy muy lejano");
		obra.setNumero(236);
		obra.setProvincia("bsas");
		obra.setPais("arg");
		
		oController.insert(obra);
		//ArrayList<ObraBean> aux = oController.getTodas();
		oController.imprimirListaDeObras(oController.getTodas());
		
		
		
		/*
		pieza.setNombre("DIEGO PUTO");
		pieza.setCodigo("N2221");
		pieza.setId(2212);
		
		try {
			pController.insert(pieza);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			pController.getAll();
		}	
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}




