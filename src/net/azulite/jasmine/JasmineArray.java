package net.azulite.jasmine;

/**
 * JasmineArray is array in JSON type.
 * @author Hiroki
 */

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;

public class JasmineArray extends JasmineData
{
	private LinkedList<JasmineData> array;

	public JasmineArray()
	{
		this.setType( Jasmine.Type.ARRAY );
		array = new LinkedList<JasmineData>();
	}

	/**
	 * @return Always return false.
	 */
	public boolean isPremitive() { return false; }

	/**
	 * @return Retrun array size.
	 */
	public double getNumber() { return array.size(); }

	/**
	 * @return Retrun array size.
	 */
	public int getInteger(){ return array.size(); }

	/**
	 * @return Always return true.
	 */
	public boolean getBoolean() { return true; }

	/**
	 * Return JSON string.(No-space)
	 * @return [V0,V1,...]
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
	 * @return Return JasmineArray.(this)
	 */
	public JasmineArray getArray() { return this; }

	/**
	 * @param index Index of the array to which you want to add.
	 * @return Return JasmineData.
	 */
	public JasmineData get( int index ) { return array.get( index ); }

	/**
	 * Add null Object(JasmineData) to the end of array.
	 */
	public void push() { array.push( new JasmineData() ); }

	/**
	 * Add JasmineData to the end of array.
	 * @param value JasmineData, JasmineArray, JasmineObject, ...etc.
	 */
	public void push( JasmineData value ) { array.push( Jasmine.nullCheck( value ) ); }

	/**
	 * Add boolean to the end of array.
	 * @param value true or false. Add value after convert JasmineBoolean.
	 */
	public void push( boolean value ) { array.push( new JasmineBoolean( value ) ); }

	/**
	 * Add number to the end of array.
	 * @param value Number. Add value after convert JasmineNumber.
	 */
	public void push( double value ) { array.push( new JasmineNumber( value ) ); }

	/**
	 * Add String to the end of array.
	 * @param value String. Add value after convert JasmineString.
	 */
	public void push( String value ) { array.push( new JasmineString( value ) ); }

	/**
	 * Retrieve JasmineData from the end of array.
	 * @return Return JasmineData.
	 */
	public JasmineData pop() { return Jasmine.nullCheck( array.pop() ); }

	/**
	 * Add null Object(JasmineData) to the beginning of array.
	 */
	public void unshift() { array.addFirst( new JasmineData() ); }

	/**
	 * Add JasmineData to the beginning of array.
	 * @param value JasmineData, JasmineArray, JasmineObject, ...etc.
	 */
	public void unshift( JasmineData value ) { array.addFirst( Jasmine.nullCheck( value ) ); }

	/**
	 * Add boolean to the beginning of array.
	 * @param value true or false. Add value after convert JasmineBoolean.
	 */
	public void unshift( boolean value ) { array.addFirst( new JasmineBoolean( value ) ); }

	/**
	 * Add Number to the beginning of array.
	 * @param value Number. Add value after convert JasmineBoolean.
	 */
	public void unshift( double value ) { array.addFirst( new JasmineNumber( value ) ); }

	/**
	 * Add String to the beginning of array.
	 * @param value String. Add value after convert JasmineBoolean.
	 */
	public void unshift( String value ) { array.addFirst( new JasmineString( value ) ); }

	/**
	 * Retrieve JasmineData from the beginning of array.
	 * @return Return JasmineData.
	 */
	public JasmineData shift() { return Jasmine.nullCheck( array.pollFirst() ); }

	/**
	 * Add null Object(JasmineData) to the array.
	 * @param index Index of the array to which you want to add.
	 */
	public void set( int index ) { array.add( index, new JasmineData() ); }

	/**
	 * Add JasmineData to the array.
	 * @param index Index of the array to which you want to add.
	 * @param value JasmineData, JasmineArray, JasmineObject, ...etc.
	 */
	public void set( int index, JasmineData value ) { array.add( index, Jasmine.nullCheck( value ) ); }

	/**
	 * Add boolean to the array.
	 * @param index Index of the array to which you want to add.
	 * @param value true or false. Add value after convert JasmineBoolean.
	 */
	public void set( int index, boolean value ) { array.add( index, new JasmineBoolean( value ) ); }

	/**
	 * Add Number to the array.
	 * @param index Index of the array to which you want to add.
	 * @param value Number. Add value after convert JasmineNumber.
	 */
	public void set( int index, double value ) { array.add( index, new JasmineNumber( value ) ); }

	/**
	 * Add String to the array.
	 * @param index Index of the array to which you want to add.
	 * @param value String. Add value after convert JasmineString.
	 */
	public void set( int index, String value ) { array.add( index, new JasmineString( value ) ); }

	/**
	 * Remove data.
	 * @param index Index of the array to which you want to remove.
	 * @return JasmineData.
	 */
	public JasmineData remove( int index ) { return Jasmine.nullCheck( array.remove( index ) ); }

	/**
	 * Remove all data.
	 */
	public void removeAll() { array.clear(); }

	/**
	 * Write JSON string.(No-space)
	 */
	public void writeData( Writer w ) throws IOException
	{
		boolean e = false;

		w.write( "[" );
		for ( JasmineData d: array )
		{
			if ( e ) { w.write( "," ); } else { e = true; }
			d.writeData( w );
		}
		w.write( "]" );
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

		w.write( indent + "[\n" );
		for ( JasmineData d: array )
		{
			if ( e ) { w.write( ",\n" ); } else { e = true; }
			d.writeData( w, ndeep, spacer );
		}
		w.write( "\n" + indent + "]" );
	}
}
