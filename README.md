# Jasmine

JasmineはJava用のJSONパーサーです。
動作が結構適当(というよりかなりガバガバ)ですが、さくっと使う分には使えるはず。

## 読み込み

JARを取り込み、`import net.azulite.jasmine.*;` で使えます。

`Jasmine.parse( 何かしらのデータ );` でいい感じに読み込んで、`JasmineData` オブジェクトを返します。

現在のparse可能なデータは以下です。

* String
* InputStream
* InputStreamReader
* File
* Reader

`JasmineData` オブジェクトは、派生クラスが以下の型と対応しています。

* JasmineData
    * null
    * `Jasmine.Type.NULL`
* JasmineBoolean
    * 真偽値( `true` / `false` )
    * `Jasmine.Type.BOOLEAN`
* JasmineNumber
    * 数値
    * `Jasmine.Type.NUMBER`
* JasmineString
    * 文字列
    * `Jasmine.Type.STRING`
* JasmineArray
    * 配列
    * `Jasmine.Type.ARRAY`
* JasmineObject
    * オブジェクト
    * `Jasmine.Type.OBJECT`

`JasmineData` オブジェクトは、`getType()` で型判別が可能です。
またキャストすることなく各型を使えるように、以下取得メソッドがあるので、型に応じて使い分けてください。
例えば型がオブジェクトで、そのオブジェクトに新しいデータを追加したい場合などには、`JasmineObject` 専用の `set( キー, 値 )` のメソッドが用意されています。

* double getNumber()
    * 数値型の場合は数値を返す。
    * 配列、オブジェクトの場合はアイテムの数を返す。
* double getInteger()
    * 数値型の場合はintにキャストした数値を返す。
    * 配列、オブジェクトの場合はアイテムの数を返す。
* boolean getBoolean()
    * 真偽値の場合は `true` か `false` を返す。
    * nullの場合は `false` を返す。
    * 数値の場合は `0` の時 `false` を返し、それ以外の場合は `true` を返す。
    * 文字列の場合は空文字列の場合 `false` を返し、それ以外の場合は `true` を返す。
    * それ以外の場合は `true` を返す。
* String getString()
    * 文字列の場合は文字列を返す。
    * それ以外の場合は文字列出力したものを返す。
        * オブジェクトなどの場合、`toString()` とは異なり、改行やスペースなどは入らない。
* JasmineArray getArray()
    * 配列の場合は配列を返す。
    * それ以外の場合は特殊な空配列を返す。
        * `getType()` では `Jasmine.Type.NULL` を返すが、オブジェクトとしては配列。
        * 値を取得しようとしても 特殊な空の値しか返さない。
* JasmineObject getObject()
    * オブジェクトの場合はオブジェクトを返す。
    * それ以外の場合は特殊な空オブジェクトを返す。
        * `getType()` では `Jasmine.Type.NULL` を返すが、オブジェクトとしては配列。
        * 値を取得しようとしても 特殊な空の値しか返さない。

`JasmineData` のその他汎用的に使えるメソッドは以下です。

* Jasmine.Type getType()
    * `Jasmine.Type` による型を返します。
    * 全ての型は `JasmineData` を継承し、すべてのデータはこの型で返されるので、適切に利用するための判別に利用してください。
* boolean isPremitive()
    * 基本的な型( null、真偽値、数値、文字列 )の場合は `true`を返し、配列・オブジェクトの場合は `false` を返す。
* writeData( Writer w, int deep, String spacer )
    * データを整形して出力する。
        * 第一引数以外は省略可能。
    * 文字列は `"文字列"` といった形や、改行等のエスケープシーケンスが入る。
        * いわゆるファイルへの出力用。
        * 第二引数以降を省略すると改行などは入らず、省略しないと人が見やすい形で出力される。
    * 第二引数はスペーサーの数を与える。基本は0。
    * 第三引数はスペーサーの文字列を指定する。デフォルトはタブ。
* String toString()
    * JSONを整形して文字列にする。

`JasmineArray` だけが持つメソッドは以下です。

* JasmineData get( int index )
    * 指定番目のデータを取得します。
* push( T value )
    * 末尾に値を追加します。
    * 引数省略時にはnullに対応する `JasmineData` が自動で追加されます。
    * `JasmineData` はそのままデータを追加しますが、基本的な型( `boolean`, `double`, `String` )に関してはそのまま与えると内部で適切なデータを作って追加します。
* JasmineData pop(){ return array.pop(); }
    * 末尾からデータを取り除き、取り除いたデータを返します。
* unshift( T value )
    * 先頭にデータを追加します。
    * 引数に関してはpushと同じ仕様です。
* JasmineData shift(){ return array.pollFirst(); }
    * 先頭からデータを取り除き、取り除いたデータを返します。
* set( int index, T value )
    * 指定番目にデータを追加します。
    * 第二引数はpushの第一引数と同じ仕様です。
* JasmineData remove( int index )
    * 指定番目のデータを取り除き、取り除いたデータを返します。
* removeAll()
    * 全てのデータを削除します。

`JasmineObject` だけが持つメソッドは以下です。

* JasmineData get( String key )
    * キーに対応する `JasmineData` を返します。
* void set( String key, T value )
    * オブジェクトにキーと対応する値を追加します。
        * 既存のデータは上書き。
    * 第二引数省略時にはnullに対応する `JasmineData` が自動で追加されます。
    * `JasmineData` はそのままデータを追加しますが、基本的な型( `boolean`, `double`, `String` )に関してはそのまま与えると内部で適切なデータを作って追加します。
* remove( String key )
    * キーに対応するデータを削除します。
* removeAll()
    * 全てのデータを削除します。

### JSON文字列

```
import java.io.IOException;

import net.azulite.jasmine.*;

public class Main
{
	public static final void main( String[] args )
	{
		JasmineData data;
		try
		{
			data = Jasmine.parse( "{\"aa\":1,\"test\":null}" );
			System.out.println( data.getString() );
			System.out.println( data.toString() );
			System.out.println( data.getObject().get( "aa" ) );
			System.out.println( data.getObject().get( "test" ) );
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
}
```

### ファイル

## 書き込み

### ファイル

ファイル出力は主に以下の2つの方法があります。

* `Jasmine.write( File file, JasmineData data )` で　`JasmineData` をファイルに出力。
* `JasmineData` の `writeData( Writer w )` メソッドで出力。
    * こちらに関しては上記参照。

## 今後について

* テスト適当なのでちゃんとパターン洗い出してテストできるようにしておきたい。
* パーサーがガバガバなのでそのうちなんとかしたい。
* このREADMEも割と適当なのでなんとかしたい。
