package model;

import java.util.Comparator;


public class AdsByDateComparator implements Comparator<Advertisement> {


    @Override
    public int compare(Advertisement ad1, Advertisement ad2) {
        return ad2.getDate().compareTo(ad1.getDate());
    }
}

