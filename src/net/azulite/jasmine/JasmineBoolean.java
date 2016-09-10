package net.azulite.jasmine;

public class JasmineBoolean extends JasmineData
{
	private boolean bool;

	public JasmineBoolean() { init( false ); }
	public JasmineBoolean( boolean value ) { init( value ); }

	public void init( boolean value )
	{
		this.setType( Jasmine.Type.BOOLEAN );
		this.set( value );
	}

	/**
	 * @return true = 1, false = 0.
	 */
	public double getNumber() { return bool ? 1 : 0; }

	/**
	 * @return true or false.
	 */
	public boolean getBoolean() { return bool; }

	/**
	 * @return "true" or "false".
	 */
	public String getString() { return bool ? "true" : "false"; }

	/**
	 * @return true or false.
	 */
	public boolean get() { return bool; }

	/**
	 * @param value Set true or false.
	 */
	public void set( boolean value ) { bool = value; }
}
