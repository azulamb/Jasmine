package net.azulite.jasmine;

import java.io.IOException;
import java.io.Reader;

class JasmineParser
{
	private  Reader reader;
	private int prev, now;

	public JasmineParser( Reader r )
	{
		reader = r;
		prev = now = 0;
	}

	private int getChar() throws IOException { prev = now; return now = reader.read(); }

	private String convertString( int c ) { return String.valueOf( (char)c ); }

	public JasmineData parse() throws IOException
	{
		String tmp = "";
		parse: while ( this.getChar() != -1 )
		{
			switch ( now )
			{
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
				return this.parseNumber( this.convertString( now ) );
			case '"':
				return new JasmineString( this.parseString() );
			case '[':
				return this.parseArray();
			case '{':
				return this.parseObject();
			case 'e':
				if ( tmp.equals( "tru" ) ) { this.getChar(); return new JasmineBoolean( true ); }
				if ( tmp.equals( "fals" ) ) { this.getChar(); return new JasmineBoolean( false ); }
				break;
			case 'l':
				if ( tmp.equals( "nul" ) ) { this.getChar(); return new JasmineData(); }
				if ( tmp.equals( "nu" ) || tmp.equals( "fa" ) ) { tmp += "l"; break; }
				break parse;
			case 'a':
			case 'f':
			case 'n':
			case 'r':
			case 's':
			case 't':
			case 'u':
				tmp += this.convertString( now );
				break;
			case '}':
			case ']':
				break parse;
			default:
				if ( !tmp.equals( "" ) ) { break parse; }
			}
		}

		return null;
	}

	private JasmineNumber parseNumber( String number ) throws IOException
	{
		parse: while ( this.getChar() != -1 )
		{
			switch ( now )
			{
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
				number += this.convertString( now );
				break;
			default:
				break parse;
			}
		}
		return new JasmineNumber( Double.valueOf( number ) );
	}

	private String parseString() throws IOException
	{
		boolean escape = false;
		String ret = "";

		while ( this.getChar() != -1 )
		{
			if ( escape )
			{
				switch ( now )
				{
				case 'n': ret += "\n"; break;
				default: ret += this.convertString( now ); break;
				}
				escape = false;
			} else if ( now == '\\' )
			{
				escape = true;
			} else if ( now == '"' )
			{
				break;
			} else
			{
				ret += this.convertString( now );
			}
		}

		return ret;
	}

	private JasmineArray parseArray() throws IOException
	{
		JasmineArray array = new JasmineArray();
		JasmineData d;

		while ( prev != -1 )
		{
			d = this.parse();
			if ( d == null ) { break; }
			array.push( d );
			if ( prev == ']' ) { break; }
		}
		return array;
	}

	private JasmineObject parseObject() throws IOException
	{
		JasmineObject object = new JasmineObject();
		String key;

		while ( this.getChar() != -1 )
		{
			if( now == '"' )
			{
				key = this.parseString();
				while ( this.getChar() != ':' ) {}
				if ( now != ':' ) { object.set( key ); break; }
				object.set( key, parse() );
				if ( now == '}' ){ break; }
			}
		}
		return object;
	}
}
