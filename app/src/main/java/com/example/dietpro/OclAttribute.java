package com.example.dietpro;

class OclAttribute {

  OclAttribute()     { 
    	//catch
    }

  OclAttribute(String nme)
  { name = nme; }

  static OclAttribute createOclAttribute()
  { 
  	return new OclAttribute();
  }

  public void setType(OclType t)
  { type = t; }

  String name = "";
  OclType type = null;

  public String getName()
  { return name; }

  public OclType getType()
  { return type; }

}


