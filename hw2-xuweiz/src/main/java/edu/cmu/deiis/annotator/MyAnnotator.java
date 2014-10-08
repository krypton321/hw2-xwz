package edu.cmu.deiis.annotator;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;

import edu.cmu.deiis.types.AnnotatorType;

public class MyAnnotator extends JCasAnnotator_ImplBase {

  public static final String PARAM_DICTDIR = "DictDirectory";

  static ConfidenceChunker mchunker = null;

  String mDictPath;

  /**
   * Initialize the LingPipe tool.
   */
  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
   // File modelFile = new File("./src/main/resources", "MyDict.dic");
    try {
     // mchunker = (ConfidenceChunker) AbstractExternalizable.readObject(modelFile);
//      String s = (String) aContext.getConfigParameterValue("LingPipeModel");
      mchunker = (ConfidenceChunker) AbstractExternalizable.readResourceObject(MyAnnotator.class, (String) aContext.getConfigParameterValue("LingPipeModel"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Process the CAS provided by the Reader. Analysis tool will load dictionary from
   * project/src/main/resources/MyDict.dic. Sentences will be analyze by tool and process into
   * phrase and single word. Related information will be put into TypeSystem and be pushed back into
   * CAS.
   */
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // TODO Auto-generated method stub
    String doc = aJCas.getDocumentText();
    String mark = "";
    FSIterator it = aJCas.getAnnotationIndex(AnnotatorType.type).iterator();
    AnnotatorType mts = null;
    if (it.hasNext()) {
      mts = (AnnotatorType) it.next();
      mark = mts.getMark();
    }

    Iterator<Chunk> chunkit = mchunker.nBestChunks(doc.toCharArray(), 0, doc.length(), 20);
    while (chunkit.hasNext()) {
      Chunk chunk = chunkit.next();
      double conf = Math.pow(2.0, chunk.score());
      if (conf > 0.4) {
        AnnotatorType mys = new AnnotatorType(aJCas);
        mys.setBegin(chunk.start());
        mys.setEnd(chunk.end());
        mys.setMark(mark);
        mys.setGene(doc.substring(chunk.start(), chunk.end()));
        mys.setConfidence(conf);
        mys.setCasProcessorId("LingPipe");
        mys.addToIndexes(aJCas);
      } else
        break;
    }

  }

}
