package com.klc.msc.db;

public class MContact
{
    private long id;
    private String name;
    private String mobile;

    public MContact()
    {
        this.id = 0;
        this.name = "";
        this.mobile = "";
    }

    public MContact(long id, String name, String mobile)
    {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getMobile()
    {
        return mobile;
    }
}
