package com.example.dietpro;

import java.util.ArrayList;

class OclOperation
{ OclOperation()     { 
    	//catch
    }

  OclOperation(String nme)
  { name = nme; }


  String name = "";
  OclType type = null;
  ArrayList<OclAttribute> parameters = new ArrayList<OclAttribute>();

  public String getName()
  { return name; }

  public void setType(OclType t)
  { type = t; }

  public OclType getType()
  { return type; }


  public OclType getReturnType()
  { return type; }


  public ArrayList<OclAttribute> getParameters()
  { return parameters; }

}

