package com.klc.msc.db;

public class Class
{
    private int id;
    private String name;
    private int price;
    private String description;
    private String location;
    private int status;
    private int lessonNo;
    private int weekNo;
    private int maxStudentNo;
    private String startDate;
    private String endDate;
    private int startTime;
    private int endTime;
    private String createdAt;
    private String updatedAt;

    public Class()
    {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.description = "";
        this.location = "";
        this.status = 0;
        this.lessonNo = 0;
        this.weekNo = 0;
        this.maxStudentNo = 0;
        this.startDate = "";
        this.endDate = "";
        this.startTime = 0;
        this.endTime = 0;
        this.createdAt = "";
        this.updatedAt = "";
    }

    public Class(String name, int price, String description, String location, int lessonNo,
                 int weekNo, int maxStudentNo, String startDate, String endDate, int startTime,
                 int endTime)
    {
        this.name = name;
        this.price = price;
        this.description = description;
        this.location = location;
        this.lessonNo = lessonNo;
        this.weekNo = weekNo;
        this.maxStudentNo = maxStudentNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
