package encapsulation;

import no.hal.jex.runtime.JExercise;
import no.hal.jex.standalone.JexStandalone;
import junit.framework.TestCase;

@JExercise(
	description="The LineEditor class must contain two fields, text and insertionIndex of types String and int, respectively."
)
public class LineEditorTest extends TestCase {

	LineEditor lineEditor;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		lineEditor = new LineEditor();
		setLineEditor("",  0);
	}

	private void setLineEditor(String text, int index) {
		lineEditor.setText(text);
		lineEditor.setInsertionIndex(index);
	}
	
	private void testLineEditor(String text, int index) {
		assertEquals(text, lineEditor.getText());
		assertEquals(index, lineEditor.getInsertionIndex());
	}
	
	@JExercise(
			tests="void setText(String);String getText()",
			description="The setText(String) method takes a String object as its argument and sets this to the text field. The getText() method returns this field."
	)
	public void testGetSetText() {
		lineEditor.setText("ABC");
		assertEquals("ABC", lineEditor.getText());
		lineEditor.setText("");
		assertEquals("", lineEditor.getText());
	}
	
	@JExercise(
			tests="void setInsertionIndex(int);int getInsertionIndex()",
			description="The setInsertionIndex(int) method takes an index as its argument and sets this to the text field. The getInsertionIndex() method returns this field."
	)
	public void testGetSetInsertionIndex() {
		lineEditor.setInsertionIndex(0);
		assertEquals(0, lineEditor.getInsertionIndex());
		lineEditor.setText("ABC");
		lineEditor.setInsertionIndex(3);
		assertEquals(3, lineEditor.getInsertionIndex());
		try {
			lineEditor.setInsertionIndex(-1);
			fail();
		} catch (Exception e) {
			assertEquals(3, lineEditor.getInsertionIndex());
			assertTrue (e instanceof IllegalArgumentException);
		}
		try {
			lineEditor.setInsertionIndex(4);
			fail();
		} catch (Exception e) {
			assertEquals(3, lineEditor.getInsertionIndex());
			assertTrue (e instanceof IllegalStateException);
		}
	}
	
	@JExercise(
			tests="void setText(String)",
			description="The insertion index must be moved to a valid place when the text field is set to a shorter String than the current insertion index."
	)
	public void testSetTextShorterThanInsertionIndex() {
		lineEditor.setText("ABC");
		assertEquals("ABC", lineEditor.getText());
		lineEditor.setInsertionIndex(3);
		assertEquals(3, lineEditor.getInsertionIndex());
		lineEditor.setText("AB");
		assertEquals("AB", lineEditor.getText());
		assertTrue(lineEditor.getInsertionIndex() >= 0 && lineEditor.getInsertionIndex() <= 2);
	}
	
	@JExercise(
		tests="void left()",
		description="The left() method moves the insertion index to the left (if possible)."
	)
	public void testLeft() {
		testLineEditor("",  0);
		lineEditor.left();
		testLineEditor("",  0);

		setLineEditor("J", 1);
		lineEditor.left();
		testLineEditor("J", 0);
	}
	
	@JExercise(
		tests="void right()",
		description="The right() method moves the insertion index to the right (if possible)."
	)
	public void testRight() {
		testLineEditor("",  0);
		lineEditor.right();
		testLineEditor("",  0);
		
		setLineEditor("J", 0);
		lineEditor.right();
		testLineEditor("J", 1);
	}

	@JExercise(
		tests="void deleteLeft()",
		description="The deleteLeft() method deletes the character to the left (if possible) and moves the insertion index correspondingly."
	)
	public void testDeleteLeft() {
		testLineEditor("",  0);
		lineEditor.deleteLeft();
		testLineEditor("",  0);
		
		setLineEditor("J", 1);
		lineEditor.deleteLeft();
		testLineEditor("", 0);
		
		setLineEditor("Ja", 1);
		lineEditor.deleteLeft();
		testLineEditor("a", 0);
	}
	
	@JExercise(
		tests="void deleteRight()",
		description="The deleteRight() method deletes the character to the right (if possible)."
	)
	public void testDeleteRight() {
		testLineEditor("",  0);
		lineEditor.deleteRight();
		testLineEditor("",  0);
		
		setLineEditor("J", 0);
		lineEditor.deleteRight();
		testLineEditor("", 0);
		
		setLineEditor("Ja", 1);
		lineEditor.deleteRight();
		testLineEditor("J", 1);
	}
	
	@JExercise(
		tests="void insertString(String)",
		description="The insertString(String) method inserts its argument at the current insertion index and moves the insertion index correspondingly."
	)
	public void testInsertString() {
		testLineEditor("",  0);
		lineEditor.insertString("");
		testLineEditor("",  0);
		lineEditor.insertString("Java");
		testLineEditor("Java",  4);
		lineEditor.insertString(" er g?y!");
		testLineEditor("Java er g?y!",  12);

		setLineEditor("Javag?y!", 4);
		lineEditor.insertString(" er ");
		testLineEditor("Java er g?y!",  8);
		
		setLineEditor("er g?y!", 0);
		lineEditor.insertString("Java ");
		testLineEditor("Java er g?y!",  5);
	}

	//

	public static void main(String[] args) {
		JexStandalone.main(LineEditorTest.class);
	}
}