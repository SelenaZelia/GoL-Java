package com.zelia.gol.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.zelia.gol.model.Board;

public class MainWindow extends JFrame {
 	class CellComp extends JLabel implements MouseListener {
		private static final long serialVersionUID = 1L;
		private int x, y;
		private boolean active = false;

		public CellComp(int x, int y, boolean active) {
			super();
			this.x = x;
			this.y = y;
			this.active = active;
			setOpaque(true);

			checkColor();

			addMouseListener(this);
			setPreferredSize(new Dimension(20, 20));
		}

		public void changeState() {
			this.active = !active;
			checkColor();
		}

		private void checkColor() {
			if (!active)
				setBackground(Color.white);
			else
				setBackground(Color.black);
		}

		public boolean getState() {
			return active;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			active = !active;
			checkColor();
			CellChanged(x, y);
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	}

	private static final long serialVersionUID = 1L;
	private int xS, yS;
	private Board board;

	private CellComp cells[][];

	public MainWindow(Board board) {
		super("Conway's Game of Life");
		xS = board.getBoardX();
		yS = board.getBoardY();
		this.board = board;
		initComp();
		initProps();
	};

	private void CellChanged(int x, int y) {
		System.out.println("User changed " + x + ";" + y + " state");
		board.switchCellState(x, y);
	};

	private void initComp() {

		setLayout(new BorderLayout());
		JPanel boardPan = new JPanel();
		JPanel controlPan = new JPanel();

		// boardPan
		GridLayout gl = new GridLayout(xS, yS,3,3);
		boardPan.setLayout(gl);

		cells = new CellComp[xS][yS];

		for (int x = 0; x < xS; x++) {
			for (int y = 0; y < yS; y++) {
				cells[x][y] = new CellComp(x, y, board.getCell(x, y));
				boardPan.add("cell " + x + ";" + y, cells[x][y]);
			}
		}
		// controlPan
		JButton nextStep = new JButton("Step >");
		nextStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.nextGen();
				refreshGUI();
			}
		});
		JButton randomize = new JButton("Randomize");
		randomize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				board.randomizeBoard(r.nextLong());
				refreshGUI();
			}
		});

		controlPan.add(nextStep);
		controlPan.add(randomize);

		// Frame
		add(controlPan, BorderLayout.SOUTH);
		add(boardPan, BorderLayout.CENTER);
	}

	private void initProps() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void refreshGUI() {
		for (int x = 0; x < xS; ++x)
			for (int y = 0; y < yS; ++y)
				if (board.getCell(x, y) != cells[x][y].getState())
					cells[x][y].changeState();

	}

}
