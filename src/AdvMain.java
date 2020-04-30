import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import command.AdvCommands;
import exception.ModelNotFoundException;
import model.Advertisement;
import model.Category;
import model.Gender;
import model.User;
import storage.AdvertisementStorage;
import storage.UserStorage;

import java.util.Date;
import java.util.Scanner;

public class AdvMain implements AdvCommands {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final AdvertisementStorage AD_STORAGE = new AdvertisementStorage();
    public static final UserStorage USER_STORAGE = new UserStorage();
    public static User user;

    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {

            AdvCommands.printMainCommands();
            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
                command = -1;
            }

            switch (command) {
                case EXIT:
                    isRun = false;
                    System.out.println("Good luck");
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;

                default:
                    System.out.println("Wrong command");

            }
        }

    }

    private static void register() {
        System.out.println("Please input your name, surname, gender, age, phonNumber, password");
        String userStr = SCANNER.nextLine();
        String[] userData = userStr.split(",");
        try {
            User user = new User();
            user.setName(userData[0]);
            user.setSurName(userData[1]);
            user.setGender(Gender.valueOf(userData[2].toUpperCase()));
            user.setAge(Integer.parseInt(userData[3]));
            user.setPhonNumber(userData[4]);
            user.setPassword(userData[5]);
            USER_STORAGE.addUser(user);
            try {
                USER_STORAGE.getUserByPhonNumber(userData[4]);
                System.out.println("PhonNumber already exist");
            } catch (ModelNotFoundException e) {
                USER_STORAGE.addUser(user);
                System.out.println("Thank you ");
            }
        } catch (Exception e) {
            System.out.println("Please input valid data");
            register();

        }
    }

    private static void login() {
        try {
            if (USER_STORAGE.isEmpti()) {
                System.out.println("Please register first");
            } else {
                System.out.println("Please input phonNumber and password");
                String userStr = SCANNER.nextLine();
                String[] userData = userStr.split(",");

                try {
                    user = USER_STORAGE.getUserByPhonNumberAndByPassword(userData[0], userData[1]);
                    System.out.println("You entered your profile");
                    loginUserCommands();

                } catch (ModelNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            login();
        }

    }

    private static void loginUserCommands() {

        boolean isRun = true;
        while (isRun) {
            AdvCommands.printADCommands();

            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
                command = -1;
            }

            switch (command) {
                case LOGOUT:
                    isRun = false;
                    System.out.println("Good luck");
                    break;
                case ADD_NEW_AD:
                    addNewAd();
                    break;
                case PRINT_MY_ALL_ADS:
                    printMyAllAds();
                    break;
                case PRINT_ALL_ADS:
                    printAllAds();
                    break;
                case PRINT_AD_BY_CATEGORY:
                    printAdByCategory();
                    break;
                case PRINT_ALL_ADS_BY_TITLE_SORT:
                    AD_STORAGE.sortAllAdsByTitle();
                    break;
                case PRINT_ALL_ADS_BY_DATE_SORT:
                    AD_STORAGE.sortAllAdsByDate();
                    break;
                case DELETE_MY_ALL_ADS:
                    deletMyAllAds();
                    break;
                case DELETE_AD_BY_TITLE:
                    deleteAdByTitle();
                    break;
                default:
                    System.out.println("Wrong command");

            }
        }
    }

    private static void printMyAllAds() {
        if (AD_STORAGE.isEmpty()) {
            System.out.println("No announcement added");
        } else {
            AD_STORAGE.printMyAllAds(user);
        }
    }

    private static void printAllAds() {
        if (AD_STORAGE.isEmpty()) {
            System.out.println("No announcement added");
        } else {
            AD_STORAGE.printAllAds();
        }
    }

    private static void deleteAdByTitle() {
        AD_STORAGE.printAllAds();
        System.out.println("Please input title for delete Ads");
        String adTitle = SCANNER.nextLine();
        try {
            AD_STORAGE.deleteAdByTitle(adTitle);
            System.out.println("ad deleted");
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deletMyAllAds() {
        if (AD_STORAGE.isEmpty())
            System.out.println("No announcement added");
        try {
            AD_STORAGE.deleteMyAllAds(user);
            System.out.println("Your all ads deleted");
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printAdByCategory() {
        System.out.println("Piease input category: REALITY,  ELECTRONICS, HOUSEHOLD, JOBS, MATERIALS");
        Category category = Category.valueOf(SCANNER.nextLine().toUpperCase());
        AD_STORAGE.PrintAdByCategory(category);


    }

    private static void addNewAd() {
        try {
            System.out.println("Please input advertisement: category, title, text, price, author ");
            String advStr = SCANNER.nextLine();
            String[] advData = advStr.split(",");

            Advertisement adv = new Advertisement();
            adv.setCategory(Category.valueOf(advData[0].toUpperCase()));
            adv.setTitle(advData[1]);
            adv.setText(advData[2]);
            adv.setPrice(Double.parseDouble(advData[3]));
            adv.setAuthor(advData[4]);

            adv.setDate(new Date());
            adv.setUser(user);
            AD_STORAGE.addAd(adv);
            System.out.println(adv);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Incorrect value! please try again");
            addNewAd();
        }
    }

}
