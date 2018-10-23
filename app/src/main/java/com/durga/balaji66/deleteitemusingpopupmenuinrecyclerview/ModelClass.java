package com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview;

import com.google.gson.annotations.SerializedName;

public class ModelClass {

    private String UId,Name,Phone;

    private String CategoryName;

    private String subCategoryName;

    private String setNumber;

    private String Question,Option_A,Option_B,Option_C,Option_D,Answer;

    private String progressCountEASY,EasySetsCount,progressCountINTERMEDIATE,IntermediateSetsCount,progressCountADVANCED,AdvancedSetsCount;

    private String Test_Date,SubCategoryName,percentage;

    public String getSet() {
        return Set;
    }

    //    @JsonProperty(value="SetNumber")
    @SerializedName("SetNumber")
    private String Set;

    public void setmUId(String mUId){
        this.UId = mUId;
    }
    public String getmUId(){
        return  UId;
    }
    public void setmName(String mName){
        this.Name = mName;
    }
    public String getmName(){
        return  Name;
    }
    public void setmMobile(String mMobile){
        this.Phone = mMobile;
    }
    public String getmMobile(){
        return  Phone;
    }


    public String getCategories(){
        return CategoryName;
    }

    public String getSubCategories(){
        return subCategoryName;
    }

    public String getsetNumber(){
        return setNumber;
    }

    public String getQuestion(){
        return Question;
    }

    public String getOption_A() {
        return Option_A;
    }

    public String getOption_B() {
        return Option_B;
    }

    public String getOption_C() {
        return Option_C;
    }

    public String getOption_D() {
        return Option_D;
    }

    public String getAnswer() {
        return Answer;
    }


    public String getProgressCountEASY(){
        return progressCountEASY;
    }
    public String getEasySetsCount(){
        return EasySetsCount;
    }
    public String getProgressCountINTERMEDIATE(){
        return progressCountINTERMEDIATE;
    }
    public String getIntermediateSetsCount(){
        return IntermediateSetsCount;
    }
    public String getProgressCountADVANCED(){
        return progressCountADVANCED;
    }
    public String getAdvancedSetsCount(){
        return AdvancedSetsCount;
    }

    public String getTest_Date(){
        return Test_Date;
    }

    public String getpercentage(){
        return percentage;
    }

    public String getSubCategoryName(){
        return SubCategoryName;
    }

    public String getCategoryName(){
        return CategoryName;
    }


}
