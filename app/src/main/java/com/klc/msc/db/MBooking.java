package com.klc.msc.db;

import android.os.Parcel;
import android.os.Parcelable;

public class MBooking implements Parcelable
{
    private long   id;
    private long   contactId;
    private long   classId;
    private long   statusId;
    private long   paymentId;
    private String createdAt;
    private String updatedAt;

    public MBooking()
    {
        this.id = 0;
        this.contactId = 0;
        this.classId = 0;
        this.statusId = 0;
        this.paymentId = 0;
        this.createdAt = "";
        this.updatedAt = "";
    }

    public MBooking(long id, long contactId, long classId, long statusId, long paymentId, String createdAt, String updatedAt)
    {
        this.id = id;
        this.contactId = contactId;
        this.classId = classId;
        this.statusId = statusId;
        this.paymentId = paymentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId()
    {
        return id;
    }

    public long getContactId()
    {
        return contactId;
    }

    public long getClassId()
    {
        return classId;
    }

    public long getStatusId()
    {
        return statusId;
    }

    public long getPaymentId()
    {
        return paymentId;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    protected MBooking(Parcel in)
    {
        id = in.readLong();
        contactId = in.readLong();
        classId = in.readLong();
        statusId = in.readLong();
        paymentId = in.readLong();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<MBooking> CREATOR = new Creator<MBooking>()
    {
        @Override
        public MBooking createFromParcel(Parcel in)
        {
            return new MBooking(in);
        }

        @Override
        public MBooking[] newArray(int size)
        {
            return new MBooking[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(id);
        dest.writeLong(contactId);
        dest.writeLong(classId);
        dest.writeLong(statusId);
        dest.writeLong(paymentId);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
}
