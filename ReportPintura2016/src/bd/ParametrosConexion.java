package bd;
public class ParametrosConexion {

	String host;
	String port;
	String bdName;
	String user;
	String pass;
	String driverString = new String("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	
	
	public ParametrosConexion() {
		// TODO Auto-generated constructor stub
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getBdName() {
		return bdName;
	}
	public void setBdName(String bdName) {
		this.bdName = bdName;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDriverString() {
		return driverString;
	}
	public void setDriverString(String driverString) {
		this.driverString = driverString;
	}
	public static ParametrosConexion getParametros(){
		ParametrosConexion retorno = new ParametrosConexion();
		retorno.setBdName("millerdb");
		//retorno.setBdName("MILLER13");
		retorno.setPort("1433");
		
		/** Server Producción: SERVERPROTO **/
		retorno.setHost("159.96.45.180");
		retorno.setUser("admin");
		retorno.setPass("ggam2013");
		
		/** Server Producción: WIN-9B65SB60LJ0 **/
		//retorno.setHost("159.96.45.192");
		//retorno.setUser("desarrollador");
		//retorno.setPass("dd");
		
		return retorno;
	}
	
	
}
