package gui;

import move.AttackMove;
import move.Move;
import piece.Alliance;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NotationPanel extends JPanel {

    private static String columnNames[];

    private DefaultTableModel tableModel;

    private String data[][];
    private JScrollPane scrollPane;
    private JTable notationTable;
    private Move previousMove;
    private int moveCount;

    public NotationPanel(){

        tableModel = new DefaultTableModel();

        columnNames = new String [] {"White", "Black"};
        data = new String [50][2];

        data[0][0] = "White";
        data[0][1] = "Black";

        notationTable = new JTable(data ,columnNames);
        notationTable.setBorder(BorderFactory.createBevelBorder(1));

        this.moveCount = 1;
        setLayout(new FlowLayout());
        setPreferredSize(Table.NOTATION_DIMENSION);
        add(notationTable);
    }

    public void addMoveToTextArea(Alliance alliance, Move move){

        if(alliance.getAlliance() == Alliance.WHITE){
            String moveRepresentation = moveCount + "." + move.getCurrentCoordinate() + "x" + move.getDestinationCoordinate();
            this.moveCount++;
        }

        else if(alliance.getAlliance() == Alliance.BLACK){
            String moveRepresentation = " " + move.getCurrentCoordinate() + "x" + move.getDestinationCoordinate() + " ";
        }

    }
}
