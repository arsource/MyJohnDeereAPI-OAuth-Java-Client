package com.deere.democlient.util;

import com.deere.api.axiom.generated.v3.Client;
import com.deere.api.axiom.generated.v3.Farm;
import com.deere.api.axiom.generated.v3.Field;

import java.util.*;
import java.util.stream.Collectors;

public class ClientFarmFieldTree {
    private TreeMap<Client, TreeSet<Farm>> clientFarmMap = new TreeMap<>((Client client1, Client client2) -> client1.getName().compareToIgnoreCase(client2.getName()));
    private HashMap<Farm, TreeSet<Field>> farmFieldMap = new HashMap<>();
    private Comparator<Field> fieldComparator = (Field field1, Field field2) -> field1.getName().compareToIgnoreCase(field2.getName());

    public void addFieldsWithEmbeddedClientAndFarm(List<Field> fields) {
        try {
            for (Field field : fields) {
                Client client = field.getClient();
                Farm farm = field.getFarm();
                addClientFarmField(client, farm, field);
            }
        } catch (Field.DataNotEmbeddedException e) {
            System.err.println("This method should only be called for fields having client and farm data embedded.");
            e.printStackTrace();
        }
    }

    public void addClientFarmField(Client client, Farm farm, Field field) {
        addFarmToClient(farm, client);
        addFieldToFarm(field, farm);
    }

    private void addFarmToClient(Farm farm, Client client) {
        TreeSet<Farm> farmsMappedToClient = clientFarmMap.get(client);
        if (farmsMappedToClient == null) {
            farmsMappedToClient = new TreeSet<>((Farm farm1, Farm farm2) -> farm1.getName().compareToIgnoreCase(farm2.getName()));
        }
        farmsMappedToClient.add(farm);
        clientFarmMap.put(client, farmsMappedToClient);
    }

    private void addFieldToFarm(Field field, Farm farm) {
        TreeSet<Field> fieldsMappedToFarm = farmFieldMap.get(farm);
        if (fieldsMappedToFarm == null) {
            fieldsMappedToFarm = new TreeSet<>(fieldComparator);
        }
        fieldsMappedToFarm.add(field);
        farmFieldMap.put(farm, fieldsMappedToFarm);
    }

    public Set<Client> getClients() {
        return clientFarmMap.keySet();
    }

    public Set<Field> getFields() {
        return farmFieldMap.values().stream().flatMap(fieldsForFarm -> fieldsForFarm.stream()).collect(Collectors.toCollection(() -> new TreeSet<Field>(fieldComparator)));
    }

    public Set<Farm> getFarmsForClient(Client client) {
        TreeSet<Farm> farms = clientFarmMap.get(client);
        return farms == null ? Collections.emptySet() : farms;
    }

    public Set<Field> getFieldsForFarm(Farm farm) {
        TreeSet<Field> fields = farmFieldMap.get(farm);
        return fields == null ? Collections.emptySet() : fields;
    }
}
