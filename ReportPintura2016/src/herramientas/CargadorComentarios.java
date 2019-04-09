package herramientas;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class CargadorComentarios {

	public void setComentariosTablaPiezas(JTable tablePiezas){
		TableColumn elementoColumn;
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		
		elementoColumn=tablePiezas.getColumnModel().getColumn(0);
		renderer.setToolTipText("ingrese cantidad");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(1);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("seleccione elemento precargado");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(2);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese correlatividad");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(3);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("descripción del elemento seleccionado");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(4);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese descripción extra");
        elementoColumn.setCellRenderer(renderer);
        /*elementoColumn=tablePiezas.getColumnModel().getColumn(5);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("tilde la casilla si la pieza debe ser pintada");
        elementoColumn.setCellRenderer(renderer);*/
        elementoColumn=tablePiezas.getColumnModel().getColumn(6);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese color de pintura");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(7);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese largo en mm");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(8);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("peso unitario");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(9);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("peso total");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(10);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("área del elemento seleccionado");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(11);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese ubicación de la pieza en el edificio");
        elementoColumn.setCellRenderer(renderer);
	}
	
		public void setComentariosTablaPiezasConCodigo(JTable tablePiezas){
		TableColumn elementoColumn;
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		
		elementoColumn=tablePiezas.getColumnModel().getColumn(0);
		renderer.setToolTipText("ingrese cantidad");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(1);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("seleccione elemento precargado");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(2);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese correlatividad");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(3);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("descripción del elemento seleccionado");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(4);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese descripción extra");
        elementoColumn.setCellRenderer(renderer);
        /*elementoColumn=tablePiezas.getColumnModel().getColumn(5);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("tilde la casilla si la pieza debe ser pintada");
        elementoColumn.setCellRenderer(renderer);*/
        elementoColumn=tablePiezas.getColumnModel().getColumn(6);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese color de pintura");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(7);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese largo en mm");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(8);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("peso unitario");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(9);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("peso total");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(10);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("área del elemento seleccionado");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(11);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("código de la pieza");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tablePiezas.getColumnModel().getColumn(12);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese ubicación de la pieza en el edificio");
        elementoColumn.setCellRenderer(renderer);
	}
	
	public void setComentariosTablaSubpiezas(JTable tableSubpiezas){
		TableColumn elementoColumn;
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		
		elementoColumn=tableSubpiezas.getColumnModel().getColumn(0);
		renderer.setToolTipText("ingrese cantidad");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(1);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese elemento");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(2);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese correlatividad");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(3);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese descripción del elemento");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(4);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("seleccione material");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(5);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("peso de material seleccionado");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(6);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese largo o área según corresponda");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(7);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("peso unitario");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(8);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("peso total");
        elementoColumn.setCellRenderer(renderer);
        elementoColumn=tableSubpiezas.getColumnModel().getColumn(9);
        renderer=new DefaultTableCellRenderer();
		renderer.setToolTipText("ingrese observaciones");
        elementoColumn.setCellRenderer(renderer);
        
	}
	
	
	
}
