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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

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
			setPreferredSize(new Dimension(5, 5));
		}

		public void changeState() {
			this.active = !active;
			checkColor();
		}

		private void checkColor() {
			if (!active) {
				setBackground(Color.white);
				setForeground(Color.black);
			} else {
				setBackground(Color.black);
				setForeground(Color.white);
			}
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
			if (SwingUtilities.isLeftMouseButton(e)) {
				active = !active;
				checkColor();
				CellChanged(x, y);
			} else if (SwingUtilities.isMiddleMouseButton(e)) {
				setCurCellInfos("Cell " + x + ";" + y + " - alive : " + active);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	}

	private static final long serialVersionUID = 1L;
	private int xS, yS;
	private Board board;

	private JLabel currCellInfos;
	private JSpinner spinner;
	private CellComp cells[][];

	public MainWindow() {
		super("Conway's Game of Life");
		this.board = new Board(40, 40);
		xS = board.getBoardX();
		yS = board.getBoardY();
		initComp();
		initProps();
	};

	private void CellChanged(int x, int y) {
		System.out.println("User changed " + x + ";" + y + " state");
		board.switchCellState(x, y);
	};

	private void initComp() {

		setLayout(new BorderLayout());
		JPanel infosPan = new JPanel();
		JPanel boardPan = new JPanel();
		JPanel controlPan = new JPanel();

		// infosPan
		currCellInfos = new JLabel("x;y - state");
		infosPan.add(currCellInfos);

		// boardPan
		GridLayout gl = new GridLayout(xS, yS, 3, 3);
		boardPan.setLayout(gl);

		cells = new CellComp[xS][yS];

		for (int x = 0; x < xS; x++) {
			for (int y = 0; y < yS; y++) {
				cells[x][y] = new CellComp(x, y, board.getCell(x, y));
				boardPan.add("cell " + x + ";" + y, cells[x][y]);
			}
		}
		// controlPan
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(10, 1, Integer.MAX_VALUE, 1));

		JButton nextStep = new JButton("Next Step");
		nextStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.nextGen();
				refreshGUI();
			}
		});

		JButton nextXStep = new JButton("Next x Step");
		nextXStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Number max = ((SpinnerNumberModel) spinner.getModel()).getNumber();
				int max_int = max.intValue();
				for (int i = 0; i < max_int; ++i)
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

		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.clear();
				refreshGUI();
			}
		});

		controlPan.add(nextStep);
		controlPan.add(spinner);
		controlPan.add(nextXStep);
		controlPan.add(randomize);
		controlPan.add(clear);

		// Frame
		add(infosPan, BorderLayout.NORTH);
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

	private void setCurCellInfos(String text) {
		currCellInfos.setText(text);
	}

}
