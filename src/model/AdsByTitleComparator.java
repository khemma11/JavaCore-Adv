package model;

import java.util.Comparator;

public class AdsByTitleComparator implements Comparator<Advertisement> {
    @Override
    public int compare(Advertisement ad1, Advertisement ad2) {
        return ad1.getTitle().compareTo(ad2.getTitle());
    }
}
