package gui;

import board.Board;
import board.BoardUtils;
import move.Move;
import piece.Alliance;
import piece.CheckerPiece;
import piece.NormalCheckerPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

public class Table extends JFrame {

    private static final Dimension FRAME_DIMENSION = new Dimension(450, 450);

    private JMenu frameMenu;
    private JMenuItem positionSetupMenu;
    private JMenuItem newGame;

    private JFrame mainFrame;

    public Table(){
        setTitle("JCheckers");

        JMenuBar menuBar = new JMenuBar();

        frameMenu = new JMenu("File");
        positionSetupMenu = new JMenuItem("Set Position");
        newGame = new JMenuItem("Set Game");

        setDefaultCloseOperation(3);
        setSize(FRAME_DIMENSION);
        add(new BoardPanel());

        frameMenu.add(positionSetupMenu);
        frameMenu.add(newGame);

        menuBar.add(frameMenu);

        setJMenuBar(menuBar);
        setVisible(true);

    }


}

