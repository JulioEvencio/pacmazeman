package game.astar;

public class Vector2i {

	public int x;
	public int y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Vector2i vector2i) {
		return vector2i.x == this.x && vector2i.y == this.y;
	}

}
