/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import java.util.HashMap;

/**
 * Simple registry to generate new unique identified. Unique here means, unique within a single generation process.
 */
public class DitaId {

	/*
	 * Maps from name-space to last used number.
	 */
	static HashMap<String, Integer> numbers = new HashMap<>();

	/**
	 * @param namespace
	 *            the name-space, never <code>null</code> and not empty
	 * @return the next unique number for that name-space
	 */
	public static int nextSerialNumber(String namespace) {
		if (numbers.containsKey(namespace)) {
			numbers.put(namespace, numbers.get(namespace) + 1);
		} else {
			numbers.put(namespace, 0);
		}

		return numbers.get(namespace);
	}

	/**
	 * @param namespace
	 *            the name-space, never <code>null</code> and not empty
	 * @return a new unique id
	 */
	public static String newId(String namespace) {
		// TODO Use a customizable text format
		return namespace + "-" + nextSerialNumber(namespace); //$NON-NLS-1$
	}

	/**
	 * Intentionally package protected. This is not part of the API but exposed here for test cases.
	 */
	static void reset() {
		numbers.clear();
	}
}
