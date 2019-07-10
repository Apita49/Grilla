package logic;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JButton;

import uinterface.UserInterface;

public class Logic {

	public void drawLine(int x0, int y0, int x1, int y1, JButton[][] grid,
			Color color) {
		int deltaX = x1 - x0;
		int deltaY = y1 - y0;
		int pasos = Math.abs(deltaX) > Math.abs(deltaY) ? Math.abs(deltaX)
				: Math.abs(deltaY);
		double xIncremento = (double) deltaX / (double) pasos;
		double yIncremento = (double) deltaY / (double) pasos;
		double x = x0;
		double y = y0;
		grid[y0][x0].setBackground(color);
		grid[y1][x1].setBackground(color);
		for (int i = 1; i < pasos; i++) {
			x += xIncremento;
			y += yIncremento;
			grid[(int) y][(int) x].setBackground(color);
		}
	}

	public void bressenhamCorectInput(int x0, int y0, int x1, int y1,
			JButton[][] grid, Color color) {
		double m = (double) (y1 - y0) / (double) (x1 - x0);
		if (m >= 0) {
			if (m <= 1) {
				bressenhaimLogic(x0, y0, x1, y1, grid, 0, color);
			} else {
				bressenhaimLogic(y0, x0, y1, x1, grid, 1, color);
			}
		} else {
			if (Math.abs(m) > 1) {
				bressenhaimLogic(y0, -x0, y1, -x1, grid, 2, color);
			} else {
				bressenhaimLogic(-x0, y0, -x1, y1, grid, 3, color);
			}
		}
	}

	private void bressenhaimLogic(int x0, int y0, int x1, int y1,
			JButton[][] grid, int option, Color color) {
		int deltaX = Math.abs(x1 - x0);
		int deltaY = Math.abs(y1 - y0);
		int p = 2 * deltaY - deltaX;
		int twoDeltaY = 2 * deltaY;
		int twoDeltaYMinusDeltaX = 2 * (deltaY - deltaX);
		int x, y, xEnd;
		if (x0 > x1) {
			x = x1;
			y = y1;
			xEnd = x0;
		} else {
			x = x0;
			y = y0;
			xEnd = x1;
		}
		setPixel(x, y, grid, option, color);
		while (x < xEnd) {
			x++;
			if (p < 0) {
				p += twoDeltaY;
			} else {
				y++;
				p += twoDeltaYMinusDeltaX;
			}
			setPixel(x, y, grid, option, color);
		}
	}

	private void setPixel(int x, int y, JButton[][] grid, int option,
			Color color) {
		switch (option) {
		case 0:
			grid[y][x].setBackground(color);
			break;
		case 1:
			grid[x][y].setBackground(color);
			break;
		case 2:
			grid[x][-y].setBackground(color);
			break;
		case 3:
			grid[y][-x].setBackground(color);
			break;
		default:
			break;
		}

	}

	public void circle(int centerX, int centerY, int farX, int farY,
			JButton[][] grid, Color color) {
		double radius = Math.pow(
				Math.pow(centerX - farX, 2) + Math.pow(centerY - farY, 2), 0.5);
		double y = radius;
		double p = (5 / 4) - radius;
		int x = 0;
		circleDraw(x, (int) y, centerX, centerY, grid, color);
		while (x < y) {
			x++;
			if (p < 0) {
				p += 2 * x + 1;
			} else {
				y--;
				p += 2 * x + 1 - 2 * y;
			}
			circleDraw(x, (int) y, centerX, centerY, grid, color);
		}

	}

	private void circleDraw(int x, int y, int centerX, int centerY,
			JButton[][] grid, Color color) {
		setPixel(x + centerX, y + centerY, grid, color);
		setPixel(y + centerX, x + centerY, grid, color);
		setPixel(-y + centerX, x + centerY, grid, color);
		setPixel(-x + centerX, y + centerY, grid, color);
		setPixel(-x + centerX, -y + centerY, grid, color);
		setPixel(-y + centerX, -x + centerY, grid, color);
		setPixel(y + centerX, -x + centerY, grid, color);
		setPixel(x + centerX, -y + centerY, grid, color);
	}

	public void elipse(int centralX, int centralY, int distancexX,
			int distancexY, int distanceyX, int distanceyY, JButton[][] grid,
			Color color) {
		double radiusX = Math.pow(
				Math.pow(centralX - distancexX, 2)
						+ Math.pow(centralY - distancexY, 2), 0.5);
		double radiusY = Math.pow(
				Math.pow(centralX - distanceyX, 2)
						+ Math.pow(centralY - distanceyY, 2), 0.5);
		double ry2 = radiusY * radiusY;
		double rx2 = radiusX * radiusX;
		double twoRY2 = 2 * ry2;
		double twoRX2 = 2 * rx2;
		int x = 0;
		double y = radiusY;
		int px = 0;
		double py = twoRX2 * y;
		elipseDraw(x, (int) y, centralX, centralY, grid, color);
		double p = ry2 - (rx2 * radiusY) + (0.25 * rx2);
		while (px < py) {
			x++;
			px += twoRY2;
			if (p < 0) {
				p += ry2 + px;
			} else {
				y--;
				py -= twoRX2;
				p += ry2 + px - py;
			}
			elipseDraw(x, (int) y, centralX, centralY, grid, color);
		}
		p = ry2 * (x + 0.5) * (x + 0.5) + rx2 * (y - 1) * (y - 1) - rx2 * ry2;
		while (y > 0) {
			y--;
			py -= twoRX2;
			if (p > 0) {
				p += rx2 - py;
			} else {
				x++;
				px += twoRY2;
				p += rx2 - py + px;
			}
			elipseDraw(x, (int) y, centralX, centralY, grid, color);
		}
	}

	private void elipseDraw(int x, int y, int centralX, int centralY,
			JButton[][] grid, Color color) {
		setPixel(x + centralX, y + centralY, grid, color);
		setPixel(x + centralX, -y + centralY, grid, color);
		setPixel(-x + centralX, -y + centralY, grid, color);
		setPixel(-x + centralX, y + centralY, grid, color);
	}

	private void setPixel(int x, int y, JButton[][] grid, Color color) {
		if (x >= 0 && x < UserInterface.NUM_ROWS_AND_COLS && y >= 0
				&& y < UserInterface.NUM_ROWS_AND_COLS) {
			grid[y][x].setBackground(color);
		}
	}

	public void FloodFill(int x, int y, Color fill, JButton[][] grid,
			Color actual) {
		Queue<LinkedList<Integer>> cola = new LinkedList<>();
		LinkedList<Integer> list = new LinkedList<>();
		list.add(x);
		list.add(y);
		cola.add(list);
		while (!cola.isEmpty()) {
			LinkedList<Integer> n = cola.poll();
			if (grid[n.get(1)][n.get(0)].getBackground() == actual) {
				grid[n.get(1)][n.get(0)].setBackground(fill);
				if (n.get(0) > 0
						&& grid[n.get(1)][n.get(0) - 1].getBackground() == actual) {
					LinkedList<Integer> toAdd = new LinkedList<>();
					toAdd.add(n.get(0) - 1);
					toAdd.add(n.get(1));
					cola.add(toAdd);
				}
				if (n.get(1) > 0
						&& grid[n.get(1) - 1][n.get(0)].getBackground() == actual) {
					LinkedList<Integer> toAdd = new LinkedList<>();
					toAdd.add(n.get(0));
					toAdd.add(n.get(1) - 1);
					cola.add(toAdd);
				}
				if (n.get(0) < UserInterface.NUM_ROWS_AND_COLS - 1
						&& grid[n.get(1)][n.get(0) + 1].getBackground() == actual) {
					LinkedList<Integer> toAdd = new LinkedList<>();
					toAdd.add(n.get(0) + 1);
					toAdd.add(n.get(1));
					cola.add(toAdd);
				}
				if (n.get(1) < UserInterface.NUM_ROWS_AND_COLS - 1
						&& grid[n.get(1) + 1][n.get(0)].getBackground() == actual) {
					LinkedList<Integer> toAdd = new LinkedList<>();
					toAdd.add(n.get(0));
					toAdd.add(n.get(1) + 1);
					cola.add(toAdd);
				}
			}
		}
	}

	public Color getColor(String name) {
		Color ans = null;
		switch (name) {
		case "Blanco":
			ans = Color.WHITE;
			break;
		case "Negro":
			ans = Color.BLACK;
			break;
		case "Azul":
			ans = Color.BLUE;
			break;
		case "Rojo":
			ans = Color.RED;
			break;
		case "Verde":
			ans = Color.GREEN;
			break;
		case "Amarillo":
			ans = Color.YELLOW;
			break;
		case "Naranja":
			ans = Color.ORANGE;
			break;
		case "Morado":
			ans = Color.MAGENTA;
			break;
		case "Rosado":
			ans = Color.PINK;
			break;
		case "Celeste":
			ans = Color.CYAN;
			break;
		case "Plomo":
			ans = Color.GRAY;
			break;
		}

		return ans;
	}

	public void drawFromList(List<List<Integer>> list, int distanceX,
			int distanceY, JButton[][] grid, Color color) {
		for (int i = 0; i < list.size(); i++) {
			setPixel(list.get(i).get(0) + distanceX, list.get(i).get(1)
					+ distanceY, grid, color);
		}

	}

	public List<List<Integer>> floodFillSeleccion(int firstX, int firstY,
			JButton[][] grid) {
		List<List<Integer>> ans = new LinkedList<>();
		Color seleccion = grid[firstY][firstX].getBackground();
		Queue<List<Integer>> cola = new LinkedList<>();
		List<Integer> iterable = new LinkedList<>();
		boolean[][] visited = new boolean[grid.length][grid.length];
		iterable.add(firstX);
		iterable.add(firstY);
		cola.add(iterable);
		while (!cola.isEmpty()) {
			List<Integer> actual = cola.poll();
			if ((!visited[actual.get(1)][actual.get(0)])
					&& grid[actual.get(1)][actual.get(0)].getBackground() == seleccion) {
				ans.add(actual);
				visited[actual.get(1)][actual.get(0)] = true;
				if (actual.get(1) > 0) {
					if (grid[actual.get(1) - 1][actual.get(0)].getBackground() == seleccion
							&& !visited[actual.get(1) - 1][actual.get(0)]) {
						iterable = new LinkedList<>();
						iterable.add(actual.get(0));
						iterable.add(actual.get(1) - 1);
						cola.add(iterable);
					}
					if (actual.get(0) > 0
							&& grid[actual.get(1) - 1][actual.get(0) - 1]
									.getBackground() == seleccion
							&& !visited[actual.get(1) - 1][actual.get(0) - 1]) {
						iterable = new LinkedList<>();
						iterable.add(actual.get(0) - 1);
						iterable.add(actual.get(1) - 1);
						cola.add(iterable);
					}
					if (actual.get(0) < UserInterface.NUM_ROWS_AND_COLS - 1
							&& grid[actual.get(1) - 1][actual.get(0) + 1]
									.getBackground() == seleccion
							&& !visited[actual.get(1) - 1][actual.get(0) + 1]) {
						iterable = new LinkedList<>();
						iterable.add(actual.get(0) + 1);
						iterable.add(actual.get(1) - 1);
						cola.add(iterable);
					}
				}
				if (actual.get(1) < UserInterface.NUM_ROWS_AND_COLS - 1) {
					if (grid[actual.get(1) + 1][actual.get(0)].getBackground() == seleccion
							&& !visited[actual.get(1) + 1][actual.get(0)]) {
						iterable = new LinkedList<>();
						iterable.add(actual.get(0));
						iterable.add(actual.get(1) + 1);
						cola.add(iterable);
					}
					if (actual.get(0) > 0
							&& grid[actual.get(1) + 1][actual.get(0) - 1]
									.getBackground() == seleccion
							&& !visited[actual.get(1) + 1][actual.get(0) - 1]) {
						iterable = new LinkedList<>();
						iterable.add(actual.get(0) - 1);
						iterable.add(actual.get(1) + 1);
						cola.add(iterable);
					}
					if (actual.get(0) < UserInterface.NUM_ROWS_AND_COLS - 1
							&& grid[actual.get(1) + 1][actual.get(0) + 1]
									.getBackground() == seleccion
							&& !visited[actual.get(1) + 1][actual.get(0) + 1]) {
						iterable = new LinkedList<>();
						iterable.add(actual.get(0) + 1);
						iterable.add(actual.get(1) + 1);
						cola.add(iterable);
					}
				}
				if (actual.get(0) > 0
						&& grid[actual.get(1)][actual.get(0) - 1]
								.getBackground() == seleccion
						&& !visited[actual.get(1)][actual.get(0) - 1]) {
					iterable = new LinkedList<>();
					iterable.add(actual.get(0) - 1);
					iterable.add(actual.get(1));
					cola.add(iterable);
				}
				if (actual.get(0) < UserInterface.NUM_ROWS_AND_COLS - 1
						&& grid[actual.get(1)][actual.get(0) + 1]
								.getBackground() == seleccion
						&& !visited[actual.get(1)][actual.get(0) + 1]) {
					iterable = new LinkedList<>();
					iterable.add(actual.get(0) + 1);
					iterable.add(actual.get(1));
					cola.add(iterable);
				}
			}
		}
		return ans;
	}
}
