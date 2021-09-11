package gui;

import move.AttackMove;
import move.Move;
import move.NormalMove;
import piece.Alliance;

import javax.naming.event.ObjectChangeListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class NotationPanel extends JPanel {

    private static final String [] NAMES = {"White", "Black"};

    private final DataModel model;
    private final JScrollPane scrollPane;

    public NotationPanel(){
        this.setLayout(new BorderLayout());
        this.model = new DataModel();
        final JTable table = new JTable(model);
        table.setRowHeight(15);
        scrollPane = new JScrollPane(table);
        scrollPane.setColumnHeaderView(table.getTableHeader());
        scrollPane.setPreferredSize(Table.NOTATION_DIMENSION);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public DataModel getModel(){
        return model;
    }

    public void update(Move move, int row, int column){
        model.setValueAt(move.toString(), row, column);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                validate();
                repaint();
            }
        });

    }

    public static class DataModel extends DefaultTableModel{

        private List<Row> values;

        public DataModel(){
            values = new ArrayList<>();
        }

        public void clear(){
            this.values.clear();
            setRowCount(0);
        }

        @Override
        public int getRowCount(){
            if(this.values == null){
                return 0;
            }

            return this.values.size();
        }

        @Override
        public int getColumnCount(){
            return NAMES.length;
        }

        public Object getValueAt(final int row, final int column){
            final Row currentRow = this.values.get(row);

            if(column == 0){
                return currentRow.getWhiteMove();
            }

            else if(column == 1){
                return currentRow.getBlackMove();
            }

            return null;
        }

        @Override
        public void setValueAt(final Object aValue, final int row, final int column){
            final Row currentRow;

            if(this.values.size() <= row){
                currentRow = new Row();
                this.values.add(currentRow);
            }

            else{
                currentRow = this.values.get(row);
            }

            if(column == 0){
                currentRow.setWhiteMove((String) aValue);
                fireTableDataChanged();
            }

            else if(column == 1){
                currentRow.setBlackMove((String) aValue);
                fireTableDataChanged();
            }

        }

        @Override
        public Class<?> getColumnClass(final int column){
            return Move.class;
        }

        @Override
        public String getColumnName(final int column){
            return NAMES[column];
        }
    }

    public static class Row{

        private String whiteMove;
        private String blackMove;

        public Row(){

        }

        public String getWhiteMove(){
            return whiteMove;
        }

        public String getBlackMove(){
            return blackMove;
        }

        public void setWhiteMove(String move){
            whiteMove = move;
        }

        public void setBlackMove(String move){
            blackMove = move;
        }

        @Override
        public String toString(){
            return "White Move: " + whiteMove + " Black Move: " + blackMove;
        }
    }
}
