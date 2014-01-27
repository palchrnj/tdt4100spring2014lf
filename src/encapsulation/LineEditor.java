package encapsulation;

public class LineEditor {

	private String text;
	private int insertionIndex;
	
	public LineEditor() {
		text = "";
		insertionIndex = 0;
	}
	
	public void setText(String text) {
		this.text = text;
		if (insertionIndex > text.length()) insertionIndex = text.length();
	}
	
	public String getText() {
		return text;
	}
	
	public void setInsertionIndex(int insertionIndex) {
		if (insertionIndex < 0) throw new IllegalArgumentException();
		if (insertionIndex > text.length()) throw new IllegalStateException();
		this.insertionIndex = insertionIndex;
	}
	
	public int getInsertionIndex() {
		return insertionIndex;
	}
	
	public void left() {
		if (insertionIndex > 0) insertionIndex--;
	}
	
	public void right() {
		if (insertionIndex < text.length()) insertionIndex++;
	}
	
	public void insertString(String s) {
		text = text.substring(0, insertionIndex) + s + text.substring(insertionIndex);
		insertionIndex += s.length();
	}
	
	public void deleteLeft() {
		if (insertionIndex > 0) {
			text = text.substring(0, insertionIndex - 1) + text.substring(insertionIndex);
			insertionIndex--;
		}
	}
	
	public void deleteRight() {
		if (insertionIndex < text.length()) {
			text = text.substring(0, insertionIndex) + text.substring(insertionIndex + 1);
		}
	}
	
	public String toString() {
		return text.substring(0, insertionIndex) + '|' + text.substring(insertionIndex);
	}
}