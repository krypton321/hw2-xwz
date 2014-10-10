package edu.cmu.deiis.annotator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.xml.sax.SAXException;

import edu.cmu.deiis.tools.MyEvaluator;
import edu.cmu.deiis.types.ConsumerType;

/**
 * Description: Output final result in file.
 * 
 * @author Xuwei Zou
 *
 */
public class MyConsumer extends CasConsumer_ImplBase {
  public static final String PARAM_OUTPUTDIR = "OutputDirectory";

  public static final String PARAM_OUTPUTNAME = "OutputFileName";

  private File mOutputDir;

  private String mOutputName;

 //  private static MyEvaluator me = new MyEvaluator();

 //  private int anwsernumber =0;

 //  private int casnumber =0 ;
  /**
   * 
   * Initialize output directory and output file.
   * 
   */
  public void initialize() throws ResourceInitializationException {
    mOutputName = (String) getConfigParameterValue(PARAM_OUTPUTNAME);
    mOutputDir = new File((String) getConfigParameterValue(PARAM_OUTPUTDIR), mOutputName);
    // mOutputDir = new File("","hw2-xuweiz.out");
    // if (!mOutputDir.exists()) {
    // mOutputDir.mkdirs();
    // }
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(mOutputDir);
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
//     try {
//     me.setAnswerText("./src/main/resources/data/GENE.eval");
//     } catch (IOException e) {
//     // TODO Auto-generated catch block
//     e.printStackTrace();
//     }
  }

  /**
   * Receive CAS from AE. Exclude the whitespace offset and output the results to the given
   * directory.
   * 
   * @param aJCas
   *          store final result
   */
  @Override
  public void processCas(CAS aCAS) throws ResourceProcessException {
    // TODO Auto-generated method stub

    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    FSIterator<Annotation> it = jcas.getAnnotationIndex(ConsumerType.type).iterator();
    String sent = jcas.getDocumentText();
  //   casnumber++;

    while (it.hasNext()) {
      ConsumerType mts = (ConsumerType) it.next();
      int start = mts.getBegin();
      int end = mts.getEnd();
      int ws = 0;
      String subs = sent.substring(0, start);
      for (char c : subs.toCharArray()) {
        if (c == ' ') {
          ws++;
        }
      }
      int wss = ws;
      subs = sent.substring(start, end);
      for (char c : subs.toCharArray()) {
        if (c == ' ') {
          ws++;
        }
      }
      ws++;
      start -= wss;
      end -= ws;

      String str = mts.getMark() + "|" + start + " " + end + "|" + mts.getGene();
     //  boolean result = me.judgeAnswer(str);
//       anwsernumber++;
      FileWriter writer = null;
      BufferedWriter bw = null;
      try {
        writer = new FileWriter(mOutputDir, true);
        bw = new BufferedWriter(writer);
        bw.append(str + "\n");

      } catch (IOException e) {
        throw new ResourceProcessException(e);
      } finally {
        try {
          bw.close();
        } catch (Exception ex) {
        }
        try {
          writer.close();
        } catch (Exception ex) {
        }

      }
    }
//     me.setAnswernum(anwsernumber);
//     me.printReport();
//     int cassize = jcas.size()/200;
//     if(casnumber>= cassize){
//     me.setAnswernum(anwsernumber);
//     me.printReport();
//     }
  }

}
