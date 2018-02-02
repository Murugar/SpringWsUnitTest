package com.iqmsoft.endpoint;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import com.iqmsoft.types.order.CustomerType;
import com.iqmsoft.types.order.LineItemType;
import com.iqmsoft.types.order.LineItemsType;
import com.iqmsoft.types.order.ObjectFactory;
import com.iqmsoft.types.order.Order;
import com.iqmsoft.types.order.ProductType;
import com.iqmsoft.endpoint.CreateOrderEndpoint;

public class CreateOrderEndpointTest {

    private CreateOrderEndpoint createOrderEndpoint = new CreateOrderEndpoint();

    private Order order;

    @Before
    public void setUp() throws Exception {
        ObjectFactory factory = new ObjectFactory();

        CustomerType customer = factory.createCustomerType();
        customer.setFirstName("Test1");
        customer.setLastName("Test2");

        ProductType product1 = factory.createProductType();
        product1.setId("1");
        product1.setName("batman action figure");

        LineItemType lineItem1 = factory.createLineItemType();
        lineItem1.setProduct(product1);
        lineItem1.setQuantity(BigInteger.valueOf(2));

        LineItemsType lineItems = factory.createLineItemsType();
        lineItems.getLineItem().add(lineItem1);

        order = factory.createOrder();
        order.setCustomer(customer);
        order.setLineItems(lineItems);
    }

    @Test
    public void testCreateOrder() {
        assertThat(createOrderEndpoint.createOrder(order)
                .getConfirmationId()).isNotBlank();
    }
}
