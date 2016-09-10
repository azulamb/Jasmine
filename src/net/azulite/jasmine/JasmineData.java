package net.azulite.jasmine;

/**
 * JasmineData is null in JSON type.
 * @author Hiroki
 */

import java.io.IOException;
import java.io.Writer;

public class JasmineData
{
	private Jasmine.Type type;

	public JasmineData() { this.setType( Jasmine.Type.NULL ); }

	protected void setType( Jasmine.Type type ) { this.type = type; }

	/**
	 * Return JSON string.(Human-readable)
	 * @return Return JSON data string.
	 */
	public String toString() { return this.getString( 0 ); }

	/**
	 * @return Always return Jasmine.Type.NULL if JasmineData. Return this type.(Jasmine.Type)
	 */
	public Jasmine.Type getType() { return type; }

	/**
	 * @return Always return true if JasmineData. Return true if type is null, boolean, number or string.
	 */
	public boolean isPremitive() { return true; }

	/**
	 * @return Always return 0 if JasmineData.
	 */
	public double getNumber() { return 0; }

	/**
	 * @return Always return 0 if JasmineData.
	 */
	public int getInteger(){ return (int)this.getNumber(); }

	/**
	 * @return Always return false if JasmineData.
	 */
	public boolean getBoolean() { return false; }

	/**
	 * Return JSON string.(No-space)
	 * @return Always return "null" if JasmineData. Return JSON data string if other type.
	 */
	public String getString() { return "null"; }

	/**
	 * Return JSON string.(Human-readable)
	 * @param deep Indent count.
	 * @return Return JSON data string.
	 */
	protected String getString( int deep ) { return this.getString( deep, "	" ); }

	/**
	 * Return JSON string.(Human-readable)
	 * @param deep Indent count.
	 * @param spacer Indent spacer.
	 * @return Return JSON data string.
	 */
	protected String getString( int deep, String spacer )
	{
		String indent = "";
		while( 0 < deep-- ) { indent += spacer; }
		return indent + this.getString();
	}

	/**
	 * @return Return empty array(getType return Jasmine.Type.NULL, and other method return empty) if other JasmineArray.
	 */
	public JasmineArray getArray() { return Jasmine.EMPTY_ARRAY; }

	/**
	 * @return Return empty object(getType return Jasmine.Type.NULL, and other method return empty) if other JasmineObject.
	 */
	public JasmineObject getObject() { return Jasmine.EMPTY_OBJECT; }

	/**
	 * Write JSON string.(No-space)
	 * @param w Writer.
	 * @throws IOException error.
	 */
	public void writeData( Writer w ) throws IOException { w.write( this.getString() ); }

	/**
	 * Write JSON string.(Human-readable)
	 * @param w Writer.
	 * @param deep Indent count.
	 * @throws IOException error.
	 */
	public void writeData( Writer w, int deep ) throws IOException { this.writeData( w, deep, "	" ); }

	/**
	 * Return JSON string.(Human-readable)
	 * @param w Writer.
	 * @param deep Indent count.
	 * @param spacer Indent spacer.
	 * @throws IOException error.
	 */
	public void writeData( Writer w, int deep, String spacer ) throws IOException { w.write( this.getString( deep, spacer ) ); }
}
