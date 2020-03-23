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
public class DitaTableTest extends DitaTest {

	@Rule
	public TestName name = new TestName();

	@Test
	public void testTableInitial() throws Exception {
		// PREPARE
		DitaTable table = new DitaTable("Bar");
		StringWriter writer = new StringWriter();

		// RUN
		write(writer, table);

		// ASSERT
		assertResult(writer, name);
	}

	@Test
	public void testTableMultipleColumns() throws Exception {
		// PREPARE
		DitaTable table = new DitaTable("Bar", new String[] { "A", "B" });
		table.addColumns(new String[] { "C", "D" }, new double[] { 1.1, 2.2 });
		table.addColumn("E");
		table.addColumns(new String[] { "F" });
		table.addColumn("G", 3.2);
		StringWriter writer = new StringWriter();

		// RUN
		write(writer, table);

		// ASSERT
		assertResult(writer, name);
	}
}
