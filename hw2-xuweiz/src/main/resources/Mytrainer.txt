import com.aliasi.chunk.CharLmHmmChunker;

import com.aliasi.hmm.HmmCharLmEstimator;

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;

import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Streams;

import java.io.File;
import java.io.IOException;

public class Mytrainer {

    static final int MAX_N_GRAM = 8;
    static final int NUM_CHARS = 256;
    static final double LM_INTERPOLATION = MAX_N_GRAM; // default behavior

   /**
    * Produce dictionary used in Annotator.
    * @param inputfile File directory point to the training file.
    * @param outputfile Directory which dictionary will be produced.
    * @throws IOException
    */
    @SuppressWarnings("deprecation")
    public void training(File inputfile, File outputfile) throws IOException{
      File corpusFile = inputfile;
      File modelFile = outputfile;

      System.out.println("Setting up Chunker Estimator");
      TokenizerFactory factory
          = IndoEuropeanTokenizerFactory.FACTORY;
      HmmCharLmEstimator hmmEstimator
          = new HmmCharLmEstimator(MAX_N_GRAM,NUM_CHARS,LM_INTERPOLATION);
      CharLmHmmChunker chunkerEstimator
          = new CharLmHmmChunker(factory,hmmEstimator);

      System.out.println("Setting up Data Parser");
      @SuppressWarnings("deprecation")
      com.aliasi.corpus.parsers.GeneTagParser parser 
          = new com.aliasi.corpus.parsers.GeneTagParser();  // PLEASE IGNORE DEPRECATION WARNING
      parser.setHandler(chunkerEstimator);

      System.out.println("Training with Data from File=" + corpusFile);
      parser.parse(corpusFile);

      System.out.println("Compiling and Writing Model to File=" + modelFile);
      AbstractExternalizable.compileTo(chunkerEstimator,modelFile);
    }
    public static void main(String[] args) throws IOException {
        File input = new File("./src/main/resources/train.tag");
        File output = new File("./src/main/resources/MyDict.dic");
        Mytrainer mt = new Mytrainer();
        mt.training(input, output);
        
    }

}