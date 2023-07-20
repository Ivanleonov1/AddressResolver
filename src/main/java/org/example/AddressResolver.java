package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddressResolver {
    private List<AsAddrObj> addressList;
    private List<AsAdmHierarchy> hierarchyList;

    public AddressResolver(String addressFilePath, String hierarchyFilePath) throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
        File addressFile = new File(addressFilePath);
        File hierarchyFile = new File(hierarchyFilePath);

        addressList = Arrays.asList(xmlMapper.readValue(addressFile, AsAddrObj[].class));
        hierarchyList = Arrays.asList(xmlMapper.readValue(hierarchyFile, AsAdmHierarchy[].class));
    }

    public List<String> getAddressDescriptions(String date, List<Integer> objectIds) {
        return objectIds.stream()
                .map(objectId -> {
                    AsAddrObj address = getAddressById(objectId);
                    if (address != null && isAddressActive(address, date)) {
                        return objectId + ": " + address.getTypeName() + " " + address.getName();
                    }
                    return null;
                })
                .filter(description -> description != null)
                .collect(Collectors.toList());
    }

    private AsAddrObj getAddressById(int objectId) {
        return addressList.stream()
                .filter(address -> address.getObjectId() == objectId)
                .findFirst()
                .orElse(null);
    }

    private boolean isAddressActive(AsAddrObj address, String date) {
        return address.getStartDate().compareTo(date) <= 0 &&
                (address.getEndDate() == null || address.getEndDate().compareTo(date) >= 0) &&
                address.isActual() && address.isActive();
    }

    public List<String> getAddressesWithPassageType() {
        return addressList.stream()
                .filter(address -> address.getTypeName().equals("проезд"))
                .map(address -> buildFullAddress(address))
                .collect(Collectors.toList());
    }

    private String buildFullAddress(AsAddrObj address) {
        StringBuilder fullAddress = new StringBuilder();
        fullAddress.append(getAddressDescription(address));
        AsAdmHierarchy hierarchy = getHierarchyByChildId(address.getObjectId());

        while (hierarchy != null) {
            AsAddrObj parentAddress = getAddressById(hierarchy.getParentObjectId());
            if (parentAddress != null) {
                fullAddress.insert(0, getAddressDescription(parentAddress) + ", ");
                hierarchy = getHierarchyByChildId(parentAddress.getObjectId());
            } else {
                hierarchy = null;
            }
        }

        return fullAddress.toString();
    }

    private String getAddressDescription(AsAddrObj address) {
        return address.getTypeName() + " " + address.getName();
    }

    private AsAdmHierarchy getHierarchyByChildId(int childId) {
        return hierarchyList.stream()
                .filter(hierarchy -> hierarchy.getObjectId() == childId)
                .findFirst()
                .orElse(null);
    }
}

