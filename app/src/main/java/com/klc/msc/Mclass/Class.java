package com.klc.msc.Mclass;

public class Class
{
    private long id;
    private String className;
    private String classDescription;

    public Class(long id, String className, String classDescription)
    {
        this.id = id;
        this.className = className;
        this.classDescription = classDescription;
    }

    public long getId()
    {
        return id;
    }

    public String getClassName()
    {
        return className;
    }

    public String getClassDescription()
    {
        return classDescription;
    }

}
