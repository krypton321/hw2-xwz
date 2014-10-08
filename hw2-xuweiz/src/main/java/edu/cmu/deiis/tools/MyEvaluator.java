package edu.cmu.deiis.tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.uima.util.FileUtils;

public class MyEvaluator {
  private int correct_num = 0;

  private int answer_num = 0;

  private int supposed_num = 0;

  // private String AnswerText = "";
  private HashMap<String, Boolean> map = new HashMap<String, Boolean>();

  public void setAnswerText(String pathname) throws IOException {
    File filename = new File(pathname);
    String[] line = null;
    String text = FileUtils.file2String(filename, "UTF-8");
    line = text.split("\n");
    supposed_num = line.length;
    for (int i = 0; i < line.length; i++) {
      map.put(line[i], false);

    }

    // AnswerText = sb.toString();
  }

  public double getPrecision() {
    return (double) correct_num / answer_num;
  }

  public double getRecall() {
    return (double) correct_num / supposed_num;
  }

  public boolean judgeAnswer(String ans) {
    if (map == null)
      return false;
    if (map.containsKey(ans)) {
      if (!map.get(ans)) {
        correct_num++;
        map.put(ans, true);
        return true;
      }
    }
    return false;
  }

  public void setAnswernum(int num) {
    answer_num = num;
  }

  public double getfScore() {
    double precision = getPrecision();
    double recall = getRecall();
    return 2.0 * precision * recall / (precision + recall);
  }

  public void printReport() {
    System.out.println();
    System.out.println("Correct Num:" + correct_num);
    System.out.println("Total Returned Answer:" + answer_num);
    System.out.println("Supposed Answer Num:" + supposed_num);
    System.out.println("Precision:" + getPrecision());
    System.out.println("Recall:" + getRecall());
    System.out.println("F-socre:" + getfScore());
  }
}
