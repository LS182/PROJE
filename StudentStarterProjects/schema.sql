-- Run once (e.g. in the NetBeans Services > Databases > Execute Command window)
-- Works in Java DB (Derby), MySQL and H2.
CREATE TABLE PARKING_TICKET (
    PLATE_NUMBER  VARCHAR(10) PRIMARY KEY,
    DRIVER_NAME   VARCHAR(50) NOT NULL,
    HOURS_PARKED  INT         NOT NULL,
    AMOUNT_DUE    DECIMAL(8,2) NOT NULL
);
