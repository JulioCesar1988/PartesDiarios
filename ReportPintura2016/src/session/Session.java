package session;


import java.sql.SQLException;

import controllers.UsuarioController;

public class Session {

	static User usuario; 	
	
	public static Permiso getPermisos() {
		return usuario.getPermiso();
	}
	
	public static String getNombreUsuario() {
		return usuario.getNombreDeUsuario();
	}

	
	public boolean cargarUsuario(String nombreDeUsuario,String password) {
		UsuarioController uController = new UsuarioController();
		int aux = 0;
		Permiso perm = new Permiso();
		try {
			aux = uController.generarPermisos(nombreDeUsuario, password, perm);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (aux==1) {
			if (Session.usuario==null) {
				usuario= new User();
			}
			Session.usuario.setNombreDeUsuario(nombreDeUsuario);
			Session.usuario.setPermiso(perm);
			return true;
		} else {
			return false;
		}
	}
}

