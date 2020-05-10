package Model;

public class StatementLine {

	private int lineNumber;
	private int complexity;
	
public StatementLine(int lineNumber, int complexity) {
		super();
		this.lineNumber = lineNumber;
		this.complexity = complexity;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public int getComplexity() {
		return complexity;
	}
	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}
	
	
	
}
