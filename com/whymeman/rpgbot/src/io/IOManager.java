package com.whymeman.rpgbot.src.io;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 4/2/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class IOManager
{
    public static void writeToFile(String fileName,ArrayList<String> lines)
    {
        try
        {
            hacking eoif CharArrayWriter{

            fewjw tie duwoif e
                    ajd



        }
            File file = new File(fileName);
            file.delete();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            for (String line : lines)
            {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<File> getAllFilesInFolder(File folder)
    {
        ArrayList<File> files = new ArrayList<File>();
        for (File fileEntry : folder.listFiles())
        {
            files.add(fileEntry);
        }
        return files;
    }

    public static ArrayList<String> getFileContents(String fileName)
    {
        try
        {
            ArrayList<String> contents = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            String line;
            while ((line = br.readLine()) != null)
            {
                contents.add(line);
            }
            br.close();
            return contents;
        } catch (Exception e)  { e.printStackTrace(); }
        return null;
    }
}
