package notationPanelTest;

import board.Board;
import move.Move;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NotationPanelTest extends JPanel {

    private static final Dimension HISTORY_PANEL_DIMENSION = new Dimension(100,400);

    private final DataModel model;
    private final JScrollPane scrollPane;

    NotationPanelTest(){
        this.setLayout(new BorderLayout());
        this.model = new DataModel();
        final JTable table = new JTable(model);
        table.setRowHeight(15);
        this.scrollPane = new JScrollPane();
        scrollPane.setColumnHeaderView(table.getTableHeader());
        scrollPane.setPreferredSize(HISTORY_PANEL_DIMENSION);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /*
    void redo(final Board board, final MoveLog moveLog){

        int currentRow = 0;
        this.model.clear();

        for(final Move move : moveLog.getMoves()){

            final String moveText = move.toString().isWhite();
        }
    }
    */

    private static class DataModel extends DefaultTableModel{

        private static final String [] NAMES = {"White", "Black"};
        private final List<Row> values;

        DataModel(){
            this.values = new ArrayList<>();
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

        @Override
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

            else {
                currentRow = this.values.get(row);
            }

            if(column == 0){
                currentRow.setWhiteMove((String) aValue);
            }

            else if(column == 1){
                currentRow.setBlackMove((String) aValue);
                fireTableCellUpdated(row, column);
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

    private static class Row{

        private String whiteMove;
        private String blackMove;

        Row(){

        }

        public String getWhiteMove(){
            return this.whiteMove;
        }

        public String getBlackMove(){
            return this.blackMove;
        }

        public void setWhiteMove(final String move){
            this.whiteMove = move;
        }

        public void setBlackMove(final String move){
            this.blackMove = move;
        }

    }

}
