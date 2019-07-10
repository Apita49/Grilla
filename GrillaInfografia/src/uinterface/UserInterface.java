package uinterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import logic.Logic;

public class UserInterface {
	private static Logic logic;
	public static final int NUM_ROWS_AND_COLS = 125;
	private int frameWidth = 600;
	private int frameHeigth = (549 * frameWidth) / 600;
	private JButton[][] grid;
	private JFrame frame;
	private JPanel rigthPanel;
	private JPanel leftPanel;
	private JButton btnDdaLine;
	private int firstX = -1;
	private int firstY;
	private int secondX;
	private int secondY;
	private int thirdX;
	private int thirdY;
	private JButton btnBressenhamLine;
	private JButton btnCircle;
	private JButton btnElipse;
	private JButton btnRelleno;
	private JComboBox<String> comboBox;
	private JButton btnTrasladar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		logic = new Logic();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		asignacionAlTerminar();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Grilla");
		frame.setBounds(0, 0, frameWidth, frameHeigth);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, frameWidth - 18, frameHeigth - 47);
		splitPane.setDividerLocation(71 * (frameWidth / 600));

		frame.getContentPane().add(splitPane);

		rigthPanel = new JPanel();
		splitPane.setRightComponent(rigthPanel);
		rigthPanel.setLayout(new GridLayout(NUM_ROWS_AND_COLS,
				NUM_ROWS_AND_COLS, 0, 0));

		leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		initializeGrid();

		btnDdaLine = new JButton("Line");
		btnDdaLine.setBackground(Color.WHITE);
		btnDdaLine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnDdaLine.getBackground() != Color.WHITE) {
					asignacionAlTerminar();
				}
				btnDdaLine
						.setBackground(btnDdaLine.getBackground() != Color.WHITE ? Color.WHITE
								: Color.CYAN);
				btnBressenhamLine.setBackground(Color.WHITE);
				btnCircle.setBackground(Color.WHITE);
				btnElipse.setBackground(Color.WHITE);
				btnRelleno.setBackground(Color.WHITE);
				btnTrasladar.setBackground(Color.WHITE);
			}
		});
		leftPanel.add(btnDdaLine);

		btnBressenhamLine = new JButton("Bressen");
		btnBressenhamLine.setBackground(Color.WHITE);
		btnBressenhamLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnBressenhamLine.getBackground() != Color.WHITE) {
					asignacionAlTerminar();
				}
				btnBressenhamLine.setBackground(btnBressenhamLine
						.getBackground() != Color.WHITE ? Color.WHITE
						: Color.CYAN);
				btnDdaLine.setBackground(Color.WHITE);
				btnCircle.setBackground(Color.WHITE);
				btnElipse.setBackground(Color.WHITE);
				btnRelleno.setBackground(Color.WHITE);
				btnTrasladar.setBackground(Color.WHITE);
			}
		});
		leftPanel.add(btnBressenhamLine);

		btnCircle = new JButton("Circle");
		btnCircle.setBackground(Color.WHITE);
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnCircle.getBackground() != Color.WHITE) {
					asignacionAlTerminar();
				}
				btnCircle
						.setBackground(btnCircle.getBackground() != Color.WHITE ? Color.WHITE
								: Color.CYAN);
				btnBressenhamLine.setBackground(Color.WHITE);
				btnDdaLine.setBackground(Color.WHITE);
				btnElipse.setBackground(Color.WHITE);
				btnRelleno.setBackground(Color.WHITE);
				btnTrasladar.setBackground(Color.WHITE);
			}
		});
		leftPanel.add(btnCircle);

		btnElipse = new JButton("Elipse");
		btnElipse.setBackground(Color.WHITE);
		btnElipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnElipse.getBackground() != Color.WHITE) {
					asignacionAlTerminar();
				}
				btnElipse
						.setBackground(btnElipse.getBackground() == Color.WHITE ? Color.CYAN
								: Color.WHITE);
				btnBressenhamLine.setBackground(Color.WHITE);
				btnDdaLine.setBackground(Color.WHITE);
				btnCircle.setBackground(Color.WHITE);
				btnRelleno.setBackground(Color.WHITE);
				btnTrasladar.setBackground(Color.WHITE);

			}
		});
		leftPanel.add(btnElipse);

		btnRelleno = new JButton("Relleno");
		btnRelleno.setBackground(Color.WHITE);
		btnRelleno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRelleno
						.setBackground(btnRelleno.getBackground() != Color.WHITE ? Color.WHITE
								: Color.CYAN);
				btnBressenhamLine.setBackground(Color.WHITE);
				btnDdaLine.setBackground(Color.WHITE);
				btnCircle.setBackground(Color.WHITE);
				btnElipse.setBackground(Color.WHITE);
				btnTrasladar.setBackground(Color.WHITE);
			}
		});
		leftPanel.add(btnRelleno);

		comboBox = new JComboBox<>();
		comboBox.addItem("Blanco");
		comboBox.addItem("Negro");
		comboBox.addItem("Azul");
		comboBox.addItem("Rojo");
		comboBox.addItem("Verde");
		comboBox.addItem("Morado");
		comboBox.addItem("Amarillo");
		comboBox.addItem("Naranja");
		comboBox.addItem("Rosado");
		comboBox.addItem("Celeste");
		comboBox.addItem("Plomo");

		btnTrasladar = new JButton("Trasladar");
		btnTrasladar.setBackground(Color.WHITE);
		btnTrasladar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnTrasladar.getBackground() != Color.WHITE) {
					asignacionAlTerminar();
				}
				btnTrasladar
						.setBackground(btnTrasladar.getBackground() != Color.WHITE ? Color.WHITE
								: Color.CYAN);
				btnBressenhamLine.setBackground(Color.WHITE);
				btnDdaLine.setBackground(Color.WHITE);
				btnCircle.setBackground(Color.WHITE);
				btnElipse.setBackground(Color.WHITE);
				btnRelleno.setBackground(Color.WHITE);
			}
		});
		leftPanel.add(btnTrasladar);
		leftPanel.add(comboBox);

	}

	private void initializeGrid() {
		grid = new JButton[NUM_ROWS_AND_COLS][NUM_ROWS_AND_COLS];
		for (int i = 0; i < NUM_ROWS_AND_COLS; i++) {
			for (int j = 0; j < NUM_ROWS_AND_COLS; j++) {
				JButton button = new JButton();
				button.setBackground(Color.WHITE);
				button.setBounds(0, 0, rigthPanel.getWidth()
						/ NUM_ROWS_AND_COLS, rigthPanel.getHeight()
						/ NUM_ROWS_AND_COLS);

				button.setBounds(0, 0, 100, 100);
				button.setBorderPainted(!(NUM_ROWS_AND_COLS > 150));

				button.addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (btnDdaLine.getBackground() != Color.WHITE) {
							int x = arg0.getXOnScreen()
									- (rigthPanel.getLocationOnScreen().x);
							int y = arg0.getYOnScreen()
									- (rigthPanel.getLocationOnScreen().y);
							x = (x * NUM_ROWS_AND_COLS) / rigthPanel.getWidth();
							y = (y * NUM_ROWS_AND_COLS)
									/ rigthPanel.getHeight();
							if (firstX == -1) {
								firstX = x;
								firstY = y;
							} else {
								secondX = x;
								secondY = y;
								logic.drawLine(firstX, firstY, secondX,
										secondY, grid, logic
												.getColor((String) comboBox
														.getSelectedItem()));
								asignacionAlTerminar();
								btnDdaLine.setBackground(Color.WHITE);
							}
						} else if (btnBressenhamLine.getBackground() != Color.WHITE) {
							int x = arg0.getXOnScreen()
									- (rigthPanel.getLocationOnScreen().x);
							int y = arg0.getYOnScreen()
									- (rigthPanel.getLocationOnScreen().y);
							x = (x * NUM_ROWS_AND_COLS) / rigthPanel.getWidth();
							y = (y * NUM_ROWS_AND_COLS)
									/ rigthPanel.getHeight();
							if (firstX == -1) {
								firstX = x;
								firstY = y;
							} else {
								secondX = x;
								secondY = y;
								logic.bressenhamCorectInput(firstX, firstY,
										secondX, secondY, grid, logic
												.getColor((String) comboBox
														.getSelectedItem()));
								asignacionAlTerminar();
								btnBressenhamLine.setBackground(Color.WHITE);
							}
						} else if (btnCircle.getBackground() != Color.WHITE) {
							int x = arg0.getXOnScreen()
									- (rigthPanel.getLocationOnScreen().x);
							int y = arg0.getYOnScreen()
									- (rigthPanel.getLocationOnScreen().y);
							x = (x * NUM_ROWS_AND_COLS) / rigthPanel.getWidth();
							y = (y * NUM_ROWS_AND_COLS)
									/ rigthPanel.getHeight();
							if (firstX == -1) {
								firstX = x;
								firstY = y;
							} else {
								secondX = x;
								secondY = y;
								logic.circle(firstX, firstY, secondX, secondY,
										grid, logic.getColor((String) comboBox
												.getSelectedItem()));
								asignacionAlTerminar();
								btnCircle.setBackground(Color.WHITE);
							}
						} else if (btnElipse.getBackground() != Color.WHITE) {
							int x = arg0.getXOnScreen()
									- (rigthPanel.getLocationOnScreen().x);
							int y = arg0.getYOnScreen()
									- (rigthPanel.getLocationOnScreen().y);
							x = (x * NUM_ROWS_AND_COLS) / rigthPanel.getWidth();
							y = (y * NUM_ROWS_AND_COLS)
									/ rigthPanel.getHeight();
							if (firstX == -1) {
								firstX = x;
								firstY = y;
							} else if (secondX == -1) {
								secondX = x;
								secondY = y;
							} else {
								thirdX = x;
								thirdY = y;
								logic.elipse(firstX, firstY, secondX, secondY,
										thirdX, thirdY, grid, logic
												.getColor((String) comboBox
														.getSelectedItem()));
								asignacionAlTerminar();
								btnElipse.setBackground(Color.WHITE);
							}
						} else if (btnRelleno.getBackground() != Color.WHITE) {
							int x = arg0.getXOnScreen()
									- (rigthPanel.getLocationOnScreen().x);
							int y = arg0.getYOnScreen()
									- (rigthPanel.getLocationOnScreen().y);
							x = (x * NUM_ROWS_AND_COLS) / rigthPanel.getWidth();
							y = (y * NUM_ROWS_AND_COLS)
									/ rigthPanel.getHeight();
							logic.FloodFill(x, y, logic
									.getColor((String) comboBox
											.getSelectedItem()), grid,
									grid[y][x].getBackground());
							btnRelleno.setBackground(Color.WHITE);
						} else if (btnTrasladar.getBackground() != Color.WHITE) {
							int x = arg0.getXOnScreen()
									- (rigthPanel.getLocationOnScreen().x);
							int y = arg0.getYOnScreen()
									- (rigthPanel.getLocationOnScreen().y);
							x = (x * NUM_ROWS_AND_COLS) / rigthPanel.getWidth();
							y = (y * NUM_ROWS_AND_COLS)
									/ rigthPanel.getHeight();
							if (firstX == -1) {
								firstX = x;
								firstY = y;
							} else {
								secondX = x;
								secondY = y;
								Color colorAPintar = grid[firstY][firstX]
										.getBackground();
								List<List<Integer>> list;
								list = logic.floodFillSeleccion(firstX, firstY,
										grid);
								// El metodo flood fill Seleccion toma el primer
								// punto al que se hizo clic y selecciona todos
								// los puntos adyacentes tanto en diagonales que
								// tengan el mismo color que el primer pixel en
								// el que se hizo clic y se los guarda en una
								// lista
								logic.drawFromList(list, 0, 0, grid,
										Color.WHITE);
								// Se pinta toda esa seleccion con el color
								// blanco con draw from list
								int distanceX = secondX - firstX;
								int distanceY = secondY - firstY;
								logic.drawFromList(list, distanceX, distanceY,
										grid, colorAPintar);
								// Pinta la figura seleccionada comparando la
								// distancia en x y, tomando en cuenta el primer
								// pixel en el que se hizo clic y el segundo,
								// despues esta distancia se aplica a todos los
								// puntos de la lista con el color deseado
								asignacionAlTerminar();
								btnTrasladar.setBackground(Color.WHITE);
							}
						} else {
							button.setBackground(logic
									.getColor((String) comboBox
											.getSelectedItem()));
						}
					}
				});
				grid[i][j] = button;
				rigthPanel.add(button);
			}
		}
	}

	private void asignacionAlTerminar() {
		firstX = -1;
		firstY = -1;
		secondX = -1;
		secondY = -1;
		thirdX = -1;
		thirdY = -1;
	}
}
