import java.io.IOException;

import net.azulite.jasmine.*;

public class Main
{
	public static final void main( String[] args )
	{
		System.out.println("Hello, Jasmine.");

		JasmineData data;
		try
		{
			/*data = Jasmine.parse( "[null,1,23,456,1.1,\"aa\",true,false]" );
			System.out.println( data.getString() );

			data = Jasmine.parse( "{\"aa\":1,\"test\":null}" );
			data.getObject().set( "bb", "aa\n\"" );
			System.out.println( data.getString() );
			System.out.println( data.toString() );*/

			data = Jasmine.parse( "{\"width\":256,\"layer\":[{\"image\":\"0\",\"visible\":true,\"name\":\"\",\"lock\":false}],\"height\":256}" );
			System.out.println( data.toString() );
			System.out.println( data.getObject().get( "height" ) );
			System.out.println( data.getObject().get( "height" ).getType() );
			System.out.println( data.getObject().get( "height" ).getNumber() );
		} catch ( IOException e )
		{
			e.printStackTrace();
		}

		/*data = new JasmineObject();
		System.out.println( data.getString() );

		JasmineObject object = new JasmineObject();
		object.set( "a" );
		JasmineArray array = new JasmineArray();
		array.push( "a" );

		data.getObject().set( "test0" );
		data.getObject().set( "test1", false );
		data.getObject().set( "test2", true );
		data.getObject().set( "test3", 1 );
		data.getObject().set( "test4", 1.1 );
		data.getObject().set( "test5", "aa" );
		data.getObject().set( "test6", object );
		data.getObject().set( "test7", array );
		System.out.println( data.getString() );
		System.out.println( data.toString() );

		data = new JasmineArray();
		data.getArray().push();
		data.getArray().push( false );
		data.getArray().push( true );
		data.getArray().push( 1 );
		data.getArray().push( 1.1 );
		data.getArray().push( "aa" );
		data.getArray().push( object );
		data.getArray().push( array );
		System.out.println( data.getString() );
		System.out.println( data.toString() );*/
	}
}
