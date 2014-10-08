package edu.cmu.deiis.tools;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.FileUtils;

public class Tag {
  public String TagString(String s) {
    s = s.replace(" ", "_TAG ");
    s = s.replace(",", "_TAG ,");
    s = s.replace(".", "_TAG .");
    s = s.replace("(", "(_TAG ");
    s = s.replace("[", "[_TAG ");
    s = s.replace(")", "_TAG )");
    s = s.replace("]", "_TAG ]");
    s = s.replace("+", "_TAG +");
    s = s.replace("/", "_TAG /");
    s = s.replace("-", "_TAG -_TAG ");
    s = s.replace(":", "_TAG :_TAG ");
    s = s.replace("%", "_TAG %_TAG ");
    s = s.replace("#", "_TAG #");
    s = s.replace("&", "_TAG &");
    s = s.replace("*", "_TAG *");
    s = s.replace(" _TAG ", " ");
    s += "_TAG";
    return s;
  }

  public void initialize() {
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(new File("./src/", "mf.txt"));
      writer.print("");
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      try {
        writer.close();
      } catch (Exception ex) {
      }
    }
  }

  public void format() throws ResourceProcessException {
    String[] line = null;
    int genetag = 1;
    String fileinput = "";
    initialize();
    try {
      File directory = new File("./src/GENE.eval");
      String text = FileUtils.file2String(directory, "UTF-8");
      line = text.split("\n");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    List l = new ArrayList<String>();
    HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    for (int i = 0; i < line.length; i++) {
      String[] s = line[i].split("[|]", 3);
      String x = s[2];
      if (map.containsKey(s[0])) {
        l = map.get(s[0]);
        String modifiedvalue = TagString(s[2]);

        l.add(modifiedvalue);
        map.put(s[0], (ArrayList<String>) l);
      } else {
        l = new ArrayList<String>();
        String modifiedvalue = TagString(s[2]);
        l.add(modifiedvalue);
        map.put(s[0], (ArrayList<String>) l);
      }
    }

    try {
      File directory = new File("./src/test.in");
      String text = FileUtils.file2String(directory, "UTF-8");
      line = text.split("\n");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    for (int i = 0; i < line.length; i++) {
      String[] s = line[i].split(" ", 2);
      String key = s[0];
      if (map.containsKey(key)) {
        String modifiedline = TagString(s[1]);
        fileinput += key + "\n";
        ArrayList<String> list = map.get(key);
        for (int j = 0; j < list.size(); j++) {
          String tagstring = list.get(j);
          String genestring = tagstring.replace("_TAG", "_GENE" + genetag);
          modifiedline = modifiedline.replace(tagstring, genestring);
          
          if (genetag == 1) {
            genetag = 2;
          } else {
            genetag = 1;
          }
        }
        fileinput += modifiedline + "\n";
        genetag = 1;
      } else {
        fileinput += key + "\n";
        String modifiedline = TagString(s[1]);
        fileinput += modifiedline + "\n";
      }
    }

    FileWriter fwriter = null;
    BufferedWriter bw = null;
    try {
      fwriter = new FileWriter("./src/mf.txt", true);
      bw = new BufferedWriter(fwriter);
      bw.append(fileinput + "\n");

    } catch (IOException e) {
      throw new ResourceProcessException(e);
    } finally {
      try {
        bw.close();
      } catch (Exception ex) {
      }
      try {
        fwriter.close();
      } catch (Exception ex) {
      }
    }

  }

  public static void main(String[] args) throws ResourceProcessException {
    Tag t = new Tag();
    t.format();
    /*
     * FileWriter fwriter = null; BufferedWriter bw = null; try { fwriter = new
     * FileWriter("./src/mf.txt", true); bw = new BufferedWriter(fwriter); bw.append(x + "\n");
     * 
     * } catch (IOException e) { throw new ResourceProcessException(e); } finally { try {
     * bw.close(); } catch (Exception ex) { } try { writer.close(); } catch (Exception ex) { } } }
     */
  }
}
