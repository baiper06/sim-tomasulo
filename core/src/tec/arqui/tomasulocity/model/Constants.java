package tec.arqui.tomasulocity.model;

public class Constants {
	public static int SIZE_REGISTERS 		= 16;
	public static int SIZE_PROGRAM			= 12;
	public static int SIZE_PAGE 			= 3;
	public static int SIZE_TEMP_REGISTERS 	= 8;
	
	public static enum Operations { ADD, MOVE, MULT, DIV, SHIFT_L, SHIFT_R };
}
