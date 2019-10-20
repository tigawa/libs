package com.tigawa.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * {@code TreeMap}を継承したクラス
 * キーが一致しない場合は直近の小さい（または大きい）値のキーに一致するValueを返します。
 * 
 * <p>重要</p>
 * <pre>
 * + キーはNumberを継承しているクラスしか指定できません。(Integer, Long, Float...)
 * + getメソッドしかオーバライドしていません。それ以外はTreeMapと同じ動作をします。
 * </pre>
 *
 * <p>キーが一致しない場合に、より小さい方のキーに一致する値を返します。</p>
 * <pre>
 * {@code
 *  AboutMap<Integer, String> aboutMap = new AboutMap<Integer, String>() {{
 *    put(2019, "A");
 *    put(2021, "B");
 *  }};
 *  aboutMap.get(2018); //=> null
 *  aboutMap.get(2019); //=> "A"
 *  aboutMap.get(2020); //=> "A"
 *  aboutMap.get(2021); //=> "B"
 *  aboutMap.get(2022); //=> "B"
 * }
 * </pre>
 *
 * <p>キーが一致しない場合に、より大きい方のキーに一致する値を返します。</p>
 * <pre>
 * {@code
 *  boolean descending = true;
 *  AboutMap<Integer, String> aboutMap = new AboutMap<Integer, String>(descending) {{
 *    put(2019, "A");
 *    put(2021, "B");
 *  }};
 *  aboutMap.get(2018); //=> "A"
 *  aboutMap.get(2019); //=> "A"
 *  aboutMap.get(2020); //=> "B"
 *  aboutMap.get(2021); //=> "B"
 *  aboutMap.get(2022); //=> null
 * }
 * </pre>
 * 
 * @param <K> 数値型である必要があります。
 * @param <V> the type of mapped values
 * 
 * @author igawa.taiichi
 * @see TreeMap
 * @version  1.0
 */
public class AboutMap<K extends Number, V> extends TreeMap<K, V> {
	private static final long serialVersionUID = 1518115962300131292L;

	private boolean descending;

	/**
	 * コンストラクタ 空のTreeマップ
	 */
	public AboutMap() {
		super();
	}

	/**
	 * コンストラクタ 空のTreeマップ
	 * @param descending　true:降順  false:昇順 (default)
	 */
	public AboutMap(boolean descending) {
		super();
		this.descending = descending;
	}

	/**
	 * キーが一致しない場合は直近の小さい（または大きい）値のキーに一致するValueを返します。
	 * @param key キー（数値型）
	 * @return 値
	 */
	@Override
	public V get(Object key) {
		if (this.containsKey(key))
			return super.get(key);
		
		if (key instanceof Number == false)
			throw new IllegalArgumentException("Key is not Number");
		
		Number nKey = ((Number)key);

		Map<K, V> map = descending 
						? this
						: this.descendingMap();
						
	    for (K k : map.keySet()) {
	    	if(descending 
				? k.doubleValue() > nKey.doubleValue()
				: k.doubleValue() < nKey.doubleValue())
	    		return super.get(k);
	    }
		return null;
	}	
}
