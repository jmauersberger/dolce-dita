/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;

/**
 * Just some base class for own test helpers.
 */
@SuppressWarnings({ "nls", "javadoc" })
public class DitaTest {

	@BeforeClass
	public static void leadIn() {
		// test cases may run continuously in the same VM so we better reset all counters
		DitaId.reset();
	}

	void assertResult(String current, String path) throws Exception {
		File base = new File("results");
		File out = new File(base, path + ".actual");
		FileUtils.write(out, current, "UTF8");
		File in = new File(base, path + ".expected");
		String expected = FileUtils.readFileToString(in, "UTF8");
		Assert.assertEquals("comparison failed for " + path, expected, current);
	}
}
