package com.RentACar.RentACar.consts;

public class EntityConstants {

    public static final String STATE = "state";
    public static final short DELETED = 0;
    public static final short UNDELETED = 1;
    public static final String WHERE_CLAUSE = STATE + " <> " + DELETED;
    public static final String SQLDELETE_CLAUSE = "set state=0 where id= ? and version=?";

}
