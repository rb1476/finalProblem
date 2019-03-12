
public class Bar {
	public int height, startX, endX;
	
	public Bar(int height, int startX, int endX) {
		this.height = height;
		this.startX = startX;
		this.endX = endX;
	}
	
	@Override
	public int hashCode() {
		return ((Integer)height).hashCode() + ((Integer)startX).hashCode()
				+ ((Integer)endX).hashCode();
	}
}