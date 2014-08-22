/**
 * Copyright (C) 2011-2014 ARM Limited. All rights reserved.
 */

package com.arm.mbed.commons.lwm2m.json;

import com.arm.mbed.commons.lwm2m.LWM2MResourceType;
import com.google.gson.annotations.SerializedName;

/**
 * @author nordav01
 */
public class JsonResource {

    @SerializedName("n")
    private String name;
    
    @SerializedName("sv")
    private String stringValue;
    
    @SerializedName("v")
    private Number numericalValue;
    
    @SerializedName("bv")
    private Boolean booleanValue;
    
    @SerializedName("t")
    private Integer time;
    
    private JsonResource (String name) {
        this.name = name;
    }
    
    public JsonResource (String name, String stringValue) {
        this(name);
        this.stringValue = stringValue;
    }
    
    public JsonResource (String name, Number numericalValue) {
        this(name);
        this.numericalValue = numericalValue;
    }
    
    public JsonResource (String name, Boolean booleanValue) {
        this(name);
        this.booleanValue = booleanValue;
    }

    public String getName() {
        return name;
    }
    
    public String getValue() {
        if (stringValue != null) {
            return stringValue;
        } else if (numericalValue != null) {
            return numericalValue.toString();
        } else if (booleanValue != null) {
            return booleanValue ? "1" : "0";
        }
        return null;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Number getNumericalValue() {
        return numericalValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public Integer getTime() {
        return time;
    }
    
    public void setTime(Integer time) {
        this.time = time;
    }
    
    public LWM2MResourceType getType() {
        if (stringValue != null) {
            return LWM2MResourceType.STRING;
        } else if (numericalValue != null) {
            return numericalValue instanceof Byte ||
                   numericalValue instanceof Short || 
                   numericalValue instanceof Integer || 
                   numericalValue instanceof Long ? LWM2MResourceType.INTEGER : LWM2MResourceType.FLOAT;
        } else if (booleanValue != null) {
            return LWM2MResourceType.BOOLEAN;
        }
        return null;
    }
    
}