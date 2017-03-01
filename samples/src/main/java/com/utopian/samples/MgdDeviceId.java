package com.utopian.samples;

import com.google.common.base.Optional;

/**
 * Created by garamasu on 2015-11-30.
 */
public class MgdDeviceId {
    private Optional<String> oui;
    private Optional<String> productClass;
    private Optional<String> serialNumber;

    public MgdDeviceId(Optional<String> oui, Optional<String> productClass, Optional<String> serialNumber) {
        this.oui = oui;
        this.productClass = productClass;
        this.serialNumber = serialNumber;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MgdDeviceId that = (MgdDeviceId) o;
        return Objects.equal(oui, that.oui) &&
                Objects.equal(productClass, that.productClass) &&
                Objects.equal(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(oui, productClass, serialNumber);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MgdDeviceId deviceId = (MgdDeviceId) o;

        if (oui != null ? !oui.equals(deviceId.oui) : deviceId.oui != null) return false;
        if (productClass != null ? !productClass.equals(deviceId.productClass) : deviceId.productClass != null)
            return false;
        return !(serialNumber != null ? !serialNumber.equals(deviceId.serialNumber) : deviceId.serialNumber != null);

    }

    @Override
    public int hashCode() {
        int result = oui != null ? oui.hashCode() : 0;
        result = 31 * result + (productClass != null ? productClass.hashCode() : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        return result;
    }
}
