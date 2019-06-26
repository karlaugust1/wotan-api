package br.com.wotan.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 
/*
 * ATTENTION: SQL file must not contain column names, etc. including comment signs (#, --, /* etc.)
 *          like e.g. a.'#rows' etc. because every characters after # or -- in a line are filtered 
 *          out of the query string the same is true for every characters surrounded by /* and */
/**/
 
public class SQLReader {      
    /*
     * @param   path    Path to the SQL file
     * @return          List of query strings 
     */
	public static String from(String path) { 
        String queryLine =      new String();
        StringBuffer sBuffer =  new StringBuffer();
         
        try {
        	
//        	ClassLoader classLoader = new SQLReader().getClass().getClassLoader();
        	
        	InputStream inputStream = 
        			new SQLReader().getClass().getClassLoader().getResourceAsStream(path);
        		   BufferedReader br = new BufferedReader(new InputStreamReader(inputStream ));
        	
/*    		File file = new File(classLoader.getResource(path).getFile());
            FileReader fr =     new FileReader(file);       
            BufferedReader br = new BufferedReader(fr);*/  
       
            //read the SQL file line by line
            while((queryLine = br.readLine()) != null)  
            {  
                // ignore comments beginning with #
                int indexOfCommentSign = queryLine.indexOf('#');
                if(indexOfCommentSign != -1)
                {
                    if(queryLine.startsWith("#"))
                    {
                        queryLine = new String("");
                    }
                    else
                        queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));
                }
                // ignore comments beginning with --
                indexOfCommentSign = queryLine.indexOf("--");
                if(indexOfCommentSign != -1)
                {
                    if(queryLine.startsWith("--"))
                    {
                        queryLine = new String("");
                    }
                    else
                        queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));
                }
                // ignore comments surrounded by /* */
                indexOfCommentSign = queryLine.indexOf("/*");
                if(indexOfCommentSign != -1)
                {
                    if(queryLine.startsWith("#"))
                    {
                        queryLine = new String("");
                    }
                    else
                        queryLine = new String(queryLine.substring(0, indexOfCommentSign-1));
                     
                    sBuffer.append(queryLine + " "); 
                    // ignore all characters within the comment
                    do
                    {
                        queryLine = br.readLine();
                    }
                    while(queryLine != null && !queryLine.contains("*/"));
                    indexOfCommentSign = queryLine.indexOf("*/");
                    if(indexOfCommentSign != -1)
                    {
                        if(queryLine.endsWith("*/"))
                        {
                            queryLine = new String("");
                        }
                        else
                            queryLine = new String(queryLine.substring(indexOfCommentSign+2, queryLine.length()-1));
                    }
                }
                 
                //  the + " " is necessary, because otherwise the content before and after a line break are concatenated
                // like e.g. a.xyz FROM becomes a.xyzFROM otherwise and can not be executed 
                if(queryLine != null)
                    sBuffer.append(queryLine + " ");  
            }  
            br.close();
             
            // here is our splitter ! We use ";" as a delimiter for each request 
            String[] splittedQueries = sBuffer.toString().split(";");
             
            // filter out empty statements
            for(int i = 0; i<splittedQueries.length; i++){
                if(!splittedQueries[i].trim().equals("") && !splittedQueries[i].trim().equals("\t")) {
                    return new String(splittedQueries[i]);
                }
            }
        }  
        catch(Exception e) {  
            e.printStackTrace();  
        }
		return null;
    } 
}