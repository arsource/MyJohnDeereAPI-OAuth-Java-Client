package com.deere.api.axiom.generated.v3;

import java.util.ArrayList;
import java.util.List;

public class Field extends Resource {
    protected String name;
    protected Clients clients;
    protected Farms farms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public void setClient(Client client) {
        clients = new Clients();
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        clients.setClients(clientList);
    }

    public Farms getFarms() {
        return farms;
    }

    public void setFarms(Farms farms) {
        this.farms = farms;
    }

    public void setFarm(Farm farm) {
        farms = new Farms();
        List<Farm> farmList = new ArrayList<>();
        farmList.add(farm);
        farms.setFarms(farmList);
    }

    public Client getClient() throws DataNotEmbeddedException {
        try {
            return clients.getClients().get(0);
        } catch (NullPointerException npe) {
            throw new DataNotEmbeddedException();
        }
    }

    public Farm getFarm() throws DataNotEmbeddedException {
        try {
            return farms.getFarms().get(0);
        } catch (NullPointerException npe) {
            throw new DataNotEmbeddedException();
        }
    }

    public class DataNotEmbeddedException extends Exception {
        DataNotEmbeddedException() {
            super("This object does not have the necessary details embedded.");
        }
    }

    public class Clients {
        List<Client> clients;

        public List<Client> getClients() {
            return clients;
        }

        public void setClients(List<Client> clients) {
            this.clients = clients;
        }
    }

    public class Farms {
        List<Farm> farms;

        public List<Farm> getFarms() {
            return farms;
        }

        public void setFarms(List<Farm> farms) {
            this.farms = farms;
        }
    }
}
