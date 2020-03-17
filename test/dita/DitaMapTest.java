/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

@SuppressWarnings({ "nls", "javadoc" })
public class DitaMapTest extends DitaTest {

	@Rule
	public TestName name = new TestName();

	@Test
	public void testMapInitial() throws Exception {
		// PREPARE
		DitaMap map = new DitaMap("Bar", "bar");
		StringWriter writer = new StringWriter();

		// RUN
		map.write(writer);

		// ASSERT
		assertResult(writer, name);
	}

	@Test
	public void testMapTitle() throws Exception {
		// PREPARE
		DitaMap map = new DitaMap("Bar", "bar");
		map.setTitle("Bar Foo Bar");
		StringWriter writer = new StringWriter();

		// RUN
		map.write(writer);

		// ASSERT
		assertResult(writer, name);
		assertEquals("Bar Foo Bar", map.getTitle());
	}
}
