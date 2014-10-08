package edu.cmu.deiis.annotator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.DocumentAnnotation;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import edu.cmu.deiis.types.AnnotatorType;

public class MyReader extends CollectionReader_ImplBase {

  public static final String PARAM_INPUTDIR = "InputDirectory";

  public static final String PARAM_ENCODING = "Encoding";

  //public static final String PARAM_SUBDIR = "BrowseSubdirectories";


  private String mEncoding;


  //private Boolean mRecursive;

  private int mCurrentIndex;


  private String[] line;
/**
 *  Initializing reader, load the parameter "InputDirectory" from linked Reader Descriptor  as the input directory.
 *  Read the document from the  directory. Separator the document into sentences. 
 *  Store the sentences in a String array;
 */
  public void initialize() throws ResourceInitializationException {
    File directory = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
    mEncoding = (String) getConfigParameterValue(PARAM_ENCODING);
    mCurrentIndex = 0;


    try {
      String text = FileUtils.file2String(directory, mEncoding);
      line = text.split("\n");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

/**
 * Retrieve sentences from String array produced in initialize phase.
 * Separate ID and text. Put the ID into typesystem. Put the text into documenttext in CAS.
 */
  @Override
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }
  //  for(;mCurrentIndex<line.length;mCurrentIndex++){
      String s=line[mCurrentIndex++];
      String[] s1 = s.split(" ", 2);
      String mark =s1[0];
      String sentence = s1[1];
      jcas.setDocumentText(sentence);
      AnnotatorType mts = new AnnotatorType(jcas);
      mts.setMark(mark);
      
      mts.addToIndexes();

  }
/**
 * Check if all the sentences provided in initialize have been put into CAS;
 */
  @Override
  public boolean hasNext() throws IOException, CollectionException {
    // TODO Auto-generated method stub
    return mCurrentIndex < line.length;
  }

  @Override
  public Progress[] getProgress() {
    // TODO Auto-generated method stub
    return new Progress[] { new ProgressImpl(mCurrentIndex, line.length, Progress.ENTITIES) };
  }



  @Override
  public void close() throws IOException {
    // TODO Auto-generated method stub

  }

}
