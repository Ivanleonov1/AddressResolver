package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String addressFilePath = "config/AS_ADDR_OBJ.xml";
        String hierarchyFilePath = "config/AS_ADM_HIERARCHY.xml";

        try {
            AddressResolver addressResolver = new AddressResolver(addressFilePath, hierarchyFilePath);

            // Задача № 1
            String date = "2010-01-01";
            List<Integer> objectIds = Arrays.asList(1422396, 1450759, 1449192, 1451562);
            List<String> addressDescriptions = addressResolver.getAddressDescriptions(date, objectIds);
            for (String description : addressDescriptions) {
                System.out.println(description);
            }

            // Задача № 2
            List<String> addressesWithPassageType = addressResolver.getAddressesWithPassageType();
            for (String address : addressesWithPassageType) {
                System.out.println(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}