package game.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import game.scenarios.Scenario;
import game.tiles.Block;
import game.tiles.Tile;

public class AStar {

	public static double lastTime = System.currentTimeMillis();

	private static Comparator<Node> nodeSorter = new Comparator<>() {
		@Override
		public int compare(Node arg0, Node arg1) {
			if (arg1.fCost < arg0.fCost) {
				return 1;
			}

			if (arg1.fCost > arg0.fCost) {
				return -1;
			}

			return 0;
		};
	};

	public static boolean clear() {
		return System.currentTimeMillis() - lastTime >= 1000;
	}

	private static double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.x - goal.x;
		double dy = tile.y - goal.y;

		return Math.sqrt(dx * dx + dy * dy);
	}

	private static boolean vectorInList(List<Node> list, Vector2i vector) {
		for (Node node : list) {
			if (node.tile.equals(vector)) {
				return true;
			}
		}

		return false;
	}

	public static List<Node> findPath(Scenario scenario, Vector2i start, Vector2i end) {
		lastTime = System.currentTimeMillis();

		List<Node> openList = new ArrayList<>();
		List<Node> closedList = new ArrayList<>();

		Node current = new Node(start, null, 0, AStar.getDistance(start, end));

		openList.add(current);

		while (!openList.isEmpty()) {
			Collections.sort(openList, nodeSorter);

			current = openList.get(0);

			if (current.tile.equals(end)) {
				List<Node> path = new ArrayList<>();

				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}

				openList.clear();
				closedList.clear();

				return path;
			}

			openList.remove(current);
			closedList.add(current);

			for (int i = 0; i < 9; i++) {
				if (i == 4) {
					continue;
				}

				int x = current.tile.x;
				int y = current.tile.y;

				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;

				Tile tile = Scenario.tiles[x + xi + ((y + yi) * Scenario.WIDTH)];

				if (tile == null || tile instanceof Block) {
					continue;
				} else if (i == 0) {
					Tile test = Scenario.tiles[x + xi + 1 + ((y + yi) * Scenario.WIDTH)];
					Tile test2 = Scenario.tiles[x + xi + 1 + ((y + yi) * Scenario.WIDTH)];

					if (test instanceof Block || test2 instanceof Block) {
						continue;
					}
				} else if (i == 2) {
					Tile test = Scenario.tiles[x + xi + 1 + ((y + yi) * Scenario.WIDTH)];
					Tile test2 = Scenario.tiles[x + xi + ((y + yi) * Scenario.WIDTH)];

					if (test instanceof Block || test2 instanceof Block) {
						continue;
					}
				} else if (i == 6) {
					Tile test = Scenario.tiles[x + xi + ((y + yi - 1) * Scenario.WIDTH)];
					Tile test2 = Scenario.tiles[x + xi + 1 + ((y + yi) * Scenario.WIDTH)];

					if (test instanceof Block || test2 instanceof Block) {
						continue;
					}
				} else if (i == 8) {
					Tile test = Scenario.tiles[x + xi + ((y + yi - 1) * Scenario.WIDTH)];
					Tile test2 = Scenario.tiles[x + xi - 1 + ((y + yi) * Scenario.WIDTH)];

					if (test instanceof Block || test2 instanceof Block) {
						continue;
					}
				}

				Vector2i a = new Vector2i(x + xi, y + yi);

				double gCost = current.gCost + AStar.getDistance(current.tile, a);
				double hCost = AStar.getDistance(a, end);

				Node node = new Node(a, current, gCost, hCost);

				if (AStar.vectorInList(closedList, a) && gCost >= current.gCost) {
					continue;
				}

				if (!AStar.vectorInList(openList, a)) {
					openList.add(node);
				} else if (gCost < current.gCost) {
					openList.remove(current);
					openList.add(node);
				}
			}
		}

		closedList.clear();

		return null;
	}

}
