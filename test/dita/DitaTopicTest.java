/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import java.io.StringWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

@SuppressWarnings({ "nls", "javadoc" })
public class DitaTopicTest extends DitaTest {

	@Rule
	public TestName name = new TestName();

	@Test
	public void testTopicInitial() throws Exception {
		// PREPARE
		DitaTopic topic = new DitaTopic("Foo", "foo");
		StringWriter writer = new StringWriter();

		// RUN
		topic.write(writer);

		// ASSERT
		assertResult(writer.toString(), name.getMethodName());
	}

	@Test
	public void testTopicSimpleParagraph() throws Exception {
		// PREPARE
		DitaTopic topic = new DitaTopic("Foo", "foo");
		topic.addParagraph(
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
		StringWriter writer = new StringWriter();

		// RUN
		topic.write(writer);

		// ASSERT
		assertResult(writer.toString(), name.getMethodName());
	}
}
