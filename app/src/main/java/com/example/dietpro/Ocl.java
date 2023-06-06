package com.example.dietpro; // Place in the relevant package directory of your application.


/******************************
* Copyright (c) 2003--2022 Kevin Lano
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0
*
* SPDX-License-Identifier: EPL-2.0
* *****************************/
/* OCL library for Java version 8+ */

import java.util.ArrayList;

  public class Ocl {
 public static List<String> tokeniseCSV(String line)  {
   // Assumes the separator is a comma
   StringBuilder buff = new StringBuilder();
   int x = 0;
   int len = line.length();
   boolean instring = false;
   ArrayList<String> res = new ArrayList<String>();
   while (x < len)
   { char chr = line.charAt(x);
     x++;
     if (chr == ',')
     { if (instring) { buff.append(chr); }
       else
       { res.add(buff.toString().trim());
         buff = new StringBuilder();
       }
     }
     else if ('"' == chr)
     { if (instring) { instring = false; }
       else { instring = true; }
     }
     else
     { buff.append(chr); }
   }
   res.add(buff.toString().trim());
   return res;
 }

 public static List<String> parseCSVtable(String rows)
 { StringBuilder buff = new StringBuilder();
   int x = 0;
   int len = rows.length();
   ArrayList<String> res = new ArrayList<String>();
   while (x < len)
   { char chr = rows.charAt(x);
     x++;
     if (chr == '\n')
     { res.add(buff.toString().trim());
       buff = new StringBuilder();
     }
     else
     { buff.append(chr); }
   }
   res.add(buff.toString().trim());
   return res;
 }
}
