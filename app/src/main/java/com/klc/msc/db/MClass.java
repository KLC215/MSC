package com.klc.msc.db;

import android.os.Parcel;
import android.os.Parcelable;

public class MClass implements Parcelable
{
    private long   id;
    private String name;
    private int    price;
    private String description;
    private String location;
    private int    lessonNo;
    private int    hours;
    private int    maxStudentNo;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private int    status;
    private String createdAt;
    private String updatedAt;

    public MClass()
    {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.description = "";
        this.location = "";
        this.status = 0;
        this.lessonNo = 0;
        this.hours = 0;
        this.maxStudentNo = 0;
        this.startDate = "";
        this.endDate = "";
        this.startTime = "";
        this.endTime = "";
        this.createdAt = "";
        this.updatedAt = "";
    }

    public MClass(long id, String name, int price, String description, String location, int lessonNo,
                  int hours, int maxStudentNo, String startDate, String endDate, String startTime,
                  String endTime, int status, String createdAt, String updatedAt)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.location = location;
        this.lessonNo = lessonNo;
        this.hours = hours;
        this.maxStudentNo = maxStudentNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected MClass(Parcel in)
    {
        id = in.readLong();
        name = in.readString();
        price = in.readInt();
        description = in.readString();
        location = in.readString();
        lessonNo = in.readInt();
        hours = in.readInt();
        maxStudentNo = in.readInt();
        startDate = in.readString();
        endDate = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        status = in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<MClass> CREATOR = new Creator<MClass>()
    {
        @Override
        public MClass createFromParcel(Parcel in)
        {
            return new MClass(in);
        }

        @Override
        public MClass[] newArray(int size)
        {
            return new MClass[size];
        }
    };

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }

    public String getDescription()
    {
        return description;
    }

    public String getLocation()
    {
        return location;
    }

    public int getStatus()
    {
        return status;
    }

    public int getLessonNo()
    {
        return lessonNo;
    }

    public int getHours()
    {
        return hours;
    }

    public int getMaxStudentNo()
    {
        return maxStudentNo;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeInt(lessonNo);
        dest.writeInt(hours);
        dest.writeInt(maxStudentNo);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeInt(status);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
}
