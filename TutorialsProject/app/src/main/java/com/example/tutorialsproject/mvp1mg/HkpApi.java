package com.example.tutorialsproject.mvp1mg;

public class HkpApi {

    public static final String API_HOST_URL = "";
    public static final String DIAGNOSTIC_HOST_URL = "";

    public static class Headers {
        public static final String ACCEPT_KEY = "Accept";
        public static final String ACCEPT_VALUE = "application/vnd.healthkartplus.v10+json";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String CONTENT_TYPE_VALUE = "application/json";
        public static final String USER_ID = "user-id";
    }

    public static class Diagnostics
    {
        public static final String HOME = new StringBuilder(2).append(DIAGNOSTIC_HOST_URL).append("home?city=%s&popular_test_size=%s&packages_page_size=%s").toString();

        public static class Cart
        {
            public static final String GET_CART_URL = new StringBuilder(2).append(DIAGNOSTIC_HOST_URL).append("cart?city=%s").toString();
        }

        public static class Address
        {
            public static final String ADD_ADDRESS = new StringBuilder(2).append(DIAGNOSTIC_HOST_URL).append("v4/users/addresses").toString();
        }

        public static class Patient
        {
            public static final String PATIENTS_URL = new StringBuilder(2).append(DIAGNOSTIC_HOST_URL).append("v4/patients?page_number=%d&page_size=%d").toString();
        }

        public static class Test
        {
            public static final String TEST_SEARCH_URL = new StringBuilder(2).append(DIAGNOSTIC_HOST_URL).append("v4/tests?search_text=%s&city=%s").toString();
        }

        public static class Lab
        {
            public static final String LAB_TEST_INVENTORY_URL = new StringBuilder(2).append(DIAGNOSTIC_HOST_URL).append("v6/inventory?sort_by=%s&city=%s&").toString();
        }

        public static class MyTest
        {
            // My Test
            public static final String STATUS_UPCOMING_ORDER = "booked&status=validated&status=confirmed&status=phlebo_assigned&status=sent_to_lab&status=sample_collected";
            public static final String STATUS_COMPLETED_ORDER = "delivered&status=conducted";
            public static final String STATUS_CANCELLED_ORDER = "cancelled";

            public static final String ORDERS_URL = new StringBuilder(2).append(DIAGNOSTIC_HOST_URL).append("v4/bookings?&page_number=%s&page_size=%s&city=%s").toString();
        }

    }


}
