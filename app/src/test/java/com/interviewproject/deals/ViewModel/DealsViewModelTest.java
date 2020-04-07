package com.interviewproject.deals.ViewModel;

import com.interviewproject.deals.Client.DealsAPI;
import com.interviewproject.deals.Model.Deals;
import com.interviewproject.deals.Model.RootObject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Unit test for some functionality of DealsViewModel
 */
public class DealsViewModelTest {

    @Mock
    DealsAPI dealsAPI;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test observable output from DealsAPI
     */
    @Test
    public void dealsObservable() {
        RootObject expectedResponse = TestResponse();
        when(dealsAPI.getDeals()).thenReturn(Observable.just(TestResponse()));
        TestSubscriber<RootObject> subscriber = new TestSubscriber<>();
        subscriber.assertNotSubscribed();
        RootObject testResponse = dealsAPI.getDeals().blockingFirst();
        assertEquals(testResponse.getData().get(0).getId(), expectedResponse.getData().get(0).getId());

    }

    /**
     * Small test to check reformatting image URLs shows expected output
     */
    @Test
    public void reformatImageURLs() {
        List<Deals> input = testDeals();
        List<Deals> expected = testDeals();
        expected.get(0).setImage("https://picsum.photos/400/?temp=0");
        DealsViewModel viewModel = new DealsViewModel();
        input = viewModel.reformatImageURLs(input);

        assertEquals(input.get(0).getImage(), expected.get(0).getImage());
    }

    private RootObject TestResponse() {
        RootObject response = new RootObject();
        response.setData(testDeals());
        response.setId("Test ID");
        response.setType("Test Type");
        return response;
    }

    private List<Deals> testDeals() {
        List<Deals> dealsList = new ArrayList<>();
        Deals testDeal = new Deals();
        testDeal.setImage("TEST_URL");
        testDeal.setAisle("T_A");
        testDeal.setDescription("TEST_DESCRIPTION");
        testDeal.setGuid("TEST_GUID");
        testDeal.setId("TEST_ID");
        testDeal.setTitle("TEST_TITLE");
        testDeal.setPrice("TEST_PRICE");
        testDeal.setSalePrice("TEST_SALE_PRICE");
        testDeal.setIndex(0);
        dealsList.add(testDeal);
        return dealsList;
    }
}