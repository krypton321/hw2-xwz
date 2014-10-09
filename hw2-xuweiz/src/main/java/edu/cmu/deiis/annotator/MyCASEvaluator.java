package edu.cmu.deiis.annotator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.deiis.types.AnnotatorType;
import edu.cmu.deiis.types.ConsumerType;

public class MyCASEvaluator extends JCasAnnotator_ImplBase {
  // HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
  static int num = 0;;

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    HashMap<Integer, Integer> Bmap = new HashMap<Integer, Integer>();
    ArrayList<AnnotatorType> Llist = new ArrayList<AnnotatorType>();
    String doc = aJCas.getDocumentText();
    FSIterator it = aJCas.getAnnotationIndex(AnnotatorType.type).iterator();
    // AnnotatorType mts = null;
    while (it.hasNext()) {
      AnnotatorType mts = (AnnotatorType) it.next();
      int start = mts.getBegin();
      int end = mts.getEnd();
      String processid = mts.getCasProcessorId();
      // Point p = new Point(start,end);
      if (processid == "ABNER") {
        Bmap.put(start, end);
      }
      if ("LingPipe".equals(processid)) {
        Llist.add(mts);
      }
    }
    for (int i = 0; i < Llist.size(); i++) {

      AnnotatorType mts = Llist.get(i);
      int start = mts.getBegin();
      int end = mts.getEnd();
      double conf = mts.getConfidence();
      String processid = mts.getCasProcessorId();

      if (conf > 0.8) {
        if (regexcheck(mts.getGene()))
          addIndex(aJCas, mts);
      } else if (conf > 0.6 && conf <= 0.8) {
        if (Bmap.containsKey(start) || Bmap.containsValue(end)) {
          if (regexcheck(mts.getGene()))
            addIndex(aJCas, mts);
        }
      } else {
        if (Bmap.containsKey(start)) {
          if (end == Bmap.get(start)) {
            if (regexcheck(mts.getGene()))
              addIndex(aJCas, mts);
          }
        }

      }
    }

  }

  public boolean regexcheck(String str) {
    char[] c = str.toCharArray();
    Stack s = new Stack<Integer>();
    for (int i = 0; i < c.length; i++) {
      if (c[i] == '(') {
        s.push(1);
      } else if (c[i] == ')') {
        if (s.isEmpty()) {
          return false;
        } else {
          s.pop();
        }
      }

    }
    if (s.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public void addIndex(JCas aJCas, AnnotatorType a) {
    ConsumerType mys = new ConsumerType(aJCas);
    mys.setBegin(a.getBegin());
    mys.setEnd(a.getEnd());
    mys.setMark(a.getMark());
    mys.setGene(a.getGene());
    mys.addToIndexes(aJCas);
  }
}
