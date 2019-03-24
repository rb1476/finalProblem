
public class Bar {
	private int height, startX, endX;
	
	public Bar(int height, int startX, int endX) {
		this.height = height;
		this.startX = startX;
		this.endX = endX;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public void setStartX(int startX) {
		this.startX = startX;
	}
	
	public int getEndX() {
		return endX;
	}
	
	public void setEndX(int endX) {
		this.endX = endX;
	}
	
	@Override
	public int hashCode() {
		return ((Integer)height).hashCode() + ((Integer)startX).hashCode()
				+ ((Integer)endX).hashCode();
	}
}