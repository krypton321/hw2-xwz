package edu.cmu.deiis.types;


/* First created by JCasGen Tue Oct 07 15:46:33 EDT 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;


/** 
 * Updated by JCasGen Tue Oct 07 15:46:33 EDT 2014
 * XML source: /home/krypton/未命名文件夹/workspace/hw2-xuweiz/src/main/resources/deiis_types.xml
 * @generated */
public class AnnotatorType extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AnnotatorType.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AnnotatorType() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public AnnotatorType(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public AnnotatorType(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public AnnotatorType(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Mark

  /** getter for Mark - gets 
   * @generated
   * @return value of the feature 
   */
  public String getMark() {
    if (AnnotatorType_Type.featOkTst && ((AnnotatorType_Type)jcasType).casFeat_Mark == null)
      jcasType.jcas.throwFeatMissing("Mark", "AnnotatorType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnnotatorType_Type)jcasType).casFeatCode_Mark);}
    
  /** setter for Mark - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setMark(String v) {
    if (AnnotatorType_Type.featOkTst && ((AnnotatorType_Type)jcasType).casFeat_Mark == null)
      jcasType.jcas.throwFeatMissing("Mark", "AnnotatorType");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnnotatorType_Type)jcasType).casFeatCode_Mark, v);}    
   
    
  //*--------------*
  //* Feature: Gene

  /** getter for Gene - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGene() {
    if (AnnotatorType_Type.featOkTst && ((AnnotatorType_Type)jcasType).casFeat_Gene == null)
      jcasType.jcas.throwFeatMissing("Gene", "AnnotatorType");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AnnotatorType_Type)jcasType).casFeatCode_Gene);}
    
  /** setter for Gene - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGene(String v) {
    if (AnnotatorType_Type.featOkTst && ((AnnotatorType_Type)jcasType).casFeat_Gene == null)
      jcasType.jcas.throwFeatMissing("Gene", "AnnotatorType");
    jcasType.ll_cas.ll_setStringValue(addr, ((AnnotatorType_Type)jcasType).casFeatCode_Gene, v);}    
  }

    