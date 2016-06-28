package net.intellectsoft.sample;

import org.parceler.Parcel;


@Parcel
public class MockDataObject {

    public String name;

    public MockDataObject() {
    }

    public MockDataObject(String name) {
        this.name = name;
    }
}
