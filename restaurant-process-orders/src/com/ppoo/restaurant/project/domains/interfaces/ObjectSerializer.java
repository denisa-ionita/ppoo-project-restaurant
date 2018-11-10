package com.ppoo.restaurant.project.domains.interfaces;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface ObjectSerializer {

    public void objectSerialier(DataOutputStream dos)
            throws IOException;

    public void objectDeserialier(DataInputStream dis)
            throws IOException;
}
