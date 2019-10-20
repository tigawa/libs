package com.tigawa.util;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Assert;
import org.junit.Test;


public class AboutMapTest {

	@Test
	public void getPramInteger() {
		@SuppressWarnings("serial")
		AboutMap<Integer, String> aboutMap = new AboutMap<Integer, String>() {
		    {
		        put(2019, "A");
		        put(2021, "B");
		    }
		};
		Assert.assertThat(aboutMap.get(2018), nullValue());
		Assert.assertThat(aboutMap.get(2019), is("A"));
		Assert.assertThat(aboutMap.get(2020), is("A"));
		Assert.assertThat(aboutMap.get(2021), is("B"));
		Assert.assertThat(aboutMap.get(2022), is("B"));
		Assert.assertThat(aboutMap.get(2023), is("B"));
		Assert.assertThat(aboutMap.get(9999), is("B"));
	}
	
	@Test
	public void getPramShort() {
		@SuppressWarnings("serial")
		AboutMap<Short, String> aboutMap = new AboutMap<Short, String>() {
		    {
		        put((short) 2019, "A");
		        put((short) 2021, "B");
		    }
		};
		Assert.assertThat(aboutMap.get((short)2018), nullValue());
		Assert.assertThat(aboutMap.get((short)2019), is("A"));
		Assert.assertThat(aboutMap.get((short)2020), is("A"));
		Assert.assertThat(aboutMap.get((short)2021), is("B"));
		Assert.assertThat(aboutMap.get((short)2022), is("B"));
		Assert.assertThat(aboutMap.get((short)2023), is("B"));
		Assert.assertThat(aboutMap.get((short)9999), is("B"));
	}
	
	@Test
	public void getPramFloat() {
		@SuppressWarnings("serial")
		AboutMap<Float, String> aboutMap = new AboutMap<Float, String>() {
		    {
		        put(2019f, "A");
		        put(2021f, "B");
		    }
		};
		Assert.assertThat(aboutMap.get(2018f), nullValue());
		Assert.assertThat(aboutMap.get(2019f), is("A"));
		Assert.assertThat(aboutMap.get(2020f), is("A"));
		Assert.assertThat(aboutMap.get(2021f), is("B"));
		Assert.assertThat(aboutMap.get(2022f), is("B"));
		Assert.assertThat(aboutMap.get(2023f), is("B"));
		Assert.assertThat(aboutMap.get(9999f), is("B"));
	}	

	
	@Test
	public void getAndDesc() {
		boolean descended = true;
		@SuppressWarnings("serial")
		AboutMap<Integer, String> aboutMap = new AboutMap<Integer, String>(descended) {
		    {
		        put(2019, "A");
		        put(2021, "B");
		    }
		};
		
		Assert.assertThat(aboutMap.get(2018), is("A"));
		Assert.assertThat(aboutMap.get(2019), is("A"));
		Assert.assertThat(aboutMap.get(2020), is("B"));
		Assert.assertThat(aboutMap.get(2021), is("B"));
		Assert.assertThat(aboutMap.get(2022), nullValue());
		Assert.assertThat(aboutMap.get(2023), nullValue());
		Assert.assertThat(aboutMap.get(9999), nullValue());
	}
	
	@Test
	public void getOrDefault() {
		@SuppressWarnings("serial")
		AboutMap<Integer, String> aboutMap = new AboutMap<Integer, String>() {
		    {
		        put(2019, "A");
		        put(2021, "B");
		    }
		};
		Assert.assertThat(aboutMap.getOrDefault(2018, "NONE"), is("NONE"));
		Assert.assertThat(aboutMap.getOrDefault(2019, "NONE"), is("A"));
		Assert.assertThat(aboutMap.getOrDefault(2020, "NONE"), is("A"));
		Assert.assertThat(aboutMap.getOrDefault(2021, "NONE"), is("B"));
		Assert.assertThat(aboutMap.getOrDefault(2022, "NONE"), is("B"));
		Assert.assertThat(aboutMap.getOrDefault(2023, "NONE"), is("B"));
		Assert.assertThat(aboutMap.getOrDefault(9999, "NONE"), is("B"));
	}

	@Test
	public void getOrDefaultAndDesc() {
		boolean descended = true;
		@SuppressWarnings("serial")
		AboutMap<Integer, String> aboutMap = new AboutMap<Integer, String>(descended) {
		    {
		        put(2019, "A");
		        put(2021, "B");
		    }
		};
		
		Assert.assertThat(aboutMap.getOrDefault(2018, "NONE"), is("A"));
		Assert.assertThat(aboutMap.getOrDefault(2019, "NONE"), is("A"));
		Assert.assertThat(aboutMap.getOrDefault(2020, "NONE"), is("B"));
		Assert.assertThat(aboutMap.getOrDefault(2021, "NONE"), is("B"));
		Assert.assertThat(aboutMap.getOrDefault(2022, "NONE"), is("NONE"));
		Assert.assertThat(aboutMap.getOrDefault(2023, "NONE"), is("NONE"));
		Assert.assertThat(aboutMap.getOrDefault(9999, "NONE"), is("NONE"));
	}
	
	@Test
	public void testException() {
		AboutMap<Integer, String> map = new AboutMap<Integer, String>();
		try{
			map.get("KEY");
		} catch (Exception e){
			Assert.assertThat(e, is(instanceOf(IllegalArgumentException.class)));
			Assert.assertThat(e.getMessage(), is("Key is not Number"));
		}
	}
}
