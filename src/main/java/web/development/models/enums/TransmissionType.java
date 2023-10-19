package web.development.models.enums;

public enum TransmissionType {
    MANUAL(1),
    AUTOMATIC(2);

    private final int transmissionTypeCode;


    TransmissionType(int transmissionTypeCode) {
        this.transmissionTypeCode = transmissionTypeCode;
    }

    public int getTransmissionTypeCode() {
        return transmissionTypeCode;
    }
}
