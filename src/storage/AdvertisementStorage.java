package storage;

import exception.ModelNotFoundException;
import model.*;

import java.util.ArrayList;
import java.util.List;


public class AdvertisementStorage {
    private List<Advertisement> ads;

    public AdvertisementStorage(int length) {
        ads = new ArrayList<>(length);
    }

    public AdvertisementStorage() {
        ads = new ArrayList<>();
    }

    public void addAd(Advertisement ad) {
        ads.add(ad);
    }

    public Advertisement getAdByTitle(String title) throws ModelNotFoundException {
        for (int i = 0; i < ads.size(); i++) {
            if (ads.get(i).equals(title)) {
                return ads.get(i);
            }
        }
        throw new ModelNotFoundException(String.format("Advertisement with %s title does not exist", title));

    }

    public void printMyAllAds(User user) {
        for (Advertisement ad : ads) {
            if (ad.getAuthor().equals(user)) {
                System.out.println(ad);
            }
        }
    }

    public void PrintAdByCategory(Category category) {
        for (int i = 0; i < ads.size(); i++) {
            if (ads.get(i).getCategory().equals(category)) {
                System.out.println(ads.get(i));
            }

        }
    }

    public void printAllAds() {
        System.out.println("-------------------------");
        for (Advertisement adv : ads) {
            System.out.println("Category : " + adv.getCategory());
            System.out.println("Title: " + adv.getTitle());
            System.out.println(adv.getText());
            System.out.println("Price: " + adv.getPrice());
            System.out.println("Author: " + adv.getAuthor());
            System.out.println("Posted: " + adv.getDate());
            System.out.println("by: " + adv.getUser().getName() + " " + adv.getUser().getSurName());
            System.out.println("---------------------------");

        }
        System.out.println("-------------------------");
    }

    public void deleteMyAllAds(User author) throws ModelNotFoundException {
        ads.removeIf(adv -> adv.getAuthor().equals(author));
        throw new ModelNotFoundException(String.format("Advertisement with %s author does not exist", author));
    }


    public void deleteAdByTitle(String title) throws ModelNotFoundException {
        ads.removeIf(adv -> adv.getTitle().equals(title));
        throw new ModelNotFoundException(String.format("Advertisement with %s title does not exist", title));
    }

    public void sortAllAdsByTitle() {
        AdsByTitleComparator sortTitle = new AdsByTitleComparator();
        ads.sort(sortTitle);
        for (Advertisement ad : ads) {
            System.out.println(ad);
        }

    }

    public void sortAllAdsByDate() {
        AdsByDateComparator sortDate = new AdsByDateComparator();
        ads.sort(sortDate);
        for (Advertisement ad : ads) {
            System.out.println(ad);
        }
    }

    public boolean isEmpty() {
        return ads.size() == 0;
    }

}