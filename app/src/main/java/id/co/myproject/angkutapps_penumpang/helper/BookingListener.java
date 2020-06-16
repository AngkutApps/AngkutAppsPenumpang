package id.co.myproject.angkutapps_penumpang.helper;

import id.co.myproject.angkutapps_penumpang.model.data_object.Driver;

public interface BookingListener {
    void onFinishedBooking(String driverToken, String kodeDriver);
}
