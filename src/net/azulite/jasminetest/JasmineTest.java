package net.azulite.jasminetest;

import static org.junit.Assert.*;

import org.junit.Test;

import net.azulite.jasmine.*;

public class JasmineTest
{

	@Test
	public void testObjectTypeNull()
	{
		JasmineData data = new JasmineData();
		assertEquals( Jasmine.Type.NULL, data.getType() );
		assertTrue( data.isPremitive() );
	}

	@Test
	public void testObjectTypeBoolean()
	{
		JasmineData data = new JasmineBoolean();
		assertEquals( Jasmine.Type.BOOLEAN, data.getType() );
		assertTrue( data.isPremitive() );
	}

	@Test
	public void testObjectTypeNumber()
	{
		JasmineData data = new JasmineNumber();
		assertEquals( Jasmine.Type.NUMBER, data.getType() );
		assertTrue( data.isPremitive() );
	}

	@Test
	public void testObjectTypeString()
	{
		JasmineData data = new JasmineString();
		assertEquals( Jasmine.Type.STRING, data.getType() );
		assertTrue( data.isPremitive() );
	}

	@Test
	public void testObjectTypeArray()
	{
		JasmineData data = new JasmineArray();
		assertEquals( Jasmine.Type.ARRAY, data.getType() );
		assertTrue( !data.isPremitive() );
	}

	@Test
	public void testObjectTypeObject()
	{
		JasmineData data = new JasmineObject();
		assertEquals( Jasmine.Type.OBJECT, data.getType() );
		assertTrue( !data.isPremitive() );
	}

	@Test
	public void testObjectNull()
	{
		JasmineData data = new JasmineData();
		assertEquals( null, data.getObject() );
	}

	@Test
	public void testObjectBoolean()
	{
		JasmineData data;

		data = new JasmineBoolean( false );
		assertEquals( false, data.getBoolean() );

		data = new JasmineBoolean( true );
		assertEquals( true, data.getBoolean() );
	}

	@Test
	public void testObjectNumber()
	{
		JasmineData data;

		data = new JasmineNumber( 0 );
		assertTrue( 0 == data.getNumber() );

		data = new JasmineNumber( 1 );
		assertTrue( 1 == data.getNumber() );

		data = new JasmineNumber( 1.1 );
		assertTrue( 1.1 == data.getNumber() );

		data = new JasmineNumber( -1.2 );
		assertTrue( -1.2 == data.getNumber() );
	}

	@Test
	public void testObjectString()
	{
		JasmineData data;

		data = new JasmineString();
		assertEquals( "", data.getString() );

		data = new JasmineString( "hello" );
		assertEquals( "hello", data.getString() );

	}

}
