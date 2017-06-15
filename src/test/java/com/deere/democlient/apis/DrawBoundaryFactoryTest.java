package com.deere.democlient.apis;

import com.deere.democlient.brokers.GenericListBroker;
import com.deere.democlient.brokers.OrganizationListBroker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrawBoundaryFactoryTest {
    @Test
    public void createdDrawBoundaryObjectHasOrganizationListBrokerInIt() throws Exception {
        DrawBoundary drawBoundary = DrawBoundaryFactory.getDrawBoundary();
        assertEquals(OrganizationListBroker.class, drawBoundary.getOrganizationBroker().getClass());
    }

    @Test
    public void createdDrawBoundaryObjectHasGenericListBrokerForFieldBroker() throws Exception {
        DrawBoundary drawBoundary = DrawBoundaryFactory.getDrawBoundary();
        assertEquals(GenericListBroker.class, drawBoundary.getFieldBroker().getClass());
    }
}