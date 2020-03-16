/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.NativeJavaPackage;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ScriptableObject;

/**
 * Execute a number of test scripts using Rhino.
 */
@RunWith(Parameterized.class)
@SuppressWarnings({ "javadoc", "nls" })
public class DitaScriptingTest {

	private File scriptFile;

	private Object expected;

	public DitaScriptingTest(File file, Object expected) {
		scriptFile = file;
		this.expected = expected;
	}

	@Parameters(name = "{0}")
	public static Collection<Object[]> getTestParameter() throws Exception {
		return findScripts("scripts", null);
	}

	static Collection<Object[]> findScripts(String scriptsPath, FileFilter filter) throws Exception {
		Collection<Object[]> result = new ArrayList<>();
		File path = new File(scriptsPath);
		Assert.assertTrue("script folder does not exist: " + path, path.exists());
		File[] files = path.listFiles();
		if (files != null) {
			for (File file : files) {
				// avoid scanning folders
				if (file.isDirectory()) {
					continue;
				}
				// filter
				if ((filter != null) && !filter.accept(file)) {
					continue;
				}
				result.add(new Object[] { file, Boolean.TRUE });
			}
		}

		return result;
	}

	@Test
	public void testExecution() throws Exception {
		Context cx = ContextFactory.getGlobal().enterContext();
		ScriptableObject scope = cx.initStandardObjects();

		// inject the "dita" package (bit of a hack)
		@SuppressWarnings("deprecation")
		NativeJavaPackage pkg = new NativeJavaPackage("dita", DitaTopic.class.getClassLoader());
		ScriptRuntime.setObjectProtoAndParent(pkg, scope);
		scope.defineProperty("dita", pkg, ScriptableObject.DONTENUM);

		cx.putThreadLocal("rootScope", scope);
		cx.putThreadLocal(Context.FEATURE_STRICT_MODE, Boolean.TRUE);
		cx.putThreadLocal(Context.FEATURE_WARNING_AS_ERROR, Boolean.TRUE);

		// load, compile and execute script
		String script = FileUtils.readFileToString(scriptFile, "UTF-8");
		Script scriptEx = cx.compileString(script, scriptFile.getName(), 1, null);
		Object result = scriptEx.exec(cx, scope);

		Assert.assertEquals(expected, result);
	}
}
