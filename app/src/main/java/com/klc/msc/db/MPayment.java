package com.klc.msc.db;

import android.os.Parcel;
import android.os.Parcelable;

public class MPayment implements Parcelable
{
    private long   id;
    private int    amount;
    private String deadline;
    private String receivedAt;
    private String createdAt;
    private String updatedAt;

    public MPayment()
    {
        this.id = 0;
        this.amount = 0;
        this.deadline = "";
        this.receivedAt = "";
        this.createdAt = "";
        this.updatedAt = "";
    }

    public MPayment(long id, int amount, String deadline, String receivedAt, String createdAt, String updatedAt)
    {
        this.id = id;
        this.amount = amount;
        this.deadline = deadline;
        this.receivedAt = receivedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected MPayment(Parcel in)
    {
        id = in.readLong();
        amount = in.readInt();
        deadline = in.readString();
        receivedAt = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<MPayment> CREATOR = new Creator<MPayment>()
    {
        @Override
        public MPayment createFromParcel(Parcel in)
        {
            return new MPayment(in);
        }

        @Override
        public MPayment[] newArray(int size)
        {
            return new MPayment[size];
        }
    };

    public long getId()
    {
        return id;
    }

    public int getAmount()
    {
        return amount;
    }

    public String getDeadline()
    {
        return deadline;
    }

    public String getReceivedAt()
    {
        return receivedAt;
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
        dest.writeInt(amount);
        dest.writeString(deadline);
        dest.writeString(receivedAt);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
}
