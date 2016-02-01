package com.corejava.chapter12_61;

import java.io.*;

/**
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
class Printf3
{
   public static native void fprint(PrintWriter out, String format, double x);
}
