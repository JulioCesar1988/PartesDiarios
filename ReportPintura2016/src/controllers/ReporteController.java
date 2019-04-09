package controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import bd.Conector;
import bd.ParametrosConexion;

public class ReporteController {
	Conector c;
	Connection conexion;
	
	public void borrarTablas() throws SQLException {
	try {
		
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		
		CallableStatement cs = conexion.prepareCall("{call reporte_produccion_borrar_tablas}");
		cs.setQueryTimeout(10000);
		
		
		cs.execute();
		
	}catch (Exception e) {
	      e.printStackTrace();
	    
	} finally {
		conexion.close();
	}}
	
	public void setReporteDeProduccion(int obra_num, String listas_num) throws SQLException {
				
		try {
		
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_consulta_fab (?, ?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
			cs.setString(2, listas_num);
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}		
		try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_guillotina_edit (?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
		
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}		
try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
		
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_plasma (?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
		
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_flange (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}

try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_conrac (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_detalle (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_armado (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_soldadura (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_amolado (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_purlin (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_slear (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_conformadora (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_plegadoras (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_zingueria (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
}
	public void borrarTablas2() throws SQLException {
	try {
		
		c = new Conector(ParametrosConexion.getParametros());
		conexion = c.getConnection();
		
		CallableStatement cs = conexion.prepareCall("{call reporte_produccion_borrar_tablas2}");
		cs.setQueryTimeout(10000);
		
		
		cs.execute();
		
	}catch (Exception e) {
	      e.printStackTrace();
	    
	} finally {
		conexion.close();
	}}
	
	public void setReporteDeProduccion2(int obra_num, String listas_num) throws SQLException {
				
		try {
		
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			System.out.println(listas_num);
			System.out.println(obra_num);
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_consulta_nofab (?, ?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
			cs.setString(2, listas_num);
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}		
		try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			System.out.println(listas_num);
			System.out.println(obra_num);
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_guillotina2 (?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
		
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}		
try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			System.out.println(listas_num);
			System.out.println(obra_num);
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_plasma2 (?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
		
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_flange2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}

try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_conrac2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_detalle2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_armado2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_soldadura2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_amolado2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_purlin2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_slear2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_conformadora2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_plegadoras2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	System.out.println(listas_num);
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_zingueria2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
}
	
	public void setReporteDeProduccion3(int obra_num, String fecha1, String fecha2) throws SQLException {
		
		try {
		
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_consulta_fab_dias_ambos (?, ?, ?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
		
			cs.setString(2, fecha1);
			cs.setString(3, fecha2);
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}		
		try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
			
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_guillotina_edit (?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
			
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}		
try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
		
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_plasma (?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
			
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_flange (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}

try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_conrac (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_detalle (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_armado (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_soldadura (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_amolado (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_purlin (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_slear (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_conformadora (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_plegadoras (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_zingueria (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);

	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
}
	
	public void setReporteDeProduccion4(int obra_num, String fecha1, String fecha2) throws SQLException {
		
		try {
		
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
		
			System.out.println(obra_num);
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_consulta_nofab_dias_ambos (?, ?,?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
		
			cs.setString(2, fecha1);
			cs.setString(3, fecha2);
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}		
		try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
	
			System.out.println(obra_num);
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_guillotina2 (?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
		//	cs.setString(2, listas_num);
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}		
try {
			
			c = new Conector(ParametrosConexion.getParametros());
			conexion = c.getConnection();
	
			System.out.println(obra_num);
			CallableStatement cs = conexion.prepareCall("{call reporte_produccion_plasma2 (?)}");
			cs.setQueryTimeout(10000);
			cs.setInt(1, obra_num);
	//		cs.setString(2, listas_num);
			
			cs.execute();
			
		}catch (Exception e) {
		      e.printStackTrace();
		    
		} finally {
			conexion.close();
		}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();

	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_flange2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}

try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();

	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_conrac2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();

	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_detalle2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_armado2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_soldadura2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_amolado2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
	//cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();
	
	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_purlin2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();

	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_slear2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();

	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_conformadora2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();

	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_plegadoras2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
try {
	
	c = new Conector(ParametrosConexion.getParametros());
	conexion = c.getConnection();

	System.out.println(obra_num);
	CallableStatement cs = conexion.prepareCall("{call reporte_produccion_zingueria2 (?)}");
	cs.setQueryTimeout(10000);
	cs.setInt(1, obra_num);
//	cs.setString(2, listas_num);
	
	cs.execute();
	
}catch (Exception e) {
      e.printStackTrace();
    
} finally {
	conexion.close();
}
}
	


}
