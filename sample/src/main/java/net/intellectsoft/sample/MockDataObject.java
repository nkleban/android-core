package net.intellectsoft.sample;

import org.parceler.Parcel;

/**
 * Created by Raman Branavitski on 6/17/16.
 */
@Parcel
public class MockDataObject {

    public String name;

    public MockDataObject(){
    }

    public MockDataObject(String name){
        this.name = name;
    }
}
