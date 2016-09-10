package net.azulite.jasmine;

public class JasmineNumber extends JasmineData
{
	private double num;

	public JasmineNumber() { init( 0 ); }

	public JasmineNumber( double value ) { init( value ); }

	private void init( double value )
	{
		this.setType( Jasmine.Type.NUMBER );
		this.set( value );
	}

	/**
	 * @return Return value.
	 */
	public double getNumber() { return num; }

	/**
	 * @return Return true if value != 0.
	 */
	public boolean getBoolean() { return num != 0; }

	/**
	 * @return Return String value.
	 */
	public String getString() { return ( num - Math.floor( num ) != 0 ) ? "" + num : "" + ( (int)num ); }

	/**
	 * @return Return value.
	 */
	public double get() { return num; }

	/**
	 * @param value set value.
	 */
	public void set( double value ) { num = value; }
}
