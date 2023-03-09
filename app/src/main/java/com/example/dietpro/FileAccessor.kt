package com.example.dietpro

import android.content.Context
import java.io.*

class FileAccessor (context: Context) {

    init
    { myContext = context }

    companion object {
        lateinit var myContext: Context

        fun createFile(filename: String)
        { try
        { 
        	//val newFile = 
        	File(myContext.filesDir, filename) }
        catch (e: Exception) { e.printStackTrace() }
        }

        fun readFile(filename: String) : ArrayList<String>
        { val result: ArrayList<String> = ArrayList()
            try {
                val inStrm: InputStream? = myContext.openFileInput(filename)
                if (inStrm != null)
                { val inStrmRdr = InputStreamReader(inStrm)
                    val buffRdr = BufferedReader(inStrmRdr)

                    var fileContent: String
                    while ((buffRdr.readLine().also { fileContent = it }) != null)
                    { result.add(fileContent)}
                    inStrm.close()
                }
            } catch (e: Exception) { e.printStackTrace() }
            return result
        }

        fun writeFile(filename: String, contents: ArrayList<String>)
        { try {
            val outStrm = OutputStreamWriter(myContext.openFileOutput(filename, Context.MODE_PRIVATE))
            try {
                for (item in contents) {
                    outStrm.write(item + "\n")
                }
            }
            catch (ix: IOException) { 
            	ix.printStackTrace()
            }
            outStrm.close()
        }
        catch (e: Exception) { e.printStackTrace() }
        }
    }

}

