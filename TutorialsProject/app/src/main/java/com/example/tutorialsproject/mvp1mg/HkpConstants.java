package com.example.tutorialsproject.mvp1mg;

import android.text.format.DateUtils;

public class HkpConstants
{
    public static final String MFILTER_IT_VENDOR_ID = "1MG01";

    public static final int MFILTER_EXTRA_DATA_POINTS = 1;

    public static final String VISITOR_ID = "visitor_id";
    public static final String APP_TOKEN = "f77f883849e5bb2099849a055a6b608c0486a564598e048834291ec7e6fe";
    public static final String URI_ASK_A_CHEMIST = "https://1mgdoctors.com/second_opinion?id=29&name=Ask%20A%20Chemist&speciality_name=Pharmacologist";
    public static final String GOOGLE_API_KEY = "AIzaSyCMudww7gNE6DjLATlPb7tsBM7HZFRhF4A";
    public static final String ONE_MG = "1mg";
    public static final String ONE_MG_LOGO_URL = "https://img.1mg.com/images/1mg-logo-large.png";
    public static final String INIT_CONFIG_FILE_NAME = "init_app_config.json";
    public static final String INIT_2_CONFIG_FILE_NAME = "init_2_app_config.json";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access. Please sign in again.";
    public static final String MESSAGE_RESOURCE_NOT_FOUND = "Requested resource does not exist. Please try again.";
    public static final String MESSAGE_UNSUPPORTED_FILE_TYPE = "Incorrect file type. Please try again.";
    public static final String MESSAGE_SERVER_ERROR = "Our servers are under maintenance. We would be back shortly.";
    public static final String REQUEST_TIMED_OUT = "Request timed out. Please try again later.";
    public static final String COULDNT_REACH_OUR_SERVERS = "Couldn't reach our servers. Please try again later.";
    public static final String BAD_REQUEST = "Bad request. Try again later";
    public static final String ITEM_ALREADY_ADDED_IN_CART = "Item already exists in cart.";
    public static final String JSON_PARSER_EXCEPTION = "Unable to parse json.";

    public static final String OPEN_COUNT = "openCount";
    public static final String SUBS_SEARCH_OPEN_COUNT = "subOpenCount";
    public static final String KNOW_MED_OPEN_COUNT = "knowMedOpenCount";
    public static final String CONFIG_VERSION_CODE = "configVersionCode";
    public static final String TYPE_PDF = "application/pdf";

    // Action - Upload Prescription options
    public static final int ACTION_CHOOSE_FILE = 1;
    public static final int ACTION_TAKE_PHOTO = 2;
    public static final int ACTION_EARLIER_PRESCRIPTION = 4;
    public static final int ACTION_CHOOSE_PDF = 3;
    public static final int ACTION_VIEW_PRESCRIPTION = 6;

    // Action - AsktoSignup Or OderScreen
    public static final int ACTION_AUTHENTICATION = 5;
    public static final String SCREEN_TYPE = "screen_type";
    public static final String SCREEN_VALUE = "screen_value";
    public static final String SCREEN = "screen";
    public static final String REFERRAL_CODE = "referral_code";

    // authentication key constants
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    // init params
    public static final String APP_VERSION = "app_version";

    public static final String APP_NAME = "app_name";
    public static final String DEVICE_OS_VERSION = "device_os_version";
    public static final String DEVICE_OS_TYPE = "device_os_type";
    public static final String DEVICE_ID = "device_id";
    public static final String GCM_ID = "gcm_id";
    public static final String USER_EMAIL_ADDRESS = "user_email_address";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String NUMBER = "number";
    public static final String CITY = "city";
    public static final double LATITUDE_DEFAULT_VALUE = 0;
    public static final double LONGITUDE_DEFAULT_VALUE = 0;
    public static final String KEY_TYPE = "key_type";
    public static final String VALUE = "value";
    public static final String UPDATE_TOKEN = "update_token";
    public static final String NEW_PASSWORD = "new_password";
    public static final String CONFIRM_PASSWORD = "confirm_password";
    public static final String BENEFIT_TYPE = "benefit_type";

    // sharing data
    public static final String PRODUCT_ID = "productId";

    // Cart Quantity update constants
    public static final String QTY = "qty";
    public static final String CART_COUNT = "cartCount";
    public static final String COUPON_CODE = "couponCode";

    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String PRODUCT_QUANTITY = "productQuantity";

    // page size
    public static final int PGSIZE = 10;
    public static final String KEY_ORDER_ID = "orderId";

    public static final String AEROSOL = "aerosol";
    public static final String BAR = "bar";
    public static final String BOTTLE = "bottle";
    public static final String BOX = "box";
    public static final String CAPSULES = "capsule";
    public static final String DEVICE = "device";
    public static final String DRESS = "dress";
    public static final String GLASS_BOTTLE = "Glass Bottle";
    public static final String INJECTION = "injection";
    public static final String JAR = "jar";
    public static final String PATCH = "patch";
    public static final String PET_BOTTLE = "pet bottle";
    public static final String POWDER = "powder";
    public static final String SACHET = "sachet";
    public static final String SOAP = "soap";
    public static final String STRIP = "strip";
    public static final String SYRUP = "syrup";
    public static final String SUPPOSITORY = "suppository";
    public static final String TABLETS = "tablet";
    public static final String TUBE = "tube";
    public static final String VIAL = "vial";
    public static final String RESPULES = "respules";
    public static final String STRIPS = "strips";
    public static final String DROPS = "drops";
    public static final String MISC = "miscellaneous";
    public static final String PATIENT = "patient";

    public static final String APP_PNAME = "com.aranoah.healthkart.plus";
    public static final String IOS_APP_CLIENT_ID = "636386266577-cnrou5b7m0cqo2sb1qdbdq8clod731an.apps.googleusercontent.com";

    public static final String SORT_TYPE = "sort_type";
    public static final String RELEVANCE = "relevance";
    public static final String PRICE = "price";

    public static final String GROUP_REORDER = "group_reorder";

    public static final String PRESCRIPTION_IDS = "prescriptionIds";
    public static final String ADDRESS_ID = "addressId";
    public static final String DURATION_IN_DAYS = "duration_in_days";
    public static final String SOURCE = "SOURCE";
    public static final String SOURCE_FILTERS = "source_filters";

    public static final String ANDROID = "Android";

    // ** Spelling mistake intentional, please do not correct. **
    public static final String DOCUMANT_ID = "documantId";
    //*****************
    public static final String DOCUMENT_ID = "documentId";

    public static final String PRESCRIPTION_URL = "prescriptionUrl";
    public static final String FILE = "file";

    //Order confirmation screen
    public static final String POSITION = "position";


    public static final String MOBILE_WITH_COLON = "Mobile: ";

    // Delimiters
    public static final String EMPTY = "";
    public static final String DOT = ".";
    public static final char CHAR_EMPTY = ' ';
    public static final String EMPTY_ARRAY = "[]";
    public static final String WHITESPACE = " ";
    public static final String TAB = "  ";
    public static final String COLON = ":";
    public static final String UNDERSCORE = "_";
    public static final String PIPE_WITH_WHITESPACE = " | ";
    public static final String PIPE = "|";
    public static final String NEW_LINE = "\n";
    public static final String HYPHEN = "-";
    public static final String HYPHEN_WITH_WHITESPACE = " - ";
    public static final String COMMA = ",";
    public static final String COMMA_WITH_WHITESPACE = ", ";
    public static final String INVERTED_COMMA = "'";
    public static final String SEMICOLON = ";";
    public static final String AMPERSAND = "&";
    public static final String OPEN_PARENTHESIS = "(";
    public static final String CLOSE_PARENTHESIS = ")";
    public static final String FORWARD_SLASH = "/";
    public static final String EQUAL = "=";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String QUESTION_MARK = "?";
    public static final String FULLSTOP_WITH_WHITESPACE = ". ";
    public static final String BULLET = "\u2022";
    public static final String SPACE_REG_EX = "\\s";


    // GCM constants
    public static final String PROVIDER = "provider";

    // Analytics Constants
    public static final String TERMS_CONDITIONS = "Terms & Conditions";
    public static final String NOVO_TITLE = "Impact India Programme";

    public static final String CONTACT_US = "Contact Us";
    public static final String NOTIFICATION_WEBVIEW = "notification_webview";
    public static final String PDF = "pdf";
    public static final String EXISTING_PRESCRIPTION = "existing_prescription";
    public static final String PRESCRIPTION = "Prescription";

    public static final String ACCESS_DENIED = "Access Denied";

    public static final String PARSE_ERROR = "Unable to parse API. Our server is having issues. Please try later.";
    public static final String COULD_NOT_FIND_PRESCRIPTION_IMAGE = "Could not find prescription image.";
    public static final String HOME = "Home";
    public static final String MARKET_URI = "market://details?id=com.aranoah.healthkart.plus";
    public static final String PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.aranoah.healthkart.plus";
    public static final String IOS_APP_BUNDLE_ID = "com.aranoah.healthkart";
    public static final String IOS_APP_STORE_ID = "554578419";
    public static final String MENU = "Menu";
    public static final String MAILTO = "mailto:";
    public static final String CC_EMAIL = "care@1mg.com";
    public static final String SUBJECT = "?subject=";
    public static final String BODY = "&body=";
    public static final String SEND_MAIL = "Send mail...";
    public static final String TEL = "tel:";
    public static final String SMS = "sms:";

    // Navigation Drawer Item Labels
    public static final String LOGIN_LABEL = "Login|Register";
    public static final String MY_ORDERS = "My Orders";
    public static final String MY_LAB_TESTS = "My Lab Tests";
    public static final String MY_APPOINTMENTS = "My Appointments";
    public static final String MY_CONSULTATIONS = "My Consultations";
    public static final String FIND_SUBSTITUTES = "Find Substitutes";
    public static final String SHOP_BY_CATEGORY = "Shop by Category";
    public static final String ORDER_WITH_PRESCRIPTION = "Order with Prescription";
    public static final String CHANGE_CITY = "Change City";
    public static final String PILL_REMINDERS = "Pill Reminders";
    public static final String GET_SECOND_OPINION = "Get Second Opinion";
    public static final String BOOK_APPOINTMENTS = "Book Appointments";
    public static final String BOOK_LAB_TESTS = "Book Lab Tests";
    public static final String BOOKMARKED_ARTICLES = "Bookmarked Articles";
    public static final String READ_HEALTH_BITES = "Read Health Bites";
    public static final String ABOUT_US = "About Us";
    public static final String FAQ = "FAQ";
    public static final String SIGN_OUT = "Signout";
    public static final String TRUECALLER_PACKAGE = "com.truecaller";

    public static final String ARTICLE_ID = "article_id";

    public static final long MILLIS_IN_SECOND = 1000;

    public static final String TIME = "time";
    public static final String ADDRESS = "address";

    public static final String NOTIFICATION_ID = "notification_id";

    public static final String HYPHON = "-";

    public static final String EMAIL = "email";

    public static final String RECAPTCHA_RESPONSE = "recaptcha_response";

    public static final String RETRY = "retry";

    public static final String IS_CORPORATE_USER = "is_corporate_user";

    public static final String ABOUT_1_MG = "About 1MG";

    public static final String MUST_IMPLEMENT = " must implement ";
    public static final String NOT_IMPLEMENT_BY_PARENT = " not implement by parent";

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";
    public static final String YYYY = "yyyy";
    public static final String DD_MMMM = "dd MMMM";

    public static final String COMMENT = "comment";

    public static final String SPECIAL_INSTRUCTIONS = "special_instructions";
    public static final String ORDER_CHOICE = "order_choice";

    public static final String CUSTOMER_CARE_PHONE = "tel:01244166666";

    public static final long SLOW_NETWORK_MESSAGE_DELAY_IN_MILLIS = 10000;

    public static final String TARGET_PHARMACY = "pharmacy";
    public static final String TARGET_LABS = "labs";
    public static final String TARGET_ONLINE_CONSULTATION = "online_consultation";
    public static final String TARGET_DOCTORS = "doctors";
    public static final String TARGET_ARTICLE = "article";
    public static final String TARGET_OTC = "otc";
    public static final String TARGET_DRUGS = "drugs";
    public static final String TARGET_PILL_REMINDER = "pill_reminder";

    public static final String KEY_ORIGINAL_FILE_PATH = "original_file_path";
    public static final String KEY_NAME = "name";
    public static final String KEY_PATH = "path";
    public static final String KEY_ID = "id";
    public static final String KEY_SELECTED_PRESCRIPTIONS = "selected_prescriptions";
    public static final int THUMBNAIL_WIDTH = 200;
    public static final int THUMBNAIL_HEIGHT = 200;
    public static final String IS_RX_ORDER = "isRxOrder";
    public static final String BEST_COUPON = "bestCoupon";
    public static final String IS_LABS_FLOW = "is_labs_flow";

    public static final String EDIT = "edit";
    public static final String CART_OOS = "cart_oos";
    public static final String CART_GET_SUMMARY = "get_summary";
    public static final String IS_PO_CART = "is_po_cart";
    public static final String SKU_IDS = "sku_ids";
    public static final int NO_POSITION = -1;
    public static final int NO_VALUE = -1;

    public static final String REQUIRE_PRESCRIPTION_SELECTED_OPTION = "require_prescription_selected_option";
    public static final String HAVE_PRESCRIPTION = "have_prescription";
    public static final String AUTO_DETECT = "autoDetect";

    public static final String PRESCRIPTIONS = "prescriptions";
    public static final String DOCUMENT_IDS = "documentIds";
    public static final String ATTACH_TO_ORDER = "attachToOrder";
    public static final String USER_NAME = "userName";
    public static final String DOC_NAME = "docName";
    public static final String AUTH_USER_NAME = "username";
    public static final String ORDER_ID = "orderId";

    public static final String URI = "uri";
    public static final String LOCALYTICS_NOTIFICATIONS = "localytics_notifications";

    public static final String H4 = "h4";
    public static final String MOBILE_INDIA_CODE = "+91-";

    public static final String KEY_CONFIG = "config";
    public static final int MOBILE_NUMBER_LENGTH = 10;
    public static final String USER_ID_PARAM = "?user_id=%s";

    public static final float TARGET_VIEW_ALPHA = 0.96f;
    public static final int TARGET_VIEW_RADIUS = 60;
    public static final String DEFAULT = "Default";
    public static final String MEDIUM = "Medium";
    public static final String LARGE = "Large";
    public static final double LINE_HEIGHT_MULTIPLIER = 1.3;
    public static final String SMSTO = "smsto:";
    public static final String CMS = "Cms";
    public static final String KGS = "Kgs";
    public static final String GOOGLE_MAP_URI = "https://www.google.com/maps/search/?api=1&query=%f,%f";

    public static final String CAMERA = "Camera";
    public static final String GALLERY = "Gallery";

    public static final String CONVERSATION_ID = "conversationId";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";

    public static final String FILEPROIVDER = ".fileprovider";
    public static final int ROTATE_TO_DEGREES = 180;
    public static final String SPECIALITY_ID = "speciality_id";
    public static final String REFERRAL_DOCTORS = "doctors";

    public static final String HOME_SCREEN = "homePage";
    public static final String OTC_SCREEN = "otcDetailPage";
    public static final String DRUG_SCREEN = "drugPage";
    public static final String ORDER_PLACED_SCREEN = "orderSuccessPage";

    public static final String USER_ID = "uid";

    public static final int REQUEST_CODE_CHANGE_ADDRESS = 100;
    public static final int REQUEST_CODE_PACK_SIZE_VARIANT = 4;
    public static final int SURVEY_REQUEST = 7;
    public static final String PAYMENT_OPTION_ID = "payment_option_id";

    public static final String UPLOAD = "upload";
    public static final String IMAGE_WIDTH_PARAM = "w_";
    public static final String IMAGE_HEIGHT_PARAM = "h_";
    public static final String IMAGE_QUALITY_PARAM = "q_";
    public static final String EXTRA_MEDICINE_NAME = "Medicine Name";
    public static final String EXTRA_SKU_ID = "Sku Id";
    public static final String ENTRY_SOURCE = "entry_source";
    public static final String DFP = "dfp";
    public static final String CURRENCY_INR = "INR";
    public static final String PAYLOAD = "payload";
    public static final String SIGNATURE = "signature";
    public static final String SIGNATURE_ALGORITHM = "signature_algorithm";
    public static final String IS_USER_EXISTING = "is_existing_user";
    public static final String EMAIL_REQUIRED = "email_required";
    public static final String OTP_REQUIRED = "otp_required";
    public static final String PILL_REMINDER_SYNC_RECEIVER = "com.aranoah.healthkart.plus.pillreminder.receiver.PillReminderSyncReceiver";
    public static final String SIGNUP = "SIGNUP";
    public static final String LOGIN_TEXT = "LOGIN";
    public static final String POSS_ACTION = "poss_action";
    public static final String DCP_TARGET_WEBVIEW = "web_view";
    public static final String ZERO_TEXT = "0";
    public static final String PLAIN_TEXT = "text/plain";
    public static final String IMAGE_FORMAT_TYPE = ".jpg";
    public static final int REQUEST_CODE_JUSPAY = 1221;
    public static final int MAX_RETRY = 3;
    public static final long MIN_BACKOFF_DELAY = 1;
    public static final String SHOULD_SHOW_EMPTY_VIEW = "should_show_empty_view";
    public static final String WEB_TO_NATIVE_CALLBACK_NAME = "AppCallbacks";
    public static final String IS_ADDRESS_AVAILABLE = "is_address_available";
    public static final String START_FOR_RESULT = "start_for_result";
    public static final int DEVICE_HEIGHT_PX = 1920;
    public static final String DEFAULT_CITY = "Default City";
    public static final int PROGRESS_COMPLETE = 100;
    public static final String URL_GOOGLE_DOCS = "https://docs.google.com/viewerng/viewer?url=";
    public static final String CLIENT_APP = "app";
    public static final String OTC = "otc";
    public static final String DRUG = "drug";
    public static final String ALLOPATHY = "allopathy";
    public static final String UTF8 = "UTF-8";
    public static final String AMP = "amp";
    public static final String VARIANT_OLD = "old";
    public static final String VARIANT_NEW = "new";
    public static final String CART_ITEM_THEME_CARE_PLAN = "care_plan";
    public static final String OFFER_TYPE_COUPON = "coupon";
    public static final String OFFER_TYPE_MULTI_COUPON = "multi_coupon";
    public static final String OFFER_TYPE_APPLY_COUPON = "apply_coupon";
    public static final String OFFER_TYPE_EXPLORE_MORE_COUPONS = "explore_more_coupons";
    public static final String UPSELL_TYPE_SINGLE = "single";
    public static final String KEY_UPSELL_SOURCE = "source";
    public static final String UPSELL_SOURCE_PHARMACY_CART = "pharmacy_cart";
    public static final String KEY_UPSELL_DETAILS = "details";
    public static final String DELIVERY_ID = "_dId";
    public static final String MESSAGE_ID = "_mId";
    public static final String FIRST_NAME = "firstName";
    public static final String SOURCE_ORDER_DETAIL = "Order detail";
    public static final String ACTIVE = "ACTIVE";
    public static final String NOTIFICATION = "NOTIFICATION";
    public static final String IS_TIME_OVER = "is_time_over";
    public static final String TIME_OVER_MESSAGE = "Aah! You just ran out of time and missed out on the 1mgCash offer.";
    public static final long TIME_OVER_VIBRATE_TIME = 1000;
    public static final String PHARMACY_ORDER_SUCCESS_PAGE_WIDGET = "PHARMACY_ORDER_SUCCESS_PAGE_WIDGET";
    public static final String CTA_ACTION_REDIRECT = "REDIRECT";
    public static final String CTA_ACTION_ADD_SKU = "ADD_SKU";
    public static final String CTA_ACTION_REMOVE_SKU = "REMOVE_SKU";
    public static final String PATIENT_LIST_SCREEN = "patient_list_screen";
    public static final String IS_PPE_KIT_REQUIRED = "ppe_kit_required";
    public static final String AND = "and";
    public static String KEY_UNAUTHORISED = "key_unauthorised";
    public static final String SINGLE = "single";

    public static final String PATHOLOGY = "Pathology";
    public static final String RADIOLOGY = "Radiology";
    public static final String ERROR_MESSAGE = "error_message";
    public static final String ADDRESS_TYPE = "addressType";
    public static final String CONTACT_NO = "contactNo";

    public static final String ATTACHMENTS = "attachments";
    public static final String QUERY_ID = "query_id";

    public static final long SEARCH_BASED_REMINDER_DELAY_IN_MILLIS = 20 * DateUtils.MINUTE_IN_MILLIS;
    public static final String ACTION_REMINDER_RECEIVER = "com.aranoah.healthkart.plus.pillreminder.receiver.ACTION_REMINDER_RECEIVER";
    public static final String PACKAGE_SUGGESTIONS = "package-suggestions";

    public static final String UPDATED_CITY = "updated_city";
    public static final String MESSAGE = "message";
    public static final int REQUEST_CODE_LABS_HOME_FOOTER = 1;

    public static final String PACKAGE_FACEBOOK = "com.facebook.katana";
    public static final String PACKAGE_TWITTER = "com.twitter.android";
    public static final String PACKAGE_WHATSAPP = "com.whatsapp";
    public static final String PACKAGE_GMAIL = "com.google.android.gm";

    public static final String SHOW_LOCATION = "show_location";
    public static final String COMMUNICATION_TYPE_WHATSAPP = "whatsapp";
    public static final String TAG_WHATSAPP_ORDER_UPDATES = "whatsapp order updates";

    public static final int BOTTOM_ANIMATION_DELAY_IN_MILLIS = 300;

    public static final String LOGIN = "login";
    public static final String SIGN_UP = "signUp";

    public static final String PUSH_EVENT_ID = "push_event_id";
    public static final String KEY_PUSH_STATUS = "key_push_status";
    public static final String DELIVERED = "delivered";
    public static final String DISABLED = "disabled";
    public static final String READ = "read";

    public static final String TRANSACTIONAL = "transactional";
    public static final String VIDEO_CONSULT = "video_consult";
    public static final String DYNAMIC_FEATURE_VIDEO_CONSULT = "videoconsult";
    public static final String MARKETING = "marketing";
    public static final String REMINDERS = "reminders";
    public static final String PATIENT_NAME = "patient_name";
    public static final String COMMUNICATION_NUMBER = "comm_number";
    public static final String SUMMARY_HELPER_SCREEN = "summary_helper_screen";
    public static final String LABS_SUMMARY_HELPER_SCREEN = "labs_summary_helper_screen";

    public static final String PATTERN_IMPACT_INDIA = "webview/impact-India";
    public static final String PATTERN_SUBSCRIPTION_URL = "subscription-plan";

    public static final String NA = "NA";
    public static final String ACTION_UPLOAD_PRESCRIPTION = "ACTION_UPLOAD_PRESCRIPTION";
    public static final String ACTION_CANCEL_NOTIFICATION = "ACTION_CANCEL_NOTIFICATION";

    public static final int SUCCESS = 2;
    public static final int ERROR = 3;

    public static final String IS_VALID_SIGNATURE = "isValidSignature";
    public static final String SIGNED_ATTESTATION = "signedAttestation";
    public static final String GOOGLE_QUICK_SEARCH_PACKAGE = "com.google.android.googlequicksearchbox";

    public static final String KEY_URL = "url";
    public static final long MILLISECONDS_500 = 500;

    public static final String EXTRA_MESSAGE = "extra_message";
    public static final int RATING_HIGH = 4;
    public static final int RATING_LOW = 2;
    public static final String QUESTION = "Question";
    public static final String DYNAMIC_LINK_BASE_URL = "https://tdgx9.app.goo.gl/";
    public static final String KEY_APP_VERSION = "appVersion";
    public static final String KEY_VISITOR_ID = "visitorId";
    public static final String SOURCE_ONLINE_CONSULTATION = "Online consultation";
    public static final String HOME_PAGE = "HomePage";
    public static final String SOURCE_REVIEW = "Review";
    public static final String SOURCE_SELECT_ADDRESS = "Select Address Page";
    public static final String SOURCE_OTC_CATEGORY = "OTC categories page";
    public static final String SOURCE_ORDER_ACTIVITY = "Order page";
    public static final String SOURCE_DCP_THANK_YOU = "Dcp Thank You";
    public static final String SOURCE_BRAND_SEARCH = "Brand search page";
    public static final String SOURCE_SEARCH_RESULTS = "Search results";
    public static final String SOURCE_DRUGS_FOR_GENERICS = "Drugs for generics";
    public static final String SOURCE_GENERIC_DETAIL = "Generic Detail";
    public static final String SOURCE_SUBSITITUTES_RESULT = "Substitutes result";
    public static final String SOURCE_SUBSITITUTES = "Substitutes page";
    public static final String SOURCE_OTC_LISTING = "OTC listing page";
    public static final String SOURCE_CART_SUMMARY = "Cart Summary";
    public static final String TRUE = "True";
    public static final String FALSE = "False";
    public static final String SEARCH = "search";
    public static final String NONE = "none";
    public static final String OFF = "Off";
    public static final String PRODUCT_PAGE = "ProductPage";
    public static final String PO_PAGE = "POPage";
    public static final String BANNER_TYPE_AD = "ad";
    public static final String ORDER_INFO_CUSTOM_DIMENSION = "new_flow=%s, search_flow=%s";
    public static final String REVIEWS_SOURCE_OTC_PAGE = "Reviews Source OTC Page";
    public static final String REVIEWS_SOURCE_VIEW_ALL = "Reviews Source View All";
    public static final String DCP_OFFER_STATUS = "dcp_offer_status";
    public static final String V4_KEY_DATA = "data";
    public static final String SUBSCRIPTIONS = "subscriptions";
    public static final String ENTITY_ID = "{{entity_id}}";
    public static final String SUPPORT_CHAT = "support/chat";
    public static final String ONE_MG_SUPPORT = "1mg Support";
    public static final int REQ_CODE_WEBVIEW = 120;
    public static final String IS_CARE_PLAN_ADDED = "is_care_plan_added";
    public static final String CARE_PLAN_BROADCAST_ACTION = "care-plan-action";
    public static final String TIMER_FORMAT = "%02d:%02d";
    public static final String QUERY_PARAM_RESPONSE_TIME = "?responseTime=%s";
    public static final String QUERY_PARAM_DURATION = "&duration=%s";
    public static final String QUERY_PARAM_CASHBACK = "&cashback=%s";
    public static final String UPSELL_TIME_OVER_BROADCAST_ACTION = "upsell-timer-over-action";
    public static final long SCROLL_DELAY_IN_MILLIS = 100;
    public static final int MIN_VALUE = 1;
    public static final int ZERO = 0;
    public static final int COUNT_DOWN_INTERVAL_MILLISECONDS = 1000;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int PREFETCH_ITEM_COUNT = 4;
    public static final String RATINGS = "ratings";
    public static final String GOOD = "good";
    public static final String AVERAGE = "average";
    public static final String BAD = "bad";
    public static final int ONE_RATING = 1;
    public static final int TWO_RATING = 2;
    public static final int THREE_RATING = 3;
    public static final int FOUR_RATING = 4;
    public static final int FIVE_RATING = 5;
    public static final String CONSULT_URI = "https://www.1mg.com/online-consultation/chat";

    public static final String VIDEO_CONSULT_PATH = "/online-doctor-consultation/video-chat";
    public static final String ACTION_CALL_RINGING = "call_ringing";
    public static final String ACTION_CALL_REJECT = "call_reject";
    public static final String ACTION_CALL_ANSWER = "call_answer";
    public static final String ACTION_CALL_MISSED = "call_missed";
    public static final String ACTION_HOME_FULL_SCREEN_BANNERS = "full_screen_banners";

    public static final String PAGE_SOURCE = "page_source";
    public static final String SKU_ID = "sku_id";
    public static final String DRUG_PAGE = "drug_page";
    public static final String OTC_PAGE = "otc_page";
    public static final String VAS_UPSELL = "vas_upsell";
    public static final String COMBO_PACK_DETAILS_PAGE = "combo_pack_details_page";
    public static final String CART_SOURCE = "source";
    public static final String CART = "cart";
    public static final String ETA_SOURCE = "eta_source";
    public static final String ETA = "eta";
    public static final String ETA_FLAG = "eta_flag";
    public static final String ETA_SUB_FLAG = "eta_sub_flag";
    public static final String FOLLOWUP_URL = "followup_url";

    public static final int REQ_CODE_AUTHENTICATE_DEEPLINK = 1031;
    public static final String PREVIOUSLY_ORDERED_ITEMS_SCREEN = "previously_ordered_items_screen";

    public static final String IS_BACKGROUND = "isBackground";

    public static final String CATEGORY = "category";
    public static final String BRAND = "brand";

    public static final String PAGE_TYPE = "page_type";
    public static final String IS_RECOMMENDATION_REQUIRED = "is_recommendation_required";
    public static final String SOURCE_MEDICINE_PDP = "medicine_pdp";
    public static final String SOURCE_OTC_PDP = "otc_pdp";
    public static final String SOURCE_SEARCH_ALL_PAGE = "search_all_page";

    public static final String BANNER = "banner";
    public static final String WIDGET = "widget";

    public static final String TOKEN = "token";
    public static final String WIDGETS = "widgets";
    public static final String RECOMMENDED = "recommended";
    public static final long INITIAL_DELAY_IN_BANNER_SWITCHING = 5000;
    public static final long DELAY_IN_BANNER_SWITCHING = 5000;

    public static final String NAVIGATION_TAGS = "navigation_tags";
    public static final String NAVIGATION_TABS = "navigation_tabs";
    public static final String NAVIGATION_CARD = "navigation_card";
    public static final String CONTROL_1 = "control_1";
    public static final String CONTROL_2 = "control_2";
    public static final int MIN_BANNER_SIZE = 2;
    public static final String EXTRA_ADDRESS = "EXTRA_ADDRESS";
    public static final String MULTI = "multi";

    public static final String CLEVERTAP = "Clevertap";
    public static final String FOLLOWUP = "followup";

    public static class Components
    {
        public static final String COMPONENT_SERVICES = "services";
        public static final String COMPONENT_WIDGET = "widget";
        public static final String COMPONENT_REFERRAL = "referral";
        public static final String COMPONENT_BANNER = "banner";
        public static final String COMPONENT_CAROUSEL = "carousel";
        public static final String COMPONENT_SUBSCRIPTION_BANNER = "subscription_banner";
        public static final String COMPONENT_MEMBERSHIP_WIDGET = "membership_widget";
        public static final String COMPONENT_REORDER_WIDGET = "reorder_widget";
        public static final String COMPONENT_TAUS = "taus";
        public static final String COMPONENT_UPLOAD_RX = "upload_rx";
        public static final String COMPONENT_OSW = "osw";
    }
}