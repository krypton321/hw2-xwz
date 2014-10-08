package edu.cmu.deiis.types;

/* First created by JCasGen Tue Oct 07 15:46:33 EDT 2014 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Tue Oct 07 15:46:33 EDT 2014
 * @generated */
public class AnnotatorType_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AnnotatorType_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AnnotatorType_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AnnotatorType(addr, AnnotatorType_Type.this);
  			   AnnotatorType_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AnnotatorType(addr, AnnotatorType_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AnnotatorType.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("AnnotatorType");
 
  /** @generated */
  final Feature casFeat_Mark;
  /** @generated */
  final int     casFeatCode_Mark;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getMark(int addr) {
        if (featOkTst && casFeat_Mark == null)
      jcas.throwFeatMissing("Mark", "AnnotatorType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Mark);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMark(int addr, String v) {
        if (featOkTst && casFeat_Mark == null)
      jcas.throwFeatMissing("Mark", "AnnotatorType");
    ll_cas.ll_setStringValue(addr, casFeatCode_Mark, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Gene;
  /** @generated */
  final int     casFeatCode_Gene;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getGene(int addr) {
        if (featOkTst && casFeat_Gene == null)
      jcas.throwFeatMissing("Gene", "AnnotatorType");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Gene);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGene(int addr, String v) {
        if (featOkTst && casFeat_Gene == null)
      jcas.throwFeatMissing("Gene", "AnnotatorType");
    ll_cas.ll_setStringValue(addr, casFeatCode_Gene, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public AnnotatorType_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Mark = jcas.getRequiredFeatureDE(casType, "Mark", "uima.cas.String", featOkTst);
    casFeatCode_Mark  = (null == casFeat_Mark) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Mark).getCode();

 
    casFeat_Gene = jcas.getRequiredFeatureDE(casType, "Gene", "uima.cas.String", featOkTst);
    casFeatCode_Gene  = (null == casFeat_Gene) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Gene).getCode();

  }
}



    