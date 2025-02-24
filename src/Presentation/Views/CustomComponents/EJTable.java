package Presentation.Views.CustomComponents;//package Presentation.Views.CustomComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.Serial;

/**
 * Aquesta custom class ens permet customitzar les JTABLE.
 */
public class EJTable extends JTable {
    @Serial
    private static final long serialVersionUID = 1L;

    public EJTable(DefaultTableModel model, int column) {
        super(model);
        decorate(model, column);

    }

    /**
     * Propietats de la taula
     *
     * @param row
     * @param column
     * @return Si es pot o no.
     */
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * Decorarla
     *
     * @param model
     * @param column
     */
    private void decorate(DefaultTableModel model, int column) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        setRowSorter(tr);

        setColumns(model, column);

        setEnabled(true);
        setBackground(new Color(20, 20, 20));
        setShowGrid(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < column; i++) {
            getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        setRowHeight(40);
        setForeground(Color.white);
        setFont((new Font("Apple Casual", Font.PLAIN, 16)));
        getColumnModel().getColumn(0).setMinWidth(100);
        getColumnModel().getColumn(0).setMaxWidth(100);
        setSelectionBackground(new Color(252, 194, 208));
        clearSelection();


        JTableHeader jTableHeader = getTableHeader();
        jTableHeader.setBackground(new Color(20, 20, 20));
        jTableHeader.setForeground(Color.gray);
        jTableHeader.setFont((new Font("Apple Casual", Font.BOLD, 13)));
    }

    /**
     * Setejar les columnes
     *
     * @param model  El model
     * @param column Columnes
     */
    private void setColumns(DefaultTableModel model, int column) {
        model.setColumnCount(0);
        model.addColumn("#");
        if (column == 2) {
            model.addColumn("LISTAS");
        }
        if (column == 6) {
            model.addColumn("TÍTULO");
            model.addColumn("ALBUM");
            model.addColumn("GÉNERO");
            model.addColumn("AUTOR");
            model.addColumn("PROPIETARIO");
        }
    }
}