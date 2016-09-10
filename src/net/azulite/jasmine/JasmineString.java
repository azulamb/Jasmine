package net.azulite.jasmine;

import java.io.IOException;
import java.io.Writer;

public class JasmineString extends JasmineData
{
	private String str;

	public JasmineString() { this.init( "" ); }

	public JasmineString( String value ) { this.init( value ); }

	private void init( String value )
	{
		this.setType( Jasmine.Type.STRING );
		this.set( value );
	}

	/**
	 * @return Return string length.
	 */
	public double getNumber() { return str.length(); }

	/**
	 * @return Return true if value != "".
	 */
	public boolean getBoolean() { return !str.equals( "" ); }

	/**
	 * @return Return value.
	 */
	public String getString() { return str; }

	protected String escape( String str )
	{
		return str.replace( "\\", "\\\\" ).replace( "\n", "\\n" ).replace( "\"", "\\\"");
	}

	/**
	 * @param value Set value.
	 */
	public void set( String value ) { str = value; }

	/**
	 * Write JSON string.(No-space)
	 */
	public void writeData( Writer w ) throws IOException { w.write( "\"" + this.escape( this.getString() ) + "\"" ); }

	/**
	 * Write JSON string.(Human-readable)
	 * @param deep Indent count.
	 */
	public void writeData( Writer w, int deep ) throws IOException { this.writeData( w, deep, "	" ); }

	/**
	 * Return JSON string.(Human-readable)
	 * @param deep Indent count.
	 * @param spacer Indent spacer.
	 */
	public void writeData( Writer w, int deep, String spacer ) throws IOException { w.write( "\"" + this.escape( this.getString( deep, spacer ) ) + "\"" ); }
}
