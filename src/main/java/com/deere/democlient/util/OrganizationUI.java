package com.deere.democlient.util;

import com.deere.api.axiom.generated.v3.Organization;
import com.deere.democlient.brokers.OrganizationListBroker;

import java.util.List;
import java.util.Scanner;

public class OrganizationUI {
    public Organization getOrganizationFromUser() {
        OrganizationListBroker organizationListBroker = new OrganizationListBroker();
        List<Organization> organizations = organizationListBroker.getOwnedOrganizations();
        return organizations.get(getOrganizationIndex(organizations));
    }

    private int getOrganizationIndex(List<Organization> organizations) {
        if (organizations.size() == 1) {
            return 0;
        } else {
            displayOrganizations(organizations);
            return getUserChoice(organizations.size()) - 1;
        }
    }

    private void displayOrganizations(List<Organization> organizations) {
        for (int i = 0; i < organizations.size(); i++) {
            System.out.println("(" + (i+1) + ") " + organizations.get(i).getName());
        }
    }

    private int getUserChoice(int listSize) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter an integer:");
        while (true) {
            if (!in.hasNextInt()) {
                System.out.println("Please enter an integer related to one of the organizations: ");
                in.nextLine();
            } else {
                int choice = in.nextInt();
                if (choice > listSize || choice < 1) {
                    System.out.println("Please enter an integer related to one of the organizations:");
                    in.nextLine();
                } else {
                    return choice;
                }
            }
        }
    }
}