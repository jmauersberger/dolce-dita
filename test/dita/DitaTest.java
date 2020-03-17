/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import java.io.File;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.rules.TestName;

/**
 * Just some base class for own test helpers.
 */
@SuppressWarnings({ "nls", "javadoc" })
public class DitaTest {

	@Before
	public void before() {
		// test cases may run continuously and in undefined order in the same VM so we better reset all counters
		DitaId.reset();
	}

	/*
	 * To make test code shorter we offer this signature.
	 */
	void assertResult(StringWriter current, TestName path) throws Exception {
		assertResult(current.toString(), path.getMethodName());
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
