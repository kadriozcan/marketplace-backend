package kadriozcan.marketplaceapp.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public String ADMIN_USERNAME = "admin";

    @UtilityClass
    public class ResponseCodes {
        public final Long SUCCESS = 100000L;
        public final Long UNKNOWN_ERROR = 1L;
        public final Long USER_NOT_FOUND = 200000L;
        public final Long ACCESS_DENIED = 200001L;

        public final Long FAVORITE_IS_ADDED = 100001L;
        public final Long FAVORITE_IS_REMOVED = 100002L;
        public final Long FAVORITE_ALREADY_ADDED = 100003L;

        public static final Long PRODUCT_DELETED = 100004L;
        public static final Long SELLER_IS_ALREADY_IN_BLACKLIST = 100005L;
        public static final Long BLACKLIST_IS_ADDED = 100006L;
        public static final Long SELLER_IS_REMOVED_FROM_BLACKLIST = 100007L;
    }

    @UtilityClass
    public class Roles {
        public String ADMIN = "ROLE_ADMIN";
        public String USER = "ROLE_USER";
    }
}
