package org.webbitserver;

import java.nio.charset.Charset;

/**
 * Writes a response back to the client.
 *
 * IMPORTANT: The connection will remain open until {@link #end()} or {@link #error(Throwable)} is called. Don't
 * forget these!
 * 
 * @author Joe Walnes
 */
public interface HttpResponse {

    /**
     * For text based responses, sets the Charset to encode the response as.
     *
     * If not set, defaults to UTF8.
     */
    HttpResponse charset(Charset charset);

    /**
     * Current Charset used to encode to response as.
     *
     * @see #charset(Charset)
     */
    Charset charset();

    /**
     * Sets the HTTP status code.
     *
     * Defaults to 200 (OK).
     */
    HttpResponse status(int status);

    /**
     * Retrieve HTTP status code that this response is going to return.
     *
     * @see #status(int)
     */
    int status();

    /**
     * Adds an HTTP header. Multiple HTTP headers can be added with the same name.
     */
    HttpResponse header(String name, String value);

    /**
     * Adds a numeric HTTP header. Multiple HTTP headers can be added with the same name.
     */
    HttpResponse header(String name, long value);

    /**
     * Write text based content back to the client.
     *
     * @see #charset(Charset)
     * @see #content(byte[])
     */
    HttpResponse content(String content);

    /**
     * Write binary based content back to the client.
     *
     * @see #content(String)
     */
    HttpResponse content(byte[] content);

    /**
     * Marks the response as erroneous. The error shall be displayed to the user (500 SERVER ERROR)
     * and the connection closed.
     *
     * Every response should have either {@link #end()} or {@link #error(Throwable)} called. No more
     * operations should be performed on a response after these.
     */
    HttpResponse error(Throwable error);

    /**
     * Marks the response as ended. At this point any remaining data shall be flushed and
     * the connection closed.
     *
     * Every response should have either {@link #end()} or {@link #error(Throwable)} called. No more
     * operations should be performed on a response after these.
     */
    HttpResponse end();

}
