package com.example.dietpro;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

class OclType {
  static
  { OclType.oclTypeIndex = new HashMap<String,OclType>();
    OclType stringType = OclType.createByPKOclType("String");
    stringType.actualMetatype = String.class;
    OclType intType = OclType.createByPKOclType("int");
    intType.actualMetatype = int.class;
    OclType longType = OclType.createByPKOclType("long");
    longType.actualMetatype = long.class;
    OclType doubleType = OclType.createByPKOclType("double");
    doubleType.actualMetatype = double.class;
    OclType booleanType = OclType.createByPKOclType("boolean");
    booleanType.actualMetatype = boolean.class;
    OclType voidType = OclType.createByPKOclType("void");
    voidType.actualMetatype = void.class;
  }

  public Class actualMetatype = null;

  OclType()     { 
    	//catch
    }

  OclType(String nme)
  { name = nme;
    OclType.createByPKOclType(nme);
  }

  OclType(Class c)
  { this(c.getName());
    actualMetatype = c;
  }


  static OclType createOclType()
  { OclType result = new OclType();
    return result;
  }

  String name = ""; /* primary */
  static Map<String,OclType> oclTypeIndex;

  static OclType createByPKOclType(String namex)
  { OclType result = OclType.oclTypeIndex.get(namex);
    if (result != null)
    { return result; }
    result = new OclType();
    OclType.oclTypeIndex.put(namex,result);
    result.name = namex;
    return result; }

  static void killOclType(String namex)
  { OclType rem = oclTypeIndex.get(namex);
    if (rem == null) { return; }
    ArrayList<OclType> remd = new ArrayList<OclType>();
    remd.add(rem);
    oclTypeIndex.remove(namex);
  }

  ArrayList<OclAttribute> attributes = new ArrayList<OclAttribute>();
  ArrayList<OclOperation> operations = new ArrayList<OclOperation>();
  ArrayList<OclOperation> constructors = new ArrayList<OclOperation>();
  ArrayList<OclType> innerClasses = new ArrayList<OclType>();
  ArrayList<OclType> componentType = new ArrayList<OclType>();
  ArrayList<OclType> superclasses = new ArrayList<OclType>();

  public void setMetatype(Class cls)
  { actualMetatype = cls; }

  public String getName()
  { return name; }


  public ArrayList<OclType> getClasses()
  { return innerClasses; }


  public ArrayList<OclType> getDeclaredClasses()
  {
    ArrayList<OclType> result = new ArrayList<OclType>();
    result = Ocl.subtract(innerClasses,Ocl.unionAll(Ocl.collectSequence(superclasses,(sc)->{return sc.getClasses();})));
    return result;
  }


  public OclType getComponentType()
  {
    OclType result = null;
	if (!componentType.isEmpty())
    {
      result = Ocl.any(componentType);
    }
    return result;
  }


  public ArrayList<OclAttribute> getFields()
  { if (actualMetatype != null)
    { attributes.clear();
      Field[] flds = actualMetatype.getFields();
      for (int i = 0; i < flds.length; i++)
      { OclAttribute att = new OclAttribute(flds[i].getName());
        att.setType(new OclType(flds[i].getType()));
        attributes.add(att);
      }
    }
    return attributes;
  }


  public OclAttribute getDeclaredField(String s)
  { attributes = getFields();
    OclAttribute result = null;
    result = Ocl.any(Ocl.selectSequence(attributes,(att)->{return att.name.equals(s);}));
    return result;
  }


  public OclAttribute getField(String s)
  { attributes = getFields();
    OclAttribute result = null;
    result = Ocl.any(Ocl.selectSequence(attributes,(att)->{return att.name.equals(s);}));
    return result;
  }


  public ArrayList<OclAttribute> getDeclaredFields()
  {
    ArrayList<OclAttribute> result = new ArrayList<OclAttribute>();
    if (actualMetatype != null)
    { Field[] flds = actualMetatype.getDeclaredFields();
      for (int i = 0; i < flds.length; i++)
      { OclAttribute att = new OclAttribute(flds[i].getName());
        att.setType(new OclType(flds[i].getType()));
        result.add(att);
      }
    }
    return result;
  }


  public ArrayList<OclOperation> getMethods()
  { if (actualMetatype != null)
    { operations.clear();
      Method[] mets = actualMetatype.getMethods();
      for (int i = 0; i < mets.length; i++)
      { OclOperation op = new OclOperation(mets[i].getName());
        if (mets[i].getReturnType() != null)
        { op.setType(new OclType(mets[i].getReturnType())); }
        operations.add(op);
      }
    }
    return operations;
  }


  public ArrayList<OclOperation> getDeclaredMethods()
  {
    ArrayList<OclOperation> result = new ArrayList<OclOperation>();
    if (actualMetatype != null)
    { Method[] mets = actualMetatype.getDeclaredMethods();
      for (int i = 0; i < mets.length; i++)
      { OclOperation op = new OclOperation(mets[i].getName());
        if (mets[i].getReturnType() != null)
        { op.setType(new OclType(mets[i].getReturnType())); }
        result.add(op);
      }
    }
    return result;
  }


  public ArrayList<OclOperation> getConstructors()
  {
    ArrayList<OclOperation> result = new ArrayList<OclOperation>();
    if (actualMetatype != null)
    { Constructor[] mets = actualMetatype.getDeclaredConstructors();
      for (int i = 0; i < mets.length; i++)
      { OclOperation op = new OclOperation(mets[i].getName());
        result.add(op);
      }
    }
    return result;
  }


  public OclType getSuperclass()
  { if (actualMetatype != null)
    { Class superclass = actualMetatype.getSuperclass();
      if (superclass != null)
      { superclasses.clear();
        superclasses.add(new OclType(superclass));
      }
    }
    OclType result = null;
    if (!superclasses.isEmpty())
    { result = Ocl.any(superclasses); }
    return result;
  }

}
