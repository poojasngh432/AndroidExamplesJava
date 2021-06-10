package com.example.tutorialsproject.mvp1mg;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Keep
public class Test implements Parcelable, Serializable
{
    private int id;
    private String name;
    private String subName;
    private String abbreviation;
    private List<String> precautions;
    private String methodology;
    private String description;
    private String symptoms;
    private List<Map<String, List<Map<String, List<Object>>>>> compositionTree;
    private boolean rxRequired;
    private int compositionCount;
    private String reason;
    private boolean isSelected;
    @SerializedName("available")
    private boolean isAvailable;
    @SerializedName("heading1")
    private String testTime;
    @SerializedName("heading2")
    private String instructions;
    private int leafCount;
    private boolean isFuzzy;
    private String highlightName;
    private String highlightSubName;
    private String highlightAbbreviation;
    private String componentTests;
    private String highlightComponentTests;
    private String tatDateText;
    private String tatText;

    public Test()
    {
        isSelected = true;
    }

    protected Test(Parcel in)
    {
        String testCategoryName = in.readString();

        id = in.readInt();
        name = in.readString();
        subName = in.readString();
        abbreviation = in.readString();
        precautions = in.createStringArrayList();
        methodology = in.readString();
        description = in.readString();
        symptoms = in.readString();
        rxRequired = in.readByte() != 0;
        compositionCount = in.readInt();
        reason = in.readString();
        isSelected = in.readByte() != 0;
        isAvailable = in.readByte() != 0;
        testTime = in.readString();
        instructions = in.readString();
        leafCount = in.readInt();
        isFuzzy = in.readByte() != 0;
        highlightName = in.readString();
        componentTests = in.readString();
        highlightComponentTests = in.readString();
        highlightAbbreviation = in.readString();
        highlightSubName = in.readString();
        tatDateText = in.readString();
        tatText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(subName);
        dest.writeString(abbreviation);
        dest.writeStringList(precautions);
        dest.writeString(methodology);
        dest.writeString(description);
        dest.writeString(symptoms);
        dest.writeByte((byte) (rxRequired ? 1 : 0));
        dest.writeInt(compositionCount);
        dest.writeString(reason);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeByte((byte) (isAvailable ? 1 : 0));
        dest.writeString(testTime);
        dest.writeString(instructions);
        dest.writeInt(leafCount);
        dest.writeByte((byte) (isFuzzy ? 1 : 0));
        dest.writeString(highlightName);
        dest.writeString(componentTests);
        dest.writeString(highlightComponentTests);
        dest.writeString(highlightAbbreviation);
        dest.writeString(highlightSubName);
        dest.writeString(tatDateText);
        dest.writeString(tatText);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<Test> CREATOR = new Creator<Test>()
    {
        @Override
        public Test createFromParcel(Parcel in)
        {
            return new Test(in);
        }

        @Override
        public Test[] newArray(int size)
        {
            return new Test[size];
        }
    };

    public String getTestTime()
    {
        return testTime;
    }

    public String getInstructions()
    {
        return instructions;
    }

    public boolean isSelected()
    {
        return isSelected;
    }

    public void setSelected(boolean selected)
    {
        isSelected = selected;
    }

    public boolean isAvailable()
    {
        return isAvailable;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(@Nullable String name)
    {
        this.name = name;
    }

    public String getSubName()
    {
        return subName;
    }

    public void setSubName(String subName)
    {
        this.subName = subName;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }

    public List<String> getPrecautions()
    {
        return precautions;
    }

    public String getDescription()
    {
        return description;
    }

    public String getSymptoms()
    {
        return symptoms;
    }

    public List<Map<String, List<Map<String, List<Object>>>>> getCompositionTree()
    {
        return compositionTree;
    }

    public int getCompositionCount()
    {
        return compositionCount;
    }

    public String getReason()
    {
        return reason;
    }

    public String getComponentTests()
    {
        return componentTests;
    }

    public String getHighlightComponentTests()
    {
        return highlightComponentTests;
    }

    public String getHighlightAbbreviation()
    {
        return highlightAbbreviation;
    }

    public String getHighlightSubName()
    {
        return highlightSubName;
    }

    @Override
    public boolean equals(Object o)
    {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        return id == test.id;
    }

    @Override
    public int hashCode()
    {
        return id;
    }

    public int getLeafCount()
    {
        return leafCount;
    }

    public boolean isFuzzy()
    {
        return isFuzzy;
    }

    public String getHighlightName()
    {
        return highlightName;
    }

    public String getTatDateText()
    {
        return tatDateText;
    }

    public String getTatText()
    {
        return tatText;
    }
}
