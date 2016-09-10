package net.azulite.jasmine;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class JasmineObject extends JasmineData
{
	private HashMap<String,JasmineData> object;

	public JasmineObject()
	{
		this.setType( Jasmine.Type.OBJECT );
		object = new HashMap<String,JasmineData>();
	}

	/**
	 * @return Always return false.
	 */
	public boolean isPremitive() { return false; }

	/**
	 * @return Retrun element count.
	 */
	public double getNumber() { return object.size(); }

	/**
	 * @return Always return true.
	 */
	public boolean getBoolean() { return true; }

	/**
	 * Return JSON string.(No-space)
	 * @return {"K0":V0String,...}
	 */
	public String getString()
	{
		StringWriter w = new StringWriter();
		try
		{
			writeData( w );
		} catch ( IOException e ) { return ""; }
		return w.toString();
	}

	/**
	 * Return JSON string.(Human-readable)
	 * @param deep Indent count.
	 * @param spacer Indent spacer.
	 * @return Return JSON data string.
	 */
	protected String getString( int deep, String spacer )
	{
		StringWriter w = new StringWriter();
		try
		{
			writeData( w, deep );
		} catch ( IOException e ) { return ""; }
		return w.toString();
	}

	/**
	 * @return Return JasmineObject.(this)
	 */
	public JasmineObject getObject() { return this; }

	/**
	 * @return Return value.
	 */
	public JasmineData get( String key ) { return Jasmine.nullCheck( object.get( key ) ); }

	/**
	 * Add null Object(JasmineData) to the object.
	 * @param key Key name.
	 */
	public void set( String key ) { object.put( key, new JasmineData() ); }

	/**
	 * Add JasmineData to the object.
	 * @param key Key name.
	 * @param value JasmineData, JasmineArray, JasmineObject, ...etc.
	 */
	public void set( String key, JasmineData value ) { object.put( key, value == null ? new JasmineData() : value ); }

	/**
	 * Add boolean to the object.
	 * @param key Key name.
	 * @param value true or false. Add value after convert JasmineBoolean.
	 */
	public void set( String key, boolean value ) { this.set( key, new JasmineBoolean( value ) ); }

	/**
	 * Add Number to the object.
	 * @param key Key name.
	 * @param value Number. Add value after convert JasmineNumber.
	 */
	public void set( String key, double value ) { this.set( key, new JasmineNumber( value ) ); }

	/**
	 * Add String to the object.
	 * @param key Key name.
	 * @param value String. Add value after convert JasmineString.
	 */
	public void set( String key, String value ) { this.set( key, new JasmineString( value ) ); }

	/**
	 * Remove data.
	 * @param index Index of the array to which you want to remove.
	 */
	public void remove( String key ) { object.remove( key ); }

	/**
	 * Remove all data.
	 */
	public void removeAll() { object.clear(); }

	/**
	 * Write JSON string.(No-space)
	 */
	public void writeData( Writer w ) throws IOException
	{
		boolean e = false;

		w.write( "{" );
		for ( Map.Entry<String, JasmineData> d : object.entrySet() )
		{
			if ( e ) { w.write( "," ); } else { e = true; }
			w.write( "\"" + d.getKey() + "\":" );
			d.getValue().writeData( w );
		}
		w.write( "}" );
	}

	/**
	 * Return JSON string.(Human-readable)
	 * @param deep Indent count.
	 * @param spacer Indent spacer.
	 */
	public void writeData( Writer w, int deep, String spacer ) throws IOException
	{
		int ndeep = deep + 1;
		String indent = "";
		while( 0 < deep-- ) { indent += spacer; }
		boolean e = false;

		w.write( indent + "{\n" );
		for ( Map.Entry<String, JasmineData> d : object.entrySet() )
		{
			if ( e ) { w.write( ",\n" ); } else { e = true; }
			if ( d.getValue().isPremitive() )
			{
				w.write( indent + spacer + "\"" + d.getKey() + "\": " );
				d.getValue().writeData( w );
			} else
			{
				w.write( indent + spacer + "\"" + d.getKey() + "\":\n" );
				d.getValue().writeData( w, ndeep, spacer );
			}
		}
		w.write( "\n" + indent + "}" );
	}
}
