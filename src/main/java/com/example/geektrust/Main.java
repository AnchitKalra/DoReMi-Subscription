package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            Service service = new Service();
            // returns true if there is another line to read
            Date date;
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                String s = sc.nextLine();
                String input[] = s.split(" ");
                if(input[0].equalsIgnoreCase(Constants.START_SUBSCRIPTION)) {
                    service.startSubscription(input[1]);
                } else if (input[0].equalsIgnoreCase(Constants.ADD_SUBSCRIPTION)) {
                    service.addSubscription(input[1], input[2]);
                } else if (input[0].equalsIgnoreCase(Constants.ADD_TOPUP)) {
                    service.addTopUp(input[1], Integer.valueOf(input[2]));
                }
                else if(input[0].equalsIgnoreCase(Constants.PRINT_RENEWAL_DETAILS)) {
                    service.printRenewal();
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
        catch (NoSubscription noSubscription) {
            System.out.println(noSubscription.getMessage());
        }
        catch (DuplicateException duplicateException) {
            System.out.println(duplicateException.getMessage());
        }
        catch (TopUpException topUpException) {
            System.out.println(topUpException.getMessage());
        }
        catch (Exception exception) {

        }

    }
}
