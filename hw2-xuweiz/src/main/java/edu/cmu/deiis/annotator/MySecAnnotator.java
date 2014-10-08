package edu.cmu.deiis.annotator;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import abner.Tagger;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

import edu.cmu.deiis.types.AnnotatorType;

public class MySecAnnotator extends JCasAnnotator_ImplBase {
  private static Tagger tg ;
  /**
   * Initialize the LingPipe tool.
   */
  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    tg = new Tagger();
  }
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    String doc = aJCas.getDocumentText();
    String mark = "";
    FSIterator it = aJCas.getAnnotationIndex(AnnotatorType.type).iterator();
    AnnotatorType mts = null;
    if (it.hasNext()) {
      mts = (AnnotatorType) it.next();
      mark = mts.getMark();
    }
    String[][] s =tg.getEntities(doc);
    for(int i = 0;i<s[0].length;i++){
      String Entitys = s[0][i];
      AnnotatorType mys = new AnnotatorType(aJCas);
      mys.setMark(mark);
      mys.setGene(Entitys);
      int start = doc.indexOf(Entitys);
      int end = start + Entitys.length();
      mys.setBegin(start);
      mys.setEnd(end);
      mys.setCasProcessorId("ABNER");
      mys.setConfidence(0);
      if(start == -1){
        
      }
      else{
        
        mys.addToIndexes();
      }
    }

  }
}
