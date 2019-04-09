package herramientas;

import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderCelda extends DefaultTableCellRenderer 
{
	
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) 
    {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if( value instanceof Integer && column==4)
        {
            Integer amount = (Integer) value;
            if( amount.intValue() == 0 )
            {
              //  cell.setBackground(new Color(205,92,92));
                cell.setForeground(new Color(255,0,0));
            }
            else
            {
            	if(amount.intValue()>0 && amount.intValue()<100){
                //    cell.setBackground(new Color(255, 215, 0));
                            cell.setForeground(new Color(0, 0, 0));
                    }
                    else{
            if(amount.intValue()==100){
            //cell.setBackground(new Color(34, 139, 34));
                    cell.setForeground(new Color(0, 128, 0));
            }
            else{
            cell.setBackground(Color.WHITE);
            cell.setForeground(Color.BLACK);
            }
            }
        }
        }
        return cell;
    }
}
