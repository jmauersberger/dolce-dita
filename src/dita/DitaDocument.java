/*
 * Copyright the Dolce Dita contributors.
 * All rights reserved.
 */
package dita;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Abstract base class for all own {@link Document} implementations.
 */
@SuppressWarnings("serial")
abstract class DitaDocument extends Document {

	/**
	 * Write the DITA Topic to a {@link File} with the given file name.
	 *
	 * @param filename
	 *            the name of the {@link File}, never <code>null</code>
	 * @throws IOException
	 *             well, you know, this may fail
	 */
	public void write(String filename) throws IOException {
		write(new File(filename));
	}

	/**
	 * Write the DITA Topic to the given {@link File}.
	 *
	 * @param file
	 *            the {@link File}, never <code>null</code>
	 * @throws IOException
	 *             well, you know, this may fail
	 */
	public void write(File file) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			write(writer);
		}
	}

	/**
	 * Write the DITA Topic to the given {@link Writer}.
	 *
	 * @param writer
	 *            the {@link Writer}, never <code>null</code>
	 * @throws IOException
	 *             well, you know, this may fail
	 */
	public void write(Writer writer) throws IOException {
		Format prettyPrinter = Format.getPrettyFormat();
		prettyPrinter.setExpandEmptyElements(true);
		prettyPrinter.setEncoding(StandardCharsets.UTF_8.name());

		XMLOutputter putter = new XMLOutputter();
		putter.setFormat(prettyPrinter);
		putter.output(this, writer);
	}

}
