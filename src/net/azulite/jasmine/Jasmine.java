package net.azulite.jasmine;

/**
 * @author Hiroki
 */

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class Jasmine
{
	public static final String VERSION = "0.0.1";
	public static final int VERSION_CODE = 0;
	protected static final JasmineObject EMPTY_OBJECT = new JasmineEmptyObject();
	protected static final JasmineArray EMPTY_ARRAY = new JasmineEmptyArray();
	protected static final JasmineData nullCheck( JasmineData value ) { return value == null ? new JasmineData() : value; }

	/**
	 * 
	 */
	public enum Type
	{
		/** null of JSON type.*/
		NULL,
		/** true or false of JSON type.*/
		BOOLEAN,
		/** Number of JSON type.*/
		NUMBER,
		/** String of JSON type.*/
		STRING,
		/** Array of JSON type.*/
		ARRAY,
		/** Object of JSON type.*/
		OBJECT,
	};

	/**
	 * @param json JSON string.(ex "{\"text\":\"hello\",\"num\":1}" )
	 */
	public static JasmineData parse( String json ) throws IOException
	{
		return Jasmine.parse( new ByteArrayInputStream( json.getBytes("utf-8") ) );
	}

	/**
	 * @param in InputStream. Charset if UTF-8.
	 */
	public static JasmineData parse( InputStream in ) throws IOException
	{
		return new JasmineParser( new InputStreamReader( in, "UTF-8" ) ).parse();
	}

	public static JasmineData parse( InputStreamReader is ) throws IOException
	{
		return new JasmineParser( is ).parse();
	}

	public static JasmineData parse( File file ) throws UnsupportedEncodingException, IOException
	{
		return new JasmineParser( new InputStreamReader( new FileInputStream( file ), "UTF-8" ) ).parse();
	}

	public static JasmineData parse( Reader r ) throws IOException
	{
		return new JasmineParser( r ).parse();
	}

	/**
	 * @param file Write file.
	 * @param data JSON data.(JasmineData,JasmineObject,JasmineArray, ...etc)
	 */
	public static void write( File file, JasmineData data ) throws IOException
	{
		PrintWriter pw = new PrintWriter( new BufferedWriter( new FileWriter( file ) ) );
		data.writeData( pw, 0 );
	}
}

class JasmineEmptyObject extends JasmineObject
{
	public JasmineEmptyObject() { this.setType( Jasmine.Type.NULL ); }

	public String getString() { return "null"; }

	protected String getString( int deep, String spacer )
	{
		String indent = "";
		while( 0 < deep-- ) { indent += spacer; }
		return indent + this.getString();
	}

	public void writeData( Writer w ) throws IOException { w.write( this.getString() ); }

	public void writeData( Writer w, int deep, String spacer ) throws IOException { w.write( this.getString( deep, spacer ) ); }
}

class JasmineEmptyArray extends JasmineArray
{
	public JasmineEmptyArray() { this.setType( Jasmine.Type.NULL ); }

	protected String getString( int deep, String spacer )
	{
		String indent = "";
		while( 0 < deep-- ) { indent += spacer; }
		return indent + this.getString();
	}

	public void writeData( Writer w ) throws IOException { w.write( this.getString() ); }

	public void writeData( Writer w, int deep, String spacer ) throws IOException { w.write( this.getString( deep, spacer ) ); }
}

