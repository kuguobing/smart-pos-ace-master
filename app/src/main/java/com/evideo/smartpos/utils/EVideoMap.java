package com.evideo.smartpos.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;


public class EVideoMap<V> extends LinkedHashMap<String, V> {

	@Override
	public V put(String key, V value) {
		return super.put(key, value);
	}

	/*@Override
	public V put(String key, V value) {
		return super.put(key.toString().toLowerCase(), value);
	}*/

	@Override
	public boolean remove(Object key, Object value) {
		return super.remove(key, value);
	}

	/*@Override
	public V remove(Object key) {
		return super.remove(key.toString().toLowerCase());
	}*/

	@Override
	public void putAll(Map<? extends String, ? extends V> m) {
		super.putAll(m);
	}

	/*@Override
	public void putAll(Map m) {
		//key转小写
		Map map = new EVideoMap();
		Iterator<Entry<String, String>> it = m.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			map.put(entry.getKey().toLowerCase(),entry.getValue());
		}
		super.putAll(map);
	}*/


	@Override
	public V get(Object key) {
		return super.get(key);
	}

	/*@Override
	public V get(Object key) {
		return super.get(key.toString().toLowerCase());
	}*/

	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(key);
	}

	/*@Override
	public boolean containsKey(Object key) {
		return super.containsKey(key.toString().toLowerCase());
	}*/

	/**
	 * 获取String数据，不存在返回""
	 * @param key 键
	 * @return 返回int类型
	 */
	public String getMapStr(String key) {
		Object o = this.get(key);
		return null == o ? "" : String.valueOf(o);
	}

	/**
	 * 获取String数据，不存在就使用默认值
	 * @param key 键
	 * @return 返回int类型
	 */
	public String getMapStr(String key, String defaultValue) {
		String s = getMapStr(key);
		return "".equals(s) ? defaultValue : s;
	}

	/**
	 * 获取int数据，不存在就使用默认值
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return 返回int类型
	 */
	public int getMapInt(String key, int defaultValue) {
		String value = getMapStr(key);
		if ("".equals(value)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	@Override
	public Object clone() {
		Object o = null;
		try {
			//深度复制
			o = CloneUtil.deepClone(EVideoMap.this);
		} catch (IllegalAccessException ill) {
			System.out.println(ill.toString());
		} catch (InstantiationException ins) {
			System.out.println(ins.toString());
		} catch (InvocationTargetException inv) {
			System.out.println(inv.toString());
		} catch (NoSuchMethodException nos) {
			System.out.println(nos.toString());
		}
		return o;
	}
}
