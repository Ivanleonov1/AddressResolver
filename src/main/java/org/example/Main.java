package org.example;

import org.eclipse.persistence.exceptions.JAXBException;
import org.eclipse.persistence.internal.oxm.Unmarshaller;
import org.eclipse.persistence.jaxb.JAXBContext;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import java.io.File;
import java.util.*;

public class AddressResolver {
    private static final String ADDR_OBJ_FILE_PATH = "AS_ADDR_OBJ.xml";
    private static final String ADM_HIERARCHY_FILE_PATH = "AS_ADM_HIERARCHY.xml";

    public static void main(String[] args) {
        Date date = new Date(110, 0, 1); // 2010-01-01
        List<Long> objectIds = List.of(1422396L, 1450759L, 1449192L, 1451562L);

        List<String> descriptions = getAddressDescriptions(date, objectIds);
        for (int i = 0; i < objectIds.size(); i++) {
            System.out.println(objectIds.get(i) + ": " + descriptions.get(i));
        }

        List<String> addresses = getAddressesWithStreet();
        for (String address : addresses) {
            System.out.println(address);
        }
    }

    public static List<String> getAddressDescriptions(Date date, List<Long> objectIds) {
        List<Address> addresses = readAddressesFromFile(ADDR_OBJ_FILE_PATH);
        List<String> descriptions = new ArrayList<>();

        for (Address address : addresses) {
            if (objectIds.contains(address.getObjectId()) &&
                    isDateInRange(date, address.getStartDate(), address.getEndDate()) &&
                    address.isActual() && address.isActive()) {
                descriptions.add(address.getObjectId() + ": " + address.getTypeName() + " " + address.getName());
            }
        }

        return descriptions;
    }

    private static boolean isDateInRange(Date date, Date startDate, Date endDate) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    public static List<String> getAddressesWithStreet() {
        List<Address> addresses = readAddressesFromFile(ADDR_OBJ_FILE_PATH);
        List<AddressHierarchy> addressHierarchies = readAddressHierarchiesFromFile(ADM_HIERARCHY_FILE_PATH);
        List<String> fullAddresses = new ArrayList<>();

        for (Address address : addresses) {
            if (address.getTypeName().equals("проезд") && address.isActual() && address.isActive()) {
                String fullAddress = getFullAddress(address, addressHierarchies);
                fullAddresses.add(fullAddress);
            }
        }

        return fullAddresses;
    }

    public static List<Address> readAddressesFromFile(String filePath) {
        try {
            Map<String, Object> properties = new HashMap<>();
            properties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY, "META-INF/eclipse.xml");
            JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[] { AddressListWrapper.class }, properties);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            AddressListWrapper wrapper = (AddressListWrapper) unmarshaller.un(new File(filePath));
            return wrapper.getAddresses();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<AddressHierarchy> readAddressHierarchiesFromFile(String filePath) {
        try {

            JAXBContext context = JAXBContext.newInstance(AddressHierarchyListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            AddressHierarchyListWrapper wrapper = (AddressHierarchyListWrapper) unmarshaller.unmarshal(new File(filePath));
            return wrapper.getAddressHierarchies();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static String getFullAddress(Address address, List<AddressHierarchy> addressHierarchies) {
        StringBuilder fullAddress = new StringBuilder();
        fullAddress.append(address.getTypeName()).append(" ").append(address.getName());

        long parentId = address.getObjectId();
        while (parentId != 0) {
            AddressHierarchy hierarchy = findHierarchyByChildId(parentId, addressHierarchies);
            if (hierarchy != null && hierarchy.isActive()) {
                Address parentAddress = findAddressById(hierarchy.getParentObjectId(), addressHierarchies);
                if (parentAddress != null) {
                    fullAddress.insert(0, parentAddress.getName() + ", ");
                }
                parentId = parentAddress != null ? parentAddress.getObjectId() : 0;
            } else {
                parentId = 0;
            }
        }

        return fullAddress.toString();
    }

    private static AddressHierarchy findHierarchyByChildId(long childId, List<AddressHierarchy> addressHierarchies) {
        for (AddressHierarchy hierarchy : addressHierarchies) {
            if (hierarchy.getChildObjectId() == childId) {
                return hierarchy;
            }
        }
        return null;
    }

    private static Address findAddressById(long objectId, List<AddressHierarchy> addressHierarchies) {
        for (AddressHierarchy hierarchy : addressHierarchies) {
            if (hierarchy.getParentObjectId() == objectId) {
                return findAddressById(hierarchy.getChildObjectId(), addressHierarchies);
            }
        }
        return null;
    }
}