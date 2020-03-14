/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import java.io.StringWriter;

import org.junit.Test;

@SuppressWarnings({ "nls", "javadoc" })
public class DitaTopicTest extends DitaTest {

	@Test
	public void testInitial() throws Exception {
		// PREPARE
		DitaTopic topic = new DitaTopic("Foo", "foo");
		StringWriter writer = new StringWriter();

		// RUN
		topic.write(writer);

		// ASSERT
		assertResult(writer.toString(), "topic-initial");
	}
}
