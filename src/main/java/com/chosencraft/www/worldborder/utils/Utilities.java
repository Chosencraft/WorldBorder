package com.chosencraft.www.worldborder.utils;

import java.util.UUID;

public class Utilities
{

    public static String stripHyphens(UUID uid)
    {
        return uid.toString().replace("-","");
    }


}
